package leetcode.dp;

import java.util.Arrays;

public class PerfectSquare_279 {

	/**
	 * basically we will fill for each number until n
	 * the first number will start at 0 for 0, 
	 * then for each will will find out i-j*j>=, so, this will find out the max j which will minimize i
	 * 
	 * let's fill up the table and try the formula, 
	 * dp[0]=0
	 * dp[1]=1
	 * dp[2]=2
	 * dp[3]=3
	 * dp[4]=1
	 * dp[5]=2
	 * dp[6]=3
	 * dp[7]=4
	 * dp[8]=2
	 * dp[9]=1
	 * 
	 * now use the formula to see dp[7]
	 * @param n
	 * @return
	 */
	public static int numSquares(int n) {
		int[] dp=new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[0]=0;
		
		for(int i=1; i<=n; i++) {
			int j=1;
			int min=Integer.MAX_VALUE;
			
			while(i-j*j>=0) {
				min=Math.min(min, dp[i-j*j]+1);
				j++;
			}
			dp[i]=min;
		}
		return dp[n];
	}
	
	public static void main(String args[]) {
		int n=numSquares(13);
		System.out.println(n);
	}
	
}
