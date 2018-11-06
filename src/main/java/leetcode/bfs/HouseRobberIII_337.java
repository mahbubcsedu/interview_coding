package leetcode.bfs;

public class HouseRobberIII_337 {
	
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			this.val=x;
		}
	}
	/**
	 * the problem is very tricky, no direcly linked house can't be rob, but no problem robing in different levels,
	 * like a grandchild  
	 * 
	 *     2
	 *   1  3
	 *    \
	 *     4
	 *     
	 *     best 7 as 3 and 4 are not connected directly, the problem does not explain you that the parent child relation but directly connected
	 *     relation is mentioned
	 * @param root
	 * @return
	 */
	public static int rob(TreeNode root) {
		int[] maxWithWithoutRoot=robHelper(root);
		return Math.max(maxWithWithoutRoot[0], maxWithWithoutRoot[1]);
	}
	
	public static int[] robHelper(TreeNode root)
	{
		if(root==null) {
			//if root equl null, max with or without root is 0
			return new int[] {0,0};
		}
		
		int[] maxWithWithoutRoot=new int[2];
		
		//left root will return two value, sum with left child or sum without left child
		int[] leftMax=robHelper(root.left);
		int[] rightMax=robHelper(root.right);
		
		maxWithWithoutRoot[0]=Math.max(leftMax[0],leftMax[1])+ Math.max(rightMax[0],rightMax[1]);// + Math.max(rightMax[0], rightMax[1]);
		//leftmax[0] will exclude left child and leftmax[1]=will include left child
		maxWithWithoutRoot[1]=leftMax[0] + rightMax[0]+root.val;
		return maxWithWithoutRoot;
	}
	
	
	/**
	 * My understanding of the problem with recursion
	 * @param args
	 */
	
	public static int rob2(TreeNode root) {
		if(root==null)
			return 0;
		int robThisHouse=root.val;
		
		if(root.left!=null) {
			robThisHouse+=rob(root.left.left)+rob(root.left.right);
		}
		if(root.right!=null) {
			robThisHouse+=rob(root.right.left)+rob(root.right.right);
		}
		
		return Math.max(robThisHouse, rob(root.left)+rob(root.right));
		
	}
	
	public static void main(String args[]) {
		TreeNode root=new TreeNode(1);
		root.left=new TreeNode(8);
		root.right=new TreeNode(4);
		
		root.left.left=new TreeNode(2);
		root.right.left=new TreeNode(3);
		root.left.right=new TreeNode(2);
		root.right.right=new TreeNode(3);
		
		
		//root.left.left.left=new TreeNode(1);
		root.right.left.right=new TreeNode(3);
		//root.left.right.left=new TreeNode(5);
		//root.right.left.left=new TreeNode(2);
		
		System.out.println(rob(root));
		
		System.out.println(rob2(root));
		
	}
}
