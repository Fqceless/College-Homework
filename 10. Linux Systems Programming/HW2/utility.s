#####################################################################
#
# Utility functions using the C language calling conventions
#
#####################################################################

#
# cExit
#   Exits program with the exit() syscall.
#       arguments: exit code
#       returns: nothing
#
  .globl cExit
  .type  cExit, @function
  cExit: 
    pushl %ebp
    movl  %esp,%ebp

	 # if the terminal is not in raw mode, then just exit
	 cmpl  $1, (isRawMode)
	 jne   justExit

    # otherwise, reset terminal to cooked I/O before exit
	 call  terminal_reset

    # Syscall exit(parm);
  justExit:
    movl  $1, %eax
    movl  8(%ebp), %ebx
    int   $0x80
    ret

#
# cReadFd
#   Reads len bytes from file descriptor fd into buf
#       arguments: fd, buf, len
#       returns: number of bytes read or -1 on error in eax
#
  .globl cReadFd
  .type  cReadFd, @function
  cReadFd: 
    pushl %ebp
    movl  %esp,%ebp

    # Syscall read(fd, buf, len);
    movl  $3, %eax
    movl  8(%ebp), %ebx
    movl  12(%ebp), %ecx
    movl  16(%ebp), %edx
    int   $0x80

    movl  %ebp,%esp
    popl  %ebp
    ret

#
# cWriteFd:
#   Writes len bytes of buf to file descriptor fd
#       arguments: fd, buf, len
#       ret: number of bytes written or -1 on error, in eax
#
  .globl cWriteFd
  .type  cWriteFd, @function
  cWriteFd: 
    pushl %ebp
    movl  %esp,%ebp

    # Syscall write(fd, buf, len);
    movl  $4, %eax
    movl  8(%ebp), %ebx
    movl  12(%ebp), %ecx
    movl  16(%ebp), %edx
    int   $0x80

    movl  %ebp,%esp
    popl  %ebp
    ret

#
# cStrIP_to_Octets
#   Parses an ASCII IP address string, e.g. "127.0.0.1", and stores the
#   numerical representation of the 4 octets in the ipOctets variable.
#       arguments: pointer to the IP address string
#       returns: 0 on success, -1 on failure
#
  .globl cStrIP_to_Octets
  .type  cStrIP_to_Octets, @function
  cStrIP_to_Octets: 
    pushl %ebp
    movl  %esp, %ebp

    # Allocate space for a temporary 3 digit substring variable of the IP
    # address, used to parse the IP address.
    subl  $4, %esp

    # Point esi to the beginning of the string
    movl   8(%ebp), %esi

    # Reset our counter, we'll use this to iterate through the
    # 3 digits of each octet.
    movl  $0, %ecx

    # Reset our octet counter, this is to keep track of the 4
    # octets we need to fill.
    movl  $0, %edx

    # Point edi to the beginning of the temporary
    # IP octet substring
    movl  %ebp, %edi
    subl  $4, %edi

    string_ip_parse_loop: 
        # Read the next character from the IP string
        lodsb

        # Increment our counter
        incl  %ecx

        # If we encounter a dot, process this octet
        cmpb  $'.', %al
        je    octet_complete

        # If we encounter a null character, process this
        # octet.
        cmpb  $0, %al
        je    null_byte_encountered

        # If we're already on our third digit,
        # process this octet.
        cmpl  $4, %ecx
        jge   octet_complete

        # Otherwise, copy the character to our
        # temporary octet string.
        stosb

        jmp   string_ip_parse_loop

      null_byte_encountered: 
        # Check to see if we are on the last octet yet
        # (current octet would be equal to 3)
        cmpl  $3, %edx

        # If so, everything is working normally
        je    octet_complete

        # Otherwise, this is a malformed IP address,
        # and we will return -1 for failure
        movl  $-1, %eax
        jmp   malformed_ip_address_exit

      octet_complete: 
        # Null terminate our temporary octet variable.
        movb  $0, %al
        stosb

        # Save our position in the IP address string
        pushl %esi

        # Save our octet counter
        pushl %edx

        # Send off our temporary octet string to our cStrtoul
        # function to turn it into a number.
        movl  %ebp, %eax
        subl  $4, %eax
        pushl %eax
        call  cStrtoul
        addl  $4, %esp

        # Check if we had any errors converting the string,
        # if so, go straight to exit (eax will hold error through)
        cmpl  $0, %eax
        jl    malformed_ip_address_exit

        # Restore our octet counter
        popl  %edx

        # Copy the octet data to the current IP octet
        # in our IP octet array.    
        #movl  $ipOctets, %edi
		  movl  12(%ebp), %edi
        addl  %edx, %edi

        # cStrtoul saved the number in eax, so we should
        # be fine writing al to [edi].
        stosb

        # Increment our octet counter.
        incl  %edx

        # Restore our position in the IP address string
        popl  %esi
        # Reset the position on the temporary octet string
        movl  %ebp, %edi
        subl  $4, %edi

        # Continue to processing the next octet
        movl  $0, %ecx

        cmpl  $4, %edx
        jl    string_ip_parse_loop

    # Return 0 for success
    movl  $0, %eax

  malformed_ip_address_exit: 
    movl  %ebp, %esp
    popl  %ebp
    ret

