package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityElementII {

	public List<Integer> majorElementMoor(int[] nums){
		
		if(nums==null || nums.length==0) return new ArrayList<Integer>();
		
		
		
		List<Integer> result=new ArrayList<>();
		
		if(nums.length==1) {
			result.add(nums[0]);
			return result;
		}
		else if(nums.length<=2 && nums[0]!=nums[1]) {
			for(int n:nums) {
				result.add(n);
			}
			return result;
		}
		
		int major1=nums[0], major2=nums[1], count_1=0, count_2=0;
		//i should start from 0 not 1, check for an example [1,2], major element will be [1,2]
		for(int i=0;i<nums.length;i++) {
			if(nums[i]==major1) {
				count_1++;
			}else if(nums[i]==major2) {
				count_2++;
			}else if(count_1==0) {
				major1=nums[i];
				count_1=1;
			}else if(count_2==0) {
				major2=nums[i];
				count_2++;
			}else {
				count_1--;
				count_2--;
			}
			
		}
		
		//now we will have two numbers which we can get match with the total numbers for validity
		count_1=0; count_2=0;
		
		for(int i=0;i<nums.length;i++) {
		  	if(nums[i]==major1)
		  		count_1++;
		  	else if(nums[i]==major2)
		  		count_2++;
		  	
		}
		
		if(count_1>nums.length/3)
			result.add(major1);
		
		if(count_2>nums.length/3)
			result.add(major2);
		
		return result;
		
	}
	
	//Leetcode best solution
	/**
	 * Please check this out later for detail algorithms and how it works but this is o(nlogn) as sorting is done
	 * @param nums
	 * @return
	 */
	public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i + n / 3 < nums.length; ) {
            if (nums[i] == nums[i + n / 3]) {
                res.add(nums[i]);
                i = i + n / 3 + 1;
                while (i < n && nums[i] == nums[i - 1])
                    i++;
            } else {
                i++;
            }
        }
        
        return res;
    }
	public static void main(String args[]) {
		MajorityElementII o=new MajorityElementII();
		
		
		
		int[] n2= {2,2,2,2,3,3,4,4,4,4,1,1}; //no majority, it would be n/3, 4 out of 12 is not a majorirty of n/3 but requires 5
		int[] n1= {1,2};
		int[] n3= {2};
		int[] n5= {2,2};
		int[] n4= {0,-1,2,-1};
		
		int[] n6= {4,2,1,1};
		List<Integer> n=o.majorElementMoor(n2);
		System.out.println(Arrays.deepToString(n.toArray()));
		
		n=o.majorElementMoor(n1);
		System.out.println(Arrays.deepToString(n.toArray()));
		
		n=o.majorElementMoor(n3);
		System.out.println(Arrays.deepToString(n.toArray()));
		
		n=o.majorElementMoor(n4);
		System.out.println(Arrays.deepToString(n.toArray()));
		
		
		n=o.majorElementMoor(n5);
		System.out.println(Arrays.deepToString(n.toArray()));
		
		n=o.majorElementMoor(n6);
		System.out.println(Arrays.deepToString(n.toArray()));
	}
}
