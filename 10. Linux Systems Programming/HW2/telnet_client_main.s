# Based on the original NASM code online at /theanine.io/projects/telnet_asm
# by Vanya A. Sergeev - vsergeev at gmail - 01/12/2009
#
# Translated by Keith Shomper to GAS assembly using intel2gas, a utility 
# located at freecode.com/projects/intel2gas, and extensively edited to
# support sending as well as receiving data.  
#
# Edited using insight from a working C-language simple telnet program from
# https://l3net.wordpress.com/2012/12/09/a-simple-telnet-client and info from
# tty_ioctl (4) for handling terminal settings for raw I/O.
#
# This version is accompanied by two separate assembly files:
#  1. term.s:       two functions for setting/resetting the terminal for raw 
#                   character I/O and
#  2. utilities.s:  miscellaneous functions for data manipulation and reading
#                   and writing to file descriptors.
#
# This new version communicates character-by-character, rather than as in
# original with full-length messages.  KAS 10/1/2016
#
# This application can be tested with telehack.com (64.13.139.230) port 23 
# from the list at http://www.telnet.org/htm/places.htm.
#          
# Usage: ./telnet_client <IP address> port
# Note: host name resolution is not implemented. This version only takes IP
# addresses.
#
# You can see what is supposed to happen by running a real telnet client, e.g.,
# telnet 64.13.139.230 23
# 
# Entering the above command gives you a connection to the telehack.com where
# you can interact via sending commands and receiving data.  To terminate the 
# connection, type "quit"
# 
# To Run:
# $ ./telnet_client 64.13.139.230 23
#
#####################################################################

.data
    msgInvalidArguments:
      .asciz "Invalid IP address or port supplied.\n"
    msgIAEnd:
      .equ msgInvalidArgumentsLen, msgIAEnd - msgInvalidArguments

    msgErrorSocket:
      .asciz "Error creating socket.\n"
    msgESocEnd:
      .equ msgErrorSocketLen, msgESocEnd - msgErrorSocket

    msgErrorConnect:
      .asciz "Error connecting to server.\n"
    msgECEnd:
      .equ msgErrorConnectLen, msgECEnd - msgErrorConnect

    msgTryingConnect:
      .asciz "Trying to connect to server.\n"
    msgTCEnd:
      .equ msgTryingConnectLen, msgTCEnd - msgTryingConnect

    msgConnected:
      .asciz "Connected to server.\n"
    msgCEnd:
      .equ msgConnectedLen, msgCEnd - msgConnected

    msgErrorSelect:
      .asciz "Error with select().\n"
    msgESelEnd:
      .equ msgErrorSelectLen, msgESelEnd - msgErrorSelect

    msgConnectionClosed:
      .asciz "Connection closed by the remote end.\n"
    msgCCEnd:
      .equ msgConnectionClosedLen, msgCCEnd - msgConnectionClosed

    msgUsage:
      .asciz "Usage: ./telnet <IP address> [port]\n"
    msgUEnd:
      .equ msgUsageLen, msgUEnd - msgUsage

    # Arguments for socket(): socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    socketArgs:
      .long 2, 1, 6

	 # Timeval structure for select
	 timevalArg:
	   .int  1, 0

	 # Global variable to denote whether the terminal is in raw mode
	 .globl isRawMode
	 isRawMode:
	   .int 0

    # Data bytes for negotiating the terminal window size
      .equ  WINW,   80
      .equ  WINH,   24
      .equ  CWINSZ, 31
      .equ  ESBN,  240
      .equ  SUBN,  250
      .equ  WILL,  251
      .equ  WONT,  252
      .equ  DO,    253
      .equ  DONT,  254
      .equ  CMD,   255

    setWinSizeStr:
      .byte CMD, WILL, CWINSZ
    winSizeStrEnd:
      .equ winSizeStrLen, winSizeStrEnd - setWinSizeStr

    setWinNegStr:
      .byte CMD, SUBN, CWINSZ, 0, WINW, 0, WINH, CMD, ESBN
    winNegStrEnd:
      .equ winNegStrLen, winNegStrEnd - setWinNegStr

#####################################################################

