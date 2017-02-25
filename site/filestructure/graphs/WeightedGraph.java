package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;

public class WeightedGraph {
	private ArrayList<Vertex> vertexes;
	private ArrayList<Edge> edges;
	private String[][] adjacencyMat;

	public WeightedGraph(ArrayList<Vertex> vertexes, ArrayList<Edge> edges, String[][] adjacencyMat) {
		this.vertexes = vertexes;
		this.edges = edges;
		this.adjacencyMat = adjacencyMat;
	}

	public String[][] getAdjacencyMat() {
		return adjacencyMat;
	} 
 
	public ArrayList<Vertex> getVertexes() {
		return vertexes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public ArrayList<Integer> getIntVer() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < this.vertexes.size(); i++) {
			list.add(Integer.parseInt(this.vertexes.get(i).getId()));
		}
		return list;
	}

}
