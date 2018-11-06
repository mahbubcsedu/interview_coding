package books.epi;

import java.util.HashMap;
import java.util.Map;

public class IsLetterConstructable {
	public static boolean isLetterConstructable(String magazine, String letter) {
		
		Map<Character, Integer> freqLetter=new HashMap<>();
		
		for(int i=0;i<letter.length();i++) {
			freqLetter.put(letter.charAt(i), freqLetter.getOrDefault(letter.charAt(i), 0)+1);
		}
		
		
		for(int i=0;i<magazine.length();i++) {
			if(freqLetter.containsKey(magazine.charAt(i)))
			{
				if(freqLetter.get(magazine.charAt(i))==0)
				{
					freqLetter.remove(magazine.charAt(i));
					
					if(freqLetter.isEmpty()) {
						break;
					}
				}
			}
		}
		
		//if it breaks, hashmap is emtpy else not, so, if emtpy then its constructible
		return freqLetter.isEmpty();
	}
	
	public static void main(String args[]) {
		String m="abcabcd";
		String letter="ca";
		System.out.println(isLetterConstructable(m,letter));
	}
}
