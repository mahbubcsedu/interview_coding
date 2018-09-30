package leetcode;

import java.util.List;

public class ValidWordSeq422 {
	
	public boolean validWordSquare(List<String> words) {
		/**
		 * There is no dynamic starting but we have to check from (0,0) and all the word
		 * in sequence. So, the list of strings are ordered and does not need to change.
		 * We only have check first word's second position and second word's first position
		 * then first word's 3rth with second words first and so on.
		 * 
		 * Also if the number of words and each words length are not equal, its not possible
		 * 
		 */
		
		// if there is no constraint set, we need to check for word size
		
		if(words == null || words.size()==0) {
			return true;
		}
		//if each words length and number of words are different, its always false
	    
		int numOfWords=words.size();
		
		if(numOfWords!=words.get(0).length()) {
			return false;
		}
		
		for(int i=0;i<numOfWords;i++) 
		{
			
			for(int j=0;j<words.get(i).length();j++) //ith words jth character
			{
				/**
				 * 
				 *
				i and j start from 0 index, so if j>=numOfWords, means square not possible as numOfwords is greater
				 if j-th word's length <=i, that means, ith less than i or equal it means number of word is less
				 j-th words i-th character, ith word jth character
				 * 
				 * abcd
				 * bnrt
				 * crmy
				 * dtye
				 * 
				 * if we loop for 4 times and check, 0-th word, 0 chara, 0th word 1-st to 1-st word's 0th, 0-th 2nd, 2-nd words 0
				 * for if we have two loop, outer loop will i and inner loop j
				 * then (i,j)
				 * 
				 * (0,0) (0,1) (0,2) (0,3)
				 * (1,0) (1,1) and so on
				 * 
				 * 
				 * 
				 */
				
				
				if(j >= numOfWords || words.get(j).length() <=i || words.get(j).charAt(i)!=words.get(i).charAt(j))
					return false;
			}
		}
	
		return true;
	}

}
