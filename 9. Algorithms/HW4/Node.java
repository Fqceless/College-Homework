package algHw4;

public class Node {
	private Integer key;
	private Node parent;
	private Node leftChild;
	private Node rightChild;
	private boolean isBlack;
	
	public Node(Integer key, Node parent, Node leftChild, Node rightChild) {
		this.key = key;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.isBlack = true;
	}
	
	public Integer GetKey() {
		return key;
	}
	
	public void SetKey(Integer key) {
		this.key = key;
	}
	
	public Node GetParent() {
		return parent;
	}
	
	public void SetParent(Node parent) {
		this.parent = parent;
	}
	
	public Node GetLeftChild() {
		return leftChild;
	}
	
	public void SetLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
	public Node GetRightChild() {
		return rightChild;
	}
	
	public void SetRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean isBlack() {
		return isBlack;
	}
	
	public void setIsBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	
	
}
