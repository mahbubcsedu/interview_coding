package ds.graph;

import java.util.ArrayList;

public class Graph {
private final int V; //number of vertex in the graph and its predefined
private int E; //number of edge, may not needed now for interview problems
private ArrayList<ArrayList<Integer>> adj;

public Graph(int V) {
	this.V=V;
	this.E=0;
	adj=new ArrayList<ArrayList<Integer>>();
	
	for(int v=0;v<V;v++) {
		adj.add(new ArrayList<Integer>());
		//adj.set(v, new ArrayList<Integer>());
	}
}

/**
 * undirected graph add edge will actually add u to w's vertex list and w to u's vertex list
 * 
 * @param u
 * @param w
 */
public void addEdge(int u,int w) {
	adj.get(u).add(w);
	adj.get(w).add(u);
	this.E++;
}

public Iterable<Integer> adj(int v){
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
		for(int w:adj.get(v)) {
			sb.append(w+" ");
		}
		sb.append("\n");
	}
	return sb.toString();
}

public static void main(String args[]) {
	Graph g=new Graph(5);
	g.addEdge(1, 2);
	g.addEdge(2, 3);
	g.addEdge(3, 4);
	g.addEdge(4,1);
	g.addEdge(0, 1);
	System.out.println(g.toString());
}
}
