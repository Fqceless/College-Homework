package algHw8;

import java.util.ArrayList;

public class AlgHw8 {
	static ArrayList<Vertex> vertexList;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) {
		SSSPtest1();
		SSSPtest3();
	}
	
	private static void MSTTest1() {
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		
		vertexList.add(new Vertex('A',"MST"));
		vertexList.add(new Vertex('B',"MST"));
		vertexList.add(new Vertex('C',"MST"));
		vertexList.add(new Vertex('D',"MST"));
		vertexList.add(new Vertex('E',"MST"));
		
		edgeList.add(new Edge(testVertex(1), testVertex(2), 1));
		edgeList.add(new Edge(testVertex(1), testVertex(3), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(4), 3));
		edgeList.add(new Edge(testVertex(1), testVertex(5), 5));
		
		edgeList.add(new Edge(testVertex(2), testVertex(3), 2));
		edgeList.add(new Edge(testVertex(2), testVertex(4), 1));
		edgeList.add(new Edge(testVertex(2), testVertex(5), 2));
		
		edgeList.add(new Edge(testVertex(3), testVertex(4), 3));
		edgeList.add(new Edge(testVertex(3), testVertex(5), 2));
		
		edgeList.add(new Edge(testVertex(4), testVertex(5), 9999));
		
		ArrayList<Edge> retList = Searches.Kruskal(vertexList, edgeList);
		
		System.out.println("MST Edges are:");
		for(Edge edge : retList) {
			System.out.println(edge);
		}
	}
	
	private static void MSTTest3() {
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		
		vertexList.add(new Vertex('A', "MST"));
		vertexList.add(new Vertex('B', "MST"));
		vertexList.add(new Vertex('C', "MST"));
		vertexList.add(new Vertex('D', "MST"));
		vertexList.add(new Vertex('E', "MST"));
		vertexList.add(new Vertex('F', "MST"));
		vertexList.add(new Vertex('G', "MST"));
		vertexList.add(new Vertex('H', "MST"));
		
		edgeList.add(new Edge(testVertex(1), testVertex(2), 3));
		edgeList.add(new Edge(testVertex(1), testVertex(3), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(4), 2));
		edgeList.add(new Edge(testVertex(1), testVertex(5), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(6), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(7), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(8), 9999));
		
		edgeList.add(new Edge(testVertex(2), testVertex(3), 2));
		edgeList.add(new Edge(testVertex(2), testVertex(4), 1));
		edgeList.add(new Edge(testVertex(2), testVertex(5), 9999));
		edgeList.add(new Edge(testVertex(2), testVertex(6), 5));
		edgeList.add(new Edge(testVertex(2), testVertex(7), 3));
		edgeList.add(new Edge(testVertex(2), testVertex(8), 9999));
		
		edgeList.add(new Edge(testVertex(3), testVertex(4), 4));
		edgeList.add(new Edge(testVertex(3), testVertex(5), 8));
		edgeList.add(new Edge(testVertex(3), testVertex(6), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(7), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(8), 9999));
		
		edgeList.add(new Edge(testVertex(4), testVertex(5), 1));
		edgeList.add(new Edge(testVertex(4), testVertex(6), 9999));
		edgeList.add(new Edge(testVertex(4), testVertex(7), 9999));
		edgeList.add(new Edge(testVertex(4), testVertex(8), 5));
	
		edgeList.add(new Edge(testVertex(5), testVertex(6), 9999));
		edgeList.add(new Edge(testVertex(5), testVertex(7), 6));
		edgeList.add(new Edge(testVertex(5), testVertex(8), 7));
		
		edgeList.add(new Edge(testVertex(6), testVertex(7), 2));
		edgeList.add(new Edge(testVertex(6), testVertex(8), 3));

		edgeList.add(new Edge(testVertex(7), testVertex(8), 4));
		
		ArrayList<Edge> retList = Searches.Kruskal(vertexList, edgeList);
		
		System.out.println("MST Edges are:");
		for(Edge edge : retList) {
			System.out.println(edge);
		}
	}
	
	private static void SSSPtest1() {
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		
		vertexList.add(new Vertex('A', "SSSP"));
		vertexList.add(new Vertex('B', "SSSP"));
		vertexList.add(new Vertex('C', "SSSP"));
		vertexList.add(new Vertex('D', "SSSP"));
		vertexList.add(new Vertex('E', "SSSP"));
		
		edgeList.add(new Edge(testVertex(1), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(2), 3));
		edgeList.add(new Edge(testVertex(1), testVertex(3), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(4), 5));
		edgeList.add(new Edge(testVertex(1), testVertex(5), 9999));

		
		edgeList.add(new Edge(testVertex(2), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(2), testVertex(2), 9999));
		edgeList.add(new Edge(testVertex(2), testVertex(3), 6));
		edgeList.add(new Edge(testVertex(2), testVertex(4), 2));
		edgeList.add(new Edge(testVertex(2), testVertex(5), 9999));
		
		edgeList.add(new Edge(testVertex(3), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(2), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(3), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(4), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(5), 2));
		
		edgeList.add(new Edge(testVertex(4), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(4), testVertex(2), 1));
		edgeList.add(new Edge(testVertex(4), testVertex(3), 4));
		edgeList.add(new Edge(testVertex(4), testVertex(4), 9999));
		edgeList.add(new Edge(testVertex(4), testVertex(5), 6));
	
		edgeList.add(new Edge(testVertex(5), testVertex(1), 2));
		edgeList.add(new Edge(testVertex(5), testVertex(2), 9999));
		edgeList.add(new Edge(testVertex(5), testVertex(3), 7));
		edgeList.add(new Edge(testVertex(5), testVertex(4), 9999));
		edgeList.add(new Edge(testVertex(5), testVertex(5), 9999));
		
		Searches.BellmanFord(vertexList, edgeList);
	}
	
	private static void SSSPtest3() {
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		
		vertexList.add(new Vertex('A', "SSSP"));
		vertexList.add(new Vertex('B', "SSSP"));
		vertexList.add(new Vertex('C', "SSSP"));
		vertexList.add(new Vertex('D', "SSSP"));
		vertexList.add(new Vertex('E', "SSSP"));
		
		edgeList.add(new Edge(testVertex(1), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(2), 10));
		edgeList.add(new Edge(testVertex(1), testVertex(3), 9999));
		edgeList.add(new Edge(testVertex(1), testVertex(4), 5));
		edgeList.add(new Edge(testVertex(1), testVertex(5), 9999));

		
		edgeList.add(new Edge(testVertex(2), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(2), testVertex(2), 9999));
		edgeList.add(new Edge(testVertex(2), testVertex(3), 1));
		edgeList.add(new Edge(testVertex(2), testVertex(4), 2));
		edgeList.add(new Edge(testVertex(2), testVertex(5), 9999));
		
		edgeList.add(new Edge(testVertex(3), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(2), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(3), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(4), 9999));
		edgeList.add(new Edge(testVertex(3), testVertex(5), 4));
		
		edgeList.add(new Edge(testVertex(4), testVertex(1), 9999));
		edgeList.add(new Edge(testVertex(4), testVertex(2), 3));
		edgeList.add(new Edge(testVertex(4), testVertex(3), 7));
		edgeList.add(new Edge(testVertex(4), testVertex(4), 9999));
		edgeList.add(new Edge(testVertex(4), testVertex(5), 2));
	
		edgeList.add(new Edge(testVertex(5), testVertex(1), 7));
		edgeList.add(new Edge(testVertex(5), testVertex(2), 9999));
		edgeList.add(new Edge(testVertex(5), testVertex(3), 6));
		edgeList.add(new Edge(testVertex(5), testVertex(4), 9999));
		edgeList.add(new Edge(testVertex(5), testVertex(5), 9999));
		
		Searches.BellmanFord(vertexList, edgeList);
	}
	
	private static Vertex testVertex(int index) {
		return vertexList.get(index - 1);
	}
}
