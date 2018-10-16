package company.uber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestConsecutiveSeq {

	static int longestConsecutiveSeq(List<Integer>A) {
		
		//main algorithm is sorting, by cost O(nlogn)
		
		//so we will use hash
		
		Map<Integer, Integer> map=new HashMap<>();
		for(int i=0; i<A.size();i++) {
			map.put(A.get(i), i);
		}
		
		//now for each elements try to find whether it is the first element of a sequence
		//by checking the existence of its previous elements
		int maxLen=0;
		for(int n: A) {
			
			//if previous element is not available
			if(!map.containsKey(n-1)) {
				int j=n;
				while(map.containsKey(j))
					j++;
				
				//now check whether this sequence is max
				maxLen=Math.max(j-n, maxLen);//as these are sequence number, different will be the length
				
			}
		}
		
		return maxLen;
	}
	
	
	public static void main(String args[]) {
		Integer[] input= {1,9,3,10,4,20,2};
		System.out.println(longestConsecutiveSeq(Arrays.asList(input)));
	}
}
