.data
	# tin is the inital termios structure of terminal atributes
	# terminal_set gets these attributes into tim, so they can
	# afterwards be used to restore the terminal on exit
	.local  tin
	.comm	  tin, 60, 32

	# tlocal is assigned a copy of tin, then modified for raw I/O
	.local  tlocal
	.comm	  tlocal, 60, 32

	.equ STDINFD,    0

	# constants defined in sys/ioctl.h for terminal control, see
	# tty_ioctl(4) for details
	.equ TCGETS,     0x5401
	.equ TCSETS,     0x5402

	# below are the constants defined in termios.h which are necessary
	# for setting raw I/O.  See the web link below for details
	#  refspecs.linuxbase.org/LSB_3.0.0/LSB-PDA/LSB-PDA/baselib-cfmakeraw-3.html
	.equ    IGNBRK,  0x0001
	.equ    BRKINT,  0x0002
	.equ    PARMRK,  0x0008
	.equ    ISTRIP,  0x0020
	.equ    INLCR,   0x0040
	.equ    IGNCR,   0x0080
	.equ    ICRNL,   0x0100
	.equ    IXON,    0x0400
	.equ    OPOST,   0x0001
	.equ    ECHO,    0x0008
	.equ    ECHONL,  0x0040
	.equ    ICANON,  0x0002
	.equ    ISIG,    0x0001
	.equ    IEXTEN,  0x8000
	.equ    CSIZE,   0x0030
	.equ    PARENB,  0x0100
	.equ    CS8,     0x0030
	.equ    IFLAG,  ~(IGNBRK+BRKINT+PARMRK+ISTRIP+INLCR+IGNCR+ICRNL+IXON)
	.equ    OFLAG,  ~(OPOST)
	.equ	  LFLAG,  ~(ECHO+ECHONL+ICANON+ISIG+IEXTEN)
	.equ    CFLAG1, ~(CSIZE+PARENB)
	.equ    CFLAG2,  (CS8)
.text
	.globl  terminal_set
	.type	  terminal_set, @function
 terminal_set:
	# prologue
	pushl	%ebp
	movl	%esp, %ebp

	# call ioctl(STDIN_FILENO, TCGETS,  &tin)
	movl  $54, %eax
	movl	$STDINFD, %ebx
	movl	$TCGETS, %ecx
	movl	$tin, %edx
	int	$0x80

	#pushl	$tin
	#pushl	$TCGETS
	#pushl	$STDINFD
	#call	ioctl
	#addl	$12, %esp

	# copy tin to tlocal one word at a time
	movl	tin, %eax
	movl	%eax, tlocal
	movl	tin+4, %eax
	movl	%eax, tlocal+4
	movl	tin+8, %eax
	movl	%eax, tlocal+8
	movl	tin+12, %eax
	movl	%eax, tlocal+12
	movl	tin+16, %eax
	movl	%eax, tlocal+16
	movl	tin+20, %eax
	movl	%eax, tlocal+20
	movl	tin+24, %eax
	movl	%eax, tlocal+24
	movl	tin+28, %eax
	movl	%eax, tlocal+28
	movl	tin+32, %eax
	movl	%eax, tlocal+32
	movl	tin+36, %eax
	movl	%eax, tlocal+36
	movl	tin+40, %eax
	movl	%eax, tlocal+40
	movl	tin+44, %eax
	movl	%eax, tlocal+44
	movl	tin+48, %eax
	movl	%eax, tlocal+48
	movl	tin+52, %eax
	movl	%eax, tlocal+52
	movl	tin+56, %eax
	movl	%eax, tlocal+56

	# set the input flag field (i.e., tlocal.c_iflag)
	# the process, which is similar for each flag, is:
	# 1. move address of tlocal.c_iflag to ebx (only once, offset the rest)
	# 2. move data at that address to eax
	# 3. AND with the appropriate flag
	# 4. store result of the ANDind in the tlocal field
	movl	$tlocal, %ebx
	movl	(%ebx), %eax
	andl	$IFLAG, %eax
	movl	%eax, (%ebx)

	#set the output flag field (i.e., tlocal.c_oflag)
	movl	4(%ebx), %eax
	andl	$OFLAG, %eax
	movl	%eax, 4(%ebx)

	# set the control flag field (i.e., tlocal.c_cflag)
	movl	8(%ebx), %eax
	andl	$CFLAG1, %eax
	orl	$CFLAG2, %eax
	movl	%eax, 8(%ebx)

	# set the local flag field (i.e., tlocal.c_lflag)
	movl	12(%ebx), %eax
	movl  $LFLAG, %eax
	movl	%eax, 12(%ebx)

	# call ioctl(STDIN_FILENO, TCSETS,  &tlocal)
	movl  $54, %eax
	movl	$STDINFD, %ebx
	movl	$TCSETS, %ecx
	movl	$tlocal, %edx
	int	$0x80

	#pushl	$tlocal
	#pushl	$TCSETS
	#pushl	$STDINFD
	#call	ioctl
	#addl	$12, %esp

	leave
	ret

	.globl  terminal_reset
	.type	  terminal_reset, @function
 terminal_reset:
	pushl	%ebp
	movl	%esp, %ebp

	# call ioctl(STDIN_FILENO, TCSETS,  &tlocal)
	movl  $54, %eax
	movl	$STDINFD, %ebx
	movl	$TCSETS, %ecx
	movl	$tin, %edx
	int	$0x80

	#pushl	$tin
	#pushl	$TCSETS
	#pushl	$STDINFD
	#call	ioctl
	#addl	$12, %esp

	leave
	ret
