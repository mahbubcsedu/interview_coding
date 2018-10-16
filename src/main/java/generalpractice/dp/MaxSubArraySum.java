package generalpractice.dp;
import java.util.*;
public class MaxSubArraySum {
/*
 * Brute force, the brute force algorithm will be to first create all subarray and then calcualte the sum of all subarray and then
 * return the max one.
 * Cost will be O(n^3)
 */
	
 public static int getSumBF(List<Integer> numbers) {
  
	 int maxSum=Integer.MIN_VALUE;
	 for(int i=0; i<numbers.size(); i++) {
		 for(int j=i; j<numbers.size();j++) {
			 int sum=0;
			 for(int k=i;k<=j;k++) {
				 sum+=numbers.get(k);
			 }
			 maxSum=Math.max(maxSum, sum);
		 }
	 }
	 return maxSum;	 
 
 }

 
 /*
  * Brute force modified to using memory, the brute force algorithm will be to first create all subarray and then calcualte the sum of all subarray and then
  * return the max one.
  * Cost will be O(n^2)
  */
 	
  public static int getSumMemory(List<Integer> numbers) {
   
 	 int maxSum=Integer.MIN_VALUE;
 	 List<Integer> temp=new ArrayList<>();
 	 
 	 temp.add(numbers.get(0));
 	 for(int i=1;i<numbers.size();i++) {
 		 temp.add(temp.get(i-1)+numbers.get(i));
 	 }
 	 for(int i=0; i<numbers.size(); i++) {
 		 for(int j=i; j<numbers.size();j++) {
 			 
 			 maxSum=Math.max(maxSum, temp.get(j)-temp.get(i));
 		 }
 	 }
 	 return maxSum;	 
  
  }
  
  /*
   * We can use devided and conquer algrithm by calculating from left half, right half or crossing left and right where middle element will be included
   * return the max one.
   * Cost will be O(nlogn) just like quick sort
   */
  	
   public static int getSumDividedConquer(List<Integer> numbers) {
    
  	 int maxSum=Integer.MIN_VALUE;
  	 List<Integer> temp=new ArrayList<>();
  	 
  	 temp.add(numbers.get(0));
  	 for(int i=1;i<numbers.size();i++) {
  		 temp.add(temp.get(i-1)+numbers.get(i));
  	 }
  	 for(int i=0; i<numbers.size(); i++) {
  		 for(int j=i; j<numbers.size();j++) {
  			 
  			 maxSum=Math.max(maxSum, temp.get(j)-temp.get(i));
  		 }
  	 }
  	 return maxSum;	 
   
   }
   
   /**
    * Try and example first 
    * @param A
    * @param l
    * @param r
    * @return 
    * {@link https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/}
    */
   public static int maxSubSumDandCHelper(List<Integer> A, int l, int r) {
	   //if the array are single elements just return it from here
	   if(l==r)
		   return A.get(l);
	   //if not, we will divide it into two halves, and calculated from both halves and also the crossing of two sides
	   int m=l+(r-l)/2;
	   
	   //return the maximum from all three cases
	   
	   return Math.max(Math.max(maxSubSumDandCHelper(A,l,m),maxSubSumDandCHelper(A,m+1,r)), maxSubCross(A,l,m,r)) ;
	   //return 0;
   }
   
   public static int maxSubCross(List<Integer>A, int l, int m, int r) {
	   int sum=0;
	   int left_sum=Integer.MIN_VALUE;
	   for(int i=m;i>=l;i--) {
		   sum+=A.get(i);
		   //is sum value changes and higher than left sum after adding current elements, left_sum will be updated, otherwise not
		   if(sum > left_sum)
			   left_sum=sum;
		   
	   }
	   
	   //for right sum we will do the same
	   sum=0;
	   
	   int right_sum=Integer.MIN_VALUE;
	   for(int i=m+1;i<=r;i++) {
		   sum+=A.get(i);
		   if(sum > right_sum)
			   right_sum=sum;
	   }
	   return left_sum+right_sum;
   }
  
  /**
   *for DP we need to find the optimal substructure and subproblem as a mathematical equations. 
   *The rules that can be divise here is that, the ith position sum is the MS(i)=Max(MS(i-1)+A[i], A[i]) which 
   *simply say that, max sum at ith position is maxSum at i-1 th position plus current element, if not, current element
   *As we do not have to choice to avoid middle elements, so, if adding current element lower the total value, why we will take previous,
   *as the current element is the greater of two
   * @param A list of integer
   * @return max value
   * cost of this solutions is O(N)
   */
   
   public static int subArraySumDp(List<Integer> A) {
	   List<Integer> dp=new ArrayList<>(Collections.nCopies(A.size()+1, 0));
	   dp.set(0,0);
	   
	   int maxSum=Integer.MIN_VALUE;
	   
	   for(int i=1;i<dp.size();i++) {
		   dp.set(i, Math.max(dp.get(i-1)+A.get(i-1), A.get(i-1)));
		   maxSum=Math.max(maxSum, dp.get(i));
	   }
	   return maxSum;
   }
   
   
    /**
     * // Kadane's algorithm also solve this problem in O(n) time. the concept is if you have a list of integer with negative and positive, the result
   //will always be positive, any negative result thus can be discarded
    * 
    * This will not work for all negative numbers and will always return 0;
    */
   
   public static int subArraySumKadane(List<Integer> A) {
	   
	  //this is simple algorithm and only require two pointers
	   int max_so_far=0;
	   int max_ending_here=0;
	   for(int i=0; i<A.size();i++)
	   {
		   max_ending_here=A.get(i);
		   if(max_ending_here<0)
			   max_ending_here=0;
		   if(max_ending_here > max_so_far)
			   max_so_far=max_ending_here;
		   
	   }
	   return max_so_far;
   }
 
   /**
    * So, Kadane's algorithm can be modified to support the negative all arrays which is a recursive algorithm 
    * and that equation we already have used to our dp solutions
    * @return
    */
   
   public static int subArraySumKadaneModified(List<Integer> A) {
	   
	   int max_so_far=A.get(0);
	   int max_ending_here=A.get(0);
	   
	   for(int i=1; i<A.size();i++)
	   {
		   
		   max_ending_here=Math.max(A.get(i), max_ending_here+A.get(i));
		   
		   max_so_far=Math.max(max_ending_here, max_so_far);
		   
	   }
	   return max_so_far;
   }
   
   
 public static void smallTest() {
	 List input=Arrays.asList(new Integer[]{-2,1,-3,4,-1,2,1,-5,4});
	 
	 System.out.println(getSumBF(input));
	 System.out.println(getSumMemory(input));
	 System.out.println(getSumDividedConquer(input));
	 System.out.println(subArraySumDp(input));
 }
 
 public static void main(String args[]) {
	 smallTest();
 }
}
