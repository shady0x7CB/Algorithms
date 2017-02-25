package eg.edu.alexu.csd.filestructure.graphs;

public class Edge  {
	   
	  private Vertex source;
	  private Vertex destination;
	  private int weight; 
	  
	  public Edge(Vertex source, Vertex destination, int weight) {
	    this.source = source;
	    this.destination = destination;
	    this.weight = weight;
	    this.source.addAdj(this.destination);
	  }
	  
	  public Vertex getDestination() {
	    return destination;
	  }

	  public Vertex getSource() {
	    return source;
	  }
	  public int getWeight(Vertex u, Vertex v) {
	    return this.weight;
	  }
	  
	  
}