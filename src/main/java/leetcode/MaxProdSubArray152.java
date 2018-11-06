package leetcode;

public class MaxProdSubArray152 {

	
	/**
	 * 
	 * The brute force algorithm we will not implement as it is too easy
	 * for each subarray we will find out the maximum product. it will requires to pointers start and end and for each end, we will do all start
	 * and calculate the product. Cost will be O(n^2)
	 * @param nums
	 * @return
	 */
	
	
	
	 /**
	  * NOTE: This algrithm is only for the positive output. If there is only negative, it will fail
	  * 
	  * Kadane algorithm is famous for subarray calculations. We will here calculate that keeping 3 different parts monitoring
	  * 1. The current element is a) negative, b) positive c) zero. 
	  * 2. Based on current item, we will calculate the product
	  * 
	  * try three example : -1,-2, 4,-4,3
	  * 
	  * So, the algorithm process all of these in simple way
	  * 1. it tracks two values, min_until_now, max_until_now just like kadane's sum problem, it is responsible for max and min from last started position
	  * 2. It iteration, it finds out the maximum so far in the array starting from 0
	  * 3. min_until_now always keeps 1 if the product goes up otherwise keep the minmum to tackle negative
	  * 4. max_until_now in negative case, also keeps the lowest value 1 as the lowest max value to avoid negative
	  * 5. If the number is positive, then max_until_now here will be the max_until_now * current_number and min will be min_until_now * current_number, but if it cross 1, the value will be 1
	  * 6. if the number is negative, then max_until_now will be the maximums of max_until_now*current number, most of the case it will be positive if previous ending was negative otherwise positive
	  *    but if it crosses 1 to negative side, the max will be 1
	  * 7. if the number is zero, all we have to start as it will always be zero, so, will initiate both variable
	  * 
	  * 
	  * This will not be able to handle [-2,0,-1] or if the output is 1, it will always find 1
	  * @param nums
	  * @return
	  * 
	  * also followup how we can get that portion that formed the answer
	  */
	 
	public static int maxProductLinear(int[] nums) {
		//this does not support if maximum is the negative, lets say -2,-3,-4
		int maxEndingHere=1;
		int minEndingHere=1;
		boolean isThereAnyPos=false;
		int maxSoFar=1;
		
		for(int i=0; i<nums.length;i++) {
			
			if(nums[i]>0) {
				isThereAnyPos=true;
				maxEndingHere=maxEndingHere*nums[i];// positive will increase
				minEndingHere=Math.min(minEndingHere*nums[i], 1); //never cross 1
			}else if(nums[i]==0) {
				maxEndingHere=1;
				minEndingHere=1;
			}else {
				int temp=maxEndingHere;
				maxEndingHere=Math.max(minEndingHere*nums[i], 1);
				minEndingHere=temp*nums[i];
			}
			
			maxSoFar=Math.max(maxSoFar, maxEndingHere);
		}
		if(!isThereAnyPos && maxSoFar==1) {
			return 0;
		}
		else { 
		return maxSoFar;
		}
	}
	
	/**
	 * The algorithm will work same way as before but will handle zero and negative case
	 * @link https://www.geeksforgeeks.org/maximum-product-subarray-added-negative-product-case/
	 * The technique is, we will keep both max and min, if current number is negative, multiply with minvalue will give the maximum and maxvalue will be minvalue
	 * or we can say the position will be swaped based on positive and negative,
	 * 
	 * -2,-4,-5,-6
	 * so,i=0 min=1, max=1, now becomes min =-2 and max=-2, now 
	 * i=1, so, min=4, max=4, next i=2, and min
	 * 
	 * this is also fails to handle only negative case
	 * @param nums
	 * @return
	 */
	public static int maxProductLinearNeg(int[] nums) {
		int result=Integer.MIN_VALUE;
		
		int maxVal=1;
		int minVal=1;
		
		int prevMax;
		
		for(int i=0; i<nums.length;i++) {
			if(nums[i] >0) {
				maxVal=maxVal*nums[i];
				minVal=Math.min(1, minVal*nums[i]);
			}else if(nums[i] <0) {
				prevMax=maxVal;
				maxVal=minVal*nums[i];
				minVal=prevMax*nums[i];
			}else {
				minVal=1;
				maxVal=0;
			}
			result=Math.max(result, maxVal);
			if(maxVal<=0) {
				maxVal=1;
			}
		}
		return result;
	}
	
	
	 
	 
	 static void smallTest() {
		 
		int[] input = {-2, 4, -4};
		//System.out.printf("max product of subarray %b \n",32==maxProductDP(input) );			
	 }
	 
	 static void smallTestLinear() {
		 
			int[] input = {-2, -4, -4};
			System.out.printf("max product of subarray %b \n",16==maxProductLinear(input) );
			int[] input2 = {-2, 0, -4};
			System.out.printf("max product of subarray %b \n",0==maxProductLinear(input2) );
			int[] input3 = {-2};
			System.out.printf("max product of subarray %b \n",-2==maxProductLinear(input3) );
		 }
	 
	 
	 static void smallTestLinearNeg() {
		 
			int[] input = {-2, -4, -4};
			System.out.printf("max product of subarray %b \n",16==maxProductLinearNeg(input) );
			int[] input2 = {-2, 0, -4};
			System.out.printf("max product of subarray %b \n",0==maxProductLinearNeg(input2) );
			int[] input3 = {-2};
			System.out.printf("max product of subarray %b \n",-2==maxProductLinearNeg(input3) );
		 }
	 public static void main(String args[]) {
		 //smallTestLinear();
		 smallTestLinearNeg();
	 }
}
