package leetcode;

import java.util.Arrays;

public class PartitionKEqualSumSubset698 {
 
	/**
	 * NP-complete problem, O(n^(n-k),k!
	 * @param nums
	 * @param k
	 * @return
	 */
	public static boolean canPartitionKSubsets(int[] nums, int k) {
		int sum=0;
		for(int n:nums) {
			sum+=n;
		}
		//check if it is actually possible to divide mathematically
		if(sum%k > 0) return false;
		
		int targetSum=sum/k;
		//sort the array so that we can start from bigger item
		Arrays.sort(nums);// it is not necessary but a step to get the result early or pruning the search space
		
		int row=nums.length-1;
		
		//a single value is greater than the target sum
		if(nums[row] > targetSum) return false;
		//a single item may fullfill a group {1,3,2} with k=2, 3 is already a partition, so we will concentrate one remaining groups
		//so the number of groups also will less 
		while(nums[row]==targetSum) {
			row--;
			k--;
		}
		
		// now we can use backtracking process to search all possible space
		
		return search(new int[k], row, nums, targetSum);
		
	}
	//we will keep a list of array where each array will keep temp sum of each set
	public static boolean search(int[] tempGroupSum,int row,int[] nums,int targetSum ) {
		if(row < 0) return true; //we already processed all the value and never failed to meet the condition
		
	   int nextVal=nums[row--];
	   for(int i=0; i<tempGroupSum.length;i++) {
		   //we will try to add this value to each group and will search for the rest with this condition
		   if(tempGroupSum[i]+nextVal <=targetSum) {
			   tempGroupSum[i]+=nextVal;
			   if(search(tempGroupSum,row,nums,targetSum)) return true;
			   //if this branches failes, we will remove the last inserted item from this group
			   tempGroupSum[i]-=nextVal;
		   }
		   //see the example {1,2,3} again, when we are in group 1, we have 3 only and we are processing for group 1, then group 2, but group 
		   //2 is empty as nothing is inserted yet, so we can avoid this loop
		   if(tempGroupSum[i]==0) break;
	   }
		return false;
	}
	
	public static void main(String args[]) {
		int[] nums= {1,4,3,2};
		boolean possible=canPartitionKSubsets(nums,2);
		System.out.printf("the subset is : %b",possible);
	}
}
