package generalpractice.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 * See the explanation of topsort on 19.8 on EPI or page 613 of coremen book
 * @author mahbub
 *
 */
public class TopologicalSort {

	/**
	 * This is another representation of adjacent list, where we can create a 
	 * vertex with a array list of vertex. This is pretty much same as linked list, but 
	 * instead of next, it will have multiple nodes. That's why, there will be a list of vertex
	 * and each vertex will have list of vertex and also have different properties.
	 * @author mahbub
	 *
	 */
	public static class GraphVertex{
		//private int V;
		//private int E;
		//private int adjList;
		final public String label;
		public static int distance;
		public boolean visited=false;
		public List<GraphVertex> edges;
		
		public GraphVertex(String label) {
			this.label=label;
			edges=new ArrayList<>();
			this.distance=1;
		}
		
		
		public void setDistance(int d) {
			this.distance=d;
		}
		
		public void addEdge(GraphVertex v) {
			edges.add(v);
		}
	}
	
	public static void printGraph(List<GraphVertex> g) {
		
		for(int v=0;v<g.size(); v++) {
			System.out.print(g.get(v).label+" ->");
			for(GraphVertex node: g.get(v).edges) {
				System.out.print(" "+node.label);
			}
			System.out.println();
		}
	}
	
	public static int findTopologicalLongestPath(List<GraphVertex> G) {
		
		Deque<GraphVertex> topsort=buildTopoGraph(G);
		return findLargestPath(topsort);
		//return topsort;
		
	}
	
    public static Deque<GraphVertex> getTopSort(List<GraphVertex> G) {
		
		Deque<GraphVertex> topsort=buildTopoGraph(G);
		return topsort;
		
	}

	private static Deque<GraphVertex> buildTopoGraph(List<GraphVertex> G){
		Deque<GraphVertex> topSort=new LinkedList<>();
		for(GraphVertex v: G) {
			if(!v.visited) {
				DFS(v, topSort);
			}
		}
		return topSort;
	}
	
	
	public static void DFS(GraphVertex cur, Deque<GraphVertex> topSort) {
		cur.visited=true;
		for(GraphVertex next: cur.edges) {
			if(!next.visited) {
				DFS(next, topSort);
			}
		}
		
		topSort.addFirst(cur);
	}
	
	public static int findLargestPath(Deque<GraphVertex> orderVertices) {
		int maxDistance=0;
		while(!orderVertices.isEmpty()) {
			GraphVertex u=orderVertices.poll();
			maxDistance=Math.max(maxDistance, u.distance);
			for(GraphVertex w: u.edges) {
				w.distance=Math.max(maxDistance, u.distance+1);
			}
		}
		return maxDistance;
	}
	
	public static void main(String args[]) {
		
		List<GraphVertex> G=new ArrayList<>();
		GraphVertex v1=new GraphVertex("A");
		
		
		GraphVertex v2=new GraphVertex("B");
		GraphVertex v3=new GraphVertex("C");
		GraphVertex v4=new GraphVertex("D");
		GraphVertex v5=new GraphVertex("E");
		GraphVertex v6=new GraphVertex("F");
		GraphVertex v7=new GraphVertex("G");
		GraphVertex v8=new GraphVertex("H");
		GraphVertex v9=new GraphVertex("I");
		
		
		v1.addEdge(v2);
		v2.addEdge(v3);
		v3.addEdge(v4);
		v5.addEdge(v6);
		v6.addEdge(v4);
		v5.addEdge(v3);
		
		v1.addEdge(v8);
		v2.addEdge(v8);
		v7.addEdge(v8);
		
		
		G.add(v1);
		G.add(v2);
		G.add(v3);
		G.add(v4);
		G.add(v5);
		G.add(v6);
		G.add(v7);
		G.add(v8);
		G.add(v9);
		
		//printGraph(G);
		
		System.out.println(findTopologicalLongestPath(G));
		//run another algorithm, so at least reset visited
		for(GraphVertex v: G) {
			v.visited=false;
		}
		Deque<GraphVertex> tops=getTopSort(G);
		while(!tops.isEmpty()) {
			GraphVertex g=tops.poll();
			System.out.print(g.label+"->");
			for(GraphVertex v:g.edges)
			{
				System.out.print(v.label);
			}
			System.out.println();
		}
		//G.edges.add(new GraphVertex());
	}
}
