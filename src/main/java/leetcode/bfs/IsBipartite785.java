package leetcode.bfs;
import java.util.*;
public class IsBipartite785 
{
	//build graph
	class GraphVertex{
		int distance=-1;
		public List<GraphVertex> neighbors=new ArrayList<>();

	}

	public boolean isBipartite(int[][] graph) {
		List<GraphVertex> G=buildGraph(graph);
		
		for(GraphVertex v: G) {
			if(v.distance==-1) {
				v.distance=0;
				if(!BFS(v)) {
					return false;
				}
			}
		}

		return true;

	}
	
	public boolean BFS(GraphVertex v) {
		Queue<GraphVertex> q=new LinkedList<>();
		q.add(v);
		
		while(!q.isEmpty()) {
			for(GraphVertex t: q.peek().neighbors) {
				if(t.distance==-1)//unvisited
				{
					t.distance=q.peek().distance+1;
					q.add(t);
				}else if(t.distance==q.peek().distance) {
					return false; // because the parent and child are at same distance and cannot be put at same graph
				}
			}
			q.remove();
		}
	 return true;
	}
	
	
    public List<GraphVertex> buildGraph(int[][] g){
    	
    	List<GraphVertex> graph=new ArrayList<>();
    	
    	GraphVertex v;
    	//we will create the graph without adding edges
    	for(int i=0;i<g.length;i++) {
    		v=new GraphVertex();
    		graph.add(v);
    	}
    	//now add edges based on provided graph
    	for(int i=0;i<g.length;i++) {
    		for(int j=0;j<g[i].length;j++) {
    			graph.get(i).neighbors.add(graph.get(g[i][j]));
    		}
    	}
    	
    	return graph;
    	//GraphVertex v=new GraphVertex();
    	
    	
    	
    }
    
    /**
     * Leetcode best solutions by coloring in 2, i will later look at this
     */
    
    public boolean isBipartite2(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == -1 && !colorGraph(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }
    public boolean colorGraph(int[][] graph, int[] colors, int i, int color) {
        if (colors[i] != -1) {
            return colors[i] == color;
        }
        colors[i] = color;
        for (int neighbor : graph[i]) {
            if (!colorGraph(graph, colors, neighbor, 1 - color)) {
                return false;
            }
        }
        return true;
    }
    
    
    
	public void test1() {
		int[][] neighbors= {{1,3}, {0,2}, {1,3}, {0,2}};
		int[][] n2= {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
		IsBipartite785 o=new IsBipartite785();
		System.out.printf("The grpah is bipartite : %b", o.isBipartite(neighbors));
		System.out.printf("The grpah is bipartite : %b", o.isBipartite(n2));
	}
	
	public static void main(String args[]) {
		IsBipartite785 o=new IsBipartite785();
		o.test1();
	}
}
