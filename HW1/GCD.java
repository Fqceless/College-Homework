/**
 * Name: GCD.java
 * Desc: Finds the greatest common divisor
 * Date created: 9/6/22
 * @author Christopher LaFave
 * @version 1.0
 */
package numeric;

public class GCD{
    public static void main(String []args){
        //check for correct # of args (2)
        if (args.length == 2){
            //String -> int
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            // run the function
            int i = GCD.compute(num1, num2);
            //print results
            System.out.println("GCD = " + i);
        }
        //if # of args != 2
        else{
            System.out.print("Only enter 2 arguements please, ");
            System.out.println("I'm a little baby program :(");
        }
    }

    public static int compute(int x, int y){
        //return condition
        if (y==0){
            //make sure no negative numbers get returned
            if (x < 0){
                return -x;
            }
            else{
                return x;
            }
        }
        //recursive condition
        else{
            return compute(y, x%y);
        }
        //this is all code gallagher told me to write
        //not sure how it works lol
    }
}
