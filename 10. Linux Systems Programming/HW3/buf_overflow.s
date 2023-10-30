	.file	"buf_overflow.c"
	.section	.rodata
.LC0:
	.string	"Usage: %s string\n"
	.align 4
.LC1:
	.string	"[BEFORE COPY] buf is at %p and contains '%s'\n"
	.align 4
.LC2:
	.string	"[AFTER  COPY] buf is at %p and contains '%s'\n"
	.text
	.globl	main
	.type	main, @function
main:
   # stack align, save argc addr in ecx
	leal	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)

   # function prologue
	pushl	%ebp
	movl	%esp, %ebp

   # save registers
	pushl	%edi
	pushl	%ebx
	pushl	%ecx

   # expand stack for local vars
	subl	$92, %esp

   # test argc, jump to main body if >= 2
	movl	%ecx, %ebx
	cmpl	$1, (%ebx)
	jg	.L2

   # print usage message and exit
	movl	4(%ebx), %eax
	movl	(%eax), %edx
	movl	stderr, %eax
	subl	$4, %esp
	pushl	%edx
	pushl	$.LC0
	pushl	%eax
	call	fprintf
	addl	$16, %esp
	movl	$1, %eax
	jmp	.L6

   # main body of code
.L2:

   # initialize buf
	movl	$0, -104(%ebp)
	leal	-100(%ebp), %edx
	movl	$0, %eax
	movl	$19, %ecx
	movl	%edx, %edi
	rep stosl

   # call first printf, if argc == 2
	cmpl	$2, (%ebx)
	jne	.L4
	subl	$4, %esp
	leal	-104(%ebp), %eax
	pushl	%eax
	leal	-104(%ebp), %eax
	pushl	%eax
	pushl	$.LC1
	call	printf
	addl	$16, %esp
.L4:

   # call strcpy
	movl	4(%ebx), %eax
	addl	$4, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	-104(%ebp), %eax
	pushl	%eax
	call	strcpy
	addl	$16, %esp

   # call second printf, if argc == 2
	cmpl	$2, (%ebx)
	jne	.L5
	subl	$4, %esp
	leal	-104(%ebp), %eax
	pushl	%eax
	leal	-104(%ebp), %eax
	pushl	%eax
	pushl	$.LC2
	call	printf
	addl	$16, %esp
.L5:

   # set return code
	movl	$0, %eax
.L6:

   # reset stack top to saved registers
   # leal	-12(%ebp), %esp

   # load saved values into registers
   # popl	%ecx
   # popl	%ebx
   # popl	%edi

   # function epilogue
   movl  %ebp, %esp
   # restore old base pointer to ebp
	popl	%ebp

   # reset top of stack using addr argc
   # leal	-4(%ecx), %esp

   # return to calling function
	ret
	.size	main, .-main
	.ident	"GCC: (Ubuntu 5.5.0-12ubuntu1~14.04) 5.5.0 20171010"
	.section	.note.GNU-stack,"",@progbits
