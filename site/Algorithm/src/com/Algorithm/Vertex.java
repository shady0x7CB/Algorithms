package com.Algorithm;

import java.util.LinkedList;

public class Vertex {

	private String name;
	private LinkedList<Vertex> adjList;
	
	public Vertex(String name){
		this.name = name;
		this.adjList = new LinkedList<Vertex>();
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<Vertex> getAdjList() {
		return adjList;
	}
	public void setAdjList(LinkedList<Vertex> adjList) {
		this.adjList = adjList;
	}
	public void printAdj(){
		System.out.print("[");
		for(int i = 0 ; i < this.adjList.size() ;i++){
			System.out.print(this.adjList.get(i).getName());
			if(i!= this.adjList.size()-1){
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	
	
}
