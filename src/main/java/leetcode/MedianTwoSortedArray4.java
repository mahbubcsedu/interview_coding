package leetcode;

public class MedianTwoSortedArray4 {

	/**
	 * Median: median is the middle of a sorted array, so if the length is odd,
	 * the median will be just the middle element
	 * If the length is even, median will be the average of two middle elements
	 * @param nums1 sorted array 1
	 * @param nums2 Sorted array 2
	 * @return median of two array
	 * 
	 * Algorithm: the brute force algorithm will be to merge the two array and calculate the median.
	 * Cost: Cost will be O(n+m) as all elements of two array need to be enumerate
	 */
	public static double getMedianBF(int[] nums1, int[] nums2) {
		
		int len=nums1.length+nums2.length;
		
		int[] result=new int[len];
		
		int i=0,j=0,k=0;
		
		while(j<nums1.length && k< nums2.length) {
			if(nums1[j]<nums2[k]) {
				result[i]=nums1[j];
				j++;
			}else {
				result[i]=nums2[k];
				k++;
			}
			i++; //increase result array every time
		}
		
		while(j<nums1.length)
			result[i++]=nums1[j++];
		while(k<nums2.length)
			result[i++]=nums2[k++];
		
		return len%2==0 ? (double)(result[len/2]+result[len/2+1])/2 : (double)(result[len/2]);
	}
	
	
	/**
	 * The condition here is both array are sorted, so we can try to find better
	 * solutions, it can be solve with better time complexity. 
	 * If we have to array 3, 5, 8, 11, 15
	 * Another array  7, 9, 15, 21, 25,31,35, we can partition two array in the middle of each array
	 * and can check which side we need to move to get the median and ultimately will find the position
	 */
	
	public double getMedianOpt(int[] nums1, int[] nums2) {
		//we will start from the middle of smaller array so that we need to iterate less
		//we also keep the lower and upper index of smaller array to determin when to stop
		int l1, l2;
		if(nums1.length > nums2.length) {
			
			getMedianHelper(nums2, nums1);
			
		}else {
			getMedianHelper(nums1,nums2);
		}
			
		return 0.0;
	}
	
	public double getMedianHelper(int[] A, int[] B) {
		int iMin=0, iMax=A.length;
		int len_b=B.length, len_a=A.length;
		
		int halfLen=(len_a+len_b+1)/2;
		
		while(iMin <= iMax) {
			int i=(iMin+iMax)/2;
			int j=halfLen-i;
			
			//i should be less than iMax as it is the right boundary for A
			//if B's left side's last element is greater than A's right side's first, that means, median is in left of B[j-1
			if(i<iMax && B[j-1] > A[i])
			{
				iMin=i+1; // so, more elements from right of A will be part of left of the result
			}
			else if(i > iMin && A[i-1] > B[j])
			{
				iMax=i-1;
			}
			else {
				//if both of the two condition fails, it means, we have got the position of median
				// and current partition is is good to go
				//now we need to find which item is the max of the left side as there are two
				//options one from A, and one from B
				
				int maxLeft=0;
				//in no element from A is on the left side, right of B is the leftMax
				if(i==0) {
					maxLeft=B[j-1];
				}
				else if(j==0) {
					//j==0 means, we do not have any elements from B in first, hafl 
					maxLeft=A[i-1];
				}
				else {
					maxLeft=Math.max(A[i-1], B[j-1]);
				}
				
				int minRight=0;
				//it reaches to the last of array A, so all elements are left side and minimum of right will
				//minimum of B's right
				if(i==len_a) minRight=B[j];
				//if j reaches to len_b
				else if(j==len_b) minRight=A[j];
				else minRight=Math.min(B[j], A[i]);
				
				if((len_a+len_b)%2==1) return maxLeft;
				
				
				else return  (maxLeft+minRight)/2.0;
			}
				
			//try different example to be clear
		}
		
		return 0;
	}
	
}
