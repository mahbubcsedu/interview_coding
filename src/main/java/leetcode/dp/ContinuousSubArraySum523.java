package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubArraySum523 {

public static boolean checkSubarraySum(int[] nums, int k) 
{
	return subArraySumHelper(nums,1,nums[0],k);
 }
	

/**
 * A good explanation of modulo zero @link https://math.stackexchange.com/questions/516251/why-is-n-mod-0-undefined
 * 
 * @param A
 * @param startPos
 * @param remaining
 * @param k
 * @return
 */

public static boolean subArraySumHelper(int[]A, int startPos, int remaining, int k) {
	//start from current position plus previous remainder and see whether this is divisible by k
	
	if(startPos==A.length) {
		return false;
	}
	//we started from previous number and current number to maintain minimum 2 elements
	int remainder=0;
	if(k!=0) {
      remainder=(remaining+A[startPos])%k;
	}
	else {
		remainder=(remaining+A[startPos]);
	}
	
	if(remainder==0) {
		return true;
	}
	//one recursion from current position and another one adding remainder and current pos
	//remaining itself maintain more than a element, while the other call start from current element and plus the next element
	
	boolean first=subArraySumHelper(A,startPos+1,remainder,k);
	boolean second=subArraySumHelper(A,startPos+1,A[startPos],k);
	
	return first || second;
   }

/**
 * Can we convert this to dynamic programming, lets see
 *memory limit exceeded
 * @param args
 */

public static boolean checkSubarraySumDp(int[] nums, int k) 
{
	//check invalid condidtion
	if(nums.length<2)
		return false;
	
	int[][] dp=new int[nums.length][nums.length];
	
	for(int i=0; i<nums.length;i++) {
		for(int j=i;j<nums.length;j++) {
			
			if(i==j) {
				dp[i][j]=nums[i];
			}
			else if(k!=0) {
				dp[i][j]=(dp[i][j-1]+nums[j])%k;
				
			}else 
			{
			dp[i][j]=dp[i][j-1]+nums[j];
			}
			
			
			if(i!=j && dp[i][j]==0) 
				return true;
		}
	}
	return false;
 }
//TODO need to find a way to limit memory usage

public static boolean checkSubarraySumDpLessMemory(int[] nums, int k) 
{
	//check invalid condidtion
	if(nums.length<2)
		return false;
	
	int[][] dp=new int[nums.length][nums.length];
	
	for(int i=0; i<nums.length;i++) {
		for(int j=i;j<nums.length;j++) {
			
			if(i==j) {
				dp[i][j]=nums[i];
			}
			else if(k!=0) {
				dp[i][j]=(dp[i][j-1]+nums[j])%k;
				
			}else 
			{
			dp[i][j]=dp[i][j-1]+nums[j];
			}
			
			
			if(i!=j && dp[i][j]==0) 
				return true;
		}
	}
	return false;
 }


/*
 * We iterate through the input array exactly once, keeping track of the running sum 
 * mod k of the elements in the process. If we find that a running sum value at index j has been previously 
 * seen before in some earlier index i in the array, then we know that the sub-array (i,j] contains a desired sum.
 */

public static boolean checkSubarraySumHash(int[] nums, int k) 
{
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);// if we see zero, we will take that as the match
    
    int sum_until_now = 0;
    for (int i=0;i<nums.length;i++) {
    	sum_until_now += nums[i];
        if (k != 0) sum_until_now %= k; 
        Integer prev = map.get(sum_until_now);
        if (prev != null) {
            if (i - prev > 1) return true;
        }
        else map.put(sum_until_now, i);
    }
    return false;
}

public static void testHash() {
	int[] input= {6,23, 2, 3, 6, 7};
	boolean t=checkSubarraySumHash(input,6);
	System.out.println(t);
}

public static void testDp() {
	int[] input= {23, 2, 4, 6, 7};
	int[] input2= {23, 2, 6, 4, 7};
	int[] input3= {0,1,0};
	int[] leetinput= {18,373,97,499,525,170,133,376,77,279,257,168,319,335,237,36,236,41,360,131,172,279,405,236,296,377,348,411,135,411,273,230,103,274,211,427,101,243,31,485,282,40,28,81,6,318,502,521,140,110,467,248,404,107,108,129,113,113,333,83,242,194,112,446,463,102,145,107,73,47,53,455,301,150,314,13,180,119,234,509,199,503,69,224,228,427,309,497,342,329,518,35,425,343,417,509,85,234,426,3342,50};
	
	boolean t=checkSubarraySumDp(input3,0);
	boolean t2=checkSubarraySumDp(input,6);
	System.out.println(t);
	System.out.println(t2);
}


public static void testRecursive() {
	int[] input= {23, 2, 4, 6, 7};
	int[] input2= {23, 2, 6, 4, 7};
	int[] input3= {0,1,0};
	int[] leetinput= {18,373,97,499,525,170,133,376,77,279,257,168,319,335,237,36,236,41,360,131,172,279,405,236,296,377,348,411,135,411,273,230,103,274,211,427,101,243,31,485,282,40,28,81,6,318,502,521,140,110,467,248,404,107,108,129,113,113,333,83,242,194,112,446,463,102,145,107,73,47,53,455,301,150,314,13,180,119,234,509,199,503,69,224,228,427,309,497,342,329,518,35,425,343,417,509,85,234,426,3342,50};
	boolean t=checkSubarraySum(input3,0);
	boolean t2=checkSubarraySum(input,6);
	System.out.println(t);
	System.out.println(t2);
}

public static void main(String args[]) {
	//testRecursive();
	//testDp();
	testHash();
}

}