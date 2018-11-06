package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaxAreaOfIsland {

	/*
	 * @link https://leetcode.com/problems/max-area-of-island/solution/
	 */
	public static int getMaxGrid(int[][] grid) {
		if(grid==null || grid.length==0)
			return 0;
		
		boolean[][] visited=new boolean[grid.length][grid[0].length];
		
		for(boolean[] row:visited) {
			Arrays.fill(row, false);
		}
		
		int maxArea=0;
		for(int r=0;r<grid.length;r++) {
			for(int c=0;c<grid[0].length;c++) {
				maxArea=Math.max(maxArea, dfsArea(grid,visited, r,c));
			}
		}
		return maxArea;
		
	}
	
	public static int dfsArea(int[][] grid, boolean[][] visited, int r,int c) {
		if(r<0 || r >= grid.length || c<0 || c>grid[0].length || visited[r][c] || grid[r][c]==0) {
			return 0;
		}
		visited[r][c]=true;
		
		return (1+dfsArea(grid,visited,r-1,c)+dfsArea(grid,visited,r,c-1)+dfsArea(grid,visited,r+1,c)+dfsArea(grid,visited,r,c+1));
		
		//return 0;
	}
	
	//O(R*C)
	
	static class Node{
		int r,c;
		Node(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
	
	public static int getMaxAreaDfsIterative(int[][]grid) {
		
		boolean[][] visited=new boolean[grid.length][grid[0].length];
		
		final int[][] DIRS= {{1,0},{0,1},{0,-1},{-1,0}};
		
		int maxArea=0;
		
		for(int r=0;r<grid.length;r++) {
			for(int c=0;c<grid[0].length;c++) {
				if(grid[r][c]==1 && !visited[r][c]) {
					int curIslandArea=0;
				    Deque<Node> stack=new LinkedList<>();
				    stack.addFirst(new Node(r,c));
				    visited[r][c]=true;
				    
				    while(!stack.isEmpty()) {
				    	Node node=stack.removeFirst();
				    	int row=node.r;
				    	int col=node.c;
				    	
				    	curIslandArea++;
				    	
				    	for(int[] dir:DIRS) {
				    		int nextR=row+dir[0];
				    		int nextC=col+dir[1];
				    		
				    		if(nextR >=0 && nextR <grid.length && nextC>=0 && nextR <=grid[0].length && !visited[nextR][nextC] && grid[nextR][nextC]==1) {
				    			stack.addFirst(new Node(nextR,nextC));
				    			visited[nextR][nextC]=true;
				    		}
				    	}
				    	
				    }
				    
				    maxArea=Math.max(maxArea, curIslandArea);
				}
			}
			
		}
		return maxArea;
	}
	static void test1(){
		int[][] grid= {{1,0,0,0,0,0},
				       {1,0,0,0,1,0},
	                   {0,0,1,1,1,0},
	                   {1,0,0,1,0,0},
		               {1,0,1,0,1,0}, 
	                   {1,0,0,1,1,0},
		};
		
		int a=getMaxGrid(grid);
		System.out.printf("the maximum island area %d", a);
		
	}
	
	static void test2(){
		int[][] grid= {{1,0,0,0,0,0},
				       {1,0,0,0,1,0},
	                   {0,0,1,1,1,0},
	                   {1,0,0,1,0,0},
		               {1,0,1,0,1,0}, 
	                   {1,0,0,1,1,0},
		};
		
		int a=getMaxAreaDfsIterative(grid);
		System.out.printf("the maximum island area %d", a);
		
	}
	public static void main(String args[]) {
		test2();
	}
}
