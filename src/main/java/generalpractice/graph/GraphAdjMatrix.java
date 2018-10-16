package generalpractice.graph;

import java.util.Arrays;

public class GraphAdjMatrix {
	private final int V;
	private int E;
	private boolean[][] adjMatrix;//this matrix will keep the information of the unweighted graph
	
	
	
	public GraphAdjMatrix(int V) {
		this.V=V;
		this.E=0;
		this.adjMatrix=new boolean[V][V];
	}
	
	public GraphAdjMatrix(int V, int e[][]) {
		this(V);
		for(int[] edge: e) {
		 addEdge(edge[0],edge[1]);	
		}
		//addEdge()
	}
	
	public void addEdge(int v, int w) {
		this.adjMatrix[v][w]=true;
		this.adjMatrix[w][v]=true;
		this.E++;
	}
	
	public int E() {
		return E;
	}
	
	public int V() {
		return V;
	}
	
	public boolean[][] getGraph(){
		return this.adjMatrix;
	}

	public static void main(String args[]) {
		GraphAdjMatrix G=new GraphAdjMatrix(5);
		G.addEdge(0, 1);
		G.addEdge(1, 3);
		G.addEdge(3, 2);
		G.addEdge(4, 3);
		
		System.out.println(Arrays.deepToString(G.getGraph()));
		
	}
}
