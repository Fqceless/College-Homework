/**
 * Name: NegativeNumberException.java
 * Desc: Creates an exception for the factorial class if a negative number is inputted
 * Date: 9/22/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package numeric;

public class NegativeNumberException extends Exception{
    public NegativeNumberException (String err){
        super(err);
    }
}
