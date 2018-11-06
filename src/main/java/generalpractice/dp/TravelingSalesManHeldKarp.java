package generalpractice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Traveling sales man problem is to visit all city exactly once with shortest path or tavel all city shortest way
 * There are many way to visit but we have to find out which is the shortest.
 * 
 * One simple way is to permute all path and calcuate the cost, find the path which has minimum
 * cost and return, but the cost is O(n!)
 * 
 * As this is NP hard problem, There are two possibility, we can reduce the cost to O(2^n*n^2) using dynamic problem
 * or if we do can gaurantee about triangular  inequality, we can solve approximate way using minimum spanning tree
 * 
 * @author mahbub
 *
 */
public class TravelingSalesManHeldKarp {

	
/**
 * The dp problem handle the issue in bottom up approach starting from the first node.
 * 1. at first node, it calculate the min cost from 0 to all other nodes directly,
 * 2. After that, it visits the every node via another node
 * 3.Then it calculate from 0 to node via set set of nodes with minimu cost
 * 
 * So, to implement this, we need to create a subset of all nodes except the first one
 * 1. The subset will have empty subset, subset with one node, 2 node, 3 nodes and so one
 * 2. it will calculate the distance from start node via each set including empty
 * 3. finally it will calculate start node to start node via the set of all nodes to complete the circle
 * 
 * The last step cost is the min cost and it will keep tract of the direction to print the whole path.
 */
	
/**
 * First create subset of all nodes except the start node
 * @param n, number of nodes in the graph
 * @return a set of all subset except the first node
 */
	
	
 private static List<Set<Integer>> generateSubset(int n){
	 //create input set from the number
	 int input[]= new int[n];
	 for(int i=0; i<n; i++)
	 {
		 input[i]=0;
	 }
	 List<Set<Integer>> allSets=new ArrayList<>();
	 Set<Integer> tempSet=new HashSet<>();
	 generateSubSet(input,0,0,tempSet,allSets);
	 return allSets;
	 
 }	
 
 
 
 private static void generateSubSet(int[] input,int start, int pos, Set<Integer> temp, List<Set<Integer>> result) {
	 
	 if(input.length==start)
		 return;
	 
	 //temp=new Hashset<>();
	 result.add(new HashSet<>(temp));
	 for(int i=start;i<input.length;i++) {
		 temp.add(i);
		 generateSubSet(input,i+1,start+1,temp,result);
		 temp.remove(temp.size()-1);
	 }
	 
 }
 
 
 public static void main(String args[]) {
	 List<Set<Integer>> l=generateSubset(2);
	 System.out.println(Arrays.deepToString(l.toArray()));
	 
	 
 }
	
}
