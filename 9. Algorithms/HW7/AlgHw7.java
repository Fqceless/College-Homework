package algHw7;

import java.util.LinkedList;

public class AlgHw7 {
	static Graph testGraph = new Graph();
	public static void main(String[] args) {
		TopoTest2();
	}
	
	private static void BFStest1() {
		//A = 0; B = 1; C = 2; D = 3; E = 4; F = 5; G = 6; H = 7; I = 8; J = 9
		testGraph.GetVertices().add(new Vertex('A'));
		testGraph.GetVertices().add(new Vertex('B'));
		testGraph.GetVertices().add(new Vertex('C'));
		testGraph.GetVertices().add(new Vertex('D'));
		testGraph.GetVertices().add(new Vertex('E'));
		testGraph.GetVertices().add(new Vertex('F'));
		testGraph.GetVertices().add(new Vertex('G'));
		testGraph.GetVertices().add(new Vertex('H'));
		testGraph.GetVertices().add(new Vertex('I'));
		testGraph.GetVertices().add(new Vertex('J'));
		
		testVertex(0).AddAdjacency(testVertex(1));
		testVertex(0).AddAdjacency(testVertex(3));
		testVertex(0).AddAdjacency(testVertex(4));
		
		testVertex(1).AddAdjacency(testVertex(0));
		testVertex(1).AddAdjacency(testVertex(2));
		testVertex(1).AddAdjacency(testVertex(3));
		
		testVertex(2).AddAdjacency(testVertex(1));
		testVertex(2).AddAdjacency(testVertex(4));
		testVertex(2).AddAdjacency(testVertex(5));
		testVertex(2).AddAdjacency(testVertex(9));
		
		testVertex(3).AddAdjacency(testVertex(0));
		testVertex(3).AddAdjacency(testVertex(1));
		testVertex(3).AddAdjacency(testVertex(6));
		
		testVertex(4).AddAdjacency(testVertex(0));
		testVertex(4).AddAdjacency(testVertex(2));
		testVertex(4).AddAdjacency(testVertex(5));
		
		testVertex(5).AddAdjacency(testVertex(2));
		testVertex(5).AddAdjacency(testVertex(4));
		testVertex(5).AddAdjacency(testVertex(6));
		testVertex(5).AddAdjacency(testVertex(7));
		
		testVertex(6).AddAdjacency(testVertex(3));
		testVertex(6).AddAdjacency(testVertex(5));
		testVertex(6).AddAdjacency(testVertex(7));
		
		testVertex(7).AddAdjacency(testVertex(5));
		testVertex(7).AddAdjacency(testVertex(6));
		testVertex(7).AddAdjacency(testVertex(8));
		
		testVertex(8).AddAdjacency(testVertex(7));
		testVertex(8).AddAdjacency(testVertex(9));
		
		testVertex(9).AddAdjacency(testVertex(2));
		testVertex(9).AddAdjacency(testVertex(8));
		
		Graph retGraph = Searches.BreadthFirstSearch(testGraph, testVertex(0));
		
		for(Vertex vertex : retGraph.GetVertices()) {
			Graph.BFSPrintPath(testVertex(0), vertex);
		}
	}
	
	private static void BFStest3() {
		//A = 0; B = 1; C = 2; D = 3; E = 4; F = 5; G = 6; H = 7
		testGraph.GetVertices().add(new Vertex('A'));
		testGraph.GetVertices().add(new Vertex('B'));
		testGraph.GetVertices().add(new Vertex('C'));
		testGraph.GetVertices().add(new Vertex('D'));
		testGraph.GetVertices().add(new Vertex('E'));
		testGraph.GetVertices().add(new Vertex('F'));
		testGraph.GetVertices().add(new Vertex('G'));
		testGraph.GetVertices().add(new Vertex('H'));
				
		testVertex(0).AddAdjacency(testVertex(1));
		testVertex(0).AddAdjacency(testVertex(2));
		testVertex(0).AddAdjacency(testVertex(5));
		
		testVertex(1).AddAdjacency(testVertex(0));
		testVertex(1).AddAdjacency(testVertex(2));
		
		testVertex(2).AddAdjacency(testVertex(0));
		testVertex(2).AddAdjacency(testVertex(1));
		testVertex(2).AddAdjacency(testVertex(7));
		
		testVertex(3).AddAdjacency(testVertex(4));
		testVertex(3).AddAdjacency(testVertex(6));
		testVertex(3).AddAdjacency(testVertex(7));
		
		testVertex(4).AddAdjacency(testVertex(3));
		testVertex(4).AddAdjacency(testVertex(5));
		
		testVertex(5).AddAdjacency(testVertex(0));
		testVertex(5).AddAdjacency(testVertex(4));
		testVertex(5).AddAdjacency(testVertex(6));
		
		testVertex(6).AddAdjacency(testVertex(3));
		testVertex(6).AddAdjacency(testVertex(5));
		testVertex(6).AddAdjacency(testVertex(7));
		
		testVertex(7).AddAdjacency(testVertex(2));
		testVertex(7).AddAdjacency(testVertex(3));
		testVertex(7).AddAdjacency(testVertex(6));
		
		Graph retGraph = Searches.BreadthFirstSearch(testGraph, testVertex(0));
		
		for(Vertex vertex : retGraph.GetVertices()) {
			Graph.BFSPrintPath(testVertex(0), vertex);
		}
	}
	
