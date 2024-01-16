package algHw7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Searches {
	public static Graph BreadthFirstSearch(Graph graph, Vertex source) {
		Queue<Vertex> searchQueue = new LinkedList<>();
		source.MakeSource();
		searchQueue.add(source);
		
		while(!searchQueue.isEmpty()) {
			Vertex currVertex = searchQueue.remove();
			for(Vertex adjVertex : graph.GetAdjacencies(currVertex)) {
				if(adjVertex.GetColor() == 'W') {
					adjVertex.SetColor('G');
					adjVertex.SetLevel(currVertex.GetLevel() + 1);
					adjVertex.SetParent(currVertex);
					searchQueue.add(adjVertex);
				}
			}
			currVertex.SetColor('B');
		}
		
		return graph;
	}
	
	public static Graph DepthFirstSearch(Graph graph) {
		int time = 0;
		for(Vertex currVertex : graph.GetVertices()) {
			if(currVertex.GetColor() == 'W') {
				time = DFSVisit(currVertex, time);
			}
		}
		return graph;
	}
	
	private static int DFSVisit(Vertex vertex, int time) {
		time += 1;
		vertex.SetStart(time);
		vertex.SetColor('G');
		for(Vertex adjVertex : vertex.GetAdjacenciesList()) {
			if(adjVertex.GetColor() == 'W') {
				adjVertex.SetParent(vertex);
				time = DFSVisit(adjVertex, time);
			}
		}
		vertex.SetColor('B');
		time += 1;
		vertex.SetFinish(time);
		return time;
	}
	
	public static LinkedList<Vertex> TopologicalSort(Graph graph) {
		DepthFirstSearch(graph);
		LinkedList<Vertex> retList = new LinkedList<>();
		while (!graph.GetVertices().isEmpty()) {
			Vertex maxFinishVertex = new Vertex('L');
			maxFinishVertex.SetFinish(Integer.MIN_VALUE);
			for(Vertex vertex : graph.GetVertices()) {
				if(vertex.GetFinish() > maxFinishVertex.GetFinish()) {
					maxFinishVertex = vertex;
				}
			}
			retList.add(maxFinishVertex);
			graph.GetVertices().remove(maxFinishVertex);
		}
		return retList;
	}
}
