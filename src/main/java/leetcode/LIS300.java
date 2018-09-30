package leetcode;

public class LIS300 
{
   /**
    * The wrong way I thought, but its not possible if you can see the second example
    * with 3,2,6,4,5
    */
	public static int getLongestIncreasingSubSequence(int[] nums) {
		int maxLen=0;
		int curLen=0;
		for(int i=0;i<nums.length;++i) {
			curLen=1;
			int lastElements=nums[i];
			for(int j=i+1; j<nums.length;++j) {
				if(lastElements < nums[j])
				{
					curLen++;
					lastElements=nums[j];
					System.out.println(curLen+" included "+nums[i] + " < " + nums[j]);
				}
			}
			maxLen=Math.max(maxLen, curLen);
		}
		
		return maxLen;
	}
	/**
	 * brute force algorithm:
	 * The brute force algorithm is to find all the subset which meet the condition of increasing
	 * Then for each subset, find the max len
	 * The cost is enormous as it requires to generate all the subset which is sum_i(N!/i!(N-i)! which is O(2^n)
	 * @param args
	 */
	public static int getLongIncSubseqBForce(int[] nums) {
		//TODO:
		
		return 0;
	}
	
	public static int getLongInSubseqRecur(int[] nums) {
		//TODO:
		return 0;
	}
	
	public static int getLongInSubseqDP(int[] nums) {
		//TODO:
		
		return 0;
	}
	public static void main(String args[]) 
	{
		
	//	LongestIncreasingSubSequence300 obj=new LongestIncreasingSubSequence300();
		
		if(4==getLongestIncreasingSubSequence(new int[] {10,9,2,5,3,7,101,18})) 
		{
			System.out.println("correct");
		
		}else
		{
			System.out.println("incorrect");
		}
		
		if(4==getLongestIncreasingSubSequence(new int[] {3,2,6,4,5})) 
		{
			System.out.println("correct");
		
		}else
		{
			System.out.println("incorrect");
		}
	}
}
