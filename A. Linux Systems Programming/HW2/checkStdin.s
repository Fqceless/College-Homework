	.file	"checkStdin.c"
	.text
	.globl	checkStdin
	.type	checkStdin, @function
checkStdin:
	#Function Prologue:
	pushl	%ebp       #Push old base pointer to the stack
	movl	%esp, %ebp #Set stack pointer to base poitner
	
	#Setting Local Variables
	subl	$24, %esp  #Expanding the stack by 24 bytes for local variables
	movb	$13, -11(%ebp) #Setting c (-11(%ebp)) to 13 ('\r')

	#First If Statement (line 9) (FD_ISSET(0, fds) != 0):
	movl	12(%ebp), %eax #Moving fds into %eax
	movl	(%eax), %eax #Moves what %eax was pointing to into %eax
	andl	$1, %eax   #And's %eax with 1, zeroing all the bits except the last one
	testl	%eax, %eax #Testing if %eax is 0
	je	return_zero #Jump to L2 if %eax == 0

	#First If Succeeds:
	#Setting up to call read
	subl	$4, %esp   #Expands the stack by 4 bytes (stack alignment)
	pushl	$1	   #Pushes 1 onto the stack
	leal	-10(%ebp), %eax #Puts buf into %eax
	pushl	%eax       #Pushes buf onto the stack
	pushl	$0         #Pushes 0 onto the stack
	call	cReadFd	   #Calls read
	addl	$16, %esp  #Cleaning up the 16 bytes allocated for read
	
	#Second If-statement (line 17) (write(sock, buf, 1) < 0):
	#Setting up to call write
	subl	$4, %esp   #Expands the stack by 4 bytes (stack alignment)
	pushl	$1         #Pushes 1 onto the stack
	leal	-10(%ebp), %eax #Puts buf into %eax
	pushl	%eax       #Pushes buf onto the stack
	pushl	8(%ebp)    #Pushes sock onto the stack
	call	cWriteFd   #Calls write
	addl	$16, %esp  #Cleans up the 16 bytes allocated for write
	testl	%eax, %eax #Testing if %eax is 0, and also if it sets the sign flag
	jns	if_two_failed #Jump to L3 if the sign flag was not set (%eax was positive)
	movl	$1, %eax   #Set %eax to 1 for return
	jmp	exit_sequence #Jump to L5
if_two_failed:
	#Second If Failed, Third If-statement (line 22) (buf[0] == '\n'):
	movzbl	-10(%ebp), %eax #Converting buf[0] into a long and storing it in %eax
	cmpb	$10, %al   #Comparing buf[0] to 10 ('\n')
	jne	return_zero #Jump to L2 if buf[0] != '/n'
	subl	$4, %esp   #Expand the stack 4 bytes (stack alignment)
	pushl	$1         #Push 1 onto the stack
	leal	-11(%ebp), %eax #Puts c into %eax
	pushl	%eax       #Push c onto the stack
	pushl	$1         #Push 1 onto the stack
	call	cWriteFd   #Calls write
	addl	$16, %esp  #Clean up the 16 bytes allocated for write
return_zero:
	movl	$0, %eax   #Set %eax to 0 to get ready for return
exit_sequence:
	#Exit sequence!
	leave              #Reset stack pointer to base pointer
	ret                #Return from the funtion
	.size	checkStdin, .-checkStdin
	.ident	"GCC: (Ubuntu 9.4.0-1ubuntu1~20.04.2) 9.4.0"
	.section	.note.GNU-stack,"",@progbits
