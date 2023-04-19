;; Chris, Josh, Sam, Issac



org 100h

mov ah, 2
mov dl, 'C'
int 21h   

mov dl, 'e'
int 21h

mov dl, 'l'
int 21h

mov dl, 's'
int 21h

mov dl, 'i'
int 21h

mov dl, 'u'
int 21h 

mov dl, 's'
int 21h

mov dl, '?'
int 21h

mov dl, ' '
int 21h 
          
          
;start your code here

;grab input, number is in bh & bl as ascii
mov ah, 1
int 21h
mov bh, al
int 21h 
mov bl, al

;convert input to decimal
sub bh, 30h
sub bl, 30h  

;push first and second digits into one number at al
mov al, bh  
mov dl, 10d
mul dl
add al, bl

;1.5*C + 32 = F 
mov dl, 18d
mul dl
mov dl, 10d
div dl
add al, 32d

;print function later grabs from bh
mov bh, al

;end of non-template code

mov ah, 2
       
; print out       
mov dl, 0AH
int 21h    

mov dl, 0DH
int 21h   

mov dl, 'F'
int 21h

mov dl, 'a'
int 21h

mov dl, 'r'
int 21h

mov dl, 'e'
int 21h

mov dl, 'n'
int 21h

mov dl, 'h'
int 21h

mov dl, 'e'
int 21h

mov dl, 'i'
int 21h

mov dl, 't'
int 21h

mov dl, ' '
int 21h

mov dl, '='
int 21h

mov dl, ' '
int 21h

mov ah, 0H
mov al, bh                      
call print_ax
hlt 


   
print_ax proc
cmp ax, 0
jne print_ax_r
    push ax
    mov al, '0'
    mov ah, 0eh
    int 10h
    pop ax
    ret 
print_ax_r:
    pusha
    mov dx, 0
    cmp ax, 0
    je pn_done
    mov bx, 10
    div bx    
    call print_ax_r
    mov ax, dx
    add al, 30h
    mov ah, 0eh
    int 10h    
    jmp pn_done
pn_done:
    popa  
    ret  
endp


print_ax_bin proc  
    pusha
    ; print result value in binary:
    mov cx, 16
    mov bx, ax
    print: mov ah, 2   ; print function.
           mov dl, '0'
           test bx, 1000000000000000b  ; test first bit.
           jz zero
           mov dl, '1'
    zero:  int 21h
           shl bx, 1
    loop print      
    ; print binary suffix:
    mov dl, 'b'
    int 21h  
    popa  
    ret
endp      

        
        
print_nl proc 
    push ax  
    push dx  
    mov ah, 2
    mov dl, 0Dh
    int 21h  
    mov dl, 0Ah
    int 21h   
    pop dx 
    pop ax      
    ret
endp
