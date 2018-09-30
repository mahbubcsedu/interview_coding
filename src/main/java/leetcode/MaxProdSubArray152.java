package leetcode;

public class MaxProdSubArray152 {

	//this is damm wrong as 
	 public static int maxProduct(int[] nums) {
		 int maxP=nums[0];
		 int res=nums[0];
		 int curMax=nums[0];
		 
		 for(int i=1;i<nums.length;i++) {
		   curMax=Math.max(curMax*nums[i],nums[i]);
		   maxP=Math.max(maxP, curMax);
		 }
		 return maxP;
		 
	 }
	 
	 /**
	  * we can do it by memorization of each steps
	  * @param nums
	  * @return
	  */
	 public static int maxProductDP(int[] nums) {
		 int maxP=nums[0];
		 int[] dp=new int[nums.length];
		 //int curMax=nums[0];
		 dp[0]=nums[0];
		 
		 for(int i=1;i<nums.length;i++) {
		   dp[i]=Math.max(dp[i-1]*nums[i], nums[i]);
		   maxP=Math.max(dp[i],maxP);
		 }
		 return maxP;
		 
	 }
}
