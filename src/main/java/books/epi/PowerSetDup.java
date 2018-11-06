package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PowerSetDup {
public static List<List<Integer>> getPowerSetDup(List<Integer> A){
		
		List<List<Integer>> res=new ArrayList<>();
		List<Integer> tempSet=new ArrayList<>();
		Collections.sort(A);
		powerSetHelper(A,0,tempSet,res);
		return res;
	}
	
	public static void powerSetHelper(List<Integer> A, int start, List<Integer> tempSet, List<List<Integer>> powerSet) {
		//if(start==tempSet.size()) {
			powerSet.add(new ArrayList<>(tempSet));
			//return;
		//}
		
		for(int i=start;i<A.size();i++) {
			//avoid adding the repeated item from second time, if it add for the first time, we will accept but not the second time
			if(i>start && A.get(i)==A.get(i-1))
				continue;
			tempSet.add(A.get(i));
			powerSetHelper(A,i+1,tempSet,powerSet);
			tempSet.remove(tempSet.size()-1);
			
		}
	}
	
	public static void test1() {
		List<Integer> input=Arrays.asList(new Integer[] {1,2,2,3});
		List<List<Integer>> res=getPowerSetDup(input);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	public static void main(String args[]) {
		test1();
	}
}
