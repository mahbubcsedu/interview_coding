package company.uber;

import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MazeShortestPathWall {


	
	
	static int[][] BFS(int[][] grid, int [] start) {
		//for 

		
		int nr=grid.length;
		int nc=grid[0].length;
		
		int[][] costgrid=new int[nr][nc];
		
		for(int[] row: costgrid) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		
		
		boolean[] visited=new boolean[nr*nc];
		int cost=0;

		final int[][] DIRS = {{0,1},{1,0},{-1,0},{0,-1}};

		Queue<Integer> q=new LinkedList<>();
		int cItem=get1Dpos(nr,nc,start[0], start[1]);
		q.add(cItem);
		visited[cItem]=true;
		
		costgrid[start[0]][start[1]]=0;

		while(!q.isEmpty()) {
			Integer item=q.element();
			for(int[] dir:DIRS) {
				int nextX=getRow(item,nc)+dir[0];
				int nextY=getCol(item,nc)+dir[1];
				int next=get1Dpos(nr,nc,nextX, nextY);

				/*if(nextX==dest[0] && nextY==dest[1])
				{
					//costgrid[nextX][nextY]=cost;
					return costgrid;
				}*/
				//if(isInside(nextX, nextY, nr,nc) && grid[nextX][nextY]==0 && !visited[next]) {
				if(isInside(nextX, nextY, nr,nc) && !visited[next]) {

					q.add(next);
					visited[next]=true;
					cost=costgrid[getRow(item,nc)][getCol(item,nc)];
					cost++;
					costgrid[nextX][nextY]=costgrid[nextX][nextY] > cost ? cost: costgrid[nextX][nextY];
					//costgrid[nextX][nextY]=cost;
				}

			}
			q.remove();
		}



		return costgrid;

	}

	static boolean isInside(int r, int c, int nr, int nc) {
		return r>=0 && c>=0 && r<nr && c<nc;
	}
	static int get1Dpos(int nr, int nc, int r, int c ) {
		return nc*r+c;
	}
	static int getCol(int n, int nc) {
		return n%nc;
	}
	static int getRow(int n, int nc) {
		return n/nc;
	}

/*


	public static int getShortestPathWithOneWall(int[][] grid, int start[], int end[]) {
		//we will do dfs search for all positions to start, all position to end, add cost at the 
		// end to cost matrix

		int[][] cost=new int[grid.length][grid[0].length];
		for(int[] row: cost) {
			Arrays.fill(row, 0);
		}

		int R=grid.length;
		int C=grid[0].length;

		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {

				boolean[][] visited=new boolean[R][C];
				int [][] dis=new int[R][C];

				initializeCost(visited, cost,dis);
				Integer cost_from_here=0;

				DfsCost(grid,r,c, start,visited,dis);

				//System.out.println(dis[start[0]][start[1]]);
				cost[r][c]=dis[start[0]][start[1]];
			}
		}


		return 0;
	}

	public static void initializeCost(boolean[][] visited,int[][] cost,int[][] dis) {


		for(int[] row: cost) {
			Arrays.fill(row, -1);
		}

		for(int[] row: dis) {
			Arrays.fill(row, 0);
		}

		for(boolean[]row: visited)
		{
			Arrays.fill(row, false);
		}
	}
	public static void  DfsCost(int[][] maze,int startX, int startY, int[] dest, boolean[][] visited, int[][] cost) {


		visited[startX][startY]=true;

		//cost[startX][startY]=0;

		//cost+=1;

		if(startX==dest[0] && startY==dest[1])
			return;

		final int[][] DIRS= {{0,1},{1,0},{0,-1},{-1,0}};

		for(int[] dir: DIRS) {
			//System.out.println(nextX+""+nextY);
			int nextX=startX+dir[0];
			int nextY=startY+dir[1];
			//System.out.println(nextX+""+nextY);
			if(nextX>=0 && nextX <maze.length && nextY>=0 && nextY < maze[0].length)
			{
				//System.out.println(nextX+"valid"+nextY);
				if(maze[nextX][nextY]==0 && !visited[nextX][nextY]){

					DfsCost(maze, nextX, nextY,dest,visited, cost);
					System.out.print(cost[startX][startY]);
					cost[nextX][nextY]=cost[startX][startY]+1;
				}
			}
		}

		//return cost;
	}
*/
	public static void main(String args[]) {
		int[][] arr = new int[][] {
			{0,0,0,0,0},
			{1,1,1,1,0},
			{0,0,0,0,0},
			{0,1,1,1,1},
			{0,1,1,1,1},
			{0,0,0,0,0},
		};

		int[] start= {0,0};
		int[] end= {arr.length-1,arr[0].length-1};

		int[][] res=BFS(arr,start);
		int[][] resd=BFS(arr,end);
		
		int[][] finalr=new int[res.length][res[0].length];
		
		for(int i=0; i<res.length;i++) {
			for(int j=0; j<res[0].length; j++) 
			{
				finalr[i][j]=res[i][j]+resd[i][j];
				
				//System.out.print("\t"+res[i][j]+"\t");
			}
			System.out.println();
		}


		
		for(int i=0; i<finalr.length;i++) {
			for(int j=0; j<finalr[0].length; j++) 
			{
				//finalr[i][j]=res[i][j]+resd[i][j];
				
				System.out.print("\t"+finalr[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
