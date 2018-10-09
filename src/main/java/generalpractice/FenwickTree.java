package generalpractice;

import java.util.Arrays;

/**
 * 
 * @author mahbub
 *Fenwick tree is array based tree which stores based on certain rules and does not use any link
 *node to left and right but only an array just like heap 
 */
public class FenwickTree {

	public static int[] buildTree(int[] nums) {
		//Fenwick tree requires one dummy node as a root, so, need n+1 size array
		int[] tree=new int[nums.length+1];
		for(int i=1;i<tree.length;i++) {
			updateBinaryIndexTree(tree, nums[i-1],i);
		}
		return tree;
	}
	
	public static void updateBinaryIndexTree(int[] tree, int value, int index) {
		while(index < tree.length) {
			//first add all child of 0 node
			// The value will be added to the currect node and all its parent node need to be updated
			//
			tree[index]+=value;
			index=getChild(index);
		}
	}
	/**
	 * 
	 * @param index current index
	 * @return nextChild index
	 * 1. First it will do 2's complement with minus
	 * 2. AND 2's complement with Original index
	 * 3. Add result with original index
	 * 
	 */
	public static int getChild(int index) 
	{
		return index + (index & -index);
		/*int childIndex=-index; 
		childIndex &=childIndex;
		childIndex= index+childIndex;
		return childIndex;*/
	}
	/**
	 * 
	 * @param index
	 * @return parentIndex
	 * 1. 2's complement to get minus
	 * 2. AND with original index
	 * 3. index-calculatedIndex to get parentIndex
	 */
	public static int getParent(int index) {
		return index - (index & -index);
		/*int parentIndex=-index;
		parentIndex &=parentIndex;
		parentIndex +=index;
		
		return parentIndex;*/
	}
	
	public static int getPrefixSum(int[] bitTree, int index) {
		int result=0;
		index=index+1; // the range should be from original array not tree array, so increase by 1 before processing
		while(index > 0 ) {
			result +=bitTree[index];
			index=getParent(index);
		}
		return result;
	}
	
	public static void main(String args[]) {
		int[] t=buildTree(new int[] {1,2,3,4,5,6,7,8,9});
		System.out.println(Arrays.toString(t));
		
		System.out.println(getPrefixSum(t,4)-getPrefixSum(t,2));
	}
}
