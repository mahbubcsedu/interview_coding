package books.epi;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithDistinctElements {

	/**
	 * Try brute force and other way later from leetcode
	 * @param nums
	 * @return
	 */
	public static int findLongestSubArrayDistinct(int[] nums) {
		//we need to keep track of the first item index of longest duplicate free subarray
		
		int longDupFreeStartIndx=0;
		int maxLen=0;
		
		Map<Integer, Integer> lastOccurrenceMap=new HashMap<>();
		//Map<Integer, Integer> lastOccurrenceMap=new HashMap<>();
		for(int i=0;i < nums.length;i++) {
			
			
			//dupIndex is the first value of the dup index
			/**
			 * 1 2 1 3 4 5 3 6 5 3
			 * 
			 * if we are at 3rd position, dup index is 0, so we increment the next length's first character as the next of 
			 * first dup, for this step, 0+1=1
			 * 
			 * Now another stage, lets say we are at 6 and the dupindex is 3, so ,next starting index will be 4 and the length here 
			 * will be 6-1=5
			 */
			Integer dupIndex=lastOccurrenceMap.put(nums[i],i);
			
			//if the current item is not present
			if(dupIndex!=null) 
			{
				
				if(dupIndex>=longDupFreeStartIndx) 
				{		
				 // if the current window is longer, update the maxlen
			 	  maxLen=Math.max(maxLen, i-longDupFreeStartIndx);
			 	// new starting index will be the next index from now or i+1
			 	  longDupFreeStartIndx=dupIndex+1; 
				}
			}
			
			
		}
		
		return maxLen;
	}
	
	public static void main(String args[]) {
		int[] nums= {1,2,1,3,4,5,3,7,8,3};
		int len=findLongestSubArrayDistinct(nums);
		System.out.println(len);
		
		int[] nums2= {1,2,1,3,4,5,3,6,5,3};
		len=findLongestSubArrayDistinct(nums2);
		System.out.println(len);
	}
}
