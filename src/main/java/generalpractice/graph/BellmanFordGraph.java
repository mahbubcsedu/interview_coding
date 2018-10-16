package generalpractice.graph;

/**
 * Single source shortest path which also supports negative weight cycle
 * is explained @link https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 * 
 * @author mahbub
 *
 */
public class BellmanFordGraph {
    //A class to represent a weighted edge in graph
	class Edge{
		int source, dest, weight;
		Edge(){
			source=dest=weight=0;
		}
	  
  }
 
	int V, E;
	Edge edge[];
	
	//Create a graph with V vertices and E edges
	
	BellmanFordGraph(int v, int e){
		this.V=v;
		this.E=e;
		edge=new Edge[e];
		
		for(int i=0; i<e;i++) {
			edge[i]=new Edge();
		}
	}
	
	/**
	 * the main function that finds shortest distance from source to all other vertices using
	 * bellman ford alg. It detects negative weight cycle if any
	 */
	
	
	void bellmanFord(BellmanFordGraph g, int source) {
		int V=g.V;
		int E=g.E;
		
		int dist[]=new int[V];
		
		//firs initialize the distance from source to all vertices as INFININTE
		
		for(int i=0; i<V; i++) 
			dist[i]=Integer.MAX_VALUE;
		dist[source]=0;
		
		//Relax all edges |V-1| times. A simple shortest path from s to any node can have at-most |v|-1
		for(int i=0; i<V; i++) {
			for(int e=0; e<E; e++) {
				int u=g.edge[e].source;
				int v=g.edge[e].dest;
				int w=g.edge[e].weight;
				relax(g,u,v,w,dist);
			}
		}
		
		
		//Step3: check negative-weight cycles. The above step guarantees shortest distances if graph doesenot contains negative weight cycle
		
		for(int e=0; e<E; e++) 
		{
			int u=g.edge[e].source;
			int v=g.edge[e].dest;
			int w=g.edge[e].weight;
			
			if(dist[u]!=Integer.MAX_VALUE && dist[u]+w < dist[v])
				System.out.println("Graph has negative weight cycle");
		}
		
		printAll(dist,V);
		
	}
	
	void printAll(int[]dist, int V) {
		System.out.println("Vertex distance from source");
		for(int i=0; i<V;i++) {
			System.out.println(i+"\\t\t\t"+dist[i]);
		}
	}
	
	void relax(BellmanFordGraph g, int u, int v, int weight, int dist[]) {
		if(dist[u]!=Integer.MAX_VALUE && dist[u]+weight < dist[v]) {
			dist[v]=dist[u]+weight;
		}
	}
	
	
	public static void main(String args[]) {
		
		int V = 5;  // Number of vertices in graph 
        int E = 8;  // Number of edges in graph 
  
        BellmanFordGraph graph = new BellmanFordGraph(V, E); 
  
        // add edge 0-1 (or A-B in above figure) 
        graph.edge[0].source = 0; 
        graph.edge[0].dest = 1; 
        graph.edge[0].weight = -1; 
  
        // add edge 0-2 (or A-C in above figure) 
        graph.edge[1].source = 0; 
        graph.edge[1].dest = 2; 
        graph.edge[1].weight = 4; 
  
        // add edge 1-2 (or B-C in above figure) 
        graph.edge[2].source = 1; 
        graph.edge[2].dest = 2; 
        graph.edge[2].weight = 3; 
  
        // add edge 1-3 (or B-D in above figure) 
        graph.edge[3].source = 1; 
        graph.edge[3].dest = 3; 
        graph.edge[3].weight = 2; 
  
        // add edge 1-4 (or A-E in above figure) 
        graph.edge[4].source = 1; 
        graph.edge[4].dest = 4; 
        graph.edge[4].weight = 2; 
  
        // add edge 3-2 (or D-C in above figure) 
        graph.edge[5].source = 3; 
        graph.edge[5].dest = 2; 
        graph.edge[5].weight = 5; 
  
        // add edge 3-1 (or D-B in above figure) 
        graph.edge[6].source = 3; 
        graph.edge[6].dest = 1; 
        graph.edge[6].weight = 1; 
  
        // add edge 4-3 (or E-D in above figure) 
        graph.edge[7].source = 4; 
        graph.edge[7].dest = 3; 
        graph.edge[7].weight = -3; 
  
        graph.bellmanFord(graph, 0); 
    } 
		
		
		
	//}
	
}