#
# cStrtoul
#   Converts a number represented in an ASCII string to an unsigned 32-bit
#   integer.
#       arguments: pointer to the string
#       returns: 32-bit integer stored in eax
#
  .globl cStrtoul
  .type  cStrtoul, @function
  cStrtoul: 
    pushl %ebp
    movl  %esp, %ebp

    # Allocate space for the multiply operand
    subl  $4, %esp

    # Point esi to the beginning of the string
    movl   8(%ebp), %esi

    # Make a copy of the string address in edi
    movl  %esi, %edi

    string_length_loop: 
        # Load the next byte from the string
        lodsb

        # Compare the byte to the null byte
        cmpb  $0, %al

        # Continue to loop until the null byte is reached
        jne   string_length_loop

    # Copy the address of the null byte + 1 and subtract the
    # address of the string to have the string length in ebx 
    movl  %esi, %ebx
    subl  %edi, %ebx

    # Decrement by one to account for the null byte
    decl  %ebx

    # Ensure that the string length > 0
    cmpl  $0, %ebx
    jle   premature_exit

    # Use eax to hold the current character
    movl  $0, %eax

    # Use ecx to hold the digit position in terms of powers of ten
    movl  $0, %ecx

    # Use edx to hold the final result
    movl  $0, %edx

    # Set esi back to the beginning of the string so we can traverse it
    movl  %edi, %esi

    digits_count_loop: 
        # Read the next digit into al
        lodsb

        # Decrement our string length counter
        decl  %ebx

        # Start out at 10^0 = 1
        movl  $1, %ecx
        movl  $0, %edi

        # Check if we need to multiply by any more powers of 10 
        cmpl  %edi, %ebx

        # If not, then ecx = 10^0 = 1, so we can skip the exponent
        # multiplication loop.
        je    exponent_loop_skip

        # Otherwise, multiply ecx by 10 for however many powers
        # the current digit requires
        exponent_loop: 
            imull $10, %ecx
            incl  %edi
            cmpl  %edi, %ebx
            jg    exponent_loop

        exponent_loop_skip: 
            # Check if the character is 0 or greater
            cmpb  $48, %al
            jge   lower_bound_met

            # Otherwise, set the result to 0 and exit
            movl  $-1, %eax
            jmp   premature_exit

        lower_bound_met: 
            # Check if the character is 9 or less
            cmpb  $57, %al
            jle   upper_bound_met

            # Otherwise, set the result to 0 and exit
            movl  $-1, %eax
            jmp   premature_exit

        upper_bound_met:    

        # Subtract 48, the ASCII code for '0', from the character,
        # leaving just the digit in al
        subb  $48, %al

        # Multiply the powers of ten with the digit
        movl  %eax, -4(%ebp)
        imull -4(%ebp), %ecx

        # Add this digit value to the final result
        addl  %ecx, %edx

        # Continue looping until we have gone through all the digits
        cmpl  $0, %ebx
        jne   digits_count_loop

    # Move the result to eax
    movl  %edx, %eax

  premature_exit: 
    movl  %ebp, %esp
    popl  %ebp
    ret

