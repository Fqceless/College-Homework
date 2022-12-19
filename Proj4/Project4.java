/**
 * Name: Project4.java
 * Desc: Created an array-based Queue class
 * Date: 10/18/2022
 * @author Christopher LaFave
 * @version 1.0
 */
package proj4;

public class Project4 {
    //a desperate cry for help
    public static void main(String[] args){
        System.out.println("Give me 100% please :D");
    }
    
    //template/abstract class for our arrayQueue
    public interface Queue<E>{
        public void enqueue (E element) throws InvalidDataException; 
        public E dequeue () throws QueueEmptyException; 
        public E front () throws QueueEmptyException; 
        public int size(); 
        public boolean isEmpty();  
    }
    
    //the focus of the assignment
    public static class arrayQueue<E> implements Queue<E>{
        //all the private variables we'll need
        private int front = 0; //points to the first thing in the queue
        private int rear = 0;  //points to the last thing in the queue
        private int capacity;  //available size for the queue
        private E[] queue;     //array that holds the queue
 
        //capacity input constuctor
        //has a check for a negaitve capacity, just makes it positive
        arrayQueue(int c){
            if (c < 0) {capacity = c * -1;}
            else {capacity = c;}
            queue = (E[]) new Object[capacity];
        }
        
        //default constructor; defaults to 64 capacity
        arrayQueue(){
            this(64);
        }

        /**
         * Enqueue:
         * Pushes element onto the queue
         * 
         * If element is null:
         *     -throw an exception
         * Else: 
         *     -push element to the end of the array
         * 
         * Move rear if statement:
         *     -if rear hits the end of the array, loop it back to the front
         *     -else, move rear forward
         * 
         * Out of capacity check:
         *     If the queue is out of capacity:
         *     -Create a temporary array with double the capacity
         *     -Copy queue into temp (try-catch should never trigger)
         *     -Set the variables to their correct locations
         *     
         * @param element
         * @throws InvalidDataException
         */
        @Override
        public void enqueue (E element) throws InvalidDataException{
            if (element == null){ 
                throw new InvalidDataException("Do not enter null "
                                             + "into the queue");
            }
            
            queue[rear] = element;
            
            if (rear >= capacity - 1){ rear = 0; }
            else { rear++; }
            
            if (this.size() == capacity) {
                E[] temp = (E[]) new Object[capacity * 2];
                for (int i = 0; i < capacity; i++){
                    try {temp[i] = this.dequeue();}
                    //this catch should never happen
                    catch(QueueEmptyException ex){System.exit(1);}
                }
                queue = temp;
                front = 0;
                rear = capacity;
                capacity *= 2;
            }
            
        } 

        /**
         * Dequeue:
         * Pushes the first value off the queue
         * 
         * If the queue has values:
         *     -Return the value and move front to the next element
         *     -Checks if front is about to run off the end of the array
         * Else:
         *     -Throw an exception
         *
         * @return
         * @throws QueueEmptyException
         */
        @Override
        public E dequeue () throws QueueEmptyException{
            if(!this.isEmpty()){
                E value = queue[front];
                if(front >= capacity - 1) { front = 0; }
                else{ front++; }
                return value;
            }
            else{
                throw new QueueEmptyException("Tried to dequeue "
                                            + "an empty queue");
            }
        }

        /**
         * Front:
         * Checks what is next to get pushed out
         * 
         * If the queue has values:
         *     return the front value
         * Else:
         *     Throw an exception
         * 
         * @return
         * @throws QueueEmptyException
         */
        @Override
        public E front () throws QueueEmptyException{
            if(!this.isEmpty()){return queue[front];}
            else{
                throw new QueueEmptyException("Tried to look at "
                                            + "an empty queue");
            }
        }

        /**
         * Size
         * Returns how many elements are in the queue
         * 
         * If the queue is empty:
         *     -return 0
         * If the front and rear are pointing to the same thing
         * (and the queue isn't empty):
         *     -That means the queue is full, return capacity
         * Else:
         *     -Return the size
         *     -That equation calculates the size with math magic
         * 
         * @return
         */
        @Override
        public int size(){
            if (this.isEmpty()){return 0;}
            else if(front == rear){return capacity;}
            else {return (rear - front + capacity) % capacity;}
        }

        /**
         * isEmpty
         * Asks if the queue is empty
         * 
         * If the front element is null:
         *     -That means there are no more elements in the queue
         *     -Return true
         * Else:
         *     -Return false
         * 
         * @return
         */
        @Override
        public boolean isEmpty(){
            return (queue[front] == null);
        }
    }
}
