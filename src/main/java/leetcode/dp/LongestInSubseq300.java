package leetcode.dp;

import java.util.Arrays;

public class LongestInSubseq300 {
	
	public static int getLongestIncreasingSub(int[] nums) {
		
		if(nums.length==0)
			return 0;
		
		int L[]=new int[nums.length];
		int maxLen=1;
		Arrays.fill(L, 1);
		
		for(int i=1;i<nums.length;i++) {
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j]) {
					L[i]=Math.max(L[i], L[j]+1);
					maxLen=Math.max(L[i], maxLen);
				}
			}
		}
		return maxLen;
	}
	
	/**
	 * This is leetcode fastest way, later look at this binary search process 
	 * {@link https://leetcode.com/problems/longest-increasing-subsequence/solution/}
	 * @param args
	 */
	
	public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        
        for(int n : nums){
            int i = binarySearch(dp, max, n);
            dp[i] = n;
            if(i == max) max++;
        }
        
        return max;
    }
    
    public int binarySearch(int[] dp, int right, int target){
        int left = 0;
        if(target < dp[0]) return 0;
        if(target > dp[Math.max(0, right - 1)]) return right;
        
        while(left < right){
            int middle = (left + right) / 2;
            if(target > dp[middle]){
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        
        return left;
    }
 public static void main(String args[]) {
	 System.out.println("Hello word");
	 
	 System.out.println(getLongestIncreasingSub(new int[] {0,8,4,12,2,10,6,14,1,9}));
	 System.out.println(getLongestIncreasingSub(new int[] {10,9,2,5,3,7,101,18}));
	 System.out.println(getLongestIncreasingSub(new int[] {1,3,6,7,9,4,10,5,6}));
	 System.out.println(getLongestIncreasingSub(new int[] {0}));
 }
}
