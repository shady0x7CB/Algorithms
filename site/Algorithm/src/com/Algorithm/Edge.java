package com.Algorithm;

public class Edge {

	private Vertex one;
	private Vertex two;
	public Edge(Vertex one, Vertex two){
		this.one = one;
		this.two = two;
	}
	
	
	public Vertex getOne() {
		return one;
	}
	public void setOne(Vertex one) {
		this.one = one;
	}
	public Vertex getTwo() {
		return two;
	}
	public void setTwo(Vertex two) {
		this.two = two;
	}
	
	
}
