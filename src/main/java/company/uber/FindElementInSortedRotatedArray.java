package company.uber;

import java.util.Arrays;
import java.util.List;

//First question is find the pivot elements or the minimum elements from the rotated sorted array

public class FindElementInSortedRotatedArray {
	/**
	 * Algorithm 1: BF algorthim will be to linear search and find the index of the elements but the cost O(n)
	 * and which is not our answer
	 * 2. Second algorhtm would be to find out where is the maximum and the minimum, they should lie side by side,
	 * so the minimum element will be the element whose both side has larger element than the element.
	 * We can search the array starting from the first element until we find the elements that meets the condition
	 * But, if the element is the first element, still we have to search whole if we don't search the last elements
	 * and best case performance will be good if the rotated part is smaller than k.
	 * 3. We can apply binary search to find o(logn) algorithm. Roated means, it may rotated or not and does not give any gaurantee of anything
	 *  
	 *  Steps: A={7 8 9 1 2 3 4 5 6} is the array, mid=8/2=4, A[mid]=2, A[low]=7, A[h]=6, 
	 *  Both side elements are bigger than mid elments, which tels us that minimum will be on left side
	 *  if the mid is 8, we should have left elements less or both less. so, we can search only low to mid-1
	 *  
	 *  Step 2: now A[mid]=low+(h-low)/2=2, which is 9, now 9 is greater than A[L] as well as A[h]
	 *  so, the elements should be on right side;
	 *  
	 *  So, the conditions we need to check will be A[mid], A[h] and A[l], 
	 *  
	 *  1. A[mid] > A[right] then we search from mid+1 to high
	 *  2. if not, set the right=mid
	 *  
	 */


	static int searchSmallestInSortedRoated(List<Integer> A) {
		int left=0, right=A.size()-1;
		int comparison=0;
		//when both are same, willl return left as the minimum element
		while(left<right)
		{
			comparison++;

			int mid=left+(right-left)/2;

			System.out.println("mid of "+mid);

			if(A.get(mid) > A.get(right)){
				left=mid+1;
			}
			else {
				//A.get(mid) < A.get(right)
				right=mid;
			}
		}

		//System.out.println("number of comparison"+comparison);
		return left;
	}

	/**
	 * This algorithm can handle repeated entry but the time complexity will increase o(n)  in worst case
	 * if the array A={1,1,1,1,1,0} then, mid=1, so, mid+1 to right
	 * @param args
	 */

	public static int findMin(int[] nums) {
		if(nums.length==0)
			return -1;
		int left=0, right=nums.length-1;
		return searchHelper(nums,0,right);

	}

	private static int searchHelper(int[] A, int left, int right){
		if(left==right){
			return A[left];
		}

		int mid=left+((right-left)/2);

		if(A[mid] < A[right]){
			return searchHelper(A,left,mid);
		}else if(A[mid] > A[right]){
			return searchHelper(A,mid+1,right);
		}
		else {
			int lresult=searchHelper(A,left,mid);
			int rresult=searchHelper(A,mid+1,right);

			return Math.min(rresult, lresult);// ? rresult : lresult;
		}
	}
	/** we can also do this without recursion 
	 * same as the first method but if the mid element is equal to righ elements, we can decrease the right
	 * element by one and try
	 * @param A
	 * @param left
	 * @param right
	 * @return
	 */

	private static int findMinIterative(int[] A){

		//have to check for empty array
		if(A.length==0)
			return -1;
		int left=0, right=A.length-1;

		if(left==right){
			return A[left];
		}

		while(left < right) {

			int mid=left+((right-left)/2);

			if(A[left] < A[right])
				return A[left];

			else if(A[mid] < A[right]){
				right=mid;
			}else if(A[mid] > A[right]){
				left=mid+1;
			}
			else {
				right--;
			}
		}
		return A[left];
	}

