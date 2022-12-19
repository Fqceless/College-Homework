/**
 * Name: Factorial.java
 * Desc: Calculates the factorial of the inputted number
 * Date: 9/6/2022
 * @author Christopher LaFave
 * @version 1.1
 * Sept 22 2022:
 *    Took out main() to fit in with project 2
 *    Moved the less than 0 case into the compute block
 *    Changed the num to double for the for-loop to increase domain
 *    Implemented NegativeNumberException
 */
package numeric;

public class Factorial{

    public static double compute(int num) throws NegativeNumberException{
        //less than zero case, exception handling
        if (num < 0){
            throw new NegativeNumberException("Tried to take the Factorial of a negative number");
        }
                
        //zero edge case: 0! = 1.0
        if (num == 0){return 1.0;}

        //actual factorial function
        //starts at num-1 and multiplies num by it going down
        double numDouble = (double)num;
        for (int i = num-1; i > 1; i--){
            numDouble *= i;
        }
        return numDouble;
    }
}
