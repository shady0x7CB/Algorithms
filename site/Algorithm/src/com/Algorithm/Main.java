package com.Algorithm;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class Main {
	private static LinkedList<String> analyze(String s){
		LinkedList<String> list = new LinkedList<String>();
		String g = "";
		for(int i = 0 ; i < s.length() ; i++){
			
			if(s.charAt(i) != '\t'){
				g += s.charAt(i);
			}else{
				
				list.add(g);
				g = "";
			}
			
			
		}
		return list;
	}
	public static void main(String[] args) throws CloneNotSupportedException {

		
		/*GraphTest*/
				
//		g.removeEdge(e34);
//		g.mergeVertecies(e34.getOne(), e34.getTwo());
		//System.out.println("***********************************************************************************\n\n\n\n\n");
		//g.printVE();
//		LinkedList<Integer> k = new LinkedList<Integer>();
//		for(int j = 1 ; j <= 100 ; j++){
//			
//
//			Graph g = new Graph();
//			for(int i = 1 ; i <= 200 ; i++){
//				Object o = i;
//				String s = o.toString();
//				g.addVertex(new Vertex(s));
//			}
//			try {
//			
//				@SuppressWarnings("resource")
//				Scanner sc = new Scanner(new FileReader("C:/Users/shady/Desktop/s.txt"));
//				LinkedList<String> done = new LinkedList<String>();
//				while(sc.hasNext()){
//					String i = sc.nextLine();
//					LinkedList<String> l = analyze(i);
//					String v1 = l.removeFirst();
//					//System.out.println(l);
//					
//					done.add(v1);
//					//System.out.println(done);
//					while(l.size() > 0){
//						int counter = 0;
//						String v2 = l.removeFirst();
//						
//						//System.out.println(v2 + " " + done.contains(v2));
//						if(!done.contains(v2)){
//							g.addEdge(new Edge(g.getVertecies().get(Integer.parseInt(v1)-1),
//									g.getVertecies().get(Integer.parseInt(v2)-1)));
//						}else{
//							//System.out.println("sssssssssss");
//							counter++;
//						}
//						if(counter > 1){
//							counter--;
//							//System.out.println("DDdddddddddddd");
//							g.addEdge(new Edge(g.getVertecies().get(Integer.parseInt(v1)-1),
//									g.getVertecies().get(Integer.parseInt(v2)-1)));
//						}
//					}
//				}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			k.add(g.randomContraction().get(0).getAdjList().size());
//		}
//		System.out.println(k);
		//System.out.println(g.randomContraction().get(0).getAdjList().size());
//		g.printVE();
//		g.printAllAdj();


		/*DSelect Test*/
//		int [] arr = {2, 1, 3, 0, 9, 5, 7, 8, 6, 10, 23, 11, 17, 13, 15, 16, 20, 22, 33, 55, 66};
//		
//		DeterministicSelection d = new DeterministicSelection(arr);
//		System.out.println(d.select(arr, 0, arr.length-1, 13));
		
		/*Heap Test*/
		
		/*int[]a = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
		Heap h = new Heap(a);
		//h.maxHeapify(1);
		//h.buildMaxHeap();
		h.heapSort();
		for(int i = 0 ; i < a.length ; i++){
			System.out.println(a[i]);
		}
		
		*/
		
		
		/*RSelection Test*/
//		int[] arr = {10, 5, 6, 8, 4, 2};
//		RandomizedSelectiob r = new RandomizedSelectiob(arr);
//		System.out.println(r.randomizedSelect(0, arr.length-1, 4));
		
		
		/*quick sort*/
		
//		int[] arr = new int[10000];
//		int k = 0;
//		try {
//			
//			@SuppressWarnings("resource")
//			Scanner sc = new Scanner(new FileReader("C:/Users/shady/Desktop/s.txt"));
//			while(sc.hasNext()){
//				arr[k++] = Integer.parseInt(sc.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int[] arr = {5, 4, 3, 2, 1, 0};
//		QuickSort q =  new QuickSort(arr);
//		q.quickSort(0, arr.length-1);
//		for(int i = 0 ; i < arr.length ; i++){
//			System.out.println(arr[i]);
//		}
//		System.out.println(q.getCounter());
		
		
		
		
		/*inversion test*/
//		int[]a = new int[100000];
//		int k = 0;
//		try {
//			Scanner sc = new Scanner(new FileReader("C:/Users/shady/Desktop/s.txt"));
//			while(sc.hasNext()){
//				a[k++] = sc.nextInt();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//		Inversion in = new Inversion(a);
//		in.ineversionCounter(0, a.length-1);
//		System.out.println(in.getCounter());
		
		
		
		/*closest pair test*/
		Point[] point = new Point[11];
		point[0] = new Point(4, 4);
		point[1] = new Point(-2, -2);
		point[2] = new Point(-3, -4);
		point[3] = new Point(-1, 3);
		point[4] = new Point(2, 3);
		point[5] = new Point(-4, 0);
		point[6] = new Point(1, 1);
		point[7] = new Point(-1, -1);
		point[8] = new Point(3, -1);
		point[9] = new Point(-4, 2);
		point[10] = new Point(-2, 4);
		
		for(int i = 0 ; i < point.length ; i++){
			System.out.println(point[i]);
		}
		System.out.println("--------------------------------------------------");
		Point[] px = new Point[point.length];
		Point[] py = new Point[point.length];
		ClothestPair c = new ClothestPair(point);
		c.mergeSort(0, point.length-1, 'x');
		for(int i = 0 ; i < point.length ; i++){
			px[i] = point[i];
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Merged in x");
		for(int i = 0 ; i < point.length ; i++){
			System.out.println(px[i]);
		}
		c.mergeSort(0, point.length-1, 'y');
		for(int i = 0 ; i < point.length ; i++){
			py[i] = point[i];
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Merged in y");
		for(int i = 0 ; i < point.length ; i++){
			System.out.println(py[i]);
		}
		System.out.println("--------------------------------------------------");
		Point[] best = c.clothestPair(px, py);
		for(int i = 0 ; i < best.length ; i++){
			System.out.println(best[i]);
		}
		System.out.println(c.d(best));
	}


}
