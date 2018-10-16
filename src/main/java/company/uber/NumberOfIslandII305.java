package company.uber;

import java.util.Arrays;

public class NumberOfIslandII305 {

	static class UnionFind{
		int parent[];
		int rank[];
		int[][] grid;
		int nOfIsland;
		
		//for()
		public UnionFind(int[][] grid) {
	       this.grid=grid;
	       this.nOfIsland=0;
		   parent=new int[grid.length*grid[0].length];
		   rank=new int[grid.length*grid[0].length];
		}
		 
		void MakeSet(int[] land) {
			int landPos=land[0]*this.grid[0].length+land[1];
			parent[landPos]=landPos;
			rank[landPos]=0;
			//this.grid
			this.nOfIsland++;
		}
		int find(int x) {
			if(parent[x]!=x)
				parent[x]=find(parent[x]);
			return parent[x];
		}
		
		void union(int x, int y) {
			int xR=find(x);
			int yR=find(y);
			
			if(xR==yR)
				return;
			if(rank[xR]>rank[yR]) {
				parent[y]=x;
				
			}else if(rank[xR] < rank[yR]) {
				parent[x]=y;
			}
			else if(rank[xR]==rank[yR]) {
				parent[x]=y;
				rank[y]++;
			}
			this.nOfIsland--;
			
		}
		
		 void addLand(int[] newLand) {
			grid[newLand[0]][newLand[1]]=1;
			MakeSet(newLand);
		}
	}
	/**
	 *  O(nxm+L) where grid operation takes O(n*m) and L add just process L positions in the grid with union find path compresssion
	 * @param m
	 * @param n
	 * @param addLands
	 * @return
	 */
	public static int getNumberOfIslands(int m, int n, int[][] addLands) {
		int[][] grid=new int[m][n];
		for(int[] row: grid) {
		Arrays.fill(row, -1);
		}
		UnionFind uf=new UnionFind(grid);
		
		final int[][] DIRS= {{1,0},{0,1},{0,-1},{-1,0}}; 
		
		for(int[] newLand : addLands) {
			uf.addLand(newLand);
			
			//find out the all neighbors and union them if they are 1
			for(int[] dir: DIRS) {
				int nextX=newLand[0]+dir[0];
				int nextY=newLand[1]+dir[1];
				if(nextX>=0 && nextX<m && nextY>=0 && nextY<n && uf.grid[nextX][nextY]==1) {
					uf.union(nextX*m+nextY,newLand[0]*m+newLand[1]);
				}
			}
		}
		return uf.nOfIsland;
		//return 0;
	}
	
	public static void main(String args[]) {
		int[][] newLands={{0,0}, {0,1}, {1,2}, {2,1},{2,2}};
		System.out.println(getNumberOfIslands(3,3,newLands));
	}
}
