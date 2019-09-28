package leetcode.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class CouseSchedule207 {

	int v; // number of courses
	enum COLOR{WHITE, BLACK, GRAY};

	Map<Integer, ArrayList<Integer>> edges;
	//Map<String, Boolean> visited =new HashMap<>();
	public CouseSchedule207(List<Integer> packages)
	{
		v=packages.size();
		edges=new HashMap<>();

		for(int i=0; i<v;i++) {
			edges.put(packages.get(i), new ArrayList<>());//create vertex with adjacency list
			//visited.put(packages.get(i), false);
		}
	}

	void addEdges(int[][] seqs) {
		for(int[] dep: seqs) {
			edges.get(dep[1]).add(dep[0]);//directed graph, so only once

		}
	}

	boolean sortInstallation(int[][] dependency) 
	{
		addEdges(dependency);
		Deque<Integer> order= new LinkedList<>();
		Map<Integer, Boolean> visited=new HashMap<>();
		Map<Integer, COLOR> color=new HashMap<>();

		for(int k: edges.keySet()){
			visited.put(k, false);
			color.put(k, COLOR.WHITE);
		}

		for(int node: edges.keySet()) {
			if(visited.get(node)==false) {
				if(!dFS(node, visited,order,color)) {
					//System.out.println("Not possible to complete");
					return false;
				}
			}
		}
		return true;

	}


	//boolean isPossible=false;
	//Deque<order>


	static boolean canFinish(int n, int[][] prerequisites) {

		Map<Integer,List<Integer>> graph=new HashMap<>();
		for(int i=0; i<n;i++) {
			graph.put(i, new ArrayList<Integer>());
		}
		for(int[] dep: prerequisites) {
			graph.get(dep[1]).add(dep[0]);//directed graph, so only once
		}

		Deque<Integer> order= new LinkedList<>();
		//Map<Integer, Boolean> visited=new HashMap<>();
		Map<Integer, COLOR> color=new HashMap<>();

		for(int k: graph.keySet()){
			//visited.put(k, false);
			color.put(k, COLOR.WHITE);
		}

		int[] isPossible=new int[1];
		isPossible[0]=1;

		for(int node: graph.keySet()) {
			if(color.get(node)==COLOR.WHITE) {
				//if(!dFSSchedule(node ,order,color,graph,isPossible)) {
				//System.out.println("Not possible to complete");
				//return false;
				dFSSchedule(node ,order,color,graph,isPossible);
				//}
			}
		}


        /** for second one just create array with this
         * 
         */
   		while(!order.isEmpty()) {
		 	System.out.println(order.removeFirst());
		}

		return isPossible[0]==1 ? true: false ;

	}
	//we cannot return, so, we need a global variable to determine

	static boolean dFSSchedule(int s, Deque<Integer> order,Map<Integer,COLOR> c,Map<Integer, List<Integer>> g,int[] isPossible) {
		//visited.put(s, true);
		c.put(s, COLOR.GRAY);

		Iterator<Integer> it = g.get(s).iterator();

		while(it.hasNext()) {
			int node=it.next();
			if(c.get(node)==COLOR.GRAY) {
				//System.out.println("Not possible");
				//break;
				isPossible[0]=0;
				return false;
			}

			else if(c.get(node)==COLOR.WHITE) {
				dFSSchedule(node,order,c,g,isPossible);
			}
		}
		c.put(s, COLOR.BLACK);
		order.addFirst(s);
		return true;
	}
	//visited is unnecessary in this case
	boolean dFS(int s, Map<Integer, Boolean> visited, Deque<Integer> order,Map<Integer,COLOR> c) {
		visited.put(s, true);
		c.put(s, COLOR.GRAY);

		Iterator<Integer> it = edges.get(s).iterator();

		while(it.hasNext()) {
			int node=it.next();
			if(c.get(node)==COLOR.GRAY) {
				//System.out.println("Not possible");
				//break;
				return false;
			}

			else if(c.get(node)==COLOR.WHITE) {
				return dFS(node,visited,order,c);
			}
		}
		c.put(s, COLOR.BLACK);
		order.addFirst(s);
		return true;
	}


	//leetcode fastest
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		//Graph problem: detect if cycle exists
		//DFS: unvisited = 0, visited = 1, searching = -1
		int[] visited = new int[numCourses];
		ArrayList[] list = new ArrayList[numCourses];

		for(int i = 0; i < numCourses; i++){
			list[i] = new ArrayList();
		}

		for(int i = 0; i < prerequisites.length; i++){
			list[prerequisites[i][0]].add(prerequisites[i][1]);
		}

		for(int i = 0; i < numCourses; i++){
			if(!visit(i, list, visited)){
				return false;
			}
		}
		return true;
	}

	public boolean visit(int n, ArrayList[] list, int[] visited){
		if(visited[n] == 1) return true;
		visited[n] = -1;
		for(int i = 0; i < list[n].size(); i++){
			if(visited[(int)list[n].get(i)] == -1 || !visit((int)list[n].get(i), list, visited)){
				return false;
			}        
		}      
		visited[n] = 1;
		return true;
	}


	public static void main(String args[]) {
		Integer[] pack=new Integer[] {0,1,2,3,4,5};

		//List<Integer> packages=new ArrayList<>();
		int[][] dependency={{5,1},{1,2},{2,3}};


		//CouseSchedule207 g=new CouseSchedule207(Arrays.asList(pack));
		//g.addEdges(dependency);
		//System.out.println(g.sortInstallation(dependency) ? "possible":"not");

		int[][] d= {{0,1},{1,2},{2,0}};
		//System.out.println(canFinish(3,d) ? "possible":"not");

		//first one is dependent on second one , 3 depends on 0, so 0--->3
		int[][] d2= {{3,0},{0,1}};
		System.out.println(canFinish(4,d2) ? "possible":"not");

		
		int[][] d3= {{1,0},{2,0},{3,1},{3,2}};
		System.out.println(canFinish(4,d3) ? "possible":"not");
		//List<List<String>> sequence=new ArrayList<>();
		//sequence.add()
		//packages.add()

		//packages.addAll();
	}
}