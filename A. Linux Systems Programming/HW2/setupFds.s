	.file	"setupFds.c"
	.text
	.globl	setupFds
	.type	setupFds, @function
setupFds:
   # comments for this function as it was
   # assembled appear below; however, in
   # its context in simple_telnet, this
   # function can be much simpler, since
   # that program only ever uses file
   # descriptors 0 (stdin) and 3 (sock).

   # set the fd bits for 0 and 3 in fds
   # which is at parameter 2 (i.e., two
   # words above the return address
	movl	8(%esp), %eax
	movl	$9, (%eax)
   ret
 
   # if we wished, we could delete the
   # remaining code of this function, i.e.,
   # up to line 137, since it is never reached

   # function prolog
	pushl	%ebp
	movl	%esp, %ebp

   # save registers edi, esi, and ebx
	pushl	%edi
	pushl	%esi
	pushl	%ebx

   # expand stack local variables __d0 and __d1
	subl	$16, %esp

   # setup registers for the inline assembly
   # the original code moved the data as:
   #   fds -> eax -> edx -> edi: destination
   #   $0  -> ebx -> eax:        source
   #   $32 -> edx -> ecx:        rep number 
   #   when cld preceeds the rep instr incr edi
   # 
   # modified the asm to move the data directly
	movl	12(%ebp), %edi
	movl	$0, %eax
	movl	$32, %ecx
   #movl	%edx, %ecx
   #movl	%eax, %edx
   #movl	%edx, %edi
   #movl	%ebx, %eax
#APP
# 5 "setupFds.c" 1
	cld; rep; stosl
# 0 "" 2
#NO_APP

   # cleanup from inline assembly
   # the original code moved the data as:
   #   edi --> edx --> __d1
   #   ecx --> __d0
   # modified the asm to move the data directly
	movl	%edi, -20(%ebp)
   movl	%ecx, -16(%ebp)
   #movl	%edx, -20(%ebp)

   # if statement begins here
   # compare parameter sock to 0 and jump past
   # the if statement body when they are equal
	cmpl	$0, 8(%ebp)
	je	past_the_if_body

   # start of FD_SET(sock)
   # find which word in __fds_bits contains sock
   # divides sock by 32 with sarl to find index 
   # and stores this index in ebx
	movl	8(%ebp), %eax
   # these instructions were unnecessary
   # leal	31(%eax), %edx
   # testl	%eax, %eax
   # cmovs	%edx, %eax
	sarl	$5, %eax
	movl	%eax, %ebx

   # save the word at __fds_bits[ebx] in esi
	movl	12(%ebp), %eax
	movl	(%eax,%ebx,4), %esi

   # compute sock mod 32, store result in eax
	movl	8(%ebp), %eax
	cltd
	shrl	$27, %edx
	addl	%edx, %eax
	andl	$31, %eax
	subl	%edx, %eax

   # use sall calculate bit mask from shift by
   # sock mod 32
	movl	$1, %edx
	movl	%eax, %ecx
	sall	%cl, %edx
	movl	%edx, %eax

   # OR mask with __fds_bits[ebx], store 
   # result in edx
	orl	%eax, %esi
	movl	%esi, %edx

   # store OR'd result in edx to __fds_bits[ebx]
	movl	12(%ebp), %eax
	movl	%edx, (%eax,%ebx,4)

past_the_if_body:
   # start of FD_SET(0)
   # store value at __fds_bits[0] in eax
	movl	12(%ebp), %eax
	movl	(%eax), %eax

   # no need to shift the bit, just OR with eax
	orl	$1, %eax

   # store the OR'd result in __fds_bits[0]
	movl	%eax, %edx
	movl	12(%ebp), %eax
	movl	%edx, (%eax)
	nop

   # shrink the stack to remove the local vars
	addl	$16, %esp

   # restore the saved registers
	popl	%ebx
	popl	%esi
	popl	%edi

   # restore the old ebp and return to the caller
	popl	%ebp
	ret
	.size	setupFds, .-setupFds
	.ident	"GCC: (Ubuntu 9.4.0-1ubuntu1~20.04.1) 9.4.0"
	.section	.note.GNU-stack,"",@progbits

