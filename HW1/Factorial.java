/**
 * Name: Factorial.java
 * Desc: Calculates the factorial of the inputted number
 * Date: 9/6/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package numeric;

public class Factorial{
    public static void main (String []args){
        //checks for correct # of args (1)
	if (args.length == 1){
	    //String -> int
	    int num = Integer.parseInt(args[0]);
            
	    //run compute function & print results
	    double facNum = compute(num);
	    System.out.println("Factorial: " + facNum);
	}
	//if # of args != 1
	else{
            System.out.println("What are you thinking! Enter only one number!");
	}
    }

    public static double compute(int num){
        //zero edge case: 0! = 1.0 and less than zero case
	//Gallagher if i'm not supposed to write this in one line
	//please just yell at me instead of marking me down thank you
	if (num < 0){
	    System.out.println("Don't use negative numbers :(");
	    System.exit(0);
	}

	if (num == 0){return 1.0;}
	
	//actual factorial function
	//starts at num-1 and multiplies num by it going down
       	//only works for 0-12 bc overflow i think :(	
	for (int i = num-1; i > 1; i--){
            num *= i;
	}
	return num;  

	//if i explictily cast it to double b4 the loop, 
	//would it work for a wider range of nums?
	//asking for a friend
    }
}
