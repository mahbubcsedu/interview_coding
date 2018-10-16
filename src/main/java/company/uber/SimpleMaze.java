package company.uber;
import java.util.*;
public class SimpleMaze {
/**
 * BFS for maze
 */
	
 static boolean ReachableToDestBFS(int[][] grid, int [] start, int[] dest) {
	 //for 
	 
	 int nr=grid.length;
	 int nc=grid[0].length;
	 boolean[] visited=new boolean[nr*nc];
	 int cost=0;
	 
	 final int[][] DIRS = {{0,1},{1,0},{-1,0},{0,-1}};
			 
	 Queue<Integer> q=new LinkedList<>();
	 int cItem=get1Dpos(nr,nc,start[0], start[1]);
	 q.add(cItem);
	 visited[cItem]=true;
	 
	 while(!q.isEmpty()) {
		 Integer item=q.element();
		 for(int[] dir:DIRS) {
			 int nextX=getRow(item,nc)+dir[0];
			 int nextY=getCol(item,nc)+dir[1];
			 int next=get1Dpos(nr,nc,nextX, nextY);
			 
			 if(nextX==dest[0] && nextY==dest[1])
			 {
				 System.out.print(cost);
				 return true;
			 }
			 if(isInside(nextX, nextY, nr,nc) && grid[nextX][nextY]==0 && !visited[next]) {
				 
				 q.add(next);
				 visited[next]=true;
				 cost+=1;
			 }
			 
		 }
		 q.remove();
	 }
	 
	 
	 
	 return false;
	 
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
 
 
 public static void main(String args[]) {
	 int[][] arr = new int[][] {
         {0,0,0,0,0},
         {1,1,1,1,0},
         {0,0,0,0,0},
         {0,1,1,0,1},
         {1,1,1,0,1},
         {0,0,0,0,0},
     };
     
     int[] start= {0,0};
     int[] end= {arr.length-1,arr[0].length-1};
	 System.out.println(ReachableToDestBFS(arr, start, end) ? "reachable" :"not reacheble");
 }
}
