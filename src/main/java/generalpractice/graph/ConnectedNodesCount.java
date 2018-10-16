package generalpractice.graph;

public class ConnectedNodesCount {
	private boolean[] visited;
	private int count;
	private int time;
	private int parent;
	
	public ConnectedNodesCount(Graph g, int s) {
		this.visited=new boolean[g.V()];
		parent=-1;
		dfs(g,s);
	}
	
	public void dfs(Graph g, int v) {
		this.count++;
		
		this.visited[v]=true;
		
		for(int w: g.adj(v)) {
			if(!this.visited[w]) {
				dfs(g,w);
			}
		}
	}
	
	public int getCount() {
		return this.count;
	}

	
	public static void main(String args[]) {
		Graph g=new Graph(5);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 2);
		g.addEdge(4, 3);
		
		
		ConnectedNodesCount cc=new ConnectedNodesCount(g,0);
		System.out.println(cc.getCount());
	}
}
