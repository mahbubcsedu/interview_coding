package generalpractice.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Eulerian path is to visit each Egdge exactly once. If it return to source, then 
 * its a Eulerian cycle
 * @author mahbub
 *{@link https://www.geeksforgeeks.org/eulerian-path-and-circuit/}
 *This is explained clearly here, so, if we want to find out Eulerian Cycle, we have
 *to maintain two conditions, all vertices with non zero degree should be connected and 
 *all vertices should have even degree
 */
public class EulerianPath {

	
		private int V;
		private int E;
		private Map<Integer, ArrayList<Integer>> adj;
		public EulerianPath(int v) {
			this.V=v;
			this.E=0;
			this.adj=new HashMap<>();
			
			for(int i=0;i<v;i++) {
				adj.put(i,new ArrayList<Integer>());
			}
		}
		void addEdge(int u, int v) {
		 adj.get(u).add(v);//add v to array list of that node	
		 adj.get(v).add(u);// this is for undirected graph
		}
		/**
		 * We need to check the connectivity of all the edges which have non zero degree
		 * We have to do DFS from all the nodes and maintain a list of boolean which will define
		 * whether all the nodes are visited during the DFS.
		 * @param v, the node from where we do dfs
		 * @param visited, a list which will keep the information of visiting a node during DFS
		 * 
		 */
		void DFS(int v, boolean[] visited) {
			visited[v]=true;
			
			Iterator<Integer> it=adj.get(v).iterator();
			while(it.hasNext()) {
				int node=it.next();
				if(!visited[node]) {
					DFS(node, visited);
				}
			}
		}
		
		/**
		 * the check that all non zero degree nodes are connected, we can run DFS from all nodes
		 * and check that all nodes are visited which has at least a neighbors
		 */
		boolean isConnected() {
			boolean[] visited=new boolean[this.V];
			Arrays.fill(visited, false);
			
			//start with a non zero degree vertex
			//we will break from the loop as soon as find a non zero degree vertex and start DFS from there
			int vertex=0;
			for(int i=0;i<this.V;i++) {
				vertex=i;
				if(adj.get(i).size()!=0)
					break;
			}
			
			//if there is no non zero degree node, its Eulerian
			if(vertex==this.V)
				return true;
			DFS(vertex,visited);
			
			//Now we will check all non visited non zero degree vertex and if there is any, it is not eulerian
			for(int i=0;i<this.V;i++)
			{
				if(!visited[i] && adj.get(i).size()>0)
					return false;
			}
			return true;
		}
		
		/**
		 * The function returns one fo the following values
		 * 1. 0- if its not Eularian
		 * 2. 1--if Graph has and Euler path
		 * 3. 2--if Graph has Euler Cycle
		 * @return
		 */
		 int whichEulerian() {
			 
			//check for connectivity first
			 if(isConnected()==false)
				 return 0;
			 
			 int oddDegree=0;
			 for(int i=0; i<this.V;i++) {
				 if(adj.get(i).size()%2!=0) {
					 oddDegree++;
				 }
			 }
			 
			 if(oddDegree>2)
				 return 0;
			 return oddDegree==2 ? 1: 2;
		}
		 
		 
		 void smallTest() {
			 int typeOfPath=whichEulerian();
				 
				 if(typeOfPath==0)
					 System.out.println("No Euler path possible");
				 if(typeOfPath==1)
					 System.out.println("Euler path possible");
				 if(typeOfPath==2)
					 System.out.println("Euler cycle possible");
			 
		 }
		 
		 public static void main(String args[]) 
		 {
			 EulerianPath g=new EulerianPath(5);
			 g.addEdge(1, 0);
			 g.addEdge(0, 2);
			 g.addEdge(2, 1);
			 g.addEdge(0, 3);
			 g.addEdge(3, 4);
			 g.smallTest();
			 
			 EulerianPath g2 = new EulerianPath(5); 
		        g2.addEdge(1, 0); 
		        g2.addEdge(0, 2); 
		        g2.addEdge(2, 1); 
		        g2.addEdge(0, 3); 
		        g2.addEdge(3, 4); 
		        g2.addEdge(4, 0); 
		        g2.smallTest(); 
		  
		        EulerianPath g3 = new EulerianPath(5); 
		        g3.addEdge(1, 0); 
		        g3.addEdge(0, 2); 
		        g3.addEdge(2, 1); 
		        g3.addEdge(0, 3); 
		        g3.addEdge(3, 4); 
		        g3.addEdge(1, 3); 
		        g3.smallTest(); 
		  
		        // Let us create a graph with 3 vertices 
		        // connected in the form of cycle 
		        EulerianPath g4 = new EulerianPath(3); 
		        g4.addEdge(0, 1); 
		        g4.addEdge(1, 2); 
		        g4.addEdge(2, 0); 
		        g4.smallTest(); 
		  
		        // Let us create a graph with all veritces 
		        // with zero degree 
		        EulerianPath g5 = new EulerianPath(3); 
		        g5.smallTest(); 
		 }
		 
		 
	}
	
	
	
	

