package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatenatedWords472 {

	static class TrieNode{
		//char c;
		String word;
		TrieNode[] children;//=new TrieNode[26];//each label will have a node, so no need to keep the label 
		boolean isWord;//=false;
		
		public TrieNode() {
			word=null;
			children=new TrieNode[26];//each label will have a node, so no need to keep the label
			isWord=false;
		}
	}
	
	//create a single root of entry point to the dictionary
	
	static TrieNode root=new TrieNode();
	static int countTotal=0;
	
	public static void addWord(String word) 
	{
		TrieNode p=root;
		for(int i=0;i<word.length();i++) 
		{
		   int c=(int)word.charAt(i)-'a';
		   if(p.children[c]==null) {
			   p.children[c]=new TrieNode();
		   }
		   p=p.children[c];
		}
		//when we are done insert the current word, mark the last node as word
		p.isWord=true;
		p.word=word;
	}
	
	
	/*
	 * break vs return: return is returned to caller position exiting furthur execution, break is breaks the loop and will not advance further
	 * @link https://stackoverflow.com/questions/6620949/difference-between-return-and-break-statements
	 */
	public static boolean searchWord(String word, int start) {
		
		//System.out.println(word.substring(start));
		if(word.length()==start)
			return true;
		
		TrieNode p=root;
		boolean isConcatenated=false;
		//countTotal++;
		
		for(int i=start;i<word.length();i++) {
			int c=(int)word.charAt(i)-'a';
			
			//must check isWord first and then next children as if the end of this is the word, its fine to return true
			if(p.children[c]!=null && p.children[c].isWord) {
				//count++;
				//countTotal++;
				//is current node is a word, start new search with suffix
				isConcatenated=searchWord(word,i+1);
				//if(!isConcatenated)
				//	countTotal--;
				//else countTotal--;
			}
			//check for current character
			else if(p.children[c]==null) {
				//we are here because, we already iterated all path exists in the dictionary but the word is not finished
				//which means its not present here
				return false;
			}
			
			//if the suffix is not concatenated, go for next check
			if(!isConcatenated) {
			   //countTotal--;
			   p=p.children[c];
			}
			if(isConcatenated) {
				countTotal++;
				break;
				//return true;
			}
		}
		return isConcatenated;
	}
	
	/**
	 * did not pass all test cases, only 6/14 passed, needs to see seriously later
	 * @param words
	 * @return
	 */
	public static List<String> findAllConcatenatedWordsInADict(String[] words){
		//Integer count=0;
		
		//build the tree of dict first
		for(String word:words) {
		   addWord(word);
		 }
		
		List<String> result=new ArrayList<>();
		
		for(String word:words) {
			countTotal=0;
			boolean res=searchWord(word,0);
			if(countTotal>1) {
				result.add(word);
			}
			
		}
		
		return result;
		//boolean res=searchWord(dict[5],0,);
	}
	
	
	static void leetCodeTest() {
		String[] dict= {"frowning","undermining","collocate","decipherers","flashier","galabiehs","padles","aquamarines","abrupt","stargazed","descriptor",
				"naturalized","effectuality","undrunk","cubically","stibium","goosiest","algicide","stargazes","halyards","stargazer","intravital","ionone",
				"intravitam","quartets","bracteal","precombustion","knottiness","housefly","intervocalic","hyperrealism","garbages","fluorotic","gremmies","reannexing",
				"zorils","carioles","perspicacity","somberness","snaffle","transilluminators","hollowware","slatternly","areolate","flit","misenrolling","seedmen","electroforming",
				"nonimmigrants","lebens","usurpers","disaffecting","flic","copyhold","hyperrealist","actinometers","resuscitation","stiffening","whinniest","flip","fretters"};
		List<String> res= findAllConcatenatedWordsInADict(dict);
		System.out.println(Arrays.deepToString(res.toArray()));
		
	}
	
	public static void main(String args[]) {
		leetCodeTest();
		//String[] dict= {"cat","cats","dogs","catsanddogs","and","dogscat"};
		//List<String> res= findAllConcatenatedWordsInADict(dict);
		//System.out.println(Arrays.deepToString(res.toArray()));
		
	}
	
}
