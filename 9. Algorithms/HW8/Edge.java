package algHw8;

import java.util.Comparator;

public class Edge {
	private Vertex firstVertex;
	private Vertex secondVertex;
	private int weight;
	
	public Edge(Vertex firstVertex, Vertex secondVertex, int weight) {
		this.firstVertex = firstVertex;
		this.secondVertex = secondVertex;
		this.weight = weight;
	}
	
	//GETTERS
	public Vertex GetFirstVertex()  { return firstVertex; }
	public Vertex GetSecondVertex() { return secondVertex; }
	public int    GetWeight()       { return weight; }
	
	//SETTERS
	public void SetFirstVertex (Vertex firstVertex) { this.firstVertex = firstVertex; }
	public void SetSecondVertex(Vertex secondVertex) { this.secondVertex = secondVertex; }
	public void SetWeight      (int weight) {this.weight = weight; }
	
	//MISC
	public int Contains(Vertex vertex) {
		if(vertex == firstVertex) {
			return 1;
		}
		else if(vertex == secondVertex) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	//COMPARATOR
		static class EdgeComparator implements Comparator<Edge>{

			@Override
			public int compare(Edge firstEdge ,Edge secondEdge) {
				if(firstEdge.GetWeight() < secondEdge.GetWeight()) {
					return -1;
				}
				else if(firstEdge.GetWeight() > secondEdge.GetWeight()) {
					return 1;
				}
				else {
					if(firstEdge.GetFirstVertex().GetName() < secondEdge.GetFirstVertex().GetName()) {
						return -1;
					}
					else if(firstEdge.GetFirstVertex().GetName() > secondEdge.GetFirstVertex().GetName()) {
						return 1;
					}
					else {
						if(firstEdge.GetSecondVertex().GetName() < secondEdge.GetSecondVertex().GetName()) {
							return -1;
						}
						else if(firstEdge.GetSecondVertex().GetName() > secondEdge.GetSecondVertex().GetName()) {
							return 1;
						}
						else {
							return 0;
						}
					}
				}
			}
	    }
	
	//PRINT FORMATTING
		@Override
	    public String toString() {
			return "(" + firstVertex + ", " + secondVertex + "): " + weight;
			
		}
}
