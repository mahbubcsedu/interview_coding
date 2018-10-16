package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class InstallationSequence {


	//class Graph
	//{
		int v; // number of courses
		enum COLOR{WHITE, BLACK, GRAY};
		
		Map<String, ArrayList<String>> edges;
		//Map<String, Boolean> visited =new HashMap<>();
		InstallationSequence(List<String> packages){
			v=packages.size();
			edges=new HashMap<>();

			for(int i=0; i<v;i++) {
				edges.put(packages.get(i), new ArrayList<>());//create vertex with adjacency list
				//visited.put(packages.get(i), false);
			}
		}

		void addEdges(String[][] seqs) {
			for(String[] dep: seqs) {
				edges.get(dep[1]).add(dep[0]);//directed graph, so only once
				
			}
		}
		
		 boolean sortInstallation() {
			Deque<String> order= new LinkedList<>();
			Map<String, Boolean> visited=new HashMap<>();
			Map<String, COLOR> color=new HashMap<>();
			
		    for(String k: edges.keySet()){
		    	visited.put(k, false);
		    	color.put(k, COLOR.WHITE);
		    }
		    
		    for(String node: edges.keySet()) {
		    	if(visited.get(node)==false) {
		    		if(!dFS(node, visited,order,color)) {
		    			System.out.println("Not possible to complete");
		    			return false;
		    		}
		    	}
		    }
		    return true;
		    //while(!order.isEmpty()) {
		   // 	System.out.println(order.removeFirst());
		    //}
		    	
		}
		//visited is unnecessary in this case
		 boolean dFS(String s, Map<String, Boolean> visited, Deque<String> order,Map<String,COLOR> c) {
			visited.put(s, true);
			c.put(s, COLOR.GRAY);
			
			Iterator<String> it = edges.get(s).iterator();
			
			while(it.hasNext()) {
				String node=it.next();
				if(c.get(node)==COLOR.GRAY) {
					System.out.println("Not possible");
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

	




	public static void main(String args[]) {
		String[] pack=new String[] {"a","b","c","d","e","f"};

		List<String> packages=new ArrayList<>(Arrays.asList(pack));
		String[][] dependency={{"f","b"},{"b","c"},{"c","a"}};

		
		InstallationSequence g=new InstallationSequence(packages);
		g.addEdges(dependency);
		System.out.println(g.sortInstallation() ? "possible":"not");
		
		//List<List<String>> sequence=new ArrayList<>();
		//sequence.add()
		//packages.add()

		//packages.addAll();
	}
}
