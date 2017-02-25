package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;



public class Main {

	public static void main(String[] args){
		
		
		GraphEngine g = new GraphEngine();
		File file = new File(System.getProperty("user.dir") + File.separator + "shady.txt");
		g.readGraph(file);
//		int[] distances = new int[g.getVertices().size()];
//		System.out.println(g.getGraph().getAdjacencyMat()[0][4]);
		//g.runDijkstra(0, distances);
//		for(int i = 0 ; i < g.getDijkstraProcessedOrder().size() ; i++){
//			System.out.println(g.getDijkstraProcessedOrder().get(i));
//		}
//		//System.out.println(g.runBellmanFord(0, distances));
//		for(int i = 0 ; i < distances.length ; i++){
//			System.out.println(distances[i]);
//		}
//		for(int i = 0 ; i < g.getDijkstraProcessedOrder().size() ; i++){
//			System.out.print(g.getDijkstraProcessedOrder().get(i) + ", ");
//		}
		
//		MyGraph g = new MyGraph();
//		File file = new File(System.getProperty("user.dir") + File.separator + "shady.txt");
//		g.readGraph(file);
//		int[] distances = new int[g.getVertices().size()];
//		g.runDijkstra(1, distances);
//		for(int i = 0 ; i < distances.length ; i++){
//			System.out.println(distances[i] + "pole");
//		}
//		
	}
}

/*1073741823
0
1
4
6
7
7
10
12*/