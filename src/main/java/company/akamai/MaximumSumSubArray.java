package company.akamai;

public class MaximumSumSubArray {

	public static int getMaxSubArraySum(int[] nums) {

		/*
		 * This problems arises only when there is negative and positive number in the array
		 * if all numbers are positive max sum should be the total sum of the array but if ther
		 * is negative positive and even 0 in the array, then we can apply Kadane's algorithm
		 * 
		 * Algorithm:
		 * if the current number is greater than current max, the localmax should be the current
		 * number or should starts from current number
		 */

		int currentMax=nums[0];

		int finalMax=nums[0];

		for(int i=1; i< nums.length;i++) 
		{

			currentMax=Math.max(nums[i], nums[i]+currentMax);
			//System.out.println("current elements:"+nums[i]);
			//System.out.println(currentMax);

			finalMax=Math.max(currentMax, finalMax);
			//System.out.println(currentMax+" "+finalMax);
		}
		return finalMax;
	}


	/**
	 * Initialize:
    max_so_far = 0
    max_ending_here = 0

Loop for each element of the array
  (a) max_ending_here = max_ending_here + a[i]
  (b) if(max_ending_here < 0)
            max_ending_here = 0
  (c) if(max_so_far < max_ending_here)
            max_so_far = max_ending_here
return max_so_far
	 */
	
	public static int getMaxSubArraySum2(int[] nums) {
		int maxEndingHere=0;
		int maxEndingSoFar=0;
		
		for(int i=0;i<nums.length;i++) {
			maxEndingHere=maxEndingHere+nums[i];
			if(maxEndingHere < 0)
				maxEndingHere=0;
			if(maxEndingSoFar < maxEndingHere)
				maxEndingSoFar=maxEndingHere;
		}
		return maxEndingSoFar;
	}
}
