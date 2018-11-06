package leetcode.dp;

public class HouseRobberII_213 {

	/**
	 * In this problem, the house are organized in cricular
	 */

	public static int rob(int[] nums) {

		if(nums.length==0)
			return 0;

		if(nums.length==1)
			return nums[0];

		int fselection=nums[0]+robHelper(nums,2, nums.length-2);
		int fnotselected=robHelper(nums,1,nums.length-1);
		return Math.max(fselection, fnotselected);


	}
	public static int robHelper(int nums[],int pos, int endPos) {
		if(pos >endPos)
			return 0;

		return Math.max(nums[pos]+robHelper(nums,pos+2,endPos), robHelper(nums,pos+1,endPos));
		//return 0;
	}


	public static int robDp(int[] nums) {
		if(nums.length==0)
			return 0;
		if(nums.length==1)
			return nums[0];

		// calculate dp solutions from 0 to n-1 and another one from 1 to n and return which one is return max

		int dp[] =new int[nums.length];
		//dp[0]=nums[0];//if one item just pick
		//dp[1]=Math.max(nums[0], nums[1]);
		for(int i=0;i<nums.length-1;i++) 
		{
			if(i==0) 
			{
				dp[i]=nums[i];
				continue;
			}
			//else if(i==1)

			dp[i]=Math.max(i>1 ?nums[i]+dp[i-2]:nums[i], dp[i-1]); //current pos plus 2 pos before, or last pos and look forward
		}

		//avoid first elements
		int dpSecond[] =new int[nums.length];
		//dp[0]=nums[0];//if one item just pick
		//dp[1]=Math.max(nums[0], nums[1]);
		for(int i=1;i<nums.length;i++) 
		{
			if(i==1) 
			{
				dpSecond[i]=nums[i];
				continue;
			}
			//else if(i==1)

			dpSecond[i]=Math.max(i>2 ?nums[i]+dpSecond[i-2]:nums[i], dpSecond[i-1]); //current pos plus 2 pos before, or last pos and look forward
		}

		//boolean firstSelected=nums[0]+dp[2] > dp[1]? true:false;
		//boolean lastSelected=nums[nums.length-1]+dp[nums.length-3]>dp[nums.length-2] ? true:false;

		//if(firstSelected && lastSelected)
		//return dp[nums.length-1]-Math.min(nums[0], nums[nums.length-1]);
		//else 
		return Math.max(dp[nums.length-2],dpSecond[nums.length-1]);

	}

	//fastest solutions by monitoring excluding and including memory
	//as we only requires previous two results, we can avoid the dp memory and calculations
	public int robFast(int[] nums) {

		if (nums.length == 1) return nums[0];
		return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
	}

	public int rob(int[] nums,int low,int high)
	{
		int include=0,exclude=0;
		for(int j=low;j<=high;j++)
		{
			int i=include,e=exclude;
			include=e+nums[j];
			exclude=Math.max(i,e);
		}

		return Math.max(include,exclude);
	}


	public static void main(String args[]) {
		int[] t1= {1,2,3,1};
		int[] t4= {2,3,2};
		int[] t2= {2,7,9,3,1};
		int[] t3= {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
		/*
		if(rob(t1)==4)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}


		if(rob(t2)==11)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}
		 */


		if(robDp(t4)==3)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}


		if(robDp(t2)==11)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}

	}

}
