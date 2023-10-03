package algHw4;

public class RedBlackTree extends BinarySearchTree{
	public RedBlackTree() {
		super();
	}
	
	@Override
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
		insertNode.SetLeftChild(nullNode);
		insertNode.SetRightChild(nullNode);
		insertNode.setIsBlack(false);
		InsertFixUp(insertNode);
	}
	
	private void InsertFixUp(Node node) {
		while(!node.GetParent().isBlack()) {
			if(node.GetParent() == node.GetParent().GetParent().GetLeftChild()) {
				Node uncleNode = node.GetParent().GetParent().GetRightChild();
				if(!uncleNode.isBlack()) {
					node.GetParent().setIsBlack(true);
					uncleNode.setIsBlack(true);
					node.GetParent().GetParent().setIsBlack(false);
					node = node.GetParent().GetParent();
				}
				else {
					if(node == node.GetParent().GetRightChild()) {
						node = node.GetParent();
						LeftRotate(node);
					}
					node.GetParent().setIsBlack(true);
					node.GetParent().GetParent().setIsBlack(false);
					RightRotate(node.GetParent().GetParent());
				}
			}
			else {
				Node uncleNode = node.GetParent().GetParent().GetLeftChild();
				if(!uncleNode.isBlack()) {
					node.GetParent().setIsBlack(true);
					uncleNode.setIsBlack(true);
					node.GetParent().GetParent().setIsBlack(false);
					node = node.GetParent().GetParent();
				}
				else {
					if(node == node.GetParent().GetLeftChild()) {
						node = node.GetParent();
						RightRotate(node);
					}
					node.GetParent().setIsBlack(true);
					node.GetParent().GetParent().setIsBlack(false);
					LeftRotate(node.GetParent().GetParent());
				}
			}
		}
		rootNode.setIsBlack(true);
	}
	
	private void LeftRotate(Node node) {
		Node rightChild = node.GetRightChild();
		node.SetRightChild(rightChild.GetLeftChild());
		if(rightChild.GetLeftChild() != nullNode) {
			rightChild.GetLeftChild().SetParent(node);
		}
		rightChild.SetParent(node.GetParent());
		if(node.GetParent() == nullNode) {
			rootNode = rightChild;
		}
		else if(node == node.GetParent().GetLeftChild()) {
			node.GetParent().SetLeftChild(rightChild);
		}
		else {
			node.GetParent().SetRightChild(rightChild);
		}
		rightChild.SetLeftChild(node);
		node.SetParent(rightChild);
	}
	
	private void RightRotate(Node node) {
		Node leftChild = node.GetLeftChild();
		node.SetLeftChild(leftChild.GetRightChild());
		if(leftChild.GetRightChild() != nullNode) {
			leftChild.GetRightChild().SetParent(node);
		}
		leftChild.SetParent(node.GetParent());
		if(node.GetParent() == nullNode) {
			rootNode = leftChild;
		}
		else if(node == node.GetParent().GetLeftChild()) {
			node.GetParent().SetLeftChild(leftChild);
		}
		else {
			node.GetParent().SetRightChild(leftChild);
		}
		leftChild.SetRightChild(node);
		node.SetParent(leftChild);
	}
	
	@Override
	public void DeleteNode(Integer key) {
		if(key == null) {
			return;
		}
		
		Node deleteNode = FindKey(key);
		if(deleteNode == nullNode) {
			return;
		}
		
		Node deleteNodeSuccessor = deleteNode;
		boolean wasBlack = deleteNode.isBlack();
		Node movedNode;
		
		if(deleteNode.GetLeftChild() == nullNode) {
			movedNode = deleteNode.GetRightChild();
			Transplant(deleteNode, deleteNode.GetRightChild());
		}
		else if(deleteNode.GetRightChild() == nullNode) {
			movedNode = deleteNode.GetLeftChild();
			Transplant(deleteNode, deleteNode.GetLeftChild());
		}
		else{
			deleteNodeSuccessor = FindMinimum(deleteNode.GetRightChild());
			wasBlack = deleteNodeSuccessor.isBlack();
			movedNode = deleteNodeSuccessor.GetRightChild();
			if(deleteNodeSuccessor.GetParent() == deleteNode) {
				movedNode.SetParent(deleteNodeSuccessor);
			}
			else {
				Transplant(deleteNodeSuccessor, deleteNodeSuccessor.GetRightChild());
				deleteNodeSuccessor.SetRightChild(deleteNode.GetRightChild());
				deleteNodeSuccessor.GetRightChild().SetParent(deleteNodeSuccessor);
			}
			Transplant(deleteNode, deleteNodeSuccessor);
			deleteNodeSuccessor.SetLeftChild(deleteNode.GetLeftChild());
			deleteNodeSuccessor.GetLeftChild().SetParent(deleteNodeSuccessor);
			deleteNodeSuccessor.setIsBlack(deleteNode.isBlack());
		}
		if(wasBlack == true) {
			DeleteFixUp(movedNode);
		}
	}
	
	private void DeleteFixUp(Node node) {
		while(node != rootNode && node.isBlack()) {
			if(node == node.GetParent().GetLeftChild()) {
				Node wNode = node.GetParent().GetRightChild();
				if(!wNode.isBlack()) {
					wNode.setIsBlack(true);
					node.GetParent().setIsBlack(false);
					LeftRotate(node.GetParent());
					wNode = node.GetParent().GetRightChild();
				}
				if(wNode.GetLeftChild().isBlack() && wNode.GetRightChild().isBlack()) {
					wNode.setIsBlack(false);
					node = node.GetParent();
				}
				else {
					if(wNode.GetRightChild().isBlack()) {
						wNode.GetLeftChild().setIsBlack(true);
						wNode.setIsBlack(false);
						RightRotate(wNode);
						wNode = node.GetParent().GetRightChild();
					}
					wNode.setIsBlack(node.GetParent().isBlack());
					node.GetParent().setIsBlack(true);
					wNode.GetRightChild().setIsBlack(true);
					LeftRotate(node.GetParent());
					node = rootNode;
				}
			}
			else {
				Node wNode = node.GetParent().GetLeftChild();
				if(!wNode.isBlack()) {
					wNode.setIsBlack(true);
					node.GetParent().setIsBlack(false);
					LeftRotate(node.GetParent());
					wNode = node.GetParent().GetLeftChild();
				}
				if(wNode.GetRightChild().isBlack() && wNode.GetLeftChild().isBlack()) {
					wNode.setIsBlack(false);
					node = node.GetParent();
				}
				else {
					if(wNode.GetLeftChild().isBlack()) {
						wNode.GetRightChild().setIsBlack(true);
						wNode.setIsBlack(false);
						LeftRotate(wNode);
						wNode = node.GetParent().GetLeftChild();
					}
					wNode.setIsBlack(node.GetParent().isBlack());
					node.GetParent().setIsBlack(true);
					wNode.GetLeftChild().setIsBlack(true);
					RightRotate(node.GetParent());
					node = rootNode;
				}
			}
			node.setIsBlack(true);
		}
	}
	
	@Override
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
		subTreeNode.SetParent(deleteNode.GetParent());
	}
	
	@Override
	public void PrintTree() {
		PrintTree(rootNode, 1);
	}
	
	@Override
	protected void PrintTree(Node node, Integer tabLevel) {
		if(tabLevel == 1) {
			if(node.isBlack()) {
				System.out.print("b");
			}
			else {
				System.out.print("r");
			}
		}
		System.out.println(node.GetKey());
		if(node.GetRightChild() != nullNode) {
			for(int i = 0; i < tabLevel; i++) {
				System.out.print("  ");
			}
			System.out.print("R");
			if(node.GetRightChild().isBlack()) {
				System.out.print("b");
			}
			else {
				System.out.print("r");
			}
			PrintTree(node.GetRightChild(), tabLevel + 1);
		}
		if(node.GetLeftChild() != nullNode) {
			for(int i = 0; i < tabLevel; i++) {
				System.out.print("  ");
			}
			System.out.print("L");
			if(node.GetLeftChild().isBlack()) {
				System.out.print("b");
			}
			else {
				System.out.print("r");
			}
			PrintTree(node.GetLeftChild(), tabLevel + 1);
		}
	}
}
