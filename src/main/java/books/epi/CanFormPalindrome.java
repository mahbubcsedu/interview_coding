package books.epi;

import java.util.HashMap;
import java.util.Map;

public class CanFormPalindrome {

	public static boolean canFormPalindrome(String s) {
		Map<Character, Integer> freqMap=new HashMap<>();
		
		for(int i=0;i<s.length();i++) {
			freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0)+1);
		}
		
		int oddCount=0;
		for(Map.Entry<Character, Integer> e: freqMap.entrySet()) {
			if(e.getValue() %2 != 0 && ++oddCount >1)
				return false;
		}
		
		return true;
	}
	
	public static void main(String args[]) {
		String s="abcabcd";
		System.out.println(canFormPalindrome(s));
	}
}
