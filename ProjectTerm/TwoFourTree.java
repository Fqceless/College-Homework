package termproj;
import java.util.Random;
/**
 * Title:        Term Project 2-4 Trees 
 * Description:  Creates a working 2-4 Tree
 * Copyright:    Copyright (c) 2001
 * Class:        Data Structures in Java
 * @author       Christopher LaFave and [Partner]
 * @version      1.0
 */
public class TwoFourTree
        implements Dictionary {

    private Comparator treeComp;
    private int size = 0;
    private TFNode treeRoot = null;

    public TwoFourTree(Comparator comp) {
        treeComp = comp;
    }

    private TFNode root() {
        return treeRoot;
    }

    private void setRoot(TFNode root) {
        treeRoot = root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Searches dictionary to determine if key is present
     * @param key to be searched for
     * @return object corresponding to key; null if not found
     */
    public Object findElement(Object key) {
        //check for empty tree
        if(isEmpty()){
            return null; 
        }
        int findIndex;
        TFNode current = root();
            //search through the tree, return if found
            while(current != null){
                findIndex = findFirstGreaterThanOrEqual(current,key);
                if(findIndex < current.getNumItems() 
                && treeComp.isEqual((current.getItem(findIndex)).key(),key)){
                    return current;
                }
                current = current.getChild(findIndex);
            }
        //if we didn't find it in the tree
        return null;
    }
    

    /**
     * Inserts provided element into the Dictionary
     * @param key of object to be inserted
     * @param element to be inserted
     * 12/9/22 5:35pm: wrote the foundation of it
     * 12/14/22 5:40pm: removed a useless line of code
     */
    public void insertElement(Object key, Object element) {
        //initialize the new [item]
        Item newItem = new Item(key, element);
        //check if tree is empty
        if(this.isEmpty()){
            treeRoot = new TFNode();
        }
        //start with root node
        TFNode currentNode = treeRoot;
        int childIndex = findFirstGreaterThanOrEqual(currentNode, key);
        //  find the right external node [node] to insert to
        while(currentNode.getChild(0) != null){
            currentNode = currentNode.getChild(childIndex);
            childIndex = findFirstGreaterThanOrEqual(currentNode, key);
        }
        //  Insert [key] into [node]
        currentNode.insertItem(childIndex, newItem);
        size++;
        //  check node for overflow
        if(currentNode.getNumItems() > currentNode.getMaxItems()){
            fixOverflow(currentNode);
        }
    }

    /**
     * Searches dictionary to determine if key is present, then
     * removes and returns corresponding object
     * @param key of data to be removed
     * @return object corresponding to key
     * @exception ElementNotFoundException if the key is not in dictionary
     */
    public Object removeElement(Object key) throws ElementNotFoundException {
        //find element to remove
        TFNode elementNode = (TFNode)findElement(key);
        //if key doesn't exist, throw exception
        if(elementNode == null){
            throw new TwoFourTreeException("Removed element not found");
        }
        
        //get values
        int index = findFirstGreaterThanOrEqual(elementNode, key);
        TFNode inOrderSuccessorNode = inOrderSuccessorNode(elementNode, key);
        Item inOrderSuccessor = inOrderSuccessorNode.getItem(0);
        Item toReturn;
        
        //if external:
        if (elementNode == inOrderSuccessorNode){
            toReturn = elementNode.removeItem(index);
            if(elementNode.getNumItems() == 0){
                fixUnderflow(elementNode);
            }
        }
        //if internal:
        else{
            toReturn = elementNode.replaceItem(index, inOrderSuccessor);
            index = findFirstGreaterThanOrEqual(inOrderSuccessorNode, 
                                                inOrderSuccessor.key());
            inOrderSuccessorNode.removeItem(index);
            if(inOrderSuccessorNode.getNumItems() == 0){
                fixUnderflow(inOrderSuccessorNode);
            }
        }
        size--;
        
        return toReturn.key();
    }
    
    /**
     * find the in-order successor of the given node
     * add .getItem() to the return for the item of this node
     * @param node
     * @param key
     * @return 
     * 12/15/2022 12:08am: Wrote out this method
     */
    private TFNode inOrderSuccessorNode(TFNode node, Object key){
        //check for no children, if so return self
        if(node.getChild(0) == null){
            return node;
        }
        
        //find and get the key's child
        int childIndex = findFirstGreaterThanOrEqual(node, key) + 1;
        TFNode childNode = node.getChild(childIndex);
        
        //just keep going left
        while(childNode.getChild(0) != null){
            childNode = childNode.getChild(0);
        }
        //return that final left child
        return childNode;
    }
    
    /**
     * Finds the first key that is greater than or equal to the given key
     * @param node
     * @param key
     * @return 
     * 
     * 12/9/22 4:18pm: wrote this in how Dr.G wrote
     *                 it on the board in the video
     *         5:44pm: added key comparable checks
     */
    private int findFirstGreaterThanOrEqual(TFNode node, Object key){
        //this is all just how Dr. G wrote it
        if (!treeComp.isComparable(key)){
            throw new TwoFourTreeException("New key not comparable");
        }
        int i;
        Object checkKey;
        for (i = 0; i < node.getNumItems(); i++){
            checkKey = node.getItem(i).key();
            if (!treeComp.isComparable(checkKey)){
                throw new TwoFourTreeException("Node key not comparable");
            }
            else if (treeComp.isGreaterThanOrEqualTo(checkKey, key)){
                return i;
            }
        }
        return i;
    }
    
    /**
     * Finds the index number of this node relative to its parent
     * @param node
     * @return 
     * 12/14/22 5:40pm: Wrote the method!
     */
    private int whatChildIsThis(TFNode node){
        //make sure node has a parent
        if(node.getParent() == null){
            throw new TwoFourTreeException("Did WCIT to a parentless node");
        }
        //get parent
        TFNode parent = node.getParent();
        //iterate through each child, look for [node]
        for (int i = 0; i < parent.getNumItems(); i++){
            if (parent.getChild(i) == node){
                return i;
            }
        }
        //if it falls out of the loop, it's the last node
        return parent.getNumItems();
    }
    /**
     * "bubbles up" the tree and fixes the overflow at a leaf node
     * @param node 
     * 12/9/22 6:38pm: Wrote foundation
     * 12/14/22 5:40pm: Fixed some indexing and corrected order of operations
     */
    private void fixOverflow(TFNode node){
        //grab 3rd item from overflow node
        Item overflowedItem = node.getItem(2);
        
        //create new child node & give it last indexed item & delete items
        TFNode splitNode = new TFNode();
        splitNode.addItem(0, node.getItem(3));
        node.deleteItem(3);
        node.deleteItem(2);
        
        //set splitNode's child pointers
        splitNode.setChild(0, node.getChild(3));
        splitNode.setChild(1, node.getChild(4));
        //set splitNode's children's parent pointers
        if (splitNode.getChild(0) != null){
            splitNode.getChild(0).setParent(splitNode);
            splitNode.getChild(1).setParent(splitNode);
        }
        //remove overflowed node's child pointers that we just stole
        node.setChild(3, null);
        node.setChild(4, null);
        
        //either assign the parent or create it if at the root
        TFNode parentNode;
        if(node.getParent() != null){
            parentNode = node.getParent();
        }
        else{
            parentNode = new TFNode();
            treeRoot = parentNode;
        }
        
        //point children to parent
        node.setParent(parentNode);
        splitNode.setParent(parentNode);
        
        //find the index of the overflowed node and add the overflowed item
        int nodeIndex = whatChildIsThis(node);
        parentNode.insertItem(nodeIndex, overflowedItem);
        
        
        //point parent to children
        parentNode.setChild(nodeIndex, node);
        parentNode.setChild(nodeIndex + 1, splitNode);
        
        //check for parent overflow
        if(parentNode.getNumItems() > parentNode.getMaxItems()){
            fixOverflow(parentNode);
        }
    }
    
    
    private void fixUnderflow(TFNode node){
        TFNode parentNode = node.getParent();
        //if the node was root
        if(parentNode == null){
            treeRoot = node.getChild(0);
            if(treeRoot != null){
                treeRoot.setParent(null);
            }
            node = null;
        }
        
      
        //check for what transfer/fusion to do
        //if not the left child and the node to the left has at least 2 items
        else if(whatChildIsThis(node) > 0 
        && parentNode.getChild(whatChildIsThis(node) - 1).getNumItems() >= 2){
            leftTransfer(node);
        }
        //if not the right child and node to the right has at least 2 items
        else if(whatChildIsThis(node) < parentNode.getNumItems() 
        && parentNode.getChild(whatChildIsThis(node) + 1).getNumItems() >= 2){
            rightTransfer(node);
        }
        //if not the left child and the left node has one item
        else if(whatChildIsThis(node) > 0 
        && parentNode.getChild(whatChildIsThis(node) - 1).getNumItems() == 1){
            leftFusion(node);
        }
        //if not the right child and the right node has one item
        else if(whatChildIsThis(node) < parentNode.getNumItems() 
        && parentNode.getChild(whatChildIsThis(node) + 1).getNumItems() == 1){
            rightFusion(node);
        }
    }
    
    private void leftTransfer(TFNode node){
        //get parent and sibling
        TFNode parentNode = node.getParent();
        int siblingIndex = whatChildIsThis(node) - 1;
        TFNode siblingNode = parentNode.getChild(siblingIndex);
        //Get node that will attach to underflowed & reset it's pointer
        TFNode tempNode = siblingNode.getChild(siblingNode.getNumItems());
        //move the items around
        Item leftItem = siblingNode.deleteItem(siblingNode.getNumItems() - 1);
        Item parentItem = parentNode.replaceItem(siblingIndex, leftItem);
        node.insertItem(0, parentItem);
        
        //reset the pointers
        if(node.getChild(0) != null){
            node.setChild(0, tempNode);
            node.getChild(0).setParent(node);
        }
    }
    
    private void rightTransfer(TFNode node){
        //get parent and sibling
        TFNode parentNode = node.getParent();
        int siblingIndex = whatChildIsThis(node) + 1;
        TFNode siblingNode = parentNode.getChild(siblingIndex);
        //Get node that will attach to underflowed & reset it's pointer
        TFNode tempNode = siblingNode.getChild(0);
        //move the items around
        Item rightItem = siblingNode.removeItem(0);
        Item parentItem = parentNode.replaceItem(siblingIndex - 1, rightItem);
        node.insertItem(0, parentItem);
        
        //reset the pointers
        if(node.getChild(0) != null){
            node.setChild(1, tempNode);
            node.getChild(1).setParent(node);
        }
    }
    
    private void leftFusion(TFNode node){
        //get parent and sibling
        TFNode parentNode = node.getParent();
        int siblingIndex = whatChildIsThis(node) - 1;
        TFNode siblingNode = parentNode.getChild(siblingIndex);
        
        //get parent and move its item to sibling
        Item parentItem = parentNode.getItem(siblingIndex);
        parentNode.removeItem(siblingIndex);
        siblingNode.insertItem(siblingNode.getNumItems(), parentItem);
        
        //store new node and fix its pointers
        TFNode tempNode = node.getChild(node.getNumItems());
        if(tempNode != null){
            siblingNode.setChild(siblingNode.getNumItems(), tempNode);
            tempNode.setParent(siblingNode);
        }
        parentNode.setChild(siblingIndex, siblingNode);
        
        //check if we need to fixUnderflow
        if(parentNode.getNumItems() == 0){
            fixUnderflow(parentNode);
        }
    }
    
    private void rightFusion(TFNode node){
        //get parent and sibling
        TFNode parentNode = node.getParent();
        int siblingIndex = whatChildIsThis(node) + 1;
        TFNode siblingNode = parentNode.getChild(siblingIndex);
        
        //get parent and move its item to sibling
        Item parentItem = parentNode.getItem(siblingIndex - 1);
        parentNode.removeItem(siblingIndex - 1);
        siblingNode.insertItem(0, parentItem);
        
        //store new node and fix its pointers
        TFNode tempNode = node.getChild(node.getNumItems());
        if(siblingNode.getChild(0) != null){
            siblingNode.setChild(0, tempNode);
            siblingNode.getChild(0).setParent(siblingNode);
        }
        
        //check if we need to fixUnderflow
        if(parentNode.getNumItems() == 0){
            fixUnderflow(parentNode);
        }
    }
    
    public static void main(String[] args) {
        Comparator myComp = new IntegerComparator();
        TwoFourTree myTree = new TwoFourTree(myComp);
        
        myTree = new TwoFourTree(myComp);
        final int TEST_SIZE = 1000000;
        
        /*
        INSERT RANDOM NUMBERS:
        */
        Random rand = new Random();
        int[] randNums = new int[TEST_SIZE];
        for (int i = 0; i < TEST_SIZE; i++) {
            randNums[i] = rand.nextInt(TEST_SIZE / 10);
            System.out.println("Inserting: " + randNums[i]);
            myTree.insertElement(randNums[i], randNums[i]);
            //myTree.printAllElements();
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            if (i > TEST_SIZE - 25){
                System.out.println("Removing: " + randNums[i]);
            }
            int out = (Integer) myTree.removeElement(randNums[i]);
            if (i > TEST_SIZE - 25){
                myTree.printAllElements();
            }
            
        }

        /*
        INSERT ACSENDING/DECSENDING NUMBERS
        *//*
        for (int i = 0; i < TEST_SIZE; i++) {
            System.out.println("Inserting: " + i);
            myTree.insertElement(new Integer(i), new Integer(i));
        }
        System.out.println("removing");
        for (int i = TEST_SIZE - 1; i >= 0; i--) {
            System.out.println("Removing: " + i);
            int out = (Integer) myTree.removeElement(new Integer(i));
            myTree.printAllElements();
            myTree.checkTree();
            if (out != i) {
                throw new TwoFourTreeException("main: wrong element removed");
            }
        }*/
        System.out.println("done");
    }

    public void printAllElements() {
        int indent = 0;
        if (root() == null) {
            System.out.println("The tree is empty");
        }
        else {
            printTree(root(), indent);
        }
    }

    public void printTree(TFNode start, int indent) {
        if (start == null) {
            return;
        }
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        printTFNode(start);
        indent += 4;
        int numChildren = start.getNumItems() + 1;
        for (int i = 0; i < numChildren; i++) {
            printTree(start.getChild(i), indent);
        }
    }

    public void printTFNode(TFNode node) {
        int numItems = node.getNumItems();
        for (int i = 0; i < numItems; i++) {
            System.out.print(((Item) node.getItem(i)).element() + " ");
        }
        System.out.println();
    }

    // checks if tree is properly hooked up, i.e., children point to parents
    public void checkTree() {
        checkTreeFromNode(treeRoot);
    }

    private void checkTreeFromNode(TFNode start) {
        if (start == null) {
            return;
        }

        if (start.getParent() != null) {
            TFNode parent = start.getParent();
            int childIndex = 0;
            for (childIndex = 0; childIndex <= parent.getNumItems(); childIndex++) {
                if (parent.getChild(childIndex) == start) {
                    break;
                }
            }
            // if child wasn't found, print problem
            if (childIndex > parent.getNumItems()) {
                System.out.println("Child to parent confusion");
                printTFNode(start);
            }
        }

        if (start.getChild(0) != null) {
            for (int childIndex = 0; childIndex <= start.getNumItems(); childIndex++) {
                if (start.getChild(childIndex) == null) {
                    System.out.println("Mixed null and non-null children");
                    printTFNode(start);
                }
                else {
                    if (start.getChild(childIndex).getParent() != start) {
                        System.out.println("Parent to child confusion");
                        printTFNode(start);
                    }
                    for (int i = childIndex - 1; i >= 0; i--) {
                        if (start.getChild(i) == start.getChild(childIndex)) {
                            System.out.println("Duplicate children of node");
                            printTFNode(start);
                        }
                    }
                }

            }
        }

        int numChildren = start.getNumItems() + 1;
        for (int childIndex = 0; childIndex < numChildren; childIndex++) {
            checkTreeFromNode(start.getChild(childIndex));
        }

    }
}
