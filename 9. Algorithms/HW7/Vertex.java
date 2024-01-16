package algHw7;

import java.util.ArrayList;

public class Vertex {
	private char name;
	private char color;
	private int level;
	private Vertex parent;
	private int start;
	private int finish;
	private ArrayList<Vertex> AdjacenciesList;
	
	public Vertex(char name) {
		this.name       = name;
		color           = 'W';
		level           = Integer.MAX_VALUE;
		parent          = null;
		start           = Integer.MAX_VALUE;
		finish          = Integer.MAX_VALUE;
		AdjacenciesList = new ArrayList<>();
	}
	
	public void MakeSource() {
		this.color = 'G';
		this.level = 0;
	}
	
	
	//GETTERS
	public char    GetName()                      { return name; }
	public char    GetColor()                     { return color; }
	public int     GetLevel()                     { return level; }
	public Vertex  GetParent()                    { return parent; }
	public int     GetStart()                     { return start; }
	public int     GetFinish()                    { return finish; }
	public ArrayList<Vertex> GetAdjacenciesList() { return AdjacenciesList; }
	
	//SETTERS
	public void SetName           (char name)                         { this.name = name; }
	public void SetColor          (char color)                        { this.color = color; }
	public void SetLevel          (int level)                         { this.level = level; }
	public void SetParent         (Vertex parent)                     { this.parent = parent; }
	public void SetStart          (int start)                         { this.start = start; }
	public void SetFinish         (int finish)                        { this.finish = finish; }
	public void SetAdjacenciesList(ArrayList<Vertex> AdjacenciesList) { this.AdjacenciesList = AdjacenciesList; }
	
	//Adjacencies
	public void AddAdjacency   (Vertex Adjacent) { AdjacenciesList.add(Adjacent); }
	public void RemoveAdjacency(Vertex Adjacent) { AdjacenciesList.remove(Adjacent); }
	
	//PRINT FORMATTING
	@Override
    public String toString() {
		return Character.toString(name);
	}	
}
