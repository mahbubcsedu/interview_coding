package leetcode.dp;

public class SupperEggDrop887 {
 
	
	 public static int eggDrop(int eggs,int floors) {
		 //if there is no floor, no trial needed
		 //one floor one trial needed
		 if(floors==0 || floors==1) {
			 return floors;
		 }
		 //we need k trials for k floors with one egg start from ground floor to top
		 if(eggs==1)
			 return floors;
		 
		 int min=Integer.MAX_VALUE;
		 int res,x;
		 
		 //try from each floor to see which starting floor gives best results
		 //x is the starting floors of dropping eggs
		 
		 for(x=1;x<=floors; x++) {
			 //we will take max of two as we are concern about worse case condition
			 //BE CAREFULL--- you should not use floors for down floor, as we are trying from x, so use x-1 to go to down floor and floor-x for up floor
			 res=Math.max(eggDrop(eggs-1,x-1), eggDrop(eggs,floors-x));
			 //if (res < min) 
	         //       min = res; 
			 min=Math.min(res, min);
		 }
		 //this iterations requires one step, so it will add to the final step count.
		 return min+1;
	 }
	 //calculate the cost of recursive process
	
	 // This recursive solution has many repeated calculations, and we can think of dp
	 /**
	  * @link https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
	  * @param eggs
	  * @param floors
	  * @return
	  */
	 
	 public static int eggDropDp(int eggs, int floors) {
		 //need to find optimal substructure and subproblems which can 
		 //we can take the recursive call and store the calculation rults in a table
		 //we will keep for each age to find the floors bottom up manner
		 int dp[][] =new int[eggs+1][floors+1];
		 
		 int res=Integer.MIN_VALUE;
		 //we need one trial for one floor and 0 trial for zero floor
		 for(int i=1; i<=eggs;i++) {
			 dp[i][1]=1;
			 dp[i][0]=0;
		 }
		 
		 //if we have only one eggs, we always requires number of floors to check and trials should be equal to that
		 for(int j=1;j<=floors; j++)
		 {
			 dp[1][j]=j;
		 }
		 
		 for(int i=2; i<=eggs;i++) {
			 for(int j=2;j<=floors; j++) {//j is the current considered floors number
				 dp[i][j]=Integer.MAX_VALUE;
				 //we will try for all floors
				 for(int x=1;x<=j;x++) {
					 //we will see the required value using one less eggs and one less floors and one more floor with same egg
					 res=1+Math.max(dp[i-1][x-1], dp[i][j-x]);
					 dp[i][j]=Math.min(res, dp[i][j]);
				 }
			 }
		 }
		 return dp[eggs][floors];
	 }
	 //the cost is O(nk^2)...as for each eggs, we and for each floor, we start from each floor, and that's why k^2 for floors and n for eggs
	 
	 
	/**
	 * using rever order by limiting number of trials to find the max floor we can go 
	 * EPI Book 501,
	 * 1. F(c,d) is the maxmum floors we can test using c eggs and d trials. 
	 * 2. We know that F(1,d)=d as with only one agg we need to try starting from first floor to last floor
	 * 3. Suppose we know F(i,j) where i<=c and j<=d, so i eggs and j trial, we can test F(i,j) floor maximum
	 * 4. If we try from current floor with c+1 case and d drops limit, we can try from F(c, d-1)+1 floors as we lost a trials and added a floor
	 * 5. Now, it the eggs breaks from Floor F(c,d-1)+1, we can limit our search to lower side. And that will must be exactly [1, F(c,d-1)] range as we are now
	 *   gauranted that F(c,d-1) breaks the egg
	 * 6. if the egg did not break from that floor, we proceed to floor F(c,d-1)+1+F(c+1,d-1) where F(c,d-1)+1 was the floor we tried and it did not break, so, we have one
	 *  more egg with one less trial
	 *  
	 *  so the recurrence F(c+1,d)=F(c,d-1)+1+F(c+1,d-1)
	 *  
	 *  More clear explanation visit @link https://leetcode.com/articles/super-egg-drop/
	 *  
	 */
	 
	 public static int eggDropDpMath(int eggs, int floors) {
		 int[][] dp=new int[eggs+1][floors+1];
		 for(int i=0; i<=eggs;i++) {
			 for(int j=0;j<=floors;j++)
			 {
				 dp[i][j]=-1;
			 }
		 }
		 
		 
		 
		 for(int i=1;i<=eggs;i++)
		 {
			 for(int j=1; j<floors;j++) {
				 if(i==1)
					 dp[i][j]=j;
				 else if(j==1)
					 dp[i][j]=1;
				 else {
					 dp[i][j]=dp[i-1][j-1]+1+dp[i][j-1];
				 }
			 }
		 }
		 
		 //do a binary search here
		 
		 //int low=dp[1][1];
		 //int high=dp[eggs+1][floors+1];
		 int res=0;
		 for(int i=1;i<=eggs;i++)
			 for(int j=1;j<floors;j++) {
				 if(dp[i][j] >= floors)
				 {
					res=j;
					break;
				 }
			 }
		 
		 return res;
	 }
	 
	 
	 
	 static void smallTestDp() {
		 int floors=10;
		 int eggs=2;
		 
		 int res=eggDropDp(eggs,floors);
		 System.out.printf("nMinimum number of tirals requires in worst case with %d eggs and %d floors is %d \n",eggs,floors,res);
		 
		 //System.out.println(way);
	 }
	 
	 static void smallTestRec() {
		 int floors=10;
		 int eggs=2;
		 
		 int res=eggDrop(eggs,floors);
		 System.out.printf("nMinimum number of tirals requires in worst case with %d eggs and %d floors is %d \n",eggs,floors,res);
	 }
	 
	 static void smallTestMath() {
		 int floors=10;
		 int eggs=2;
		 
		 int res=eggDropDpMath(eggs,floors);
		 System.out.printf("nMinimum number of tirals requires in worst case with %d eggs and %d floors is %d \n",eggs,floors,res);
	 }
	 
	 public static void main(String args[]) {
		 smallTestMath();
	 }
}