	/**
	 * if we want to find the position where the target element reside or if not where it should go
	 * The array is rotated and sorted
	 * 
	 * Algorithm:
	 *  1. 7,8,9, 1,2,3,4,5 is an array, see carefully. If A[left]<= A[mid] which means pivot piont is on the right 
	 *  of mid. Consider part of the array, lets say at ith step, the array left =0 and mid is 9, so, pivot will be at right, after that if mid > 2, mid elements will always be less the left
	 *  , may be at jth iteration, the array left element is 2, and mid is 4, so, pivot will be in the left.
	 *  2. If we know the pivot position, we can see that if pivot is on the right of middle element, then we can divide it as left to mid-1 and mid+1 to right
	 *  3. If we know that pivot is on the left, so, if the target is between mid and right, we can divide it from mid+1, right otherwise search to left to mid-1 
	 * @param A
	 * @return
	 * {@link http://blog.gainlo.co/index.php/2017/01/12/rotated-array-binary-search/}
	 */
	static int findTargetElement(int[]A, int target) {

		int l=0, r=A.length-1;

		//the rules is same but have to process in different way considering the target

		while(l<r) {

			int mid=l+(r-l)/2;

			if(A[mid]==target)
				return mid;
			
			if(A[l] <= A[mid]) 
			{ //pivot is on the right
				//if the mid element is less than or equal to target, 
				//so
				if(target >=A[l] && target <= A[mid])
					r=mid-1;
				else l=mid+1;

			}
			//so, here we are because, pivot is on the left, so if the item is between mid to right, we can search right

			else {
				
			if(target >= A[mid] && target <=A[r]) 
			{
				l=mid+1;
			}else 
			{
				r=mid-1;
			}

		}
		}

		return l;
	}

	/**
	 * leetcode solution
	 * @param nums
	 * @param target
	 * @return
	 */
	public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = -1;
        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            //target only right side to prune
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            //If we know for sure left side is sorted or right side is unsorted
                //3 4 5 5 5 1 2 2 3 mid 5, target 4, if case
                //3 4 4 4 4  1 2 2 3 where mid 4 is greater than start , target 2
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
            //any of the two sides won't change the result but can help remove duplicate from
            //consideration, here we just use end-- but left++ works too
             //lets say the list is 2 2 2 2 2 2 2 2 2
            } else {
                end--;
            }
        }
        
        return false;
    }

	static void smallTestfindTargetElement() {
		int[][] rarr= {{4,5,1,2,3},{8,1,3,5},{5,6,7,8,9,1,2,3,4},{3,4,8,9,10,11,12,2},{1},{4,5,6,6,6,6,6,6,7}};
		int[] target= {3,3,7,9,1,6};

		for(int i=0;i<rarr.length;i++) {
			int pos=findTargetElement(rarr[i],target[i]);
			System.out.printf("piovt elements positions %d",pos);
			System.out.println();
		}

	}
	static void smallTestFindMinIterative() {
		int[][] rarr= {{1,2,2},{1,3,5},{2,2,2,0,2,2,2,2,2},{1,1,1,1},{},{1,3,3}};
		//Integer[] rarr1= {1,2,3,4,6,8};
		//Integer[] rarr2= {8};
		for(int i=0;i<rarr.length;i++) {
			int pos=findMinIterative(rarr[i]);


			System.out.printf("piovt elements positions %d",pos);
			System.out.println();
		}
	}

	static void smallTestFindMin() {
		int[][] rarr= {{1,2,2},{1,3,5},{2,2,2,0,1},{1,1,1,1},{},{1,3,3}};
		//Integer[] rarr1= {1,2,3,4,6,8};
		//Integer[] rarr2= {8};
		for(int i=0;i<rarr.length;i++) {
			int pos=findMin(rarr[i]);


			System.out.printf("piovt elements positions %d",pos);
			System.out.println();
		}
	}


	static void smallTestFindMinNonRepeat() {
		Integer[][] rarr= {{20,33,60,1,2,3,4,6,8},{1,2,3,4,6,8},{8},{},{1,1,1,1,1,1,1,1,0}};
		//Integer[] rarr1= {1,2,3,4,6,8};
		//Integer[] rarr2= {8};
		for(int i=0;i<rarr.length;i++) {
			int pos=searchSmallestInSortedRoated(Arrays.asList(rarr[i]));
			System.out.printf("piovt elements positions %d",pos);
			System.out.println();
		}
	}


	public static void main(String args[]) {
		//smallTestFindMin();
		//smallTestFindMinIterative();
		smallTestfindTargetElement();
	}
}
