package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllBinaryTrees {
	
	static class BinaryTreeNode{
		int val;
		BinaryTreeNode left,right;
		BinaryTreeNode(int v,BinaryTreeNode left, BinaryTreeNode right){
			this.val=v;
			this.left=left;
			this.right=right;
		}
	}
	/**
	 * The cost of this algorithm is Catalan number because, for each number there C(n)=sum(i,n)(C(n-i)C(i-1) which can 
	 * be proved as 2n!/n!(n+1)!
	 * @param numOfNodes
	 * @return
	 */
	public static List<BinaryTreeNode> generateAllBinaryTrees(int numOfNodes){
		
		List<BinaryTreeNode> res=new ArrayList<>();
		
		if(numOfNodes==0)
			res.add(null);
		
		for(int numOfLeftNodes=0;numOfLeftNodes < numOfNodes;numOfLeftNodes++) {
			//keep 1 node for root, and rest of the nodes for right subtree
			int numOfRightNodes=numOfNodes-1-numOfLeftNodes;
			
			List<BinaryTreeNode> leftSubTree=generateAllBinaryTrees(numOfLeftNodes);
			List<BinaryTreeNode> rightSubTree=generateAllBinaryTrees(numOfRightNodes);
			
			for(BinaryTreeNode left: leftSubTree) {
				for(BinaryTreeNode right:rightSubTree) {
					res.add(new BinaryTreeNode(0,left,right));
				}
			}
		}
		return res;
	}
	
	
	public static void test2() {
		 List<BinaryTreeNode> res=generateAllBinaryTrees(3);
		 //need to print or display in way like serialize or deserialize way, will try later
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void test3() {
		List<BinaryTreeNode> res=generateAllBinaryTrees(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	
	public static void test4() {
		List<BinaryTreeNode> res=generateAllBinaryTrees(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		//test1();
		test2();
	}
}
