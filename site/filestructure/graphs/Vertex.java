package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;

public class Vertex {

	private ArrayList<Integer> intAdjVer;
	private ArrayList<Vertex> adjVer;
	private String id;
	private int distance;
	private Vertex next;

	public Vertex(String id) {
		this.id = id;
		intAdjVer = new ArrayList<Integer>();
		adjVer = new ArrayList<Vertex>();

	}

	public void addAdj(Vertex v) {
		this.intAdjVer.add(Integer.parseInt(v.getId()));
		this.adjVer.add(v);
	}
	
	public ArrayList<Integer> getIntAdjVer(){
		return this.intAdjVer;
	}
	public ArrayList<Vertex> getAdjVer(){
		return this.adjVer;
	}

	public String getId() {
		return id;
	}  

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex getNext() {
		return next;
	}

	public void setNext(Vertex next) {
		this.next = next;
	}

	public boolean hasNext() {
		if (this.next == null) {
			return false;
		}
		return true;
	}
	

}
