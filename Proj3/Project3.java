/**
 * Name: Project3.java
 * Desc: Contains a method to calculate through RPN strings
 * Date: 10/6/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package project3;

import java.util.*;

public class Project3 {

    public static void main(String[] args) throws InvalidRPNString {
            //System.out.println(RPNcalc("yeet 3 0 -"));
    }
    
    //main function
    //takes a string and give back the RPN calculation.
    public static double RPNcalc(String str) throws InvalidRPNString{
        //create scanner and (soon to be) scanner input variable.
        Scanner in = new Scanner(str);
        String nextInput;
        
        //create the stack that stores everything
        Stack RPNstack = new Stack();
        
        
        /* main function loop
        3 states are isDouble, isOp, and else.
        isDouble: Just pushes onto the stack
        isOp: Does the op with doMath and pushes the result back to the stack
        else: throws an error
        exit condition: scanned to the end of str */
        while (in.hasNext()){
            //update scanned variable every loop
            nextInput = in.next();
            
            /*isDouble state
            Note: doesn't handle NumberFormatException directly
            because it's handled in isDouble */
            if (isDouble(nextInput)){
                RPNstack.push(Double.parseDouble(nextInput));
            }
            
            /*isOp state
            checks for +,-,*,/ and does the correct operation with doMath
            throws an exception if it cannot pull two numbers from the stack */
            else if (isOp(nextInput)){
                try{
                    RPNstack.push(doMath((double)RPNstack.pop(),
                                  (double)RPNstack.pop(), nextInput ) );
                }
                catch(EmptyStackException ex){
                    throw new InvalidRPNString("Too few numbers");
                }
            }
            
            //else state, just throws an exception
            else{
                throw new InvalidRPNString("An input is not a "
                                         + "number or operation");
            }
        }
        
        /*pops the last number out of the stack, which should be the answer.
        if stack is empty (which should only happen if an empty sting
        is input) throw exception. */
        double ans;
        if (!RPNstack.empty()){
            ans = (double)RPNstack.pop();
        }
        else{
            throw new InvalidRPNString("Don't enter empty strings");
        }
        //throws and exception if there is still extra stuff in the stack
        if (!RPNstack.empty()){
            throw new InvalidRPNString("Too few operators");
        }
        
        return ans;
    }
    
    //returns true if str is a double
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    //returns true if str is any of +, -, *, /
    private static boolean isOp(String str){
            return (str.equals("+") || str.equals("-") ||
                    str.equals("*") || str.equals("/"));
    }
    
    /*returns num2 (op) num1
    throws exception if trying to divide by zero
    if it for some reason gets to default it returns a big negative number */
    private static double doMath(double num1, double num2, String op) 
            throws InvalidRPNString{
        switch (op) {
            case "+" -> {
                return num2 + num1;
            }
            case "-" -> {
                return num2 - num1;
            }
            case "*" -> {
                return num2 * num1;
            }
            case "/" -> {
                if (num1 != 0){ return num2 / num1; }
                else { throw new InvalidRPNString("Cannot divide by 0"); }
            }
            default -> {
                return -1000000.0;
            }
        }
    }
}


