package com.Algorithm;

import java.util.LinkedList;
import java.util.Random;

public class Graph implements Cloneable{

	private LinkedList<Vertex> vertecies;
	private LinkedList<Edge> edges;
	public Graph(){
		this.vertecies = new LinkedList<Vertex>();
		this.edges = new LinkedList<Edge>();
	}
	
	public void addVertex(Vertex v){
		vertecies.add(v);
	}
	public void addEdge(){
		
	}
	public LinkedList<Vertex> getVertecies() {
		return vertecies;
	}

	public void setVertecies(LinkedList<Vertex> vertecies) {
		this.vertecies = vertecies;
	}

	public void addEdge(Edge e){
		edges.add(e);
		e.getOne().getAdjList().add(e.getTwo());
		if(e.getTwo().getName() != e.getOne().getName()){
			e.getTwo().getAdjList().add(e.getOne());
		}
		
	}
	public void removeEdge(Edge e){
		e.getOne().getAdjList().remove(e.getTwo());
		e.getTwo().getAdjList().remove(e.getOne());
		edges.remove(e);
	}
	public void mergeVertecies(Vertex v1, Vertex v2){
		vertecies.remove(v2);
		LinkedList<Edge> e = new LinkedList<Edge>();
		for(int i = 0 ; i < edges.size() ; i++){
			if(edges.get(i).getOne().getName().equals(v2.getName())){
				e.add(edges.get(i));
				addEdge(new Edge(edges.get(i).getTwo(), v1));
				
			}else if(edges.get(i).getTwo().getName().equals(v2.getName())){
				e.add(edges.get(i));
				addEdge(new Edge(edges.get(i).getOne(), v1));
			}
		}
		while(e.size() > 0){
			Edge r = e.getFirst();
			e.removeFirst();
			removeEdge(r);
		}
	}
	public void removeSelfLoops(){
		LinkedList<Edge> e = new LinkedList<Edge>();
		for(int i = 0 ; i < edges.size(); i++){
			if(edges.get(i).getOne().getName().equals(edges.get(i).getTwo().getName())){
				e.add(edges.get(i));
				//System.out.println("hey");
			}
		}
		while(e.size() > 0){
			removeEdge(e.removeFirst());
		}
	}
	public LinkedList<Vertex> randomContraction(){
		Random rand = new Random();
		//printAllAdj();
		while(vertecies.size() > 2){
			int r = rand.nextInt(edges.size());
			Edge e = edges.get(r);
			//System.out.println("(" + e.getOne().getName() + ", " +e.getTwo().getName() + ")");
			removeEdge(e);
			mergeVertecies(e.getOne(), e.getTwo());
			removeSelfLoops();
//			printVE();
//			printAllAdj();
		}
		
		return this.vertecies;
	}
	public void printVE(){
		System.out.println("V are --> ");
		for(int i = 0 ; i < vertecies.size() ; i++){
			System.out.print(vertecies.get(i).getName() + " ");
		}
		System.out.println("");
		System.out.println("E are --> ");
		for(int i = 0 ; i < edges.size() ; i++){
			System.out.print("(" + edges.get(i).getOne().getName() + ", " + edges.get(i).getTwo().getName() + ")");
		}
		System.out.println("");
	}
	public void printAllAdj(){
		for(int i = 0 ; i < vertecies.size() ; i++){
			System.out.println("V of Name " + vertecies.get(i).getName() + " has adj List --> ");
			for(int j = 0 ; j < vertecies.get(i).getAdjList().size() ; j++){
				System.out.print(vertecies.get(i).getAdjList().get(j).getName() + " ");
			}
			System.out.println("");
		}
	}
	public Graph clone()throws CloneNotSupportedException{
		Graph ms;
		try {
			ms = (Graph) super.clone();
			 
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
		}
		return ms;
	}
}
