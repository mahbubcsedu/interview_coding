package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromPaires336
{

	public class Trie{
		//boolean isWord;
		int index;
		Trie[] child=new Trie[26];
		List<Integer> palList=new ArrayList<>();
	}
	
	Trie root;
	
	
	public PalindromPaires336() {
		root=new Trie();
	}
	
	/**
	 * create Trie usind index and isWord
	 * @param words
	 */
	public void buildDictionary(String[] words) {
		Trie p=root;
		int index=0;
		for(String word:words) {
			p=root;
			
			for(int i=word.length()-1;i>=0;i--) 
			{
				int c=(int)word.charAt(i)-'a';
				if(p.child[c]==null) {
					p.child[c]=new Trie();
				}
				
				if(isPalindrome(word,0,i)) {
					p.palList.add(index);
				}
				
				p=p.child[c];
			}
			p.palList.add(index);
			p.index=index;
			index++;
		}
	}
	
	
	boolean isPalindrome(String word, int start, int last) {
		while(start<last) {
			if(word.charAt(start++)!=word.charAt(last--)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * for reverse case, there could be two cases
	 * 1. abc cba normal
	 * 2. abc ba or abc .cba
	 * so we can search for both cases to build
	 * 
	 * think about this lls s   llss is palindrom, think about the concept again
	 */
	/*public void buildPalindrom(String[] dict) {
		//first build dictionary
		buildDictionary(dict);
		//now search total three times for a word
		
		StringBuilder sb=new StringBuilder();
		int dictIndex=0;
		List<List<Integer>> result=new ArrayList<>();
		
		int rindex[] =new int[3];
		for(String word:dict) {
			sb=new StringBuilder(word);
			
			int i=search(sb.reverse().toString());
			rindex[0]=i;
			i=search(sb.append('.').reverse().toString());
			rindex[1]=i;
			i=search(sb.deleteCharAt(0).toString());
			rindex[2]=i;
			
			System.out.println(Arrays.toString(rindex));
		}
	}
	*/
	
	public void search(String word, int index, List<List<Integer>> res) {
		Trie p=root;
		
		for(int j=0;j<word.length();j++) 
		{
			if(p.index>=0 && p.index!=index && isPalindrome(word,j,word.length()-1)) {
				res.add(Arrays.asList(index,p.index));
			}
			int c=(int)word.charAt(j)-'a';
			
			p=p.child[c];
			//if word not finished and there is not node, early exit
			if(p==null)
				return;
		}
		
		for(int j:p.palList) {
			if(j==index) continue;
			res.add(Arrays.asList(index,j));
		}
	}
	
	
	
	
	public List<List<Integer>> palindromePairs(String[] words) {
		
        List<List<Integer>> result=new ArrayList<>();
        
        for(int i=0;i<words.length;i++) {
        	search(words[i],i,result);
        }
        return result;
    }
	
	public static void main(String args[]) 
	{
		String[] dict= {"abcd","dcba","lls","s","sssll"};
		PalindromPaires336 obj=new PalindromPaires336();
		obj.buildDictionary(dict);
		System.out.println(Arrays.deepToString(obj.palindromePairs(dict).toArray()));
		
		//String[] dict2= {"abcd","dcba","lls","s","sssll"};
		//obj.palindromePairs(dict2);
		//System.out.println(Arrays.deepToString(obj.palindromePairs(dict2).toArray()));
	}
}
