package algHw8;

import java.util.ArrayList;
import java.util.PriorityQueue;
import algHw8.Edge.EdgeComparator;

public class Searches {
	public static ArrayList<Edge> Kruskal(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList) {
		PriorityQueue<Edge> searchQueue = new PriorityQueue<>(new EdgeComparator());
		ArrayList<Edge> retList = new ArrayList<>();
		
		searchQueue.addAll(edgeList);
		while(!searchQueue.isEmpty()) {
			Edge currEdge = searchQueue.remove();
			if(FindSet(currEdge.GetFirstVertex()) != FindSet(currEdge.GetSecondVertex())) {
				retList.add(currEdge);
				Union(currEdge.GetFirstVertex(), currEdge.GetSecondVertex());
			}
		}
		return retList;
	}
	
	
	private static Vertex FindSet(Vertex vertex) {
		if(vertex != vertex.GetParent()) {
			vertex.SetParent(FindSet(vertex.GetParent()));
		}
		return vertex.GetParent();
	}
	
	private static void Union(Vertex firstVertex, Vertex secondVertex) {
		FindSet(secondVertex).SetParent(FindSet(firstVertex));
	}
	
	public static void BellmanFord(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList) {
		vertexList.get(0).SetDistance(0);
		for(int i = 0; i < vertexList.size() + 1; i++) {
			for(Edge currEdge : edgeList) {
				Relax(currEdge);
			}
			for(Edge currEdge : edgeList){
				if(currEdge.GetSecondVertex().GetDistance() > currEdge.GetFirstVertex().GetDistance() + currEdge.GetWeight()) {
					System.out.println("Negative value loop found.");
					return;
				}
			}
		}
		for(Vertex vertex : vertexList) {
			System.out.print("Path from " + vertexList.get(0) + " to " + vertex + "(D: " + vertex.GetDistance() + " P: " + vertex.GetParent() + ") is: ");
			PrintSSSPPath(vertex);
			System.out.println();
		}
	}
	
	private static void Relax(Edge currEdge) {
		Relax(currEdge.GetFirstVertex(), currEdge.GetSecondVertex(), currEdge.GetWeight());
	}
	
	private static void Relax(Vertex currVertex, Vertex nextVertex, int edgeWeight) {
		if(nextVertex.GetDistance() > currVertex.GetDistance() + edgeWeight) {
			nextVertex.SetDistance(currVertex.GetDistance() + edgeWeight);
			nextVertex.SetParent(currVertex);
		}
	}
	
	private static void PrintSSSPPath(Vertex vertex) {
		if(vertex.GetParent() != null && vertex != vertex.GetParent()) {
			PrintSSSPPath(vertex.GetParent());
			System.out.print(vertex + " ");
		}
		else {
			System.out.print(vertex + " ");
		}
	}
	
}