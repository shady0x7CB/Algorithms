package eg.edu.alexu.csd.filestructure.graphs;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphEngine implements IGraph{
	
	private WeightedGraph graph;
	private String[][] adjMatrix;	
	private int versize, edgeSize = 0;
	private ArrayList<Vertex> vertecies = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Integer> order;
	private Scanner read;
	
	@Override
	public void readGraph(File file) {
		int counter = 0;
		try {
			read = new Scanner(file);
			String lines = "";
			counter = 0;
			while(read.hasNextLine()){
				lines = read.nextLine();
				if(counter == 0 && lines.matches("\\s*\\d+\\s*(\\W|\\s+)\\s*\\d+\\s*")){//in the first line #  #
					String[] split = lines.split("\\s*(\\W|\\s+)\\s*");
					versize = Integer.parseInt(split[0]);
					edgeSize = Integer.parseInt(split[1]);
					adjMatrix = new String[versize][versize];
					for(int i = 0 ; i < versize ; i++){
						Object o = i;
						vertecies.add(new Vertex(o.toString()));
					}
					
				}
				if(counter > 0 && lines.matches("\\s*\\d+\\s*(\\W|\\s+)\\s*\\d+\\s*(\\W|\\s+)\\s*\\d+\\s*")){//in any line except first #  #  #
					String[] split = lines.split("\\s*(\\s+)\\s*");
					adjMatrix[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = split[2];
					edges.add(new Edge(vertecies.get(Integer.parseInt(split[0])),
							vertecies.get(Integer.parseInt(split[1])), Integer.parseInt(split[2])));
				}
				if(counter > 0 && 
						lines.matches("\\s*\\d+\\s*(\\W|\\s+)\\s*\\d+\\s*")){//if #  # was found in sec or third or.. line  error 
					throw new RuntimeException();
				}
				if(counter == 0 &&
						lines.matches("\\s*\\d+\\s*(\\W|\\s+)\\s*\\d+\\s*(\\W|\\s+)\\s*\\d+\\s*")){//if # # # was found in the first line error occured
					throw new RuntimeException();
				}
				counter++;
			}
			read.close();
			this.graph = new WeightedGraph(vertecies, edges, this.adjMatrix);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		if(counter - 1 != edgeSize){
			throw new RuntimeException();
		}
		
	}

	@Override
	public int size() {
		return this.edgeSize;
	}

	@Override
	public ArrayList<Integer> getVertices() {
		return this.graph.getIntVer();
	}
	
	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		return this.vertecies.get(v).getIntAdjVer();
	}
	
	private Vertex extractMin(LinkedList<Vertex> vertecies){
		int min = vertecies.get(0).getDistance();
		int index = 0;
		for(int i = 0 ; i < vertecies.size() ; i++){
			if(vertecies.get(i).getDistance() < min){
				min = vertecies.get(i).getDistance();
				index = i;
			}
		}
		Vertex v = vertecies.get(index);
		vertecies.remove(index);
		return v;
	}
	@Override
	public void runDijkstra(int src, int[] distances) {
		LinkedList<Vertex> vert = new LinkedList<Vertex>();
		order = new ArrayList<Integer>();
		try{
			for(Vertex v : this.vertecies){
				v.setDistance(Integer.MAX_VALUE / 2);
				v.setNext(null);
			}
			this.vertecies.get(src).setDistance(0);
			for(Vertex v : this.vertecies){
				vert.add(v);
			}
		}catch(Exception e){
			throw new RuntimeException();
		}
		while(!vert.isEmpty()){
			Vertex u = extractMin(vert);
			if(u.getDistance() != Integer.MAX_VALUE / 2){
				order.add(Integer.parseInt(u.getId()));
			}
			for(Vertex v : u.getAdjVer()){
				if(v.getDistance() > u.getDistance() + 
						Integer.parseInt(adjMatrix[Integer.parseInt(u.getId())][Integer.parseInt(v.getId())])){
					v.setDistance(u.getDistance() + 
							Integer.parseInt(adjMatrix[Integer.parseInt(u.getId())][Integer.parseInt(v.getId())]));
					v.setNext(u);
				}
			}
		}
		for(int i = 0 ; i < vertecies.size() ; i++){
			distances[i] = vertecies.get(i).getDistance();
		}
	}
	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {		
		return this.order;
	}
	@Override
	public boolean runBellmanFord(int src, int[] distances) {

		//try{
			for(Vertex ver : this.graph.getVertexes()){
				ver.setDistance(Integer.MAX_VALUE / 2);
				ver.setNext(null);
			}
			this.graph.getVertexes().get(src).setDistance(0);
		/*}catch(Exception e){
			throw new RuntimeException(); 
		}*/ 
		for(int i = 1 ; i < this.graph.getVertexes().size()-1 ; i++){
			for(Edge e : this.graph.getEdges()){
				Vertex u = this.vertecies.get(Integer.parseInt(e.getSource().getId()));
				Vertex v = this.vertecies.get(Integer.parseInt(e.getDestination().getId()));
				if(v.getDistance() > u.getDistance()
						+ Integer.parseInt(adjMatrix[Integer.parseInt(u.getId())]
								[Integer.parseInt(v.getId())])){
					v.setDistance(u.getDistance() + 
							Integer.parseInt(adjMatrix[Integer.parseInt(u.getId())]
									[Integer.parseInt(v.getId())]));
					v.setNext(u);
				}
			} 
			
		}
		int k = 0;
		for(Vertex v : this.vertecies){
			distances[k++] = v.getDistance();
		}
		for(Edge e : this.graph.getEdges()){
			Vertex u = this.vertecies.get(Integer.parseInt(e.getSource().getId()));
			Vertex v = this.vertecies.get(Integer.parseInt(e.getDestination().getId()));
			if(v.getDistance() > u.getDistance()
					+ Integer.parseInt(adjMatrix[Integer.parseInt(u.getId())]
							[Integer.parseInt(v.getId())])){
				return false;
			}
		}
		return true;
	}
	public WeightedGraph getGraph() {
		return graph;
	}

}
