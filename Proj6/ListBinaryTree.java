/**
 * Name: ListBinaryTree.java
 * Desc: Creates a binary tree to search
 *       and the methods to do so
 *       Also (Houses Main Method)
 * Date: 11/15/2022
 * @author Christopher LaFave
 * @version 1.0
 */

package project6;

public class ListBinaryTree implements BinaryTree {
    
    STNode root;
    int size;
    
    public void fillTree() {
        root = new STNode(0, null, null, null);
        STNode node1 = new STNode(1, root, null, null);
        STNode node2 = new STNode(2, root, null, null);
        root.setLeftChild(node1);
        node1.setSibling(node2);

        STNode node3 = new STNode(3, node1, null, null);
        STNode node4 = new STNode(4, node1, null, null);
        node1.setLeftChild(node3);
        node3.setSibling(node4);

        STNode node5 = new STNode(5, node2, null, null);
        STNode node6 = new STNode(6, node2, null, null);
        node2.setLeftChild(node5);
        node5.setSibling(node6);

        STNode node7 = new STNode(7, node3, null, null);
        STNode node8 = new STNode(8, node3, null, null);
        node3.setLeftChild(node7);
        node7.setSibling(node8);

        STNode node9 = new STNode(9, node4, null, null);
        STNode node10 = new STNode(10, node4, null, null);
        node4.setLeftChild(node9);
        node9.setSibling(node10);

        size = 11;
    }

    @Override
    public STNode root() throws EmptyTreeException {
        if (root.element() == null){
            throw new EmptyTreeException();
        }
        return (STNode)root;
    }

    @Override
    public STNode leftChild(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getLeftChild();
    }

    @Override
    public STNode rightChild(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getLeftChild().getSibling();
    }

    @Override
    public STNode sibling(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getSibling();
    }

    @Override
    public STNode parent(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getParent();
    }

    @Override
    public boolean isInternal(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getLeftChild() != null;
    }

    @Override
    public boolean isExternal(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getLeftChild() == null;
    }

    @Override
    public boolean isRoot(Position pos) throws InvalidPositionException {
        return posToSTNode(pos).getParent() == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root.element() == null;
    }
    
    //makes it easier to handle positions -> nodes
    //if the position is null, throws an exception
    public STNode posToSTNode(Position pos) throws InvalidPositionException{
        if(pos.element() == null){
            throw new InvalidPositionException();
        }
        return (STNode)pos;
    }
    
    //my test-cases
    public static void main (String[] args) {
        //fill tree
        ListBinaryTree myTree = new ListBinaryTree();
        myTree.fillTree();
       
        //instanciate walks
        InOrderWalk in = new InOrderWalk(myTree);
        PreOrderWalk pre = new PreOrderWalk(myTree);
        PostOrderWalk post = new PostOrderWalk(myTree);
        
        //test
        System.out.println("In: " + in.execute()); 
                          //In: 7 3 8 1 9 4 10 0 5 2 6
        System.out.println("Pre: " + pre.execute());
                          //Pre: 0 1 3 7 8 4 9 10 2 5 6
        System.out.println("Post: " + post.execute()); 
                          //Post: 7 8 3 9 10 4 1 5 6 2 0
        
    }
}