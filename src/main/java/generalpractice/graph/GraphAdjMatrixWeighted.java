package generalpractice.graph;

import java.util.Arrays;

public class GraphAdjMatrixWeighted {
	private final int V;
	private int E;
	private int[][] adjMatrix;//this matrix will keep the information of the unweighted graph
	
	
	
	public GraphAdjMatrixWeighted(int V) {
		this.V=V;
		this.E=0;
		this.adjMatrix=new int[V][V];
	}
	/**
	 * the edge will contains [source, dest, weight]
	 * @param V
	 * @param e
	 */
	public GraphAdjMatrixWeighted(int V, int e[][]) {
		this(V);
		for(int[] edge: e) {
		 addEdge(edge[0],edge[1], edge[2]);	
		}
		//addEdge()
	}
	
	public void addEdge(int v, int w, int weight) {
		this.adjMatrix[v][w]=weight;
		this.adjMatrix[w][v]=weight;
		this.E++;
	}
	
	public int E() {
		return E;
	}
	
	public int V() {
		return V;
	}
	
	public int[][] getGraph(){
		return this.adjMatrix;
	}

	public static void main(String args[]) {
		GraphAdjMatrixWeighted G=new GraphAdjMatrixWeighted(5);
		G.addEdge(0, 1,3);
		G.addEdge(1, 3,5);
		G.addEdge(3, 2,100);
		G.addEdge(4, 3,200);
		
		System.out.println(Arrays.deepToString(G.getGraph()));
		
	}
}
