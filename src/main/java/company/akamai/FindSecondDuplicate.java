package company.akamai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindSecondDuplicate {
/**
 * 1. Brute force approach would be test for each element starting from item at 0 and use linear
 * search to find the match, return as soon as the second match is found.
 * The cost of this functions is O(nlogn) as we need sorting
 * @param nums
 * @return
 */
  public int getSecondDuplicate(int[] nums) {
	//We can sort the array and then find which second duplicate
	  
	  Arrays.sort(nums);
	  //keep a counter for second duplicate
	  int dupCount=0;
	  
	  for(int i=1;i<nums.length;i++) 
	  {
	   if(nums[i]==nums[i-1] && dupCount>0)
		   return nums[i];
	   
	   else if(nums[i]==nums[i-1])
		   dupCount++;
	  }
	return -1;
	  
  }
  /**
   * we can further improve the time complexity using memory as HashMap.
   * We will store the objects and if the count is already 1, we will return, now, 
   * @param nums
   * @return
   */
  public int getSecondDuplicate2(int[] nums) {
		//We can sort the array and then find which second duplicate
		  
		  //Arrays.sort(nums);
		  //keep a counter for second duplicate
		  int dupCount=0;
		  Map<Integer, Integer> numMap=new HashMap<>();
		  
		  for(int i=1;i<nums.length;i++) 
		  {
			  if(numMap.containsKey(nums[i]) && dupCount > 0)
				  return nums[i];
			  else if(numMap.containsKey(nums[i]))
				  dupCount++;
			  else numMap.put(nums[i], numMap.getOrDefault(nums[i],0)+1);
		  }
		return -1;
		  
	  }
  public static void main(String args[]) {
	  FindSecondDuplicate n=new FindSecondDuplicate();
	  n.getSecondDuplicate(new int[] {1,2,3});
  }
  
}
