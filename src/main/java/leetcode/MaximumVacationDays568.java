package leetcode;

import java.util.Arrays;

public class MaximumVacationDays568 {

	/**
	 * Brute force approach using DFS
	 * 1. we start from 0, at week 0.
	 * 2. for each city that has the option to move(have flights) or for current city, we will take the vacaction days and recursively search
	 * for all other options.
	 * 3. The options which return max vaction day will be the answer
	 */


	public static int getMaxVacationDFS(int[][] flights, int[][] days) {
		return dfs(flights, days, 0, 0);
	}

	public static int dfs(int[][] flights, int[][] days, int current_city, int cur_weekend ) {

		//if we reach the last weekend, we are done,
		if(cur_weekend==flights.length)
			return 0;

		//we will try all possible way, though called dfs ,but actually backtracking with no pruning
		int maxVac=0;
		for(int i=0;i<flights.length;i++) {
			//if we are considering current city, we can take the vaction allowed here
			//if not, then obviously looks for flight
			if(i==current_city || flights[current_city][i]==1) {
				//we will take this weekend vaction to current city or city where we can fly after taking vaction of this city this weekend
				//so, i is necessary to send as we need to know which city we can stay next weekend, this city, or any other city
				int vac=days[i][cur_weekend]+dfs(flights,days,i,cur_weekend+1);
				maxVac=Math.max(maxVac, vac);//chose which one is greater
			}
		}
		return maxVac;

	}


	/**
	 * we are considering in this method exhaustively all the options, so, from first city, we are trying 2nd, 3rd and so on, then from there
	 * next week all other city, so, lets say at 3rd week, we reached a place from two different city of 2nd week spending, 
	 * so, from now on, rest of the calculation will be same, and we are computing everytime.
	 * It would be a good idea to store the computed results once we first reach there and for subsequent iteration use that value
	 */


	public static int getMaxVactionMemo(int[][] flights, int[][] days) {
		//we need 2d table to memorize
		int[][] memo = new int[flights.length][flights[0].length];
		for(int[] row:memo) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}
		return dfsmemo(flights, days, 0, 0,memo);
	}

	public static int dfsmemo(int[][] flights, int[][] days, int current_city, int cur_weekend,int[][] memo) {

		//if we reach the last weekend, we are done,
		if(cur_weekend==flights.length)
			return 0;
		//check if we already visited this place or not
		if(memo[current_city][cur_weekend]!=Integer.MIN_VALUE) {
			return memo[current_city][cur_weekend];
		}

		//we will try all possible way, though called dfs ,but actually backtracking with no pruning
		int maxVac=0;
		for(int i=0;i<flights.length;i++) {
			//if we are considering current city, we can take the vaction allowed here
			//if not, then obviously looks for flight
			if(i==current_city || flights[current_city][i]==1) {
				//we will take this weekend vaction to current city or city where we can fly after taking vaction of this city this weekend
				//so, i is necessary to send as we need to know which city we can stay next weekend, this city, or any other city
				int vac=days[i][cur_weekend]+dfs(flights,days,i,cur_weekend+1);
				maxVac=Math.max(maxVac, vac);//chose which one is greater
			}
		}
		//store before return from this loop
		memo[current_city][cur_weekend]=maxVac;
		return maxVac;

	}
	
	/**
	 * From the solution we can see that, whatever we have done previous weeks does not affect the next one, i.e,
	 * if we have processed last week, we don't need to go back to know the best for this week, we can select any city
	 * if we have the flights, so this gives us the optimal subproblems from one to another, we can use the dynamic programming this case
	 * 
	 * This is optimization problem and we can do top down fashion from last week towards the first week
	 * 
	 * we can find the city where we can stay to gain max vactions, then from their we will go for next week. also the flight is not
	 * one way but both way, so it does not matter
	 */
	
	public static int getMaxVacDp(int[][] flights, int[][] days) {
		if(days.length==0 || flights.length==0) return 0;
		
		int[][] dp=new int[days.length][days[0].length+1];//one column extra to fill with 0
		
		for(int week=days[0].length-1; week>=0;week--) {
			//for each week starting from last, we will process each city where we can get max vaction
			for(int cur_city=0; cur_city < days.length;cur_city++) {
				//first takes the current city vacation and compare all others later to find where is the maximu
				dp[cur_city][week]=days[cur_city][week]+days[cur_city][week+1];
				
				for(int next_city=0;next_city<days.length;next_city++) {
					dp[cur_city][week]=Math.max(dp[cur_city][week], dp[next_city][week]+dp[cur_city][week+1]);
				}
			}
		}
		return dp[0][0];
	}
	
	public static void main(String args[]) {
		int[][] days= {{1,3,1},{6,0,3},{3,3,3}};
		int[][]flights= {{0,1,1},{1,0,1},{0,1,1}};
		System.out.println(getMaxVacDp(flights,days));
	}
}
