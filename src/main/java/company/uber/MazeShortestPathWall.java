package company.uber;
import java.util.ArrayList;
import java.util.Arrays;

//import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;



public class MazeShortestPathWall {
/**
 * This link implemented this things clearly 
 * https://codereview.stackexchange.com/questions/153242/shortest-path-for-google-foobar-prepare-the-bunnies-escape
 * @author mahbub
 *
 */
	public static class Node{
		int x,y;
		int nAllowedWall;
		int[][] grid;

		public Node(int x,int y,int w, int[][]grid) {
			this.x=x;
			this.y=y;
			this.grid=grid;
			this.nAllowedWall=w;
		}

		@Override 
		public boolean equals(Object o) {
			if(o==null || !(o instanceof Node))
				return false;
			if(o==this)
				return true;

			Node that=(Node)o;
			return this.x==that.x && this.y==that.y;
			//return false;

		}

		@Override
		public
		int hashCode(){
			return Objects.hash(this.x+this.y);

		}

		List<Node> getNeigbers()
		{
			List<Node> neighbors=new ArrayList<>();
			int x=this.x;
			int y=this.y;
			int block=this.nAllowedWall;
			int[][] g=this.grid;
			int row=grid.length;
			int col=grid[0].length;

			if(x>0) {
				//if there is a wall on x-1
				if(g[x-1][y]==1) 
				{
					if(block>0) {//only can go if there is at least a break option available
						neighbors.add(new Node(x-1,y,block-1,g));
					}
					else {
						neighbors.add(new Node(x-1,y,block,g));
					}
				}
			}
			
			
			if(y<col-1) {
				//if there is a wall on x-1
				if(g[x][y+1]==1) 
				{
					if(block>0) {//only can go if there is at least a break option available
						neighbors.add(new Node(x,y+1,block-1,g));
					}
					
				}else {
					neighbors.add(new Node(x,y+1,block,g));
				}
			}
			
			
			if(x<row-1) {
				//if there is a wall on x-1
				if(g[x+1][y]==1) 
				{
					if(block>0) {//only can go if there is at least a break option available
						neighbors.add(new Node(x+1,y,block-1,g));
					}
					
				}else {
					neighbors.add(new Node(x+1,y,block,g));
				}
			}
			
			
			if(y>0) {
				//if there is a wall on x-1
				if(g[x][y-1]==1) 
				{
					if(block>0) {//only can go if there is at least a break option available
						neighbors.add(new Node(x,y-1,block-1,g));
					}
					
				}else {
					neighbors.add(new Node(x,y-1,block,g));
				}
			}
			if(neighbors.size()==0) {
				System.out.println(x+" "+y);
			}
			return neighbors;
		}
		
		
	}
    
	  

	static void BFS(int[][] grid, int [] start, int dest[], int allowedwall) {
		
		//Map<Node, Boolean> visited=new HashMap<>();
		boolean[][] visited=new boolean[grid.length][grid[0].length];
		int[][] costmap=new int[grid.length][grid[0].length];
		
		for(boolean[] row: visited) {
			Arrays.fill(row, false);
		}
		
		for(int[] row: costmap) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		//int cost=0;
		
		Map<Node,Integer> distanceMap=new HashMap<>();

		final int[][] DIRS = {{0,1},{1,0},{-1,0},{0,-1}};

		Queue<Node> q=new LinkedList<>();
		//int cItem=get1Dpos(nr,nc,start[0], start[1]);
		
		Node sNode=new Node(start[0],start[1],allowedwall,grid);
		//q.add(new Node(start[0],start[1],1,grid));
		q.add(sNode);
		
		visited[sNode.x][sNode.y]=true;
		
		costmap[sNode.x][sNode.y]=0;
		
		distanceMap.put(sNode, 0);

		//costgrid[start[0]][start[1]]=0;

		while(!q.isEmpty()) {
			Node item=q.element();
			
			if(item.x==dest[0] && item.y==dest[1])
			{
				//System.out.println(costmap[item.x][item.y]);
				System.out.println(distanceMap.get(item));
				return;
			}
			visited[item.x][item.y]=true;
			List<Node> neighbors=item.getNeigbers();
			System.out.printf("parent (x%d,y%d) Number of neighber %d",item.x,item.y,neighbors.size());
			//System.out.printf("Number of neighber %d",neighbors.size());
			System.out.println();
			
			for(Node node:neighbors) {

				

				//if(isInside(nextX, nextY, nr,nc) && (grid[nextX][nextY]==0 || (grid[nextX][nextY]==1 && allowedwall>0)) && !visited[next])
				
				//if(!visited[node.x][node.y])
				//if(!distanceMap.containsKey(node))
				//if(item.x!=node.x && item.y!=node.y)
				//{  
					distanceMap.put(node, distanceMap.get(item)+1);
					q.add(node);
					
					
					
					//if(costmap[item.x][item.y]+1 < costmap[node.x][node.y]) 
					//{
					//   costmap[node.x][node.y]=costmap[item.x][item.y]+1;
					//}
 
     			//}
			}
	    q.remove();
	  }//end of while loop;
      


		//return costgrid;

	}

	//but this will not break as there will be loop infinite
	public static void main(String args[]) {
		int[][] arr = new int[][] {
			{0,0,0,0,0},
			{1,1,1,1,0},
			{1,0,0,0,1},
			{1,1,1,1,1},
			{1,1,1,1,1},
			{0,0,0,0,0},
		};

		int[] start= {0,0};
		int[] end= {arr.length-1,arr[0].length-1};

		BFS(arr,start,end, 3);
		
	}

}
