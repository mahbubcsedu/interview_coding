package leetcode;
/**
 * General Disjoin set class. 
 * 
 * @author mahbub
 * This data structure can be modified based on the desired problem to solve. For example
 * Connected componenet of Graph, Number of Island in Grid Graph etc
 */
public class DisjointSet{
	  int[] rank;
	  int[] parent;
	  //we will write inline MakeSet in constructor
	  public DisjointSet(int n) {
		  rank= new int[n];
		  parent=new int[n];
		  for(int i=0;i<n;i++) {
			  parent[i]=i;
			  rank[i]=0;
			  }
	  }
	  // set representative will have the parent itself, 
	  //if no we recursively find the parent until we find the set representative
	  public int find(int x) {
	   if(parent[x]!=x)
		    parent[x]=find(parent[x]);
	   return parent[x];
	  }
	  
	  /**
	   *  tow set will be union by their representative or union by rank. Union by any order
	   *  is also explained in the above link but for solving any problem, following best 
	   *  approach is better
	   */
	  
	  void union(int x, int y) {
		  //find the representative of the set where x belongs
		  int xRepresent=find(x);
		  //find representative where y belongs
		  int yRepresent=find(y);
		  
		  //also calculate the rank of both of the set
		  int xRank=rank[xRepresent];
		  int yRank=rank[yRepresent];
		  
		  //if both of them are of same group, don't do anything and return
		  if(xRepresent==yRepresent)
			  return;
		  
		  //The new parent will be the one which has higher rank to ensure path compression
		  if(xRank > yRank)
			  parent[yRepresent]=xRepresent;
		  else { 
			  parent[xRepresent]=yRepresent;
			  if(xRank==yRank) //if both of them has same rank, but y is parent so,it's rank should be higher. increase rank of y
				  rank[yRepresent]=rank[yRepresent]+1;
		  }
	  }
	  
	  
}
