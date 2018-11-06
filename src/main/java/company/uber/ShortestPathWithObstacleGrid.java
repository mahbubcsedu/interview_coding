package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * {@linkplainhttps://www.quora.com/Given-start-and-target-position-of-a-robot-on-a-2D-grid-with-obstacles-What-is-the-shortest-path-length-if-robot-can-remove-at-most-3-obstacles}
 * @author mahbub
 *
 */
public class ShortestPathWithObstacleGrid {


	class Node{
		GraphVertex v;
		int obstacle;
		Node(GraphVertex v, int ob){
			this.v=v;
			this.obstacle=ob;
		}
	}
	private class GraphVertex
	{
		public String Id;// { get; set; }
		public boolean IsObstacle;// { get; set; }
		public List<GraphVertex> neighbours;// { get; set; }

		public GraphVertex(String id, boolean isObstacle)
		{
			Id = id;
			IsObstacle = isObstacle;
			neighbours = new ArrayList<GraphVertex>();
		}


		/// Add an undirected edge
		/// </summary>
		/// <param name="neighbour"></param>
		public void AddEdge(GraphVertex neighbour)
		{
			neighbours.add(neighbour);
			neighbour.neighbours.add(this);
		}

		@Override
		public String toString()
		{
			return String.format("{0}", Id);
		}


	}

	private GraphVertex[][] CreateGraph(int[][] mat)
	{
		// Create the graph
		GraphVertex[][] AllVerticesMat = new GraphVertex[mat.length][mat[0].length];

		for (int r = 0; r < mat.length; r++)
		{
			for (int c = 0; c < mat[0].length; c++)
			{
				if (mat[r][c] == 1)
				{
					// Add new graph vertex
					AllVerticesMat[r][c] = new GraphVertex(String.format("{%d}#{%d}", r, c), false);
				}
				else
				{
					// Add an obstacle vertex
					AllVerticesMat[r][c] = new GraphVertex(String.format("{%d}#{%d}", r, c), true);
				}
			}
		}

		for (int r = 0; r < mat.length; r++)
		{
			for (int c = 0; c < mat[0].length; c++)
			{

				// Add the  South edges
				if (r + 1 < mat.length)
				{
					AllVerticesMat[r][c].AddEdge(AllVerticesMat[r + 1][c]);
				}
				//Add the East edges
				if (c + 1 < mat[0].length)
				{
					AllVerticesMat[r][c].AddEdge(AllVerticesMat[r][c + 1]);
				}
				//Add the South-East edges
				if(c+1 <mat[0].length && r+1< mat.length)
				{
					AllVerticesMat[r][c].AddEdge(AllVerticesMat[r + 1][c + 1]);
				}
				//Add the North-East edges
				if (c + 1 < mat[0].length && r -1 >=0)
				{
					AllVerticesMat[r][c].AddEdge(AllVerticesMat[r -1][c + 1]);
				}
			}
		}

		return AllVerticesMat;
	}


	public List<GraphVertex> GetPath(int[][] mat, int maxNumOfObs, int stRow, int stCol, int endRow, int endCol)
	//public boolean GetPath(int[][] mat, int maxNumOfObs, int stRow, int stCol, int endRow, int endCol)
	{
		// Create the graph
		GraphVertex[][] AllVerticesMat = CreateGraph(mat);
		GraphVertex endVertex = AllVerticesMat[endRow][endCol];
		GraphVertex startVertex = AllVerticesMat[stRow][stCol];
		//List<HashMap<GraphVertex,Integer>> init=new ArrayList<>(null,0);
		//init.add(null,0);
		List<GraphVertex> result=new ArrayList<>();

		Map<GraphVertex, List<Node>> obstaclesAndBackTrack = new HashMap<>();
		List<Node> path=new ArrayList<>();
		path.add(new Node(null,0));
		obstaclesAndBackTrack.put(startVertex, path);

		//obstaclesAndBackTrack.put(startVertex, obstaclesAndBackTrack.get(key). new List<HashMap<GraphVertex, Integer>>(null,0));// { new HashMap<GraphVertex, Integer>( null, 0 ) };
		Queue<GraphVertex> queue = new LinkedList<>();
		queue.offer(startVertex);

		while(!queue.isEmpty()) 
	    {
	    	   GraphVertex vertex = queue.poll();
		        if(vertex == endVertex)
		        {
		        	//return true;
		            // return the path
		        	//return result;
		            return BackTrackToGetThePath(obstaclesAndBackTrack, startVertex, endVertex);
		        }
		        
		        for (GraphVertex neighbour: vertex.neighbours)
		        {
		        	for (Node kvPair: obstaclesAndBackTrack.get(vertex))
		            {
		        		int obsFromVertex = kvPair.obstacle;
		        		int currentObs = (neighbour.IsObstacle) ? 1 : 0;
		        		
		        		if (obsFromVertex + currentObs <= maxNumOfObs)
		                {
		        			if (!obstaclesAndBackTrack.containsKey(neighbour))
		                    {
		        				Node n=new Node(vertex,obsFromVertex + currentObs);
		        				List<Node> l=new ArrayList<>();
		        				l.add(n);
		                        obstaclesAndBackTrack.put(neighbour,l);
		                        queue.add(neighbour);
		                    }
		        			//but if the neighber is already present, that means, the neighbor is already visited from different path have the shortest path listed
		        			//with obstacle. The last node has the farthest path with lowest number of obstacle. So the the neighbor already shortest path
		        			// from different direction. So, we can only visit that from current vertex if the last node obstacles is greater than the current path
		        			//then we can add this to the path, otherwise we can easily discard this path
		        			//keep in mind that, if the node is reached earlier in BFS, the earlier path is the shortest path. So, we are only concern about the number of obstacle
		        			//and this path should have lower number of obstacle
		                    else if (obstaclesAndBackTrack.get(neighbour).get(obstaclesAndBackTrack.get(neighbour).size()-1).obstacle > obsFromVertex + currentObs)
		                    {
		                       // obstaclesAndBackTrack[neighbour].Add(new KeyValuePair<GraphVertex, int>(vertex, obsFromVertex + currentObs));
		                    	List<Node> l=obstaclesAndBackTrack.get(neighbour);
		                    	l.add(new Node(vertex,obsFromVertex + currentObs));
		                    	obstaclesAndBackTrack.put(neighbour, l);//we will ad this path at the end of the list
		                    }
		                }
		            }
		        }
	    }

		return null;
	}
	
	public List<GraphVertex> BackTrackToGetThePath(Map<GraphVertex,List<Node>> backpath, GraphVertex s, GraphVertex e){
		List<GraphVertex> path=new ArrayList<>();
		path.add(e);
		
		if (backpath.containsKey(e))
	    {
	        // This check is needed to make sure that there is a path. if there is no path we will return an empty list
	        Node currentPair = backpath.get(e).get(0);//[endVertex][0];
	        int previousObstacleVal = currentPair.obstacle;
	        
	        while (currentPair!=null)//.Equals(default(KeyValuePair<GraphVertex, int>)))
	        {
	            path.add(currentPair.v);
	            Node next=null;// = default(KeyValuePair<GraphVertex, int>);
	            if (currentPair.v != null)
	            {
	                for(Node pathPair: backpath.get(currentPair.v))
	                {
	                    if (pathPair.obstacle == previousObstacleVal)
	                    {
	                    	//if we have any node which has same obstacle, we will take that path
	                        next = pathPair;
	                        break;
	                    }
	                }
	                //if their is no neighbor with same obstacle, we will check current nodes obstacle 
	                //if current node has obstacle, we will reduce the previousobstacle by one
	                if (currentPair.v.IsObstacle)
	                {
	                    previousObstacleVal--;
	                }
	            }
	            currentPair = next;
	                    
	        }
	        //path;
	    }
	    return path;
	}
	
	public void printPath(List<GraphVertex> path) {
		
		for(int i=path.size()-2; i>=0;i--) {
			System.out.println(path.get(i).Id);
		}
	}
	
	public static void main(String args[]) {
		int[][] mat =
			    {
			        {0,0,0,0,0,1 },
			        {0,0,0,0,0,0 },
			        {0,0,0,0,0,0 },
			        {1,1,0,1,1,1 },
			        {1,0,1,1,1,1 },
			        {1,1,1,1,1,1 }
			 
			    };
		ShortestPathWithObstacleGrid hs = new ShortestPathWithObstacleGrid();
			    List<GraphVertex> path = hs.GetPath(mat, 2, 5, 0, 0, 5);
			    hs.printPath(path);
		         //boolean path = hs.GetPath(mat, 2, 5, 0, 0, 5);
		         //System.out.println(path);
			    //PrintPath(path);
			 
			    path = hs.GetPath(mat, 3, 5, 0, 0, 5);
			    //System.out.println(path);
			    //PrintPath(path);
			    hs.printPath(path);
			    path = hs.GetPath(mat, 1, 5, 0, 0, 5);
			    //System.out.println(path);
			   // PrintPath(path);
			    hs.printPath(path);
	}
	


}
