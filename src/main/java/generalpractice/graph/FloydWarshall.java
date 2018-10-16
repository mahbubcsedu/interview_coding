package generalpractice.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Floyd-warshall algorithm is the dynamic algorithm to find all source shortest path
 * This requires adjacency matrix so, we will use just the 2d matrix as graph
 * will have no add or remove edge or vertex
 * @author mahbub
 *
 */
public class FloydWarshall {

	final static int INF=10000;
	int V;
	
	void floydWarshalSP(int[][] g) {
		this.V=g.length;
		int dist[][] = new int[V][V];
		
		int k;
		
		//initialize the solutions matrix sam as input graph matrix which is considered as no 
		//intermediate vertices
		
		for(int i=0; i<V; i++) {
			for(int j=0;j<V;j++) {
				dist[i][j]=g[i][j];
			}
		}
		
		//now for each k intermediate vertex run the relax
		
		for(k=0; k<V; k++) {
			for(int i=0; i<V; i++)
				for(int j=0;j<V;j++) {
					if(dist[i][k]+dist[k][j] < dist[i][j])
						dist[i][j]=dist[i][k]+dist[k][j];
				}
		}
		
		printSolutions(dist);
		
	}
	
	static void printSolutions(int[][] s) {
		for(int i=0;i<s.length;i++) {
			for(int j=0;j<s.length;j++) {
				if(s[i][j]==INF)
					System.out.print(" INF ");
				else
					System.out.print(s[i][j]+"  ");
				
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		
		int[][] g = {{0,5,INF,10},{INF,0,3,INF}
		,{INF,INF,0,1},{INF,INF,INF,0}};
		
		FloydWarshall a=new FloydWarshall();
		a.floydWarshalSP(g);
	}
}
