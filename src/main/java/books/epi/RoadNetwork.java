package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Add a new highway as a proposal which will savings more with the existing network
 * This will use floyd warshall as it dense graph all highway connects to most of the highway
 * @author mahbub
 *
 */
public class RoadNetwork {

	static class HighwaySection{
		int from, to;
		public double distance;
		
		HighwaySection(int from, int to, double distance){
			this.from=from;
			this.to=to;
			this.distance=distance;
		}
	}
	/**
	 * 
	 * @param H existing highway
	 * @param p proposed highway
	 * @param n number of possible highways
	 * @return best proposal highway
	 * 
	 * Algorithm: 
	 * 1. The bellman ford algorithm will find out existing network shortest path
	 * 2. We then find out, for all the pair (from, to) pairs. If we add s(x,y) with distance d(x,y), then
	 *  then the min will be min{S(a,b), S(a,x)+d(x,y)+S(y,b), S(b,x)+d(y,x)+S(x,a)}
	 */
	
	public static HighwaySection findBestProposal(List<HighwaySection> H, List<HighwaySection> P, int n) {
		//G stores the shortest path from any source to any destination
		List<List<Double>> G =new ArrayList<>(n);
		
		for(int i=0; i<n;i++) {
			G.add(new ArrayList<>(Collections.nCopies(n, Double.MAX_VALUE)));// add n and n column with infinite
		}
		
		for(int i=0; i<n;++i) {
			G.get(i).set(i, 0.0);//fill diagonal with 0 as self loop is always zero
		}
		
		//builds the undirected graph based on the provided highways H
		for(HighwaySection h: H) {
			G.get(h.from).set(h.to, h.distance);
			G.get(h.to).set(h.from, h.distance);//for undirected
		}
		
		//run floydwarshall to get all source shortest path
		floydWarshall(G);
		//Now try for all the proposed highway, try from all to and from and find the minimum one
		
		 double bestProposalSaving=0.0; 
		 
		 HighwaySection bestProp=new HighwaySection(-1,-1,0.0);
		 
		 for(HighwaySection p: P) {
			 double propSavings=0.0;
			 //we will try to find for all pair(a,b) from n highways
			 for(int a=0;a<n;a++) {
				 for(int b=0; b<n; b++) {
					 double savings= G.get(a).get(b)-(G.get(a).get(p.from)+p.distance+G.get(p.to).get(b));
					 propSavings+=savings >0.0 ? savings : 0.0;//we will accumulate all savings for a single propsal for all combination of roads
				 }
			 }
			 
			 //for each propsal, we will keep track of best one until now
			 if(propSavings > bestProposalSaving) {
				 bestProposalSaving=propSavings;
				 bestProp=p;//also update the best prop
			 }
			 
		 }
		 
		
		return bestProp;
	}
	
	
	static void floydWarshall(List<List<Double>> g) {
		for(int k=0; k<g.size();++k) {
			for(int u=0;u<g.size();++u) {
				for(int v=0; v<g.size();++v) {
					if(g.get(u).get(v)!=Double.MAX_VALUE && g.get(v).get(u)!=Double.MAX_VALUE 
							&& g.get(u).get(v) > g.get(u).get(k)+g.get(k).get(v)) {
						g.get(u).set(v, g.get(u).get(k)+g.get(k).get(v));
					}
				}
			}
		}
	}
	
	
	private static void simpleTest() {
	    List<HighwaySection> H = Arrays.asList(new HighwaySection(0, 1, 10),
	                                           new HighwaySection(1, 2, 10),
	                                           new HighwaySection(2, 3, 10));
	    List<HighwaySection> P = Arrays.asList(new HighwaySection(0, 3, 1),
	                                           new HighwaySection(0, 2, 2),
	                                           new HighwaySection(0, 1, 3));

	    HighwaySection t = findBestProposal(H, P, 4);
	    
	    System.out.printf("the best one will be from %s to %s and distance: %f ",t.from,t.to,t.distance);
	    assert(t.from == 0 && t.to == 3 && t.distance == 1.0);
	  }
	
	public static void main(String args[]) {
		//design test data is very time consuming and important for validity check
		
		simpleTest();
	}
	
}