.bss
    # Socket file descriptor returned by socket()
    .lcomm sockfd,   4

    # Storage for the 4 IP octets 
    .lcomm ipOctets, 4

    # Storage for the connection port represented in one 16-bit word
    .lcomm ipPort,   2

    # Arguments for connect(): 
    #   connect(sockfd, serverSockaddr, serversockaddrLen);
    .lcomm connectArgs,        24

    # The read file descriptor struct for select()
    .lcomm fdSetValues,       128
    .equ   fdSetValuesLen,    128

    # sockaddr_in structure that needs to be filled in for the
    # connect() system call.
    #   struct in_addr {
    #       unsigned long s_addr;
    #   };
    #   struct sockaddr_in {
    #       short            sin_family;
    #       unsigned short   sin_port;
    #       struct in_addr   sin_addr;
    #       char             sin_zero[8];
    #   };
    .lcomm serverSockaddr,     16    # i.e., 2+2+4+8  -- see above structs
    .equ   serverSockaddrLen,  16

##########################################################################

.text
  .global _start
  _start: 
    # Pop argc
    popl  %eax

    # Check if we have the correct number of arguments (3), for the 
    # program name, IP address, and port number.
    cmpl  $3, %eax
    je    parse_program_arguments

    # Otherwise, print the usage and quit.
	 pushl $msgUsageLen
	 pushl $msgUsage
	 pushl $1
	 call  cWriteFd
    addl  $12, %esp

    pushl $1
    call  cExit

  parse_program_arguments: 
    # Set the direction flag to increment, so edi/esi are INCREMENTED
    # with their respective load/store instructions.
    cld

    # Pop the program name string from the command line
    popl  %eax

    # Convert the port and IP address strings to numbers
    # pop the IP address from the command line 
	 popl  %eax
	 
    # Convert the IP address string to four byte sized octets.
	 pushl $ipOctets
    pushl %eax
    call  cStrIP_to_Octets
    addl  $8, %esp

    # Check for errors
    cmpl  $0, %eax
    jl    invalid_program_arguments

    # Pop the port number (as an ascii sring) from the command line
    popl  %eax

    # Convert the port string to a 16-bit word.
	 pushl %eax
    call  cStrtoul
    addl  $4, %esp
    movl  %eax, ipPort

    # Check for errors
    cmpl  $0, %eax
    jge network_open_socket

    # Otherwise, print error for invalid arguments and quit.
  invalid_program_arguments: 
    pushl $msgInvalidArgumentsLen
    pushl $msgInvalidArguments
    pushl $1
    call  cWriteFd
    addl  $12, %esp

    pushl $1
    call  cExit

  network_open_socket: 
    # Open a socket and store it in sockfd 
    # Syscall socketcall(1, ...); for socket()
    movl  $102, %eax
    movl  $1, %ebx
    movl  $socketArgs, %ecx
    int   $0x80

    # Copy our socket file descriptor to our variable sockfd
    movl  %eax, sockfd

    # Check if socket() returned a valid socket file descriptor
    cmpl  $0, %eax
    jge   network_connect

    # Otherwise, print error creating socket and quit.
    pushl $msgErrorSocketLen
    pushl $msgErrorSocket
    pushl $1
    call  cWriteFd
    addl  $12, %esp

    pushl $1
    call  cExit

  network_connect: 
    # Print trying to connect message
    pushl $msgTryingConnectLen
    pushl $msgTryingConnect
    pushl $1
    call  cWriteFd
    addl  $12, %esp

    # Setup the argument to connect() and call connect()
    # Fill in the sockaddr_in structure with the
    # network family, port, and IP address information,
    # along with the zeros in the zero field.
    movl $serverSockaddr, %edi

    # Store the network family, AF_INET = 2
    movb  $2, %al
    stosb
    movb  $0, %al
    stosb

    # Store the port, in network byte order (big endian).
    # High byte first
    movw  ipPort, %ax

    # Truncate the lower byte
    shrw  $8, %ax
    stosb

    # Low byte second
    movw  ipPort, %ax
    stosb

    # Store the 4 octets (bytes) of the IP address, reading from the
    # ipOctets 4-byte array and copying to the respective
    # locations in the serverSockaddr structure.
    movl  $ipOctets, %esi
    movsl

    # Zero out the remaining 8 bytes of the structure
    movb  $0, %al
    movl  $8, %ecx
    rep
    stosb

    # Setup the array that will hold the arguments for connect 
    # we are passing through the socketcall() system call.
    movl  $connectArgs, %edi

    # sockfd
    movl  sockfd, %eax
    stosl

    # Pointer to serverSockaddr structure
    movl  $serverSockaddr, %eax
    stosl

    # serverSockaddrlen
    movl  $serverSockaddrLen, %eax
    stosl

    # Syscall socketcall(sockfd, ...); for connect();
    movl  $102, %eax
    movl  sockfd, %ebx
    movl  $connectArgs, %ecx
    int   $0x80

    # Check if connect() returned a success
    cmpl  $0, %eax
    jge   network_setup_file_descriptors

    # Otherwise, print error creating socket and quit.
    pushl $msgErrorConnectLen
    pushl $msgErrorConnect
    pushl $1
    call  cWriteFd
    addl  $12, %esp

    pushl $1
    jmp   network_premature_exit

  network_setup_file_descriptors: 
    # Print connect message
    pushl $msgConnectedLen
    pushl $msgConnected
    pushl $1
    call  cWriteFd
    addl  $12, %esp

    # Set terminal for raw I/O and make note of the setup by setting isRawMode
	 call terminal_set
	 movl $1, (isRawMode)

    # Head of infinite loop in simple_telnet to read and write the socket 
    # i.e., while (1) {
  network_read_write_loop: 

    # Setup file descriptors
	 pushl $fdSetValues
	 pushl sockfd
	 call  setupFds
	 addl  $8, %esp

    # Syscall select(sock+1, &fds, (fd_set*) 0, (fd_set*) 0, &ts);
    movl  $142, %eax
    movl  sockfd, %ebx
    incl  %ebx
    movl  $fdSetValues, %ecx
    movl  $0, %edx
    movl  $0, %esi
    movl  $timevalArg, %edi
    int   $0x80

    # Check return code of select, non-neg values OK
    cmpl  $0, %eax
    jns   check_file_descriptors

    # Otherwise, print error calling select and quit
    pushl $msgErrorSelectLen
    pushl $msgErrorSelect
    pushl $1
    call  cWriteFd
    addl  $12, %esp

	 # Push exit return code
    pushl $1
    jmp   network_premature_exit

  check_file_descriptors: 
    # Check return code.  If zero reset time struct
	 # i.e., implements else if (nready == 0) { ...
	 jnz   fds_have_data

	 movl  $1, (timevalArg)
	 movl  $0, (timevalArg+1)
  	 jmp   check_file_descriptors_done 

  fds_have_data:
    # Handle socket input: (read socket/write stdout)
    check_socket_file_descriptor: 
	 pushl $fdSetValues
	 pushl sockfd
	 call  checkSocket
	 addl  $8, %esp

    # NOTE: use 1 as a normal return (goto loop end)
	 cmpl  $1, %eax
	 je    check_file_descriptors_done 

    # NOTE: use 0 to indicate previous if-not-taken (i.e, check input on stdin)
	 cmpl  $0, %eax
	 je    check_stdin_file_descriptor

	 # return code was not 0 or 1, exit loop
	 jmp   exitLoop

    check_stdin_file_descriptor: 
	 # Handle socket output: (read stdin/write socket)
	 pushl $fdSetValues
	 pushl sockfd
	 call  checkStdin
	 addl  $8, %esp

    # normal return is 0 (goto loop end)
	 cmpl  $0, %eax
	 je    check_file_descriptors_done 

	 # any other return code is cause for exit
  exitLoop:
    # a return code of -1 is "normal" -- other return codes
    # will generate the abnormal "Connection Closed" message
    testl %eax, %eax
    js    toExit

	 pushl $msgConnectionClosedLen
	 pushl $msgConnectionClosed
    pushl $1
	 call  cWriteFd
	 addl  $12, %esp

  toExit:
	 pushl %eax
	 call  cExit

  check_file_descriptors_done: 
    jmp   network_read_write_loop
    # Go to top of "while" loop to check for more data
    # } // end of while(1)

  network_close_socket: 
    pushl $0

  network_premature_exit: 
    # Syscall close(sockfd);
    movl  $6, %eax
    movl  sockfd, %ebx
    int   $0x80

    # the path through premature_exit has pushed a one on the stack to use as
    # the exit return code, but the fall-through path via network_close socket
    # pushes a zero for the return code.  NOTE:  these pushes assume that
    # the syscall for close() does not modify the stack
    call cExit

