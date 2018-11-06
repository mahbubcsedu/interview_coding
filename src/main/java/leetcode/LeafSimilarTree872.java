package leetcode;

import java.util.List;
import java.util.ArrayList;

public class LeafSimilarTree872 {

	
	public static class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
		}
	
	
	
	   public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
		   
		   List<Integer> leaves1=new ArrayList<>();
		   List<Integer> leaves2=new ArrayList<>();
		   
		   getLeaves(root1,leaves1);
		   getLeaves(root2,leaves2);
		   
		   return leaves1.equals(leaves2);
     }
	   
	   
	   static void getLeaves(TreeNode root, List<Integer> leaves) {
		   if(root==null)
			   return;
		   if(root.left==null && root.right==null)
			   leaves.add(root.val);
		  getLeaves(root.left,leaves);
		  getLeaves(root.right,leaves);
			   
	   }



	   
	   public static void main(String args[]) {
		   TreeNode t=new TreeNode(3);
		   
		   t.left=new TreeNode(5);
		   t.right=new TreeNode(1);
		   
		   t.left.left=new TreeNode(6);
		   
		   t.left.right=new TreeNode(2);
		   
		   t.left.right.left=new TreeNode(7);
		   t.left.right.right=new TreeNode(4);
		   
		   t.right.left=new TreeNode(9);
		   t.right.right=new TreeNode(8);
		   
	
		   
		   
          TreeNode t2=new TreeNode(3);
		   
		   t2.left=new TreeNode(5);
		   t2.right=new TreeNode(1);
		   
		   
		   
		   t2.left.left=new TreeNode(2);
		   t2.left.left.left=new TreeNode(6);
		   
		   t2.left.left.right=new TreeNode(7);
		   t2.left.right=new TreeNode(4);
		   
		   t2.right.left=new TreeNode(9);
		   t2.right.right=new TreeNode(8);
	
		   System.out.println(leafSimilar(t,t2) ? "true":"false");
		   
	   }
       
}