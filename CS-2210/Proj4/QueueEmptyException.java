/**
 * Name: Project4Test.java
 * Desc: Creates the QueueEmptyException
 * Date: 10/18/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package proj4;

public class QueueEmptyException extends Exception{
    public QueueEmptyException (String err){
        super(err);
    }
}
