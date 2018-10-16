package generalpractice.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

//import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * @link https://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
 * 
 * graph coloring with minimum color is NP hard problem but there is gridy approximate algorithm
 * which colors the graph vertex where the maximum color is d+1 where d is the maximum degree of the graph
 * @author mahbub
 *
 */
public class GraphColoringKcolor {

	private int V;
	private LinkedList<Integer> adj[];
	//constructor
	
	GraphColoringKcolor(int V){
		this.V=V;
		adj=new LinkedList[V];
		for(int i=0; i<V; i++) {
			adj[i]=new LinkedList();
		}
		
		
	}
	void addEdge(int v, int w) {
	  adj[v].add(w);
	  adj[w].add(v);
	}
	
	
	void approximateGreedyColor() {
		int result[] =new int[V];
		Arrays.fill(result, -1);
		//assign first color to first vertex
		result[0]=0;
		
		boolean available[] =new boolean[V];
		
		//initially all colors are available
		Arrays.fill(available, true);
		
		for(int u=1;u<V;u++) 
		{
			
			Iterator<Integer> it = adj[u].iterator();
			
			//we iterate for all adjacent nodes and color with different colors which are still available
			while(it.hasNext()) 
			{
				/**
				 * 
				 */
				int i=it.next();
				//ith neighbor is not colored yet
				if(result[i]!=-1) {
					//make the color unavailable which is neighbor of current node and already colored
					// so, initially 0th node is color and result[0]=0 and others are -1, so, 0th color will be unavailable
					available[result[i]]=false;
				}
			}
			//find first available color, to keep the color minimum
			int color;
			for(color=0; color <V; color++) 
			{
				if(available[color])
					break;
			}
			
			result[u]=color; //assign the found color
			Arrays.fill(available,true);
		}
		
		for(int u=0; u<V; u++) {
			System.out.println("vertex"+ u+ " color ->"+result[u]);
		}
	}
	
	
	public static void main(String args[]) {
		
		
		GraphColoringKcolor g1 = new GraphColoringKcolor(5); 
        g1.addEdge(0, 1); 
        g1.addEdge(0, 2); 
        g1.addEdge(1, 2); 
        g1.addEdge(1, 3); 
        g1.addEdge(2, 3); 
        g1.addEdge(3, 4); 
        System.out.println("Coloring of graph 1"); 
        g1.approximateGreedyColor(); 
  
        System.out.println(); 
        GraphColoringKcolor g2 = new GraphColoringKcolor(5); 
        g2.addEdge(0, 1); 
        g2.addEdge(0, 2); 
        g2.addEdge(1, 2); 
        g2.addEdge(1, 4); 
        g2.addEdge(2, 4); 
        g2.addEdge(4, 3); 
        System.out.println("Coloring of graph 2 "); 
        g2.approximateGreedyColor(); 
    } 
	
}
