/**
 * Name: InvalidRPNString.java
 * Desc: Creates the InvalidRPNString exception used in Project3.java
 * Date: 10/6/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package project3;

//just a basic exception
public class InvalidRPNString extends Exception{
    public InvalidRPNString (String err){
        super(err);
    }
}
