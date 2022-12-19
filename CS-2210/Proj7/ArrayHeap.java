package project7;

import java.util.Random;

/**
 * Title:        Project #7
 * Description:  Makes an Array Heap data structure
 * Copyright:    Copyright (c) 2001
 * Company:      Gallagher Corp.
 * @author       Christopher LaFave
 * @version      12/1/2022
 */

public class ArrayHeap extends ArrayBinaryTree implements Heap {

    Comparator heapComp;

    public ArrayHeap(Comparator newComp) {
        this (newComp, DEFAULT_SIZE);
    }

    public ArrayHeap(Comparator newComp, int newSize) {
        super (newSize);
        heapComp = newComp;
    }

  /*add numbers into the heap
    -Check if the key is valid (throw an exception if not)
    -Create a new Item and ArrayPosition
    -Check if array is about to be full
        -If yes, create an array with 2x the space and copy it over
    -Throw the input into the end of the array
    -Update size
    -Bubble Up
  */
  public void add(Object newKey, Object newElement)
                                        throws InvalidObjectException {
      if(!heapComp.isComparable(newKey)){
          throw new InvalidObjectException("Key is not a number");
      }
      
      Item data = new Item(newKey, newElement);
      ArrayPosition pos = new ArrayPosition(size,data);
      
      if(btArray.length-1 == size){
          ArrayPosition[] temp = new ArrayPosition[size*2];
          for (int i = 0; i < btArray.length; i++){
              temp[i] = btArray[i];
          }
          btArray = temp;
      }
      
      btArray[size] = pos;
      size++;
      bubbleUp(btArray[size-1]);
  }

  
  /*remove the minimum key from the heap
    -Check if heap is empty (if yes, throw an exception)
    -Store the value to be returned
    -Set the last element in the array to root
    -Delete the last element
    -Bubble Down
    -Change size
    -return
  */
  public Object removeRoot() throws EmptyHeapException {
      if(this.isEmpty()){
          throw new EmptyHeapException("Heap is Empty");
      }
      Object min = this.root().element();
      
      btArray[0].setElement(btArray[size - 1].element());
      btArray[size-1] = null;
      bubbleDown(this.root());
      size--;
      return (Object)min;
  }
  
  /*Sort the heap upon insert
    -Create new variables for neatness
        -Check if they are null to avoid exceptions
        -Skip whole method if pos is null, else is fine
    -While pos is not root and pos's key is less than it's parents:
        -Swap the elements of pos and pos.parent
        -Assign pos to pos.parent
        -Check for nulls again
        -Terminates loop (and method) if pos is null
  */
  public void bubbleUp(Position pos){
      Item posItem = new Item();
      Item parentItem = new Item();
      
      if (pos != null){
          posItem = ((Item)pos.element());
      }
      else{
          return;
      }
      if(this.parent(pos) != null){
          parentItem = ((Item)(this.parent(pos)).element());
      }
      
      while (pos != this.root() && 
             heapComp.isLessThan(posItem.key(), parentItem.key())){
          Object temp = pos.element();
          ((ArrayPosition)pos).setElement(this.parent(pos).element());
          ((ArrayPosition)this.parent(pos)).setElement(temp);
          
          pos = this.parent(pos);
          if (pos != null){
              posItem = ((Item)pos.element());
          }
          else{
              return;
          }
          if(this.parent(pos) != null){
              parentItem = ((Item)(this.parent(pos)).element());
          }
      }
  }
  
  /*Sort the heap upon removal
    -Create variables for neatness
        -Check for null, if yes, terminate the method
    -While pos is not right and pos
            -Swap Array Positions of left and pos
        -Else if right is smaller than pos
            -Swap Array Positions of right and pos
        -Else
            -pos is smallest, terminate method
        -Check for nulls again
            -Terminates loop (and method) if null
  */
  public void bubbleDown(Position pos){
      Item posItem;
      Item leftItem = new Item();
      Item rightItem;
      
      if (pos != null){
          posItem = ((Item)pos.element());
      }
      else{
          return;
      }
      if (this.leftChild(pos) != null){
          leftItem = ((Item)(this.leftChild(pos)).element());
      }
      else{
          return;
      }
      if (this.rightChild(pos) != null){
          rightItem = ((Item)(this.rightChild(pos)).element());
      }
      else{
          return;
      }
      
      while (this.isInternal(pos)){
      //    find smallest key of pos, pos.lchild, pos.rchild
          if (heapComp.isLessThan(leftItem.key(), rightItem.key()) &&
              heapComp.isLessThan(leftItem.key(), posItem.key())){
              Object temp = pos.element();
              ((ArrayPosition)pos).setElement(this.leftChild(pos).element());           
              ((ArrayPosition)this.leftChild(pos)).setElement(temp);
              pos = this.leftChild(pos);   

          }
          else if(heapComp.isLessThan(rightItem.key(), posItem.key())){
              Object temp = pos.element();
              ((ArrayPosition)pos).setElement(this.rightChild(pos).element());
              ((ArrayPosition)this.rightChild(pos)).setElement(temp);
              pos = this.rightChild(pos);
          }
          else{
              return;
          }
          
          if (pos != null){posItem = ((Item)pos.element());}
          else{return;}
          if (this.leftChild(pos) != null){
             leftItem = ((Item)(this.leftChild(pos)).element());
          }
          else{return;}
          if (this.rightChild(pos) != null){
             rightItem = ((Item)(this.rightChild(pos)).element());
          }
          else{return;}
      }
  }

        // you may want to expand main; it is just provided as a sample
    public static void main (String[] args) {
	Comparator myComp = new IntegerComparator();
        Heap myHeap = new ArrayHeap (myComp, 8);
        
        //first test
        /*myHeap.add(14, 14);
        myHeap.add(17, 17);
        myHeap.add(3, 3);
        myHeap.add(2, 21);
        myHeap.add(8, 8);
        myHeap.add(7, 18);
        myHeap.add(1, 1);
        myHeap.add(19, 11);
        myHeap.add(17, 17);
        myHeap.add(25, 6);*/
        
        //RNG
        Random rand = new Random();
        for (int i = 0; i < 999999; i++){
            myHeap.add(rand.nextInt(70000),rand.nextInt(70000));
        }

        //code not written by me for output
        System.out.println(myHeap.size());
        while (!myHeap.isEmpty()) {

            Item removedItem = (Item) myHeap.removeRoot();
            System.out.print("Key:   " + removedItem.key() + "     ");
            System.out.println("Removed " + removedItem.element());
        }
        System.out.println("All nodes removed");
    }
}
