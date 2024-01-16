package algHw7;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	private ArrayList<Vertex> graphList;
	
	public Graph() {
		graphList = new ArrayList<>();
	}
	
	public Graph(ArrayList<Vertex> graphList) {
		this.graphList = graphList;
	}
	
	public ArrayList<Vertex> GetVertices() { return graphList; }
	
	public ArrayList<Vertex> GetAdjacencies(Vertex vertex){
		if(!graphList.contains(vertex)) {
			System.out.println("Vertex not found!");
			return null;
		}
		else {
			return graphList.get(graphList.indexOf(vertex)).GetAdjacenciesList();
		}
	}
	
	public Graph ComputeTranspose() {
		Graph TransposedGraph = new Graph();
		
		for(Vertex currVertex : graphList) {
			for(Vertex adjVertex : this.GetAdjacencies(currVertex)) {
				if(!TransposedGraph.GetVertices().contains(adjVertex)) {
					currVertex.RemoveAdjacency(adjVertex);
					adjVertex.AddAdjacency(currVertex);
				}
			}
			TransposedGraph.graphList.add(currVertex);
		}
		
		return TransposedGraph;
	}
	
	public static void BFSPrintPath(Vertex source, Vertex currVertex) {
		System.out.print("Path from " + source + "to " + currVertex + "(" + currVertex.GetLevel() + "/- " + currVertex.GetColor() + " " + currVertex.GetParent() + ") is: ");
		BFSPrintPathBody(source, currVertex, false);
		System.out.println();
	}
	
	private static void BFSPrintPathBody(Vertex source, Vertex currVertex, boolean doComma) {
		if(currVertex == source) {
			if(doComma) {
				System.out.print(source + ", ");
			}
			else {
				System.out.print(source + " ");
			}
		}
		else if(currVertex.GetParent() == null) {
			System.out.println("No path from " + source + "to" + currVertex);
		}
		else {
			BFSPrintPathBody(source, currVertex.GetParent(), true);
			if(doComma) {
				System.out.print(currVertex + ", ");
			}
			else {
				System.out.print(currVertex + " ");
			}
		}
	}
	
	public static void DFSPrintPath(Vertex vertex) {
		System.out.println(vertex.GetName() + "(" + vertex.GetStart() + "/" + vertex.GetFinish() + " " + vertex.GetColor() + " " + vertex.GetParent() + ")");
	}
	
	public static void TopoPrintPath(LinkedList<Vertex> list) {
		for(Vertex vertex : list) {
			System.out.println(vertex.GetName() + "(" + vertex.GetStart() + "/" + vertex.GetFinish() + " " + vertex.GetColor() + " " + vertex.GetParent() + ")");
		}
	}
}
