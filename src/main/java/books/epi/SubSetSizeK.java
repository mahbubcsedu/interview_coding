package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubSetSizeK {
	
	/**
	 * cost O((n,k))
	 * @param A
	 * @param k
	 * @return
	 */
public static List<List<Integer>> getSubSetK(List<Integer> A, int k){
		
		List<List<Integer>> res=new ArrayList<>();
		List<Integer> tempSet=new ArrayList<>();
		//Collections.sort(A);
		subSetHelper(A,0,tempSet,res,k);
		return res;
	}
	
	public static void subSetHelper(List<Integer> A, int start, List<Integer> tempSet, List<List<Integer>> powerSet,int k) {
		if(k==tempSet.size()) {
			powerSet.add(new ArrayList<>(tempSet));
			return;
		}
		
		for(int i=start;i<A.size();i++) {
			//avoid adding the repeated item from second time, if it add for the first time, we will accept but not the second time
			//if(i>start && A.get(i)==A.get(i-1))
			//	continue;
			tempSet.add(A.get(i));
			subSetHelper(A,i+1,tempSet,powerSet,k);
			tempSet.remove(tempSet.size()-1);
			
		}
	}
	
	
	/**
	 * This is not resolved yet and have to find solutions later, except filtering out same set
	 * @param A
	 * @param k
	 * @return
	 */
public static List<List<Integer>> getSubSetKDup(List<Integer> A, int k){
		
		List<List<Integer>> res=new ArrayList<>();
		List<Integer> tempSet=new ArrayList<>();
		Collections.sort(A);
		subSetHelperDup(A,0,tempSet,res,k);
		return res;
	}
	public static void subSetHelperDup(List<Integer> A, int start, List<Integer> tempSet, List<List<Integer>> powerSet,int k) {
		if(k==tempSet.size()) {
			powerSet.add(new ArrayList<>(tempSet));
			return;
		}
		
		for(int i=start;i<A.size();i++) {
			//avoid adding the repeated item from second time, if it add for the first time, we will accept but not the second time
			if(i>start && A.get(i)==A.get(i-1))
				continue;
			tempSet.add(A.get(i));
			subSetHelper(A,i+1,tempSet,powerSet,k);
			tempSet.remove(tempSet.size()-1);
			
		}
	}
	
	
	public static void test1() {
		List<Integer> input=Arrays.asList(new Integer[] {1,2,3});
		List<List<Integer>> res=getSubSetK(input,2);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void test2() {
		//we can remove duplicate and then go for that
		List<Integer> input=Arrays.asList(new Integer[] {1,2,2,2,3});
		List<List<Integer>> res=getSubSetKDup(input,2);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		test2();
	}
}
