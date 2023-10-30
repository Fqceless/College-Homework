	.file	"checkSocket.c"
	.text
	.globl	checkSocket
	.type	checkSocket, @function
checkSocket:
	#Function Prolouge:
	pushl	%ebp        #Save the old base pointer
	movl	%esp, %ebp  #Set stack pointer to base pointer
	
	#Setting Local Variables:
	pushl	%ebx	    #Save %ebx register
	subl	$36, %esp   #Expand the stack by 36 bytes for local variables
	movl	$0, -12(%ebp) #Set the variable at -12 (rc) to 0
	
	#Checking the First If-statement (line 14):
	#Checking the First Condition (sock != 0):
	cmpl	$0, 8(%ebp) #Check if the first parameter (sock) is equal to 0
	je	exit_sequence #Jump to exit sequence if sock == 0

	#Checking the Second Condition (FD_ISSET(sock, fds) != 0):
	#Find where sock is in fds, finds the index (sarl divide by 32), and stores it in %edx:
	movl	8(%ebp), %eax #Put the first parameter (sock) into register %eax
	leal	31(%eax), %edx #Adding 31 to %eax's value (sock) and storing it in %edx
	testl	%eax, %eax  #Checking if %eax is 0, setting flags if needed
	cmovs	%edx, %eax  #Moves %edx into %eax if the last line sets the sign flag
	sarl	$5, %eax    #Bit-shifting %eax (sock) by 5, effectively dividing by 32
	movl	%eax, %edx  #Moves %eax (sock/32) into %edx
	#Saves the word at fds[%edx] to %ebx:
	movl	12(%ebp), %eax #Moves the second parameter (fds) into %eax
	movl	(%eax,%edx,4), %ebx #Gets the %edx'th element in %eax (fds) and stores it in %ebx
	#Computes sock % 32, stores in %eax:
	movl	8(%ebp), %eax #Puts sock back into %eax
	cltd                #Turns the 32 bit signed integer in %eax into a 64 and stores in %edx
	shrl	$27, %edx   #Bit-shifts %edx by 27, dividing it by 2^27 to get the last 5 bits
	addl	%edx, %eax  #Adds what's in %eax and %edx together and stores them in %eax
	andl	$31, %eax   #And's 31 and %eax together, zeroing all bits except the last 5
	subl	%edx, %eax  #Subtracts what's in %eax by %edx and stores that in %eax
	#Calculate something to do with that sock % 32:
	movl	$1, %edx    #Moves 1 into %edx
	movl	%eax, %ecx  #Moves %eax into %ecx
	sall	%cl, %edx   #Shift %edx left by %cl bits
	movl	%edx, %eax  #Moves %edx into %eax
	#Checks if %eax and the word at %ebx have the right things set
	andl	%ebx, %eax  #And's %ebx and %eax
	testl	%eax, %eax  #Checks if %eax is 0
	je	exit_sequence #Jump to exit sequence if what's in %eax is 0
	
	#Checking Second If-statement (line 18) (readSocket(sock, buf, 1) != 0):
	subl	$4, %esp    #Expand the stack by 4 bytes (stack alignment)
	#Setting up readSocket and its parameters (sock, buf, 1)
	pushl	$1	    #Pushes 1 onto the top of the stack
	leal	-33(%ebp), %eax #Stores in %eax the address of 33 above the base pointer (buf)
	pushl	%eax	    #Pushes %eax (buf) onto the stack
	pushl	8(%ebp)	    #Pushes sock onto the stack
	call	readSocket  #Calls readSocket
	addl	$16, %esp   #Cleans up the 16 bytes added to run readSocket
	#The code that handles the if
	movl	%eax, -12(%ebp) #Sets rc to the return value of reasSocket
	cmpl	$0, -12(%ebp) #Compares rc and 0
	je	if_two_success #Jump to if_two_success if rc == 0
	#If rc != 0 (falls into the if-statement)
	#movl	-12(%ebp), %eax	#Moves rc to %eax to be returned
	jmp	exit_sequence #Jump to exit sequence
if_two_success:
	#Checking Third If-statement (line 21) (if buf[0] == CMD):
	movzbl	-33(%ebp), %eax #Turns buf[0] into a long and stores it in %eax
	cmpb	$-1, %al    #Compares -1 (CMD) to buf[0]
	jne	if_three_else #If buf[0] != CMD, jump to if_three_else
	#Checking Fourth If-statement (line 23) (readSocket(sock, buf+1, 2) != 0):
	#Setting up for readSocket
	leal	-33(%ebp), %eax #Storing buf in %eax
	addl	$1, %eax    #Adding 1 to %eax (buf)
	subl	$4, %esp    #Expand the stack by 4 bytes (stack alignment)
	pushl	$2	    #Pushes 2 onto the stack
	pushl	%eax	    #Pushes %eax (buf+1) onto the stack
	pushl	8(%ebp)     #Pushes sock onto the stack
	call	readSocket  #Calls readSocket
	addl	$16, %esp   #Cleans up 16 bytes from readSocket
	#If handling
	movl	%eax, -12(%ebp) #Sets rc to the return of readSocket
	cmpl	$0, -12(%ebp) #Comparing rc to 0
	je	if_four_success	#Jump to if_four_success if rc == 0
	#If rc != 0 (falls into if-statement)
	#movl	-12(%ebp), %eax #Moves rc to %eax to be returned
	jmp	exit_sequence #Jump to exit sequence
if_four_success:
	#Fourth If was Successful:
	#Call negotiate
	subl	$4, %esp    #Expand the stack by 4 bytes (stack alignment)
	pushl	$3          #Push 3 onto the stack
	leal	-33(%ebp), %eax #Puts buf into %eax
	pushl	%eax        #Push buf onto the stack
	pushl	8(%ebp)     #Push sock onto the stack
	call	negotiate   #Calls negotiate
	addl	$16, %esp   #Cleans up the 16 bytes added for negotiate
	jmp	set_rc	    #Jump to set_rc
if_three_else:
	#Third If Failed, buf != CMD Else-statement (line 28)
	subl	$4, %esp    #Expand the stack by 4 bytes (stack alignment)
	pushl	$1	    #Push 1 onto the stack
	leal	-33(%ebp), %eax #Put buf into %eax
	pushl	%eax        #Push buf onto the stack
	pushl	$2          #Push 2 onto the stack
	call	cWriteFd    #Calls write
	addl	$16, %esp   #Cleans up the 16 bytes added for write
set_rc:
	#Set rc to 1 to Signify First If Was Successful
	movl	$1, -12(%ebp) #Moves 1 into rc
#.L2:
	#First If Failed
	#movl	-12(%ebp), %eax #Move rc into %eax to get returned
exit_sequence:
	#Exit Sequence!
	movl    -12(%ebp), %eax #Move rc into %eax to be returned (we always return rc)
	movl	-4(%ebp), %ebx #Moves whatever is in -4(%ebp) to %ebx (we never used that?)
	leave               #Reset the stack pointer to the base pointer
	ret                 #Return from the function
	.size	checkSocket, .-checkSocket
	.ident	"GCC: (Ubuntu 9.4.0-1ubuntu1~20.04.2) 9.4.0"
	.section	.note.GNU-stack,"",@progbits
