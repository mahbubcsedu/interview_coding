package leetcode.dp;

import java.util.Arrays;

public class InterleavingString {
    /**
     * The brute force algorithm is to check for each combination using recursion. First look at few examples, how they are built
     * abc, bcd abcbdc
     * 
     * a bc b d c are the substring from two string which forms the thirds. So, the relation position of each substring
     * should be intact.
     * {@link https://leetcode.com/problems/interleaving-string/solution/} has the solutions of both recursive and dynamic
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
	public static boolean isInterLeavingString(String s1, String s2, String s3) {
		//we will start with empty result, 0th position from both string
		return isInterLeaving(s1,0,s2, 0, "",  s3);
		//return true;
	}
	//to better understand, draw a recursive tree and see all the functions call
	//as this will add one character at a time, it will maintain the actual character order,
	//we can try use stringbuilder instead of string ,but requires to convert to string each time to check equality
	public static boolean isInterLeaving(String s1,int s1pos, String s2, int s2pos, String result, String s3) 
	{
		if(result.equals(s3) && s1.length()==s1pos && s2.length()==s2pos)
			return true;
		
		boolean isTempScramble=false;
		
		if(s1pos < s1.length()) {
			isTempScramble |= isInterLeaving(s1,s1pos+1,s2,s2pos,result+s1.charAt(s1pos),s3);
		}
		if(s2pos < s2.length()) {
			isTempScramble |= isInterLeaving(s1,s1pos,s2,s2pos+1,result+s2.charAt(s2pos),s3);
		}
		return isTempScramble;
	}
	/**
	 * Both time complexity will be O(2^(m+m)).m as we will have to 
	 * monitor all substring of all size from m+n and also requires to compare with one of the string
	 * s1 as we will always start with that
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	
	public static boolean isInterLeavingMemo(String s1,String s2,String s3) 
	{
		int[][] memo=new int[s1.length()][s2.length()];
		
		for(int[] row: memo) {
			Arrays.fill(row, -1);
		}
		
		return isInterLeavingMemoHelper(s1,0,s2, 0, s3, 0, memo);
	}
	
	public static boolean isInterLeavingMemoHelper(String s1,int s1pos,String s2,int s2pos,String s3, int k, int[][] memo) {
		if(s1pos==s1.length()) {
			 return s2.substring(s2pos).equals(s3.substring(k));
		}
		if(s2pos==s2.length()) {
			 return s1.substring(s1pos).equals(s3.substring(k));
		}
		//if we have in memory processed already of s1pos and s2pos, then we will return based on the previous calculation
		if(memo[s1pos][s2pos]>=0) {
			return memo[s1pos][s2pos]==1 ? true: false;
		}
		boolean tempRes=false;
		
		if(s3.charAt(k)==s1.charAt(s1pos) && isInterLeavingMemoHelper(s1,s1pos+1,s2,s2pos,s3,k+1,memo))
			tempRes |=true;
		
		if(s3.charAt(k)==s2.charAt(s2pos) && isInterLeavingMemoHelper(s1,s1pos,s2,s2pos+1,s3,k+1,memo))
			tempRes |=true;
		//update memory by 1 if tempRes true else 0
		memo[s1pos][s2pos]=tempRes ? 1 : 0;
		return tempRes;
		
		//return true;
	}
	/**
	 * time complexity is much less O(m.n) as well as space 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	
	public static boolean isInterLeavingDp(String s1, String s2, String s3) {
		//first we will check the length combination
		if(s1.length()+s2.length()!=s3.length())
			return false;
		
		//create a dp array to keep the bottom up dynamic programming
		boolean dp[][] =new boolean[s1.length()+1][s2.length()+1];
		// we are keeping dp[0][0] non crossing and thus first row and column are extra for empty set 
		// we have iterative until the length+1 or i<=s1.length()
		for(int i=0; i<=s1.length();i++) {
			for(int j=0; j<=s2.length();j++) {
				if(i==0 && j==0)
					dp[i][j]=true;
				else if(i==0) {
					//make sure to look here, s2 not s1 every time you review the code
					dp[i][j]=dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
				}
				else if(j==0) {
					dp[i][j]=dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1);
				}
				else {
					dp[i][j] = (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1)) ||
							(dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
	
	//Check few examples to grasph the actual process
	
	public boolean isInterLeavingDp1D(String s1, String s2, String s3) {
		//first we will check the length combination
		if(s1.length()+s2.length()!=s3.length())
			return false;
		
		//create a dp array to keep the bottom up dynamic programming
		boolean dp[] =new boolean[s2.length()+1];
		
		for(int i=0; i<=s1.length();i++) {
			for(int j=0; j<=s2.length();j++) {
				if(i==0 && j==0)
					dp[j]=true;
				else if(i==0) {
					//make sure to look here, s2 not s1 every time you review the code
					dp[j]=dp[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
				}
				else if(j==0) {
					dp[j]=dp[j] && s1.charAt(i-1)==s3.charAt(i+j-1);
				}
				else {
					dp[j] = dp[j] && s1.charAt(i-1)==s3.charAt(i+j-1) ||
							dp[j-1] && s1.charAt(j-1)==s3.charAt(i+j-1);
				}
			}
		}
		return dp[s2.length()];
	}
	
	public static void smallTestRecursive() {
		String s1="abc", s2="bcd", s3="abcbdc";
		System.out.printf("The string %s and %s is interleave to produce %s is %b \n", s1,s2,s3,isInterLeavingString(s1,s2,s3));
	}
	
	
	public static void smallTestMemo() {
		String s1="abc", s2="bcd", s3="abcbdc";
		System.out.printf("The string %s and %s is interleave to produce %s is %b \n", s1,s2,s3,isInterLeavingMemo(s1,s2,s3));
		
		 s1="abc"; s2="bad"; s3="abcbdc";
		System.out.printf("The string %s and %s is interleave to produce %s is %b \n", s1,s2,s3,isInterLeavingMemo(s1,s2,s3));
	}
	
	public static void smallTestDp() {
		String s1="abc", s2="bcd", s3="abcbdc";
		System.out.printf("The string %s and %s is interleave to produce %s is %b \n", s1,s2,s3,isInterLeavingDp(s1,s2,s3));
		
		 s1="abc"; s2="bad"; s3="abcbdc";
		System.out.printf("The string %s and %s is interleave to produce %s is %b \n", s1,s2,s3,isInterLeavingDp(s1,s2,s3));
		s1="aabcc"; s2="dbbca"; s3="aadbbcbcac";
		System.out.printf("The string %s and %s is interleave to produce %s is %b \n", s1,s2,s3,isInterLeavingDp(s1,s2,s3));
	
	
	}
	
	
	public static void main(String args[]) {
		//smallTestRecursive();
		//smallTestMemo();
		smallTestDp();
	}
}
