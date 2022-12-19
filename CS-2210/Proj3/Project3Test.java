/**
 * Name: Project3Test.java
 * Desc: Unit tests for RPNcalc in Project3.java
 * Date: 10/6/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package project3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Project3Test {
    
    public Project3Test() {
    }
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing addition
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcAddition() throws InvalidRPNString {
        assertEquals(6.0, Project3.RPNcalc("3 3 +"));
        assertEquals(59.0, Project3.RPNcalc("2 57 +"));
        assertEquals(489.0, Project3.RPNcalc("420 69 +"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing subtraction
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcSubtraction() throws Exception{
        assertEquals(0.0, Project3.RPNcalc("3 3 -"));
        assertEquals(351.0, Project3.RPNcalc("420 69 -"));
        assertEquals(-45.0, Project3.RPNcalc("0 45 -"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing multiplication
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcMultiplication() throws Exception{
        assertEquals(9.0, Project3.RPNcalc("3 3 *"));
        assertEquals(28980.0, Project3.RPNcalc("420 69 *"));
        assertEquals(0.0, Project3.RPNcalc("0 45 *"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing division
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcDivision() throws Exception{
        assertEquals(1.0, Project3.RPNcalc("3 3 /"));
        assertEquals(420.0/69.0, Project3.RPNcalc("420 69 /"));
        assertEquals(4.0, Project3.RPNcalc("16 4 /"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing divide by zero exception
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcDivideByZero() throws Exception{
        Exception exception = assertThrows(InvalidRPNString.class, 
                                           ()-> Project3.RPNcalc("3 0 /"));
        String msg = exception.getMessage();
        assertTrue(msg.equals("Cannot divide by 0"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing too few operators exception
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcTooFewOps() throws Exception{
        Exception exception = assertThrows(InvalidRPNString.class, 
                                           ()-> Project3.RPNcalc("3 3 3 3 +"));
        String msg = exception.getMessage();
        assertTrue(msg.equals("Too few operators"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing invalid input exception
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcBadInput() throws Exception{
        Exception exception = assertThrows(InvalidRPNString.class, 
                                           ()-> Project3.RPNcalc("3 yeet 3 +"));
        String msg = exception.getMessage();
        assertTrue(msg.equals("An input is not a number or operation"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * testing too few numbers exception
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcTooFewNumbers() throws Exception{
        Exception exception = assertThrows(InvalidRPNString.class, 
                                           ()-> Project3.RPNcalc("3 +"));
        String msg = exception.getMessage();
        assertTrue(msg.equals("Too few numbers"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * testing too few numbers exception
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcEmptyString() throws Exception{
        Exception exception = assertThrows(InvalidRPNString.class, 
                                           ()-> Project3.RPNcalc(""));
        String msg = exception.getMessage();
        assertTrue(msg.equals("Don't enter empty strings"));
    }
    
    /**
     * Test of RPNcalc method, of class Project3.
     * Testing the project example
     * @throws project3.InvalidRPNString
     */
    @org.junit.jupiter.api.Test
    public void testRPNcalcProjExample() throws Exception{
        assertEquals(23.3-(5+16.2)*8, Project3.RPNcalc("23.3 5 16.2 + 8 * -"));
    }
}
