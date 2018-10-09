package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class NumberOfIslands200 {
/**
 * According to graph theory, this can be done using BFS or DFS search for each and every node.
 * Here the grid is proided with 0 or 1, 1 means node is available and 0 empty
 * Every node with 1 should be search and try to find whether they are connected
 * If connected with be discovered by DFS or BFS
 * But, if we use disjoint set and Make-Set will create different componenet who are connected
 * among themselves
 */
	
 /*
  * 
  * How we can process grid
  * 0101
  * 1000
  * 0000
  * 
  * so, each grid has r row, and c columns, if the language(java) is row based, then any
  * position can be retreived using nr*nc+c where nr=number of row, nc=number of columns and
  * c is the current column, (1,1)  grid positions unique id will be 1*4+1=5 starting from 0
  * a good explanation of coremen algorithm is explained here @link https://www.mathblog.dk/disjoint-set-data-structure/
  * 
  */
  
	
	
	//define DisjoinSet class for grid graph and then run the operations
	public int numIslands(char[][] grid) {
	    if (grid == null || grid.length == 0) {
	      return 0;
	    }
	    // we can define ones about right,down,up,lef
	    UnionFind uf=new UnionFind(grid);
	    
	    for(int i=0;i<grid.length;i++)
	    	for(int j=0;j<grid[0].length;j++)
	    		if(grid[i][j]=='1')
	    			connectNeighbors(grid,i,j,uf);
	    
		return uf.numberOfIsland;
	}
	
	public void connectNeighbors(char[][] grid, int x, int y, UnionFind uf) {
		final int[][] DIRS= {{0,1},{1,0},{0,-1},{-1,0}};
		
		//set the current positions value 0 to avoid loop
		grid[x][y]='0';
		
		for(int[] dir:DIRS) {
			int nextX=x+dir[0];
			int nextY=y+dir[1];
			//System.out.println("(x,y)=("+x +"," + y+")  -> (nextX,nextY)=("+nextX + "," + nextY+")");
			if(nextX>=0 && nextX< grid.length && nextY>=0 && nextY< grid[0].length && grid[nextX][nextY]=='1') {
				
				//System.out.println("Union two neighbor");
				//we have to send the actual position in union find, not just nextX and nextY,
				//the new eligible neighbors will now be connected, the (x,y) and (nextX,nextY) which we can 
				//get in 1-D format as x*grid[0[.length+y, and nextX*grid[0].length+nextY
				
				uf.union(nextX*grid[0].length+nextY, x*grid[0].length+y);
				connectNeighbors(grid,nextX,nextY,uf);
			}
			
		}
		grid[x][y]='1'; // after analyzing from this grid, revert it back to original state
	}
	
	class UnionFind{
		int parent[];
		int rank[];
		int numberOfIsland;//to keep count of isolated componenet
		
		//The graph provided is as a grid, so, the length of total node will be nc*nr
		//the inline MakeSet can be done in constructor
		public UnionFind(char[][] grid) {
			int nr=grid.length;
			int nc=grid[0].length;
			
			parent=new int[nc*nr];
			rank=new int[nc*nr];
			
			
			for(int i=0;i<nc*nr;i++) 
			{
				if(grid[i/nc][i%nc]=='1') //we are interested only where we can find 1 in the grid
				{
				  parent[i]=i;
				  rank[i]=0;
				  numberOfIsland++;//initially all are isolated, and thus comp
				}
			}
		}
		
		public int find(int x) {
			if(parent[x]!=x)
				parent[x]=find(parent[x]);
			return parent[x];
		}
		
		public void union(int x, int y) {
			int xRoot=find(x);
			int yRoot=find(y);
			//if both are in the same island, return
			if(xRoot==yRoot)
				return;
			if(rank[x]>rank[y])
			{
				parent[y]=x;
			}else if( rank[x] < rank[y]) {
				parent[x]=y;
			}
			else if(rank[x]==rank[y]) {
				parent[x]=y;
				rank[y]++;
			}
			//System.out.println("current islands:"+this.numberOfIsland);
			this.numberOfIsland--; //two component are union to one and thus a island will be less
		}
		
		public int getNumberOfIsland() {
			return this.numberOfIsland;
		}
		
		
	}
	
	
	public static int numIslandsDFS(char[][] grid) {
		if(grid==null || grid.length==0)
			return 0;
		
		int nr=grid.length;
		int nc=grid[0].length;
		int numberOfIsland=0;
		
		for(int r=0;r<nr;r++)
			for(int c=0;c<nc;c++)
				if(grid[r][c]=='1') {
					DFS(grid,r, c);
					numberOfIsland++;
				}
				
		return numberOfIsland;
	}
	
	public static void DFS(char[][] grid, int r, int c) {
       
		final int[][] DIRS= {{0,1},{1,0},{0,-1},{-1,0}};
		
		//set the current positions value 0 to avoid loop
		grid[r][c]='0';
		
		for(int[] dir:DIRS) {
			int nextX=r+dir[0];
			int nextY=c+dir[1];
			//System.out.println("(x,y)=("+x +"," + y+")  -> (nextX,nextY)=("+nextX + "," + nextY+")");
			if(nextX>=0 && nextX< grid.length && nextY>=0 && nextY< grid[0].length && grid[nextX][nextY]=='1') {
				
				//System.out.println("Union two neighbor");
				//we have to send the actual position in union find, not just nextX and nextY,
				//the new eligible neighbors will now be connected, the (x,y) and (nextX,nextY) which we can 
				//get in 1-D format as x*grid[0[.length+y, and nextX*grid[0].length+nextY
				
				//uf.union(nextX*grid[0].length+nextY, x*grid[0].length+y);
				DFS(grid,nextX,nextY);
			}
			
		}
		//grid[r][c]='1';
	}
	
	
	public static int numIslandsBFS(char[][] grid) {
		if(grid==null || grid.length==0)
			return 0;
		
		int nr=grid.length;
		int nc=grid[0].length;
		int numberOfIsland=0;
		
		for(int r=0;r<nr;r++)
			for(int c=0;c<nc;c++)
				if(grid[r][c]=='1') {
					BFS(grid,r, c);
					numberOfIsland++;
				}
				
		return numberOfIsland;
	}
	/**
	// The hardle with BFS is we have to deal with (x,y), we can solve this two different way.
	 * 1. We can make 1-D array or vector from 2-D and we are free to roam around with that , r*nc+c is the 1-D from 2-D
	 *    and from n, we can find r and c by, r=n/nc, c=n%/nc
	 * 2. The second way is that we can just convert it to coordinate object, but for object we have to write methods for equality
	 * in other case, probably not for here.
	 * 
	 * Here we are using the first one, will try using second process in later one
	*/
	public static void BFS(char[][] grid, int r, int c) {
	       
		final int[][] DIRS= {{0,1},{1,0},{0,-1},{-1,0}};
		
		//set the current positions value 0 to avoid loop
		grid[r][c]='0';
		
		int nr=grid.length;
		int nc=grid[0].length;
		
		Deque<Integer> q=new LinkedList<>(); //This linkedList can be used as queue and stack, so 
		q.add(nc*r+c);//add just first elements or the given elements
		
		while(!q.isEmpty()) 
		{
			int e=q.getFirst();
			
			for(int[] dir:DIRS) 
			{
				int nextR=e/nc+dir[0];
				int nextC=e%nc+dir[1];
				
				if(nextC>=0 && nextC<nc && nextR>=0 && nextR < nr && grid[nextR][nextC]=='1') {
					grid[nextR][nextC]='0';
					q.addFirst(nextR*nc+nextC);
				}
				
			}
			//after for loop, remove the elements from the queue
			q.removeLast();
		}
		
	}
  	
	public static void smallTestUnionFind() {
		char[][] grid1= {{'1','1','0','0','0'},
		        {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'1','1','0','0','0'}};

       char[][] grid2= {{'1','1','0','0','0','1'},
                 {'1','1','0','0','0','0'},
                 {'1','1','0','1','0','1'},
                 {'1','1','0','0','1','0'}};
  
       char[][] grid3= {{'1','1','0','0','0'},
                 {'1','1','0','0','0'},
                 {'1','1','0','0','0'},
                 {'1','1','0','1','1'}};

       NumberOfIslands200 ni=new NumberOfIslands200();
       
      System.out.println(ni.numIslands(grid1)==1);
      System.out.println(ni.numIslands(grid2)==5);
      System.out.println(ni.numIslands(grid3)==2);
      
      
     // System.out.println(numIslandsDFS(grid1)==1);
      //System.out.println(numIslandsDFS(grid2)==5);
      //System.out.println(numIslandsDFS(grid3)==2);
      
      System.out.println(numIslandsBFS(grid1)==1);
      System.out.println(numIslandsBFS(grid2)==5);
      System.out.println(numIslandsBFS(grid3)==2);
	}
	
	public static void main(String args[]) {
		smallTestUnionFind();
		
		
	}
  
	
}
