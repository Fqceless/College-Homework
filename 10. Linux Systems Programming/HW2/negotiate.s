	.file	"negotiate.c"
	.text
	.globl	negotiate
	.type	negotiate, @function
negotiate:
	#Expands the stack
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	#Gets buf, increments one position forward, and stores only the fbuf[1] term
	movl	12(%ebp), %eax
	addl	$1, %eax
	movzbl	(%eax), %eax
	#Compares buf[1] to -3 and jumps if not equal
	cmpb	$-3, %al
	jne	.INITALIZE_I
	#Gets buf, increments two positions forward, and stores only the fbuf[2] term
	movl	12(%ebp), %eax
	addl	$2, %eax
	movzbl	(%eax), %eax
	#Compares buf[1] to -3 and jumps if not equal
	cmpb	$31, %al
	jne	.INITALIZE_I
	#Adds tmp1[] list to the stack
	movw	$-1025, -15(%ebp)
	movb	$31, -13(%ebp)
	#Pushes all needed variables to the stack, calls write, then collapses
	subl	$4, %esp
	pushl	$3
	leal	-15(%ebp), %eax
	pushl	%eax
	pushl	8(%ebp)
	call	cWriteFd
	addl	$16, %esp
	#Checks and jumps if write return is greater than zero
	testl	%eax, %eax
	jns	.CONTINUE
	#Exists the program with 1
	subl	$12, %esp
	pushl	$1
	call	cExit
.CONTINUE:
	#Adds tmp2[] list to stack
	movl	$2095871, -24(%ebp)
	movl	$-15204272, -20(%ebp)
	movb	$-16, -16(%ebp)
	#Pushes all needed variables to the stack, calls write, then collapses
	subl	$4, %esp
	pushl	$9
	leal	-24(%ebp), %eax
	pushl	%eax
	pushl	8(%ebp)
	call	cWriteFd
	addl	$16, %esp
	#Checks and jumps if write return is greater than zero
	testl	%eax, %eax
	jns	.END
	#Exists the program with 1
	subl	$12, %esp
	pushl	$1
	call	cExit
.INITALIZE_I:
	#Initializes i and jumps into the loop
	movl	$0, -12(%ebp)
	jmp	.FORLOOP
.IF:
	#Gets buf[i]
	movl	-12(%ebp), %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	#Checks and jumps if buf[i] not equal to -3
	cmpb	$-3, %al
	jne	.ELSEIF
	#Set buf[i] = WONT
	movl	-12(%ebp), %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movb	$-4, (%eax)
	jmp	.INCREMENT_I
.ELSEIF:
	#Gets buf[i] 
	movl	-12(%ebp), %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	#Checks and jumps if buf[i] not equal to -5
	cmpb	$-5, %al
	jne	.INCREMENT_I
	#Set buf[i] = DO
	movl	-12(%ebp), %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movb	$-3, (%eax)
.INCREMENT_I:	
	#Increments i
	addl	$1, -12(%ebp)
.FORLOOP:
	#Checks looping condition
	movl	-12(%ebp), %eax
	cmpl	16(%ebp), %eax
	jl	.IF
	#Exists for loop
	#Pushes all needed variables to the stack, calls write, then collapses
	movl	16(%ebp), %eax
	subl	$4, %esp
	pushl	%eax
	pushl	12(%ebp)
	pushl	8(%ebp)
	call	cWriteFd
	addl	$16, %esp
	#Checks and jumps if write return is greater than zero
	testl	%eax, %eax
	jns	.END
	#Exit with 1
	subl	$12, %esp
	pushl	$1
	call	cExit
.END:
	#Properly exits program
	leave
	ret
	.size	negotiate, .-negotiate
	.ident	"GCC: (Ubuntu 9.4.0-1ubuntu1~20.04.2) 9.4.0"
	.section	.note.GNU-stack,"",@progbits
