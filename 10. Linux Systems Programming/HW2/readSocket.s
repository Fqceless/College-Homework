	.file	"readSocket.c"
	.text
	.section	.rodata
	.align 4
.LC0:
	.string	"Connection closed by the remote end\n\r"
	.text
	.globl	readSocket
	.type	readSocket, @function
readSocket:
	#Initalize and expand the stack
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	#Add local variable
	movl	$0, -12(%ebp)
	#Expand stack, push parameters, and call read
	#Removed unnecessary move to register
	#movl	16(%ebp), %eax
	subl	$4, %esp
	#pushl	%eax
	pushl   16(%ebp)#Added
	pushl	12(%ebp)
	pushl	8(%ebp)
	call	cReadFd
	addl	$16, %esp
	movl	%eax, 16(%ebp)
	#Checks if returned length it less than zero 
	cmpl	$0, 16(%ebp)
	jns	.ELSEIF
	movl	$2, -12(%ebp)
	jmp	.CONCLUSION
.ELSEIF:
	#Checks if length is equal to zero
	cmpl	$0, 16(%ebp)
	jne	.CONCLUSION
	#Pushes varaibles to stack, writes, and collapses stack
	subl	$4, %esp
	pushl	$37
	pushl	$.LC0
	pushl	$1
	call	cWriteFd
	addl	$16, %esp
	#Updates rv var
	movl	$3, -12(%ebp)
.CONCLUSION:
	#Returns rv and cleans up stack
	movl	-12(%ebp), %eax
	leave
	ret
	.size	readSocket, .-readSocket
	.ident	"GCC: (Ubuntu 9.4.0-1ubuntu1~20.04.2) 9.4.0"
	.section	.note.GNU-stack,"",@progbits
