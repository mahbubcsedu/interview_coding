package hackerearth;
import java.util.*;

public class BlackAndWhite {
	
  static class Edge{
	  private int source;
	  private int dest;
	  private int weight;
	  public Edge(int s, int d, int w) {
		  this.source=s;
		  this.dest=d;
		  this.weight=w;
	  }
  }
  
  
  static class Graph{
       private int V;
       private int E;
      //private int color;
      private List<Edge>[] adj;
      private short[] color;
  
  Graph(int v){
	  this.V=v;
	  this.E=0;
	  
	  //initialize graph with all V vertices adjacent list
	  adj=new LinkedList[V];
	  this.color=new short[V];//for each vertex, keep a field of color 0 or 1
	  
	  for(int i=0;i<V; i++) {
		  adj[i]=new LinkedList<Edge>();
		  color[i]=0; //we can avoid intialize color and later update
	  }
  }
  
   private void addEdge(int u, int v, int w) {
	   Edge e=new Edge(u,v,w);
	   adj[u].add(e);
	   adj[v].add(e);
	   this.E++;
   }
   
   private void setColor(int v, short color) {
	   //this.color=color;
	   this.color[v]=color;
   }
   
   
   public int getShortestPath(int s, int d) {
	   //we can use dfs or bfs, bfs is more appropriate as we need to get multiple way 
	   //to go there
	   boolean[] visited=new boolean[this.V];
	   int[] dist=new int[this.V];
	   
	   Deque<Integer> q=new LinkedList<>();
	   q.addFirst(s);
	   visited[0]=true;
	   dist[s]=-1;
	   while(!q.isEmpty()) {
		   int node=q.pollFirst();
		  // if(node==d-1)
	   }
	   
	   return -1;
   }
   public String toString() {
	   StringBuilder sb=new StringBuilder();
	   sb.append("Edge:"+this.E+" Vertices: "+this.V);
	   for(int i=0; i<this.V;i++) {
		   sb.append(i+":->");
		   for(int j=0;j<adj[i].size();j++) {
			   sb.append(" ("+adj[i].get(j).dest+","+adj[i].get(j).weight+")");
		   }
		   sb.append("   color:"+color[i]+"\n");
	   }
	   
	   return sb.toString();
   }
  }
	
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		String fline=s.nextLine();
		StringTokenizer nodes= new StringTokenizer(fline);
		
		
		
		int nOfNodes=Integer.parseInt(nodes.nextToken());
		int nOfEdges=Integer.parseInt(nodes.nextToken());
		Graph g=new Graph(nOfNodes);
		
		
		for(int i=0;i<nOfEdges;i++) {
			StringTokenizer st=new StringTokenizer(s.nextLine());
			g.addEdge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
		}
		
		StringTokenizer st=new StringTokenizer(s.nextLine());
		int nnode=0;
		while(st.hasMoreTokens()) {
			g.setColor(nnode, Short.parseShort(st.nextToken()));
			nnode++;
		}
		
		System.out.println(g.toString());
		
	}
}
