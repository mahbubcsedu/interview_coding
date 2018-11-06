package leetcode;

public class PrefixSuffixSearch745 {

	static class Trie{
		int weight;
		Trie[] child;//=new Trie[27];
		
		Trie() {
			child=new Trie[27];
			weight=0;
		}
	}
	
	static Trie root=new Trie();
	
	/**
	 * we need to build the dictionary with the words
	 * @param prefix
	 * @param suffix
	 */
	public static void buildDictionary(String[] words) {
		//Trie cur=root;
		
		for(int weight=0; weight < words.length;weight++) {
			String word=words[weight] + "{"; // "{" this aschi is next to z
			
			for(int i=0;i<word.length();i++) {
				
				Trie cur=root;
				cur.weight=weight;
				//we can create all combination of apple{ pple{e, ple{el le{elp
				
				/**
				 * j=i to 2*length = 0, 1, 2,3,4,5,6,7,8,9,10,11
				 * so, j%6=0,1,2,3,4,5,0,1,2,3,4,5 apple{apple
				 * * j=1 to 2*length = 0, 1, 2,3,4,5,6,7,8,9,10,11
				 * so, j%6=1,2,3,4,5,0,1,2,3,4,5 pple{apple
				 * * j=2 to 2*length =   2,3,4,5,6,7,8,9,10,11
				 * so, j%6=2,3,4,5,0,1,2,3,4,5 ple{apple
				 */
				for(int j=i;j<2*word.length()-1;++j) {
					int k=word.charAt(j%word.length())-'a';
					if(cur.child[k]==null) {
						cur.child[k]=new Trie();
					}
					cur=cur.child[k];
					//everytime we updated the weight, so that, the last word, which has the more weight, will prevail, and searching only will rertun that value
					//this is the tricky part, see the drawing
					cur.weight=weight;
				}
				
			}
			//start from root of the tree	
			
		}
	}
	
	
	public static int filter(String prefix, String suffix) {
		Trie cur=root;//for ease of code, using static
		
		for(char ch:(suffix + "{" + prefix).toCharArray()) {
			if(cur.child[ch-'a']==null) return -1;
			cur=cur.child[ch-'a'];
		} 
		return cur.weight;
	
		
	}
	
	/**
	 * this is easy to debug
	 */
	public void test2() {
		
		String[] dict= {"abc","adc"};
		buildDictionary(dict);
		System.out.println(filter("a","c"));
	}
	
	public static void main(String args[]) {
		String[] dict= {"apple","bannana","ample","mapple","ripple"};
		buildDictionary(dict);
		System.out.println(filter("a","e"));
	}
}
