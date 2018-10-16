package generalpractice.graph;

import java.util.ArrayList;

public class GraphAdjListWeighted {
	
	class EdgeWeighted{
		int source;
		int destination;
		int weight;
		
		public EdgeWeighted(int s, int d, int w) {
			this.source=s;
			this.destination=d;
			this.weight=w;
		}
		
		public int from() {
			return source;
		}
		
		public int to() {
			return destination;
		}
		
		public int getWeight() {
			return weight;
		}
	}

	private final int V; //number of vertex in the graph and its predefined
	private int E; //number of edge, may not needed now for interview problems
	private ArrayList<ArrayList<EdgeWeighted>> adj;

	public GraphAdjListWeighted(int V) {
		this.V=V;
		this.E=0;
		adj=new ArrayList<ArrayList<EdgeWeighted>>();
		
		for(int v=0;v<V;v++) {
			adj.add(new ArrayList<EdgeWeighted>());
			//adj.set(v, new ArrayList<Integer>());
		}
	}

	/**
	 * undirected graph add edge will actually add u to w's vertex list and w to u's vertex list
	 * 
	 * @param u
	 * @param w
	 */
	public void addEdge(int u,int w, int weight) {
		adj.get(u).add(new EdgeWeighted(u,w,weight));
		adj.get(w).add(new EdgeWeighted(w,u,weight));
		this.E++;
	}

	public Iterable<EdgeWeighted> adj(int v){
		return adj.get(v);
	}

	public int V() {
		return this.V;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(V+"vertices:"+E +" Edges");
		sb.append("\n");
		
		for(int v=0;v<V; v++) {
			sb.append(v + " ->");
			for(EdgeWeighted w:adj.get(v)) {
				sb.append(w.destination+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		GraphAdjListWeighted g=new GraphAdjListWeighted(5);
		g.addEdge(1, 2,3);
		g.addEdge(2, 3,5);
		g.addEdge(3, 4,78);
		g.addEdge(4,1,90);
		g.addEdge(0, 1,76);
		System.out.println(g.toString());
	}
}