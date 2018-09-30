package leetcode;

import java.util.ArrayList;
import java.util.List;

public class WordSquares425 {
	class TrieNode
	{
		List<String> startsWith;
		TrieNode[] children;

		TrieNode(){
			this.startsWith=new ArrayList<>();
			this.children=new TrieNode[26]; //if all are lowercase later
		}
	}

	class Trie{

		TrieNode root;
		/**
		 * This constructor will build a trie based the on provided dictionary
		 * and can be accessed using the root node
		 * @param words are provided as dictionary of words
		 */
		Trie(String[] words) 
		{
			root = new TrieNode();

			for(String word : words) 
			{
				TrieNode p=root;

				char[] wchar=word.toCharArray();

				for( char c: wchar) 
				{
					int idx=c-'a' ;

					if(p.children[idx]== null) 
					{
						p.children[idx] = new TrieNode();
					}
					p=p.children[idx];
					//anythin come to any node, that node starts with the that prefix

					p.startsWith.add(word); 

				}

			}
		}

		/**
		 * the process starts with a emtpy list and root of the tree.
		 * it traverse with each character and if it cannnot find with a chracter the next children
		 * it will return null list. Otherwise will return the list of words when the total prefix
		 * completed traversal of the tree.
		 * @param prefix prefix that will be used to get the list of words
		 * @return a list of words start with prefix
		 */
		public List<String> findByPrefix(String prefix)
		{
			List<String> wordsWithPrefx=new ArrayList<>();

			TrieNode p=root;
			for(char c: prefix.toCharArray()) {
				if(p.children[c-'a']==null)
					return wordsWithPrefx;
				p=p.children[c-'a'];
			}
			wordsWithPrefx.addAll(p.startsWith);

			return wordsWithPrefx;
		}
	}//End of trie class

	/**
	 * Now we will use the backtracking and will find the word square from the list of words
	 * 
	 */

	public List<List<String>> getWordsSquares(String[] words){
		
		List<List<String>> result=new ArrayList<>();
		//first we will check basic constrainds to go further
		if(words.length==0 || words==null)
			return result;
		
		//we will also need to iteratively build the square word and if we are successful, add it to final results and go for next one
		List<String> squareWordBuild=new ArrayList<>();
		
		//First build the Trie
		Trie t=new Trie(words);
		
		//now we will search exhaustively for all the words starting from the first one
		for(String word:words) {
			//add current word to the temp square word and try to find the square word for this. According the backgracking algorithm rules, we can always remove this
			//when we are done searching using this word first by removing the last word from the temp list
			squareWordBuild.add(word); 
			searchHelper(words[0].length(),t,result,squareWordBuild);
			squareWordBuild.remove(squareWordBuild.size()-1);
		}
		
		return result;
	}
	
	public void searchHelper(int wordLength,Trie t,List<List<String>> result, List<String> tempSW) {
		//first check that we already have reached to a square word, and we can gaurantee it by comparing the tempSW's size with the word length
		//if we get one, we will add to the final result and return to this loop in backtracking
		if(tempSW.size()==wordLength)
		{
			result.add(tempSW);
			return;
		}
		
		// we can apply the trick here to find out which prefix we require for next words
		int idx=tempSW.size();
		//now we can create a String builder as we need to find out the character at index idx from all the words of the tempSW
		//please see the explanation from here https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616
		StringBuilder nextPrefix=new StringBuilder();
		//StringBuilder will give the prefix which we can apply to tree to get the list of words that starts with that prefix
		for(String s: tempSW) {
			nextPrefix.append(s.charAt(idx));
		}
		
		List<String> wordsStartWith=t.findByPrefix(nextPrefix.toString());
		
		for(String s: wordsStartWith) {
			tempSW.add(s);
			searchHelper(wordLength,t,result,tempSW);
			tempSW.remove(tempSW.size()-1);
		}
		
	}

}
