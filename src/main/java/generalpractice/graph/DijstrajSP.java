package generalpractice.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DijstrajSP {
	
	private Set<Node> nodes=new HashSet<>();
	
	
	
	public void addNode(Node nodeA) {
		nodes.add(nodeA);
	}
	
	public Set<Node> getNodes(){
		return this.nodes;
	}
	public static class Node implements Comparable<Node>{
		private Integer id;
		private List<Node> shortestPath = new LinkedList<>();
		private int distance = Integer.MAX_VALUE;
		Map<Node, Integer> adjNodes=new HashMap<>();
		public void addDestination(Node dest, int distance) {
			adjNodes.put(dest, distance);
		}
		
		public Node(int id) {
			this.id=id;
		}
		
		public int getId() {
			return this.id;
		}
		public void setDistance(int distance) {
			this.distance=distance;
		}
		
		public int getDistance() {
			return this.distance;
		}
		
		public Map<Node, Integer> getAdjList(){
			return this.adjNodes;
		}
		public List<Node> getShortestPath(){
			return this.shortestPath;
		}
		
		public void setShortestPath(List<Node> s) {
			this.shortestPath=s;
		}

		
		
		@Override
		public boolean equals(Object obj) {
			if(obj==null || !(obj instanceof Node))
				return false;
			if(this==obj)
				return true;
			
			Node that=(Node)obj;
			
			return id==that.id && this.getDistance()==that.getDistance();
		}
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public int compareTo(Node o) {
			if(this.getDistance()!=o.getDistance())
				return Integer.compare(this.distance, o.distance);
			// TODO Auto-generated method stub
			return Integer.compare(this.getId(),o.getId());
		}
	}
	
	public  DijstrajSP djShortestPath(DijstrajSP graph, Node source) {
		
		source.setDistance(0);
		
		Set<Node> settleNodes = new HashSet<>();
		//Set<Node> unsettleNodes = new HashSet<>();
		SortedSet<Node> unsettleNodes = new TreeSet<>();
		
		unsettleNodes.add(source);
		
		while(unsettleNodes.size()!=0) {
			//Node curr=getLowestDistance(unsettleNodes);
			Node curr=unsettleNodes.first();
			unsettleNodes.remove(curr);
			
			for(Entry<Node, Integer> adjPair: curr.getAdjList().entrySet()) {
				Node adjNode=adjPair.getKey();
				Integer edgeWeight= adjPair.getValue();
				
				if(!settleNodes.contains(adjNode)) {
					Relax(adjNode, edgeWeight, curr);
					unsettleNodes.add(adjNode);
				}
			}
			settleNodes.add(curr);
		}
		
		//printAllPath(curr);
		return graph;
		
	}
	/**
	 * This function will do relax as described in corement
	 * @param evalNode
	 * @param edgeWeight
	 * @param sNode
	 */
	public static void Relax(Node evalNode, int edgeWeight, Node sNode) {
		Integer sourceDistance=sNode.getDistance();
		if(sourceDistance + edgeWeight < evalNode.getDistance()) {
			evalNode.setDistance(sourceDistance+edgeWeight);
			LinkedList<Node> shortestPath= new LinkedList<>(sNode.getShortestPath());
			shortestPath.add(sNode);
			evalNode.setShortestPath(shortestPath);
		}
	}
	/**
	 * We will remove this using TreeSet and Comparable Function implementations
	 * @param unsettleNodes
	 * @return
	 */
	public static Node getLowestDistance(Set<Node> unsettleNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance=Integer.MAX_VALUE;
		for(Node node:unsettleNodes) {
			int nodeDistance=node.getDistance();
			if(nodeDistance < lowestDistance) {
				lowestDistance =nodeDistance;
				lowestDistanceNode=node;
			}
		}
		
		return lowestDistanceNode;
	}
	
	
	public static void main(String args[]) {
		
		Node nodeA = new Node(1);
		Node nodeB = new Node(2);
		Node nodeC = new Node(3);
		Node nodeD = new Node(4); 
		Node nodeE = new Node(5);
		Node nodeF = new Node(6);

		nodeA.addDestination(nodeB, 10);
		nodeA.addDestination(nodeC, 15);
		 
		nodeB.addDestination(nodeD, 12);
		nodeB.addDestination(nodeF, 15);
		 
		nodeC.addDestination(nodeE, 10);
		 
		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);
		 
		nodeF.addDestination(nodeE, 5);
		 
		DijstrajSP graph = new DijstrajSP();
		 
		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);
		 
		graph = graph.djShortestPath(graph, nodeA);
		
		
		List<Node> path=nodeF.getShortestPath();
		System.out.println(nodeF.distance);
		
		Iterator<Node> it=path.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getId());
		}
		
		
		Set<Node> sl=graph.getNodes();
		Iterator<Node> it2 =sl.iterator();
		while(it2.hasNext()) {
			Node c=it2.next();
			List<Node> list=c.getShortestPath();
			System.out.println(c.getId()+" -"+c.getDistance());
		}

	}
	/*

	public static class GraphVertex implements Comparable<GraphVertex>{

		public List<GraphVertex> edges;
		public int id;
		public GraphVertex pred=null;
		public int distance;
		
		public GraphVertex(int id) {
			this.id=id;
			distance=Integer.MAX_VALUE;
			edges=new ArrayList<>();
		}
		
		@Override
		public int compareTo(GraphVertex o) {
			if(distance!=o.distance)
				return Integer.compare(this.distance, o.distance);
			
			// TODO Auto-generated method stub
			return Integer.compare(id, o.id);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof GraphVertex)) {
				return false;
			}
			if(this==obj)
				return true;
			GraphVertex that=(GraphVertex)obj;
			
			return id==that.id && distance==that.distance;
			
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(distance);
		}
	}
	
	
	
	
	public static void dijstrajSP(GraphVertex s, GraphVertex t) {
		s.distance=0;
		SortedSet<GraphVertex> nodeSet=new TreeSet<>();
		
		nodeSet.add(s);
		
		while(!nodeSet.isEmpty()) {
			GraphVertex u=nodeSet.first();
			if(u.equals(t)) {
				break;
			}
			
			nodeSet.remove(nodeSet.first());
			
			//Relax
			for(GraphVertex v : u.edges) {
				int vDist=u.distance+v.distance;
				if(v.distance > vDist) {
					nodeSet.remove(v);
					v.pred=u;
					nodeSet.add(v);
				}
			}
		}
		
		outputShortestPath(t);
	}
	
	private static void outputShortestPath(GraphVertex v) {
		if(v!=null) {
			outputShortestPath(v.pred);
			System.out.println(v.id + " ");
		}
	}
	
	
	
	public static void main(String args[]) {
	 GraphVertex n1=new GraphVertex(1);
	 
	}
	*/
}
