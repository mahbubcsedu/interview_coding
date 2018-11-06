package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePartition131 {
	
public static List<List<String>> palindromePartition(String s){
		
		List<List<String>> res=new ArrayList<>();
		List<String> tempSet=new ArrayList<>();
		//int temp
		//Collections.sort(A);
		Helper(s,0,tempSet,res);
		return res;
	}
	
	public static void Helper(String s, int start, List<String> tempSet, List<List<String>> palDecompose) {
		if(start==s.length()) {
			palDecompose.add(new ArrayList<>(tempSet));
			return;
		}
		
		for(int i=start;i<s.length();i++) {
			//avoid adding the repeated item from second time, if it add for the first time, we will accept but not the second time
			String prefix=s.substring(start, i+1);
			if(isPalindrome(prefix)) {
			tempSet.add(prefix);
			Helper(s,i+1,tempSet,palDecompose);
			tempSet.remove(tempSet.size()-1);
			}
			
		}
	}
	
	public static boolean isPalindrome(String pref) {
		for(int i=0,j=pref.length()-1;i<j;i++,j--) {
			if(pref.charAt(i)!=pref.charAt(j))
				return false;
			
		}
		return true;
	}
	public static void test1() {
		//List<Integer> input=Arrays.asList(new Integer[] {1,2,3});
		List<List<String>> res=palindromePartition("aab");
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void test2() {
		//we can remove duplicate and then go for that
		//List<Integer> input=Arrays.asList(new Integer[] {1,2,2,3,4,5});
		List<List<String>> res=palindromePartition("0204451881");
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		//test2();
		test1();
	}
}
