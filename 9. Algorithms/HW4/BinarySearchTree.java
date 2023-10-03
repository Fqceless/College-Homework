package algHw4;

public class BinarySearchTree {
	
	protected Node nullNode = new Node(null, null, null, null);
	protected Node rootNode = nullNode;
	
	public BinarySearchTree() {
		nullNode.SetParent(nullNode);
		nullNode.SetLeftChild(nullNode);
		nullNode.SetRightChild(nullNode);
	}
	
	protected Node FindMinimum(Node node) {
		while(node.GetLeftChild() != nullNode) {
			node = node.GetLeftChild();
		}
		return node;
	}
	
	private Node FindMaximum(Node node) {
		while(node.GetRightChild() != nullNode) {
			node = node.GetRightChild();
		}
		return node;
	}
	
	private Node FindSuccessor(Node node) {
		if(node.GetRightChild() != nullNode) {
			return FindMinimum(node.GetRightChild());
		}
		Node parentNode = node.GetParent();
		while(parentNode != nullNode && node == parentNode.GetRightChild()) {
			node = parentNode;
			parentNode = parentNode.GetParent();
		}
		return parentNode;
	}
	
	private Node FindPredecessor(Node node) {
		if(node.GetLeftChild() != nullNode) {
			return FindMaximum(node.GetLeftChild());
		}
		Node parentNode = node.GetParent();
		while(parentNode != nullNode && node == parentNode.GetLeftChild()) {
			node = parentNode;
			parentNode = parentNode.GetParent();
		}
		return parentNode;
	}
	
	protected Node FindKey(Integer key) {
		Node node = rootNode;
		while(node != nullNode && node.GetKey() != key) {
			if(node.GetKey() > key) {
				node = node.GetLeftChild();
			}
			else {
				node = node.GetRightChild();
			}
		}
		return node;
	}
	
	protected void Transplant(Node deleteNode, Node subTreeNode) {
		if(deleteNode.GetParent() == nullNode) {
			rootNode = subTreeNode;
		}
		else if(deleteNode == deleteNode.GetParent().GetLeftChild()) {
			deleteNode.GetParent().SetLeftChild(subTreeNode);
		}
		else {
			deleteNode.GetParent().SetRightChild(subTreeNode);
		}
		if(subTreeNode != nullNode) {
			subTreeNode.SetParent(deleteNode.GetParent());
		}
	}
	
	public void InsertNode(Integer key) {
		Node searchNode = rootNode;
		Node parentOfSearchNode = nullNode;
		Node insertNode = new Node(key, nullNode, nullNode, nullNode);
		
		while(searchNode != nullNode) {
			parentOfSearchNode = searchNode;
			if(insertNode.GetKey() < searchNode.GetKey()) {
				searchNode = searchNode.GetLeftChild();
			}
			else {
				searchNode = searchNode.GetRightChild();
			}
		}
		insertNode.SetParent(parentOfSearchNode);
		if(parentOfSearchNode == nullNode) {
			rootNode = insertNode;
		}
		else if(insertNode.GetKey() < parentOfSearchNode.GetKey()) {
			parentOfSearchNode.SetLeftChild(insertNode);
		}
		else {
			parentOfSearchNode.SetRightChild(insertNode);
		}
	}
	
	public void DeleteNode(Integer key) {
		if(key == null) {
			return;
		}
		
		Node deleteNode = FindKey(key);
		if(deleteNode == nullNode) {
			return;
		}
		
		if(deleteNode.GetLeftChild() == nullNode) {
			Transplant(deleteNode, deleteNode.GetRightChild());
		}
		else if(deleteNode.GetRightChild() == nullNode) {
			Transplant(deleteNode, deleteNode.GetLeftChild());
		}
		else{
			Node deleteNodeSuccessor = FindMinimum(deleteNode.GetRightChild());
			if(deleteNodeSuccessor.GetParent() != deleteNode) {
				Transplant(deleteNodeSuccessor, deleteNodeSuccessor.GetRightChild());
				deleteNodeSuccessor.SetRightChild(deleteNode.GetRightChild());
				deleteNodeSuccessor.GetRightChild().SetParent(deleteNodeSuccessor);
			}
			Transplant(deleteNode, deleteNodeSuccessor);
			deleteNodeSuccessor.SetLeftChild(deleteNode.GetLeftChild());
			deleteNodeSuccessor.GetLeftChild().SetParent(deleteNodeSuccessor);
		}
	}
	
	public void PrintTree() {
		PrintTree(rootNode, 1);
	}
	
	protected void PrintTree(Node node, Integer tabLevel) {
		System.out.println(node.GetKey());
		if(node.GetRightChild() != nullNode) {
			for(int i = 0; i < tabLevel; i++) {
				System.out.print("  ");
			}
			System.out.print("R");
			PrintTree(node.GetRightChild(), tabLevel + 1);
		}
		if(node.GetLeftChild() != nullNode) {
			for(int i = 0; i < tabLevel; i++) {
				System.out.print("  ");
			}
			System.out.print("L");
			PrintTree(node.GetLeftChild(), tabLevel + 1);
		}
	}
}
