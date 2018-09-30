package leetcode;
import java.util.HashMap;
import java.util.Map;
//import java.util.Map;
import java.util.Set;
//import java.util.HashMap;


public class ShortestUniquePrefixEpi {
 
	private class  TrieNode{
		//this field will define whether it is a words or not
		private boolean isString=false;
		
		private Map<Character, TrieNode> leaves = new HashMap<>();
		//get this node whether it is end of a dictionary word or just a prefix
		public boolean getIsString() {
			return isString;
		}
		//set this node as dictionary words
		public void setIsString(boolean string) {
			this.isString=string;
		}
		
		//get leaves of this node
		public Map<Character, TrieNode> getLeaves(){
			return leaves;
		}	
	}
	
	
	/**
	 * if we have all functions ready for Trie, we can first create Trie dictionary
	 * and then search for prefix
	 * @author mahbub
	 *
	 */
	public static String findShortestPrefix(String s, Set<String> D) {
		ShortestUniquePrefixEpi T =new ShortestUniquePrefixEpi();
		for(String word:D) {
			T.insert(word);
		}
		
		return T.getShortestUniquePrefix(s);
		//return "";
	}
	
	
	
	
	
	    private TrieNode root=new TrieNode();
	
		//insert all the string from a dictionary
		public boolean insert(String s) {
			TrieNode p=root;
			for(int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if(!p.getLeaves().containsKey(c)) {
					p.getLeaves().put(c, new TrieNode());
				}
				p=p.getLeaves().get(c);//p will move to next stage or p will be moved to p's child
			}
			
			//now we already have inserted the string s on trie
			//now check whether the last node encountered already existed as a dictionary word
			if(p.getIsString()) {
				return false; // this will return false, to let it know that the word is already existed
			
			}else {
				//p.getIsString() ==false, means, this s is a new word to trie
				p.setIsString(true);
				return true;
			}
			
			}//end of insert method
			
		/**
		 * The question is bit tricky, find the shortest prefix which is not the prefix of any dictionary word's prefix
		 * This means, using search string, in some point, it will fail to find the prefix from the dictionary and will have empty node for,
		 * the first that nodes prefix will be returned
		 * 
		 * now we already have trie implemented together with insert, but no search
		 * so if we require to find shortest unique prefix, we have to implement search function in such way
		 */
		public String getShortestUniquePrefix(String s) {
			TrieNode p=root;
			StringBuilder prefix=new StringBuilder();
			
			for(int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				prefix.append(c);
				if(!p.getLeaves().containsKey(c)) {
					return prefix.toString();//because, the first time it failes to find a child with current character
				}
				p=p.getLeaves().get(c);
			}
			
			return "";
		}
		
		
	
}
