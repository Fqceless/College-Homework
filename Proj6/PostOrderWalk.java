/**
 * Name: PostOrderWalk.java
 * Desc: Overrides EulerTour.java to walk a Binary Tree with  a Post-order walk
 * Date: 11/15/2022
 * @author Christopher LaFave
 * @version 1.0
 */

package project6;

//constructor, just like the EulerTour one
public class PostOrderWalk extends EulerTour {
    public PostOrderWalk (BinaryTree newTree) {
       tree = newTree;
    }
    
    //just runs performTour
    protected Object execute(){
        return performTour(tree.root());
    }
    
    //Post-Order Walk
    //Just like the template, but prints on only the Postorder or External steps
    @Override
    public Object performTour (Position pos) {
        TraversalResult result = initResult();
        if (tree.isExternal (pos) ) {
            visitExternal (pos, result);
        }
        else {
            //visitPreorder (pos, result);
		if (tree.leftChild(pos) != null) {
                    result.leftResult = performTour (tree.leftChild(pos) );
		}
            //visitInorder (pos, result);
		if (tree.rightChild(pos) != null) {
                    result.rightResult = performTour (tree.rightChild(pos) );
		}
            visitPostorder (pos, result);
        }
        return computeResult(result);
    }
    
    @Override
    protected void visitExternal (Position pos, TraversalResult result) { 
        result.nodeResult = pos.element();
    }
    
    @Override
    protected void visitPostorder (Position pos, TraversalResult result) {
        result.nodeResult = pos.element();
    }
    
    //Post-order walk prints leftChild->rightChild->self
    //if no right,    prints leftChild->self
    //if external,    prints self
    @Override
    protected Object computeResult (TraversalResult result) {
        if (result.leftResult != null && result.rightResult != null){
            return result.leftResult + " " +
                   result.rightResult + " " +
                   result.nodeResult;
        }
        else if(result.leftResult != null && result.rightResult == null){
            return result.leftResult + " " +
                   result.nodeResult;
        }
        return result.nodeResult;
    }
}
