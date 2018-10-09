package company.walmart;

public class OptimalWalk {
 /**
  * recursive solutions we can think of, one option is moving right and left
  * any move will cost A, jump will cost b, 2*x
  * Given a point on x axis, (N,0), you are standing at origin and you can only move on x-axis. Lets say you are at (x,0), at each step you can either move one step forward ie (x+1,0) or one step backwards (x-1,0) or make a jump to double your current position (2*x,0). One step movement costs you A while a jump costs you B. If your initial position is (0,0) , find the minimum cost to reach (N,0) .

Input:
First line of input consist of a single integer T denoting the total number of test case. Then T test cases follow. Each line of test case contains 3 space separated integers N, A, B as described in the problem statement.

Output:
For each test case, print a single line containing the minimum cost to reach (N,0).


Constraints:
1<=T<=30
1<=N<=10^5
1<=A,B<=10^9


Example:
Input:
1
7 1 100

Output:
7
  */
	
 public static long getOptimumWalk(int N, int a, int b) {
	 
	 long[] processedData=new long[N+1];
	 processedData[0]=0;
	 processedData[1]=a;
	 
		 
	 for(int i=2; i<=N;i++) {
		 
		 //if the current N is even
		 if((i&1)==0) {
			 //For this case, we can get the cost of ith value from i/2th position cost by jumping with cost b
			 //we can get to ith position by moving one step ahead using only moving cost a from i-1 th position
			 processedData[i]=Math.min(processedData[i/2]+b, processedData[i-1]+a);
		 }else {
			 //lets say i=3, so, we can either process processedData[3/2=1]+jumpcost + move ahead cost
			 // or we can get there from i-1 th position with one move and cost a or we can get to i+1 th pos with cost b and move left with cost a whichaver is minimum
			 processedData[i]=Math.min(processedData[i/2]+b+a, Math.min(processedData[i-1]+a,processedData[(i+1)/2]+a+b));
		 }
	 }
	 return processedData[N];
 }
 
 public static void main(String args[]) {
	 System.out.println(getOptimumWalk(7,1,1));
 }
}
