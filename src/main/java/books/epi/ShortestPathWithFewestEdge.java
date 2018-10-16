package books.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This shortest path will be from source to destination where source to destination 
 * will have minimum weight or distance and also fewest edges. But, we have to maintain that
 * the distance will be first considered and then number of edges.
 * @author mahbub
 *
 */
public class ShortestPathWithFewestEdge {

	/**
	 *we will create class of vertex with distance where each vertex will have it distance from
	 *the source
	 *
	 *vertexwith distance will keep adjacent list of vertex and its distance from from the source while enumerating
	 *Initially it will have the weight
	 *
	 *How we can define the graph for this case,
	 *1. We can define the graph as a link list which could have cycle and which may have multiple adjacent nodes.
	 *2. The list will consists of VertexWithDistance so that we dont need to care about edge as well and at the same time can 
	 *keep the distance of this vertex from source
	 *3. Each vertex With distance will have a field called distance and a Graphvertex which will handle all other properties.
	 *4. The vertex  will have list of VertexWithDistance node or the graph node of same properties, intialize by the distance and their neighbor.
	 *5. We will not add any properties here to add Edge or vertex as we will build the graph once
	 *6. Each GraphVertex which is part of actual node will have a object keeping distance as well as minNumEdges from the source, this is important here
	 *7. Also this will keep an ID for the vertex and its parent which is intially null
	 *8. also we will define a comparable function to handle this using heap or sorting collections
	 */
	
	 static class VertexWithDistance{
		Integer distance;
		GraphVertex vertex;
		
		public VertexWithDistance(GraphVertex vertex, int disance) {
			this.distance=distance;
			this.vertex=vertex;
		}
	}
	
	/**
	 * we will define another class to keep the information of distance and number of endge of the vertex rom the source
	 * 
	 */
	 static class DistanceWithFewestEdge {
		Integer distance;
		public Integer minNumEdge;
		
		DistanceWithFewestEdge(Integer distance, Integer minNumEdge){
			this.distance=distance;
			this.minNumEdge=minNumEdge;
		}
	}
	
	 /**
	  * This class is the Vertex class with the properties of distance and vetex, then distance to min edge
	  * we need two class as we not only have to provide weight of the edge but also the number of edge from the source
	  * @author mahbub
	  *
	  */
	public static class GraphVertex implements Comparable<GraphVertex>{
		//intialize a vertex with infinite distance and 0 number of edge as it's distance from itself is 0;
		public DistanceWithFewestEdge dfe;
		//we will keep the list of GraphVertex of all the adjacent vertex
		public List<VertexWithDistance> edges;
		public int id; //id of this vertex, we also can keep the label
		public GraphVertex pred;//=null; //the parent of in the shortest path
		
		
		public GraphVertex(int id) {
			this.id=id;
			edges=new ArrayList<>();
			dfe=new DistanceWithFewestEdge(Integer.MAX_VALUE,0);
			pred=null;
		}
		
		/**
		 * we will define the comparable functions to sort the vertex in sorted collections based on these condidtions
		 * 1. the distance is the first key to compare, if both are equal, then it will compare based on number of edges
		 */
		
		@Override
		public int compareTo(GraphVertex o) {
			if(dfe.distance!=o.dfe.distance) {
				return Integer.compare(dfe.distance, o.dfe.distance);
			}
			if(dfe.distance==o.dfe.distance) {
				return Integer.compare(dfe.minNumEdge, o.dfe.minNumEdge);
			}
			return Integer.compare(id, o.id);
		}
		
		//we weill also write method ot compare two objects and it requires 4 diferent section of implements
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof GraphVertex))
				return false;
			
			if(this==obj)
				return true;
			GraphVertex that=(GraphVertex)obj;
			return id==that.id &&
					dfe.distance.equals(that.dfe.distance) &&
					dfe.minNumEdge.equals(that.dfe.minNumEdge);
			
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(dfe.distance, dfe.minNumEdge);
		}
	
		
	}//end of GraphVertex class
	
	
	public static void dijstrajShortestPath(GraphVertex s, GraphVertex d) {
		//Source vertex will start with 0 distance and 0 edge
		s.dfe=new DistanceWithFewestEdge(0,0);
		
		//now define a minHeap where the neighbours vertex will be stored by sorted order provided in the previous class
		SortedSet<GraphVertex> nodeSet=new TreeSet<>();
		//its a BFS search but the neighbors will be sorted as greedy algorithm will be applied on them
		nodeSet.add(s);
		while(!nodeSet.isEmpty()) {
			//it will not remove but will compare while in the set
			GraphVertex u=nodeSet.first();
			if(u.equals(d))
				break;
			
			//now remove as we will process it now, and if required will add again updating its distance and edge
			nodeSet.remove(nodeSet.first());
			
			for(VertexWithDistance v: u.edges) {
				//the distance will be u distance from source + v distance, 
				int vDist=u.dfe.distance+v.distance;
				int vNumEdges=u.dfe.minNumEdge+1;
				
				if(v.vertex.dfe.distance > v.distance || (v.vertex.dfe.distance==vDist 
						&& v.vertex.dfe.minNumEdge > vNumEdges)) {
					nodeSet.remove(v);
					v.vertex.pred=u;
					v.vertex.dfe=new DistanceWithFewestEdge(vDist,vNumEdges);
					nodeSet.add(v.vertex);
				}
				
			}
		}
		
		outputShortestPath(d);
	}
	
	private static void outputShortestPath(GraphVertex v) {
		if(v!=null) {
			outputShortestPath(v.pred);
			System.out.println(v.id+" ");
		}
	}
	
	public static void buildGraph(List<List<Integer>> graph) {
		List<GraphVertex> g=new ArrayList<>();
		
		for(List<Integer> edge: graph) {
			if(isPresent(edge.get(0),g)==null) {
				GraphVertex v=new GraphVertex(edge.get(0));
				
				g.add(v);
			}
		}
		
		
	}
	
	public static GraphVertex isPresent(int vertexId, List<GraphVertex> g) {
		for(GraphVertex v: g) {
			if(vertexId==v.id)
				return v;
		}
		return null;
	}
	
	public static void main(String args[]) {
		/*GraphVertex v=new GraphVertex(1);
		GraphVertex v=new GraphVertex(1);
		GraphVertex v=new GraphVertex(1);
		GraphVertex v=new GraphVertex(1);
		GraphVertex v=new GraphVertex(1);
		
		GraphVertex v=new GraphVertex(1);
		GraphVertex v=new GraphVertex(1);
		GraphVertex v=new GraphVertex(1);
		
		
		v.edges*/
	}
	
}
