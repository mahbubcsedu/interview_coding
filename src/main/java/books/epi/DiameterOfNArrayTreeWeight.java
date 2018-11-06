package books.epi;

import java.util.ArrayList;
import java.util.List;
/**
 * This is one of the important and hard problem and usually ask as to find the max distance between two nodes, 
 * This is weighted, try one without using weight or weight only 1
 * @author mahbub
 *
 */
public class DiameterOfNArrayTreeWeight {

	//Node does not anything label or weight
	public static class TreeNode{
		List<Edge> edges=new ArrayList<>();
	
	}
	
	//each child will consists of a node and its legnth from its parent
	public static class Edge{
		TreeNode root;
		Double length;
		
		public Edge(TreeNode root, Double length) {
			this.root=root;
			this.length=length;
		}
	}
	
	
	private static class HeightAndDiameter{
		private Double height;
		private Double diameter;
	
		public HeightAndDiameter(Double height, Double diameter) {
			this.height=height;
			this.diameter=diameter;
		}
		
	}
	
	
	public static double computeDiameter(TreeNode T) {
		return T !=null ? computeHeightAndDiameter(T).diameter:0;
	}
	
	public static HeightAndDiameter computeHeightAndDiameter(TreeNode r) {
		double diameter=Double.MIN_VALUE;
		double[] height= {0.0,0.0};
		//height[0] max and height[1] second max
		for(Edge e :r.edges) {
		
			HeightAndDiameter hd=computeHeightAndDiameter(e.root);
			if(hd.height + e.length > height[0]) {
				height[1]=height[0]; //move max to second max
				height[0]=hd.height+e.length;
			}
			else if(hd.height+e.length > height[1]) {
				height[1]=hd.height+e.length;// if current height greater than second max, just replace
			}
			
		
		
		//after processing each subtree height, process diameter
		diameter=Math.max(diameter, hd.diameter);//this keeps the diameter of each subtree and will be used to compare next diameter
	   }
		//After processing all children of root r, return only the max
		//max height is the  distance from root to farthest child
		//diameter is the max distance between any two nodes
		
		//if any subtree diameter is largest than the combination of top two passing through root, and return the appropriate diameter
		return new HeightAndDiameter(height[0],Math.max(diameter, height[0]+height[1]));
		
		
	}
	
	
	public static void buildTree() {
		TreeNode r = null;
	    assert(0.0 == computeDiameter(r));
	    /**
	     *      r
	     *    /  \
	     *    10  20
	     *   / 
	     *  50
	     */  
	       
	    r = new TreeNode();
	    r.edges.add(new Edge(new TreeNode(), 10.0));
	    r.edges.get(0).root.edges.add(new Edge(new TreeNode(), 50.0));
	    r.edges.get(0).root.edges.add(new Edge(new TreeNode(), 40.0));
	    r.edges.add(new Edge(new TreeNode(), 20.0));
	    assert(90 == computeDiameter(r));
	    System.out.println(computeDiameter(r));
	    r.edges.get(0).root.edges.add(new Edge(new TreeNode(), 100.0));
	    assert(150 == computeDiameter(r));
	    System.out.println(computeDiameter(r));
	}
	
	public static void main(String args[]) {
		buildTree();
	}
	
}