package books.epi;
import java.util.*;

public class PowerSet {

	public static List<List<Integer>> getPowerSet(List<Integer> A){
		
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
			tempSet.add(A.get(i));
			powerSetHelper(A,i+1,tempSet,powerSet);
			tempSet.remove(tempSet.size()-1);
			
		}
	}
	
	
public static List<List<Integer>> getPowerSetEPI(List<Integer> A){
		
		List<List<Integer>> res=new ArrayList<>();
		List<Integer> tempSet=new ArrayList<>();
		//Collections.sort(A);
		powerSetHelperEPI(A,0,tempSet,res);
		return res;
	}
	
	public static void powerSetHelperEPI(List<Integer> A, int start, List<Integer> tempSet, List<List<Integer>> powerSet) {
		if(A.size()==start) {
			powerSet.add(new ArrayList<>(tempSet));
			return;
		}
		
		//for(int i=start;i<A.size();i++) {
			tempSet.add(A.get(start));
			powerSetHelperEPI(A,start+1,tempSet,powerSet);
			tempSet.remove(tempSet.size()-1);
			powerSetHelperEPI(A,start+1,tempSet,powerSet);
			
		//}
	}
	//cost O(2^n)
	
	/**
	 * This all about the binary index from binary count
	 * long(number)/log2 will give us that..... 0, empty, 1, 2^0, so, {1}, 2=2^1 so {2}, 3=2^1+2^0 so, {1,2}
	 * and so on
	 * @param A
	 * @return
	 */
	
	public static List<List<Integer>> powerSetCount(List<Integer> A) {
		final double LOG_2=Math.log(2);
		List<List<Integer>> powerSet=new ArrayList<>();
	    //we will count from 0 until the 2^n where n <=32 for int
		for(int inForSubset=0; inForSubset < 1<< A.size();inForSubset++) {
			//process for each count, we need the binary representation of that number
			int bitArray=inForSubset;
			List<Integer> subset=new ArrayList<>();
			
			while(bitArray!=0) {
				System.out.printf("bit respresent "+Integer.toBinaryString(bitArray));
				System.out.println();
				//see the math
				int index=(int)(Math.log(bitArray & ~(bitArray-1))/LOG_2);
				subset.add(A.get(index));
				System.out.printf("index "+index);
				System.out.println();
				bitArray&=bitArray-1;//remove last setbit
				System.out.printf("bit respresent "+Integer.toBinaryString(bitArray));
				System.out.println();
			}
			powerSet.add(subset);
		}
		return powerSet;
	}
	public static void test1() {
		List<Integer> input=Arrays.asList(new Integer[] {1,2,3});
		List<List<Integer>> res=getPowerSet(input);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	public static void test2() {
		List<Integer> input=Arrays.asList(new Integer[] {1,2,3});
		List<List<Integer>> res=getPowerSetEPI(input);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	
	public static void test3() {
		List<Integer> input=Arrays.asList(new Integer[] {1,2,3});
		List<List<Integer>> res=powerSetCount(input);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	public static void main(String args[]) {
		test3();
	}
}
