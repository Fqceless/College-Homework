	.file	"printESP.cpp"
	.section	.rodata
.LC0:
	.string	"%p\n"
	.text
	.globl	main
	.type	main, @function
main:
	leal	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)

	pushl	%ebp
	movl	%esp, %ebp

	pushl	%ecx

	subl	$20, %esp

	subl	$8, %esp
	leal	-24(%ebp), %eax   # was -12(%ebp), mod to ref actual %esp
	pushl	%eax
	pushl	$.LC0
	call	printf
	addl	$16, %esp

	movl	$0, %eax

	movl	-4(%ebp), %ecx
	leave
	leal	-4(%ecx), %esp
	ret
	.size	main, .-main
	.ident	"GCC: (Ubuntu 5.5.0-12ubuntu1~14.04) 5.5.0 20171010"
	.section	.note.GNU-stack,"",@progbits
