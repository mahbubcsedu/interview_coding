package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement {
/**
 * Majority element algorithms,
 */
  /**
   * The first one brute force is to try for every element and find out the count of each, finally compare, I am not going to code or copy
   * 1. for each number, count how many times it present and see if it crosses floor n/2
   */
	
	
	public int majorityElement(int[] nums) {
        
		int majorityCount = nums.length/2;

        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }

            if (count > majorityCount) {
                return num;
            }

        }

        return -1;    
    }
	
	/**
	 * second algorithm is to sort O(nlogn)
	 * This will not decide whethere there is major elments or not, its assume that the array has major element
	 */
	
	
	public int majorElementSort(int[] nums) {
		//List<Integer> num=(Integer)nums;
		Arrays.sort(nums);
		return nums[nums.length/2];
	}
	
	/**
	 * We can store the value and their freuqnecy to a hashmap and keep the maxmum one with the element to return
	 */
	
	public int majorElementHashMap(int[] nums) {
		int max=0;
		int majorNum=-1;
		Map<Integer, Integer> freqmap=new HashMap<>();
		
		for(Integer i:nums) {
			freqmap.put(i, freqmap.getOrDefault(i, 0)+1);
			
			if(freqmap.get(i)>max) 
			{
			max=Math.max(max, freqmap.get(i));
			majorNum=i;
			
			}
		}
		
		return majorNum;
	}
	
	
	/**
	 * Moore algorithm voting
	 */
	public int majorElementMoor(int[] nums) {
		int count=1;
		int majorNum=nums[0];
		//Map<Integer, Integer> freqmap=new HashMap<>();
		
		for(int i=1;i<nums.length;i++) {
			//if less than, will fail if it is 
			if(count==0) { //only change when it is 0
				majorNum=nums[i];
			}
			if(majorNum==nums[i])
				count++;
			else count--;
			
			
		}
		
		return majorNum;
	}
	
	
	public static void main(String args[]) {
		MajorityElement o=new MajorityElement();
		
		int[] nums= {47,47,72,47,72,47,79,47,12,92,13,47,47,83,33,15,18,47,47,47,47,64,47,65,47,47,47,47,70,47,47,55,47,15,60,47,47,47,47,47,46,30,58,59,47,47,47,47,47,90,64,37,20,47,100,84,47,47,47,47,47,89,47,36,47,60,47,18,47,34,47,47,47,47,47,22,47,54,30,11,47,47,86,47,55,40,49,34,19,67,16,47,36,47,41,19,80,47,47,27};
		
		int[] n2= {5,1,1,1,14,3,2,2,2,2,2,2,2};
		int n=o.majorElementMoor(n2);
		System.out.println(n);
	}
	
}
