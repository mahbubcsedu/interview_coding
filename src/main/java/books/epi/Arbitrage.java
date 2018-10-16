package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** 
 * This problem is coremen exercise of shortest path problems and also the EPI books page 519
 * This problem requires to comprehend the meaning of exchange policy of goods and thus 
 * has n commmodities like goat, grain, gold, silver etc
 * we will find whether from one unit of goat, we can get more goat by different exchange
 * @author mahbub
 *
 *Lets draw a graph where vertex v is the commodities and w(u,v) is the amount of v we can get by selling 
 *1 unit of u
 */
public class Arbitrage {

	/**
	 * There is a good explanation of bellman ford algorithm 
	 * @link https://www.dailycodingproblem.com/blog/how-to-find-arbitrage-opportunities-in-python/
	 * 
	 * if -log(x) <0 if x is greather than zero. So, if we have negative weight cycle
	 * the product of weighted edge is biggar than 1
	 * @param G
	 * @return
	 */
	static boolean isArbitrage(List<List<Double>> G) {
		
		//transform each edge in G
		/**
		 * Transformation can be clearer if you draw a graph
		 * So, a->b=2
		 *     b->c=2*3
		 *     c->a=2*3*4
		 *     
		 * a--2--->b
		 * ^\      /
		 *   4    3
		 *    \  /
		 *     c
		 *     
		 *  if we convert it to log, it will be additive instead of multiplicative and 
		 *  and if the cycle has negative value of log, it has arbitrage cycle otherwise not
		 */
		
		for(List<Double> edgeList: G) {
			for(int i=0;i<edgeList.size();i++) {
				//convert to log of all egdge
				edgeList.set(i, -Math.log10(edgeList.get(i)));
			}
		}
		
		return bellmanFord(G,0);
	}
	
	
	
	private static boolean bellmanFord(List<List<Double>> G, int source) {
		
		List<Double> disToSource = new ArrayList<>(Collections.nCopies(G.size(), Double.MAX_VALUE));
		
		disToSource.set(source, 0.0);
		
		//the algorithm will Relax the edges |V-1| times
		
		for(int times=1; times < G.size();times++) {
			boolean haveUpdate=false; //each relax we will check for update of weight
			for(int u=0;u<G.size();u++) {
				for(int v=0; v<G.size();v++) {
					if(disToSource.get(u) != Double.MAX_VALUE &&
							disToSource.get(v) > disToSource.get(u)+G.get(u).get(v)) 
					{
						haveUpdate=true;
						disToSource.set(v, disToSource.get(u)+G.get(u).get(v));
					}
				}
			}
			//no update at this iteration means no negative cycle and no arbitrage
			if(!haveUpdate) {
				return false;
			}
		}
		
		//after |v-1| update check if there is any more update
		
		for(int u=0; u < G.size();u++) {
			for(int v=0; v < G.size(); v++) {
				if(disToSource.get(u) > disToSource.get(u)+G.get(u).get(v))
					return true;
			}
		}
		
		return false;
	}
	
	
	
	public static List<List<Double>> buildGraph(){
		List<List<Double>> g=new ArrayList<>();
		
		for(int i=0; i<3; i++) {
			List<Double> newList=new ArrayList<>();
			
			for(int j=0;j<3;j++) {
			   newList.add(0.0);	
			}
			g.add(newList);
		}
		
		g.get(0).set(1, 2.0);
		g.get(1).set(0, 0.5);
		g.get(0).set(2, 1.0);
		g.get(2).set(0, 1.0);
		g.get(1).set(2, 4.0);
		g.get(2).set(1, 0.25);
		
	    return g;
		//g.addAll(gr)
	}
	//Define a graph of adjcency matrix
	
	
	public static void main(String args[]) {
		List<List<Double>> g=buildGraph();
		System.out.println(isArbitrage(g));
	}
	
}
