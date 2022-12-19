/**
 * Name: Project4Test.java
 * Desc: Tests for the array-based queue
 * Date: 10/18/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package proj4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Project4Test {
    
    public Project4Test() {
    }

    /**
     * Tests that Enqueue, Front, and Dequeue work at a basic level
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testEnqueueFrontAndDequeue() throws InvalidDataException, 
                                                    QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(7);
        bleh.enqueue(7);
        assertEquals(7, bleh.front());
        assertEquals(7, bleh.dequeue());
    }
    
    /**
     * Tests that the capacity rows properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testCapacityInc() throws InvalidDataException, 
                                         QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(2);
        bleh.enqueue(1);
        bleh.enqueue(2);
        bleh.enqueue(3);
        bleh.enqueue(4);
        bleh.enqueue(5);
        assertEquals(1, bleh.dequeue());
        assertEquals(2, bleh.dequeue());
        assertEquals(3, bleh.dequeue());
        assertEquals(4, bleh.dequeue());
        assertEquals(5, bleh.dequeue());
    }
    
    /**
     * Tests that the looping of front and rear work properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testLoopStuff() throws InvalidDataException, 
                                       QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(2);
        bleh.enqueue(1);
        bleh.enqueue(2);
        bleh.dequeue();
        bleh.enqueue(3);
        bleh.enqueue(4);
        bleh.dequeue();
        bleh.enqueue(5);
        bleh.enqueue(6);
        assertEquals(3, bleh.dequeue());
        assertEquals(4, bleh.dequeue());
        assertEquals(5, bleh.dequeue());
        assertEquals(6, bleh.dequeue());
    }
    
    /**
     * Tests that the queue properly rejects null from it's inserts
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testNullException() throws InvalidDataException, 
                                           QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(2);
        Exception exception = assertThrows(InvalidDataException.class, 
                                           ()-> bleh.enqueue(null));
        String msg = exception.getMessage();
        assertTrue(msg.equals("Do not enter null into the queue"));
    }
    
    /**
     * Tests that both empty queue exceptions are thrown properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testQueueEmptyException() throws InvalidDataException, 
                                                 QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(2);
        Exception exception = assertThrows(QueueEmptyException.class, 
                                           ()-> bleh.dequeue());
        String msg = exception.getMessage();
        assertTrue(msg.equals("Tried to dequeue an empty queue"));
        
        Project4.arrayQueue blech = new Project4.arrayQueue<Integer>(2);
        Exception exception2 = assertThrows(QueueEmptyException.class, 
                                           ()-> bleh.front());
        String mssg = exception2.getMessage();
        assertTrue(mssg.equals("Tried to look at an empty queue"));
    }
    
    /**
     * Tests that the default constructor works properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testDefaultCap() throws InvalidDataException, 
                                        QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>();
        for (int i = 0; i < 64; i++){
            bleh.enqueue(i);
        }
        
        for (int i = 0; i < 64; i++){
            assertEquals(i, bleh.dequeue());
        }
    }
    
    /**
     * Tests that the isEmpty method works properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testIsEmpty() throws InvalidDataException, 
                                     QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(7);
        assertEquals(true, bleh.isEmpty());
        
        for (int i = 0; i < 7; i++){
            bleh.enqueue(i);
        }
        
        for (int i = 0; i < 7; i++){
            bleh.dequeue();
        }
        
        assertEquals(true, bleh.isEmpty());   
    }
    
    /**
     * Tests that the size method works properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testSize() throws InvalidDataException, 
                                  QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(2);
        
        for (int i = 0; i < 64; i++){
            bleh.enqueue(i);
            assertEquals(i + 1, bleh.size());
        }
    }
    
    /**
     * Tests that the front method works properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testFront() throws InvalidDataException, 
                                   QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<Integer>(2);
        
        for (int i = 0; i < 64; i++){
            bleh.enqueue(i);
            assertEquals(0, bleh.front());
        }

        for (int i = 0; i < 64; i++){
            assertEquals(i, bleh.front());
            bleh.dequeue();
        }
        
    }
    
    /**
     * Tests that the front method works properly
     *
     * @throws InvalidDataException
     * @throws QueueEmptyException
     */
    @Test
    public void testNonInt() throws InvalidDataException, 
                                   QueueEmptyException {
        Project4.arrayQueue bleh = new Project4.arrayQueue<String>();
        bleh.enqueue("Hello mom!");
        assertEquals("Hello mom!", bleh.dequeue());
    }
}
