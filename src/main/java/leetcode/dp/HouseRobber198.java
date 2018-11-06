package leetcode.dp;

public class HouseRobber198 {

	public static int rob(int[] nums) {

		if(nums.length==0)
			return 0;
		if(nums.length==1)
			return nums[0];

		return robHelper(nums,0);


		//return 0;

	}
	public static int robHelper(int nums[],int pos) {
		if(pos >=nums.length)
			return 0;

		return Math.max(nums[pos]+robHelper(nums,pos+2), robHelper(nums,pos+1));
		//return 0;
	}
	/**
	 * we don't need explanation at all why we need dp, many repeated calculations
	 * @param nums
	 * @return
	 */
	public static int robDp(int[] nums) {
		if(nums.length==0)
			return 0;
		if(nums.length==1)
			return nums[0];


		int dp[] =new int[nums.length];
		//dp[0]=nums[0];//if one item just pick
		//dp[1]=Math.max(nums[0], nums[1]);
		for(int i=0;i<nums.length;i++) 
		{
			if(i==0) 
			{
				dp[0]=nums[0];
				continue;
			}
			//else if(i==1)

			dp[i]=Math.max(i>1 ?nums[i]+dp[i-2]:nums[i], dp[i-1]); //current pos plus 2 pos before, or last pos and look forward
		}

		return dp[nums.length-1];

	}



	public static void main(String args[]) {
		int[] t1= {1,2,3,1};
		int[] t2= {2,7,9,3,1};

		if(rob(t1)==4)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}


		if(rob(t2)==12)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}


		if(robDp(t1)==4)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}


		if(robDp(t2)==12)
			System.out.println("correct");
		else {
			System.out.println("incorrect");
		}

	}
}
