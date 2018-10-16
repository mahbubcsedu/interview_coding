package company.uber;

import java.util.Arrays;
import java.util.List;

/*
 * 
 */
public class MaxdistanceIncreasing {

	/**
	 * {@link https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/}
	 * 
	 */
	
	static int getMaxDistance(List<Integer>A) {
		
		int maxDiff;
		int i,j;
		
		int rMax[] =new int[A.size()];
		int lMin[] =new int[A.size()];
		
		lMin[0]=A.get(0);
		rMax[A.size()-1]=A.get(A.size()-1);
		
		for(i=1; i< A.size();i++)
			lMin[i]=Math.min(lMin[i-1], A.get(i));
		for(j=A.size()-2;j>=0;j--)
			rMax[j]=Math.max(rMax[j+1], A.get(j));
		
		
		i=0;
		j=0; maxDiff=0;
		
		while(j<A.size() && i <A.size()) {
			if(lMin[i] < rMax[j])
			{
				maxDiff=Math.max(maxDiff, j-i);
				j++;
			}
			else {
				i++;
			}
		}
		
		
		return maxDiff;
	}
	
	
	public static void main(String args[]) {
		Integer[] input= {34, 8, 10, 3, 2, 80, 30, 33, 1};
		System.out.println(getMaxDistance(Arrays.asList(input)));
	}
}
