;PLS Lab #1!
;Name: Chris LaFave
;Section: 11:00 (section 2 probably?)
;DrRacket R5RS

;Problem #1
;Write a Scheme function that computes the volume of sphere, given its radius.

(define svol
  (lambda (r)
    (* r r r 3.1415 (/ 4 3))))

(svol 5)
;(svol 69)
;(svol 420)

;Problem #2
;Write a Scheme function that takes two number parameters, A and B, and returns A raised to the B power. Do not use any built-in exponent functions.

(define pow
  (lambda (a b)
    (letrec ((p (lambda (a b n)
                 (if (= b 1)
                 a
                 (p (* a n) (- b 1) n)))))
      (p a b a))))

(pow 3 3)
;(pow 2 4)
;(pow 420 69)

;Problem #3
;Write a Scheme function that returns the number of zeros in a given simple list of numbers.

(define count
  (lambda (L)
    (letrec ((counter (lambda (L n)
                      (cond ((null? L) n)
                            ((= (car L) 0) (counter (cdr L) (+ n 1)))
                            (else (counter (cdr L) n)) ))))
             (counter L 0))))

(count '(9 0 4 2 1 0))
;(count '(0 0 0 0 0 0 0 2 2 0 0 0))
;(count '(4 2 0 6 9))

;Problem #4
;Write a Scheme tail-recursive function to compute the length of an arbitrary list.

(define len
  (lambda (L)
    (letrec ((counter (lambda (L n)
                        (if (null? L)
                            n
                            (counter (cdr L) (+ n 1))))))
      (counter L 0))))

(len '(1 (5 (5)) 3))
;(len '( 1 2 3 4 5 6 7 8 9 10))
;(len '((420 (69))))

;Problem #5
;Write a Scheme function that returns the reverse of its simple list parameter. Do not use any built-in reversing functions.

(define rev
  (lambda (L)
    (letrec ((reverse (lambda (L R)
                      (if (null? L)
                          R
                          (reverse (cdr L) (cons (car L) R))))))
      (reverse L '()))))

(rev '(a b c))
;(rev '(1 2 3 4 5 6 7 8 9 10))
;(rev '(420 69))

;Problem #6
;Write a higher-order function twice that takes as a parameter a function of one argument
;and returns a function that represents the application of that function to its argument twice.
(define square
  (lambda (n)
    (* n n)))

(define (twice fun)
  (lambda (x) (fun (fun x))))
    
;(twice square)
((twice square) 2)
;((twice (twice square)) 10)
;((twice square) 69)

;Problem #7
;Write a higher-order function inc_n that takes an integer n as a parameter and returns the nth increment function, which increments by its parameter n.

(define (inc_n i)
  (lambda (n) (+ n i)))

;(inc_n 10)
((inc_n 10) 2)
;((inc_n 3) 2)
;((inc_n 420) 69)

;Problem #8
;Suppose that you want to use a list data structure to implement a Set data type.
;Sets may only contain a single copy of a particular member. (a b b c) is not a set, but (b a c) is.
;Write member and insert operations.

(define mem
  (lambda (x L)
    (cond ((null? L) #f)
          ((= (car L) x) #t)
          (else (mem x (cdr L))) )))

(mem 4 (list 1 2 4))
;(mem 7 '(1 2 3 4 5 6))
;(mem 420 '(4 2 0 6 9))

(define ins
  (lambda (x L)
    (if (mem x L)
        L
        (cons x L))))

(ins 4 (list 1 2))
;(ins 3 '(1 2 3 4 5 6))
;(ins 420 '(69))