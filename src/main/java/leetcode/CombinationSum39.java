package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum39 {
public static List<List<Integer>> combinationSum(List<Integer> A, int k){
		
		List<List<Integer>> res=new ArrayList<>();
		List<Integer> tempSet=new ArrayList<>();
		//int temp
		//Collections.sort(A);
		combinationSumHelper(A,0,tempSet,res,k);
		return res;
	}
	
	public static void combinationSumHelper(List<Integer> A, int start, List<Integer> tempSet, List<List<Integer>> powerSet,int remain) {
		if(remain==0) {
			powerSet.add(new ArrayList<>(tempSet));
			return;
		}
		else if(remain<0) {
			return;
		}
		for(int i=start;i<A.size();i++) {
			//avoid adding the repeated item from second time, if it add for the first time, we will accept but not the second time
			//if(i>start && A.get(i)==A.get(i-1))
			//	continue;
			tempSet.add(A.get(i));
			combinationSumHelper(A,i+1,tempSet,powerSet,remain-A.get(i));
			tempSet.remove(tempSet.size()-1);
			
		}
	}
	
	public static void test1() {
		List<Integer> input=Arrays.asList(new Integer[] {1,2,3});
		List<List<Integer>> res=combinationSum(input,2);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void test2() {
		//we can remove duplicate and then go for that
		List<Integer> input=Arrays.asList(new Integer[] {1,2,3,4,5});
		List<List<Integer>> res=combinationSum(input,7);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		test2();
	}
}
