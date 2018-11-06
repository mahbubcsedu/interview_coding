package leetcode;

public class FindMinimumRotatedArray153 {
	
public static int findMin(int[] nums) 
{
	int left=0, right=nums.length-1;
	
	while(left<right) {
		int mid=left+((right-left)/2);
		
		//if(nums[mid]nums[mid];
		//mid elements less than right, that mins its in left subarray
		if(nums[mid] < nums[right])
			right=mid;
		//if mid element is greather than right that mins its in right subarray
		else if(nums[mid] > nums[right])
			left=mid+1;
		
	}
    
	return nums[left];
 }

public static void smallTest() {
	int[] input= {7,8,9,1,2,3,4,5,6};
	System.out.println(findMin(input)==1);
}
 public static void main(String args[]) {
	 smallTest();
 }
}
