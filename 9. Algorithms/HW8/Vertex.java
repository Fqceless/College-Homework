package algHw8;

public class Vertex {
	private char name;
	private int distance;
	private Vertex parent;
	
	public Vertex(char name, String type) {
		this.name  = name;
		distance   = 9999;
		if(type.equals("MST")) {
			parent = this;
		}
		else {
			parent = null;
		}
	}
	
	//GETTERS
	public char   GetName()     { return name; }
	public int    GetDistance() { return distance; }
	public Vertex GetParent()   { return parent; }
	
	//SETTERS
	public void SetName     (char name)     { this.name = name; }
	public void SetDistance (int distance)  { this.distance = distance; }
	public void SetParent   (Vertex parent) { this.parent = parent; }
	
	//PRINT FORMATTING
	@Override
    public String toString() {
		return Character.toString(name);
	}
}
