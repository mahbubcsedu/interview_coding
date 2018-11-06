package leetcode;

public class FindPeakElement162 {
	/**
	 * Linear search of increment and then decrement and return the extreme index but its not acceptable
	 */

	//only binary search is possible as the requirements O(logn)

	//Iterative binary search where in the while loop we will continuously narrow down the search space

	public int findPeakElement(int[] nums) {
		int l=0; 
		int r=nums.length-1;

		while(l<r) {
			int mid=l+(r-l)/2;
			//should I check mid+1 is valid location or not, please try an example
			if(nums[mid]>nums[mid+1]) {
				r=mid;
			}else {
				l=mid+1;
			}
		}
		return l;
	}


	//please practice recursive as well

	public int findPeakElement2(int[] nums) {
		return search(nums, 0, nums.length - 1);
	}
	public int search(int[] nums, int l, int r) {
		if (l == r)
			return l;
		int mid = (l + r) / 2;
		if (nums[mid] > nums[mid + 1])
			return search(nums, l, mid);
		return search(nums, mid + 1, r);
	}


	public static void main(String args[]) {
		FindPeakElement162 ob=new FindPeakElement162();
		int[] nums= {1,2,3,4,5,6,4,3,2,1};
		System.out.println(ob.findPeakElement(nums)==5);
	}
}
