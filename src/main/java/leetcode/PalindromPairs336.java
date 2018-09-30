package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromPairs336 {
	
	private class TrieNode336{
		private int whichWord=-1;
		private List<Integer> suffixWithPalindrom;
		
		public TrieNode336() {
			this.whichWord=-1;
			this.suffixWithPalindrom=new ArrayList<>();
		}
		
		//this field will define whether it is a words or not
		//private boolean isString=false;
		
		
		//get this node whether it is end of a dictionary word or just a prefix
		//public boolean getIsString() {
	//		return isString;
	//	}
		//set this node as dictionary words
		//public void setIsString(boolean string) {
		//	this.isString=string;
		//}
		
		
		
		Map<Character, TrieNode336> leaves = new HashMap<>();
		
		public Map<Character, TrieNode336> getLeaves(){
			return this.leaves;
		} 
		
		public void setWitchWord(int index) {
			this.whichWord=index;
		}
		
		public int getWichWord() 
		{
		   return this.whichWord;
		}
		
		//public void setSuffixWithPalindrom() {
			
		//}
		public List<Integer> getSuffixWithPalindrom(){
			return this.suffixWithPalindrom;
		}
		
		public boolean isSameWord(int index) {
			return this.whichWord==index;
		}
	}
	
	TrieNode336 root=new TrieNode336();
	
	
	
	
	
	public void insertReverse(String s, int indexOfThisWord) {
		TrieNode336 p=root;
		System.out.println("insert word:"+s);
		/*
		 * We will build dictionary in reverse order, so starting from last
		 * char and will end after first char
		 */
		for(int i=s.length()-1;i>=0;i--) 
		{
			char c=s.charAt(i);
			
			//if current character is not part of the dictionary, create one
			
			if(!p.getLeaves().containsKey(c)) {
				p.getLeaves().put(c, new TrieNode336());
			}
			//check wether the word from here until the first char is palindrom
			if(isPalindrom(s, 0, i)) {
				p.getSuffixWithPalindrom().add(indexOfThisWord);
			}
			
			p=p.getLeaves().get(c);
		}
		
		
		p.getSuffixWithPalindrom().add(indexOfThisWord);
		p.setWitchWord(indexOfThisWord);//add index to the last node to mark the end of this word
		
		//for this one, we do not need to check that this is word or not as
		//the index will mark both isWord and also the index of the word in the dictionary
		
		System.out.println("word insert complete:"+s);
	}
	
	
	public void searchForPalindrom(String word,int wordIndex, List<List<Integer>> res) {
		/**
		 * First try to see which case, the current word is not palindrom with the dictionary words
		 * Case 1: if node.index==-1 that means, it does not represent any word from the dictionary
		 * Case 2: if the node.index= current word index, that means, it is the same word, it it is always true to appending same word in reverse order 
		 * will generate palindrom, so, avaid it.
		 * Case 3: if the current node is Word and rest of the path in Trie from this node is palindrom, append the this word
		 * with all the words whose suffix from this node is palindrom will generate palindrom
		 * 
		 * for example "ab"  "baccdcc", "baddzzdd", so, after traversing ab in the tree, the node will have marker of 
		 * the word ab itself, but after that, both the otherwords from ccdcc and ddzzdd is palindrom,
		 * so, appending ab with baccdcc will be baccdccab and baddzzddab will both be palindrom.
		 * 
		 */
		
		//for(int w=0; w<queryWords.length; w++) {
			TrieNode336 p=root;
			
			for(int i=0;i<word.length();i++) 
			{
				char c=word.charAt(i);
				
				if(p.getWichWord()>=0 && p.getWichWord()!=wordIndex && isPalindrom(word,i,word.length()-1))
				{
					res.add(Arrays.asList(wordIndex,p.getWichWord()));
					System.out.println("found match"+word);
				}
				p=p.getLeaves().get(c);
				
				//if the next node is null just return from there as this wil not form any palindrom
				if(p==null) return;
			}
			
			/**
			 * now if there is anything in the palindrom list, we can add them now as
			 *  they will form palindrom with the current prefix or current word
			 */
			for(int j: p.getSuffixWithPalindrom()) 
			{
				System.out.println("adding res of the pal list"+j);
				if(wordIndex!=j)
				    res.add(Arrays.asList(wordIndex,j));
			}
		
	}
	
	
	public boolean isPalindrom(String s, int start, int end) {
		while(start<end) {
			if(s.charAt(start)!=s.charAt(end)) 
				return false;
			
			start++;
			end--;
		}
		System.out.println(s.substring(start, end+1)+" is palindrom");
		return true;
	}
	
	
	
public List<List<Integer>> getPalindromPairs(String[] D){
		
		int index=0;
		for(String word:D) {
			insertReverse(word,index);
			index++;
		}
		
		
		
		List<List<Integer>> result=new ArrayList<>();
		
		for(int i=0;i<D.length;i++) {
		     searchForPalindrom(D[i],i,result);
		}
		
		return result;
	}

/**
 * we can try using brute force and then using hashmap only
 * 1. Brute for, we take two string from the list and merge them together to see 
 * whether they are palindrom O(n^2)
 * 
 * 2. second approach is to for each string, we divide the string in two parts (0,n)(1,n-1) ... 
 * and for each part reverse it, check in the map
 * try this link later {@link} https://leetcode.com/problems/palindrome-pairs/discuss/79210/The-Easy-to-unserstand-JAVA-Solution
 * O(n) with memory O(n)
 * @param args
 */
	
	public static void main(String args[]) {
		PalindromPairs336 obj=new PalindromPairs336();
		List<List<Integer>> res=obj.getPalindromPairs(new String[] {"ba","bbccdd","a","aaa"});
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	
}
