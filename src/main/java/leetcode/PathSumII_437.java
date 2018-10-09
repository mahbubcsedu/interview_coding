package leetcode;

public class PathSumII_437 {
 
	static public class TreeNode{
		int val;
		TreeNode left,right;
		public TreeNode(int v) {
			this.val=v;
			this.left=null;
			this.right=null;
		}
	}
	
	public static int getPathSum(TreeNode root, int k) {
		int count=0;
		if(root==null) return 0;
		
		int remaining=k-root.val;
		
		if(remaining==0)
			return 1;
		//we can start from root and have to use remaining balace
		count+= getPathSum(root.left,remaining) + getPathSum(root.right,remaining);
		//we can ignore root and start from starting balance
		count+=getPathSum(root.left,k) + getPathSum(root.right,k);
		return count;
	}
	
	public static void main(String args[]) {
		TreeNode t=new TreeNode(10);
		t.left=new TreeNode(5);
		t.right=new TreeNode(-3);
		t.left.left=new TreeNode(3);
		t.left.right=new TreeNode(2);
		t.left.left.left=new TreeNode(3);
		t.left.left.right=new TreeNode(-2);
		t.right.right=new TreeNode(1);
		t.right.right=new TreeNode(11);
		
		System.out.println(getPathSum(t,8));
	}
}