	private static void DFStest1(){
		//u = 0; v = 1; w = 2; x = 3; y = 4; z = 5
		testGraph.GetVertices().add(new Vertex('u'));
		testGraph.GetVertices().add(new Vertex('v'));
		testGraph.GetVertices().add(new Vertex('w'));
		testGraph.GetVertices().add(new Vertex('x'));
		testGraph.GetVertices().add(new Vertex('y'));
		testGraph.GetVertices().add(new Vertex('z'));
				
		testVertex(0).AddAdjacency(testVertex(1));
		testVertex(0).AddAdjacency(testVertex(3));
		
		testVertex(1).AddAdjacency(testVertex(4));
		
		testVertex(2).AddAdjacency(testVertex(4));
		testVertex(2).AddAdjacency(testVertex(5));
		
		testVertex(3).AddAdjacency(testVertex(1));
		
		testVertex(4).AddAdjacency(testVertex(3));
		
		testVertex(5).AddAdjacency(testVertex(5));
		
		Graph retGraph = Searches.DepthFirstSearch(testGraph);
		
		for(Vertex vertex : retGraph.GetVertices()) {
			Graph.DFSPrintPath(vertex);
		}
	}
	
	private static void DFStest2(){
		//s = 0; t = 1; u = 2; v = 3; w = 4; x = 5; y = 6; z = 7
		testGraph.GetVertices().add(new Vertex('s'));
		testGraph.GetVertices().add(new Vertex('t'));
		testGraph.GetVertices().add(new Vertex('u'));
		testGraph.GetVertices().add(new Vertex('v'));
		testGraph.GetVertices().add(new Vertex('w'));
		testGraph.GetVertices().add(new Vertex('x'));
		testGraph.GetVertices().add(new Vertex('y'));
		testGraph.GetVertices().add(new Vertex('z'));
				
		testVertex(0).AddAdjacency(testVertex(4));
		testVertex(0).AddAdjacency(testVertex(7));
		
		testVertex(1).AddAdjacency(testVertex(2));
		testVertex(1).AddAdjacency(testVertex(3));
		
		testVertex(2).AddAdjacency(testVertex(1));
		testVertex(2).AddAdjacency(testVertex(3));
		
		testVertex(3).AddAdjacency(testVertex(0));
		testVertex(3).AddAdjacency(testVertex(4));
		
		testVertex(4).AddAdjacency(testVertex(5));
		
		testVertex(5).AddAdjacency(testVertex(7));
		
		testVertex(6).AddAdjacency(testVertex(5));
		
		testVertex(7).AddAdjacency(testVertex(4));
		testVertex(7).AddAdjacency(testVertex(6));
		
		Graph retGraph = Searches.DepthFirstSearch(testGraph);
		
		for(Vertex vertex : retGraph.GetVertices()) {
			Graph.DFSPrintPath(vertex);
		}
	}
	
	private static void TopoTest1() {
		//b = 0; h = 1; j = 2; o = 3; p = 4; s = 5; t = 6; u = 7; w = 8
		testGraph.GetVertices().add(new Vertex('b'));
		testGraph.GetVertices().add(new Vertex('h'));
		testGraph.GetVertices().add(new Vertex('j'));
		testGraph.GetVertices().add(new Vertex('o'));
		testGraph.GetVertices().add(new Vertex('p'));
		testGraph.GetVertices().add(new Vertex('s'));
		testGraph.GetVertices().add(new Vertex('t'));
		testGraph.GetVertices().add(new Vertex('u'));
		testGraph.GetVertices().add(new Vertex('w'));
		
		testVertex(0).AddAdjacency(testVertex(2));
		
		//testVertex(1)
		
		//testVertex(2)
		
		testVertex(3).AddAdjacency(testVertex(5));
		
		testVertex(4).AddAdjacency(testVertex(0));
		testVertex(4).AddAdjacency(testVertex(1));
		
		testVertex(5).AddAdjacency(testVertex(0));
		testVertex(5).AddAdjacency(testVertex(6));
		
		testVertex(6).AddAdjacency(testVertex(2));
		
		testVertex(7).AddAdjacency(testVertex(1));
		testVertex(7).AddAdjacency(testVertex(4));
		
		//testVertex(8)
		
		LinkedList<Vertex> retList = Searches.TopologicalSort(testGraph);
		
		Graph.TopoPrintPath(retList);
	}
	
	private static void TopoTest2() {
		//S = 0; W = 1; U = 2; P = 3; T = 4; B = 5; h = 6; J = 7; o = 8
		testGraph.GetVertices().add(new Vertex('S'));
		testGraph.GetVertices().add(new Vertex('W'));
		testGraph.GetVertices().add(new Vertex('U'));
		testGraph.GetVertices().add(new Vertex('P'));
		testGraph.GetVertices().add(new Vertex('T'));
		testGraph.GetVertices().add(new Vertex('B'));
		testGraph.GetVertices().add(new Vertex('h'));
		testGraph.GetVertices().add(new Vertex('J'));
		testGraph.GetVertices().add(new Vertex('o'));
		
		testVertex(0).AddAdjacency(testVertex(4));
		testVertex(0).AddAdjacency(testVertex(5));
		
		//testVertex(1)
		
		testVertex(2).AddAdjacency(testVertex(3));
		testVertex(2).AddAdjacency(testVertex(6));
		
		testVertex(3).AddAdjacency(testVertex(5));
		testVertex(3).AddAdjacency(testVertex(6));
		
		testVertex(4).AddAdjacency(testVertex(7));
		
		testVertex(5).AddAdjacency(testVertex(7));
		
		//testVertex(6)
		
		//testVertex(7)
		
		testVertex(8).AddAdjacency(testVertex(6));
		
		LinkedList<Vertex> retList = Searches.TopologicalSort(testGraph);
		
		Graph.TopoPrintPath(retList);
	}
	
	private static Vertex testVertex(int index) {
		return testGraph.GetVertices().get(index);
	}
}
