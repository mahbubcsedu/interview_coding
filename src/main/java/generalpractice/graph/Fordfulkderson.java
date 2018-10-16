package generalpractice.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Fordfulkderson {

	//final int V;
	/**
	 * This standard BFS will find out the source to sink bfs node in the residual network
	 * where there is a chance of at least a flow to run
	 * @param residualGraph
	 * @param s
	 * @param t
	 * @param p
	 * @return
	 */
	boolean bFS(List<List<Integer>> residualGraph, int s, int t, List<Integer> p) {
		
		
		List<Boolean> visited=new ArrayList<>(Collections.nCopies(residualGraph.size(),false));
		//Arrays.fill(visited, false);
		Queue<Integer> queue=new LinkedList<>();
		
		queue.add(s);
		visited.set(0, true);
		p.set(s, -1);
		
		//standard BFS loop
		
		while(queue.size()!=0) 
		{
			int u=queue.poll();
			
			for(int v=0; v<residualGraph.size();v++ ) 
			{
				if(visited.get(v)==false && residualGraph.get(u).get(v)>0)
				{
					queue.add(v);
					p.set(v, u);
					visited.set(v, true);
				}
			}
		}
		
		return visited.get(t);
		
	}
	
	
	//fordfulkerson maximum flow network method in karp algorithm
	
	int getMaxFlow(List<List<Integer>> G, int s, int t) 
	{
		
		List<List<Integer>> residualGraph = new ArrayList<>(G.size());
		
		for(List<Integer> row: G) {
			residualGraph.add(row);
		}
		
		/*for(int u=0; u<G.size();u++) {
			for(int v=0; v<G.size();v++) {
				residualGraph.get(u).set(v, G.get(u).get(v));
			}
		}*/
		
		List<Integer> parent=new ArrayList<>(Collections.nCopies(G.size(), -1));
		
		int maxFlow=0;
		
		while(bFS(residualGraph, s, t, parent)) 
		{
			
			int flow=Integer.MAX_VALUE;
			
			for(int v=t; v!=s;v=parent.get(v)) 
			{
				int u=parent.get(v);
				//find min flow from a path
				flow=Math.min(flow, residualGraph.get(u).get(v));
			}
			
			for(int v=t; v!=s; v=parent.get(v)) {
				int u=parent.get(v);
				//substract min flow from the path and add a augmented path wth that flow
				residualGraph.get(u).set(v, residualGraph.get(u).get(v)-flow);//
				residualGraph.get(v).set(u, residualGraph.get(v).get(u)+flow);
			}
			
			maxFlow+=flow;
		}
		
		
		return maxFlow;
	}
	
	
	public static void main(String args[]) {
		// Let us create a graph shown in the above example 
        int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0}, 
                                     {0, 0, 10, 12, 0, 0}, 
                                     {0, 4, 0, 0, 14, 0}, 
                                     {0, 0, 9, 0, 0, 20}, 
                                     {0, 0, 0, 7, 0, 4}, 
                                     {0, 0, 0, 0, 0, 0} 
                                   }; 
        List<List<Integer>> g=new ArrayList<>();
        
        for(int i=0;i<graph.length;i++) {
        	List<Integer> l=new ArrayList<>();
        	for(int j=0; j<graph[0].length;j++) {
        		l.add(new Integer(graph[i][j]));
        	}
        	g.add(l);
        }
        
        Fordfulkderson m = new Fordfulkderson(); 
  
        System.out.println("The maximum possible flow is " + 
                           m.getMaxFlow(g, 0, 5)); 
  
    
	}
	
}
