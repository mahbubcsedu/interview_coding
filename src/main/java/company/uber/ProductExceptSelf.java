package company.uber;

import java.util.Arrays;

/*
 * This problem is explained in EPI 25.4 page 446 also in leetcode
 * 
 */
public class ProductExceptSelf {

	/**
	 * Good solutions using o(n) space,
	 * Also handle the 0 case properly
	 * 
	 * algorithm:
	 * 1. 2 pass from left to right and right to left
	 * 2. Create a result array of size num and filled r[0]=1
	 * 3. multiply from elements 1 to n with result and num as res[i]=res[i-1]*a[i-1]
	 * 4.  Second pass, do a little variation, again create a variable called r=1 and update it every time
	 * 5. update r as r[i]=r*res[i] and r=r*a[i]
	 * 
	 * the result will be produced
	 * @param nums
	 * @return
	 */
	static int[] getProductSelf(int[] nums) {
		int n=nums.length;
		int[] result=new int[n];
		result[0]=1;

		for(int i=1;i<n;i++) {
			result[i]=result[i-1]*nums[i-1];

		}

		int right=1;

		for(int i=n-1;i>=0;i--) {
			result[i]=result[i]*right;
			right=right*nums[i];
		}
		return result;
	}

	/**
	 * we also can do this using constant space but we need to keep track of zeros and also have some division
	 * but there is a chance of overflow as well 
	 * @param args
	 */

	public static int[] getProductExceptSelf(int[] nums) {

		int result=1;

		int countZero=0;

		for(int i=0; i<nums.length;i++) {
			if(nums[i]!=0) {
				result*=nums[i];
			}else {
				countZero++;
			}
		}

		if(countZero>1)
			Arrays.fill(nums, 0);
		else {
			for(int i=0; i<nums.length;i++) {
				if(nums[i]!=0) {

					nums[i]=result/nums[i];
				}
			}
		}

		return nums;
	}

	public static void main(String args[]) {
		int[]input= {1,2,0,3,4,0};

		int res[]=getProductSelf(input);

		int res2[]=getProductExceptSelf(input);

		System.out.println(Arrays.toString(res2));
	}

}
