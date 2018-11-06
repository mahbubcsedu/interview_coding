package leetcode;

public class AddSearchWord211 {
/**
 * Problem Statement: Design a data structure that supports the following two operations:

* void addWord(word)
* bool search(word)
* search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

* Example:

* addWord("bad")
* addWord("dad")
* addWord("mad")
* search("pad") -> false
* search("bad") -> true
* search(".ad") -> true
* search("b..") -> true

* Note:
* You may assume that all words are consist of lowercase letters a-z.
 */
	
/**
 * This approach is Trie based solution. This is default implementation except the fact that it can accept a regex alphabet (.) which is for all char
 * So, we can do all the process except the fact that if we can encounter ., then we have to check all available character links from that node too.
 */
TrieNode root;	
public AddSearchWord211() {
	root=new TrieNode();
}	
	
 /**
  * @author mahbub
  * In another problem, I have tried using hashmap for leaves, here we will try using array	
  */
	
  public class TrieNode {
	  public TrieNode[] children = new TrieNode[26];
	  public boolean item=false; // if it string, this is empty string and means no words end in this node, otherwise we will get the whole word
  }
  
  //private TrieNode root=new TrieNode();
  
  /**
   * Trie add information uses simple N-array tree format and each node just keep empty string or the word that end here
   * @param word
   */
  
  public void addWord(String word) {
	  TrieNode p=root;
	  for(char c : word.toCharArray()) {
		  if(p.children[c-'a'] == null) {
			  p.children[c-'a'] = new TrieNode();
		  }
		  p=p.children[c-'a'];
	  }
	  
	  //after adding whole word put a marker
	  p.item=true;
  }
  
  /**
   * this is simple Trie implementation except that we have extra character (.) in our alphabet which will represent all 26
   * This will require us to search recursively and try for all possible character when we see .
   * We will create a recursive function to help on this situation
   * @param s
   * @return
   */
  public boolean search(String word) {
	   return searchHelper(word.toCharArray(), 0, root);
	  
	  //return false;
  }
  
  public boolean searchHelper(char[] word, int startPosition, TrieNode startNode) {
	  
	  /**
	   * if the startPosition is the length of the word then we can check whether this is a word or not
	   * if it's a word return true, otherwise return false
	   */
	  if(startPosition==word.length) return startNode.item;
	  /**
	   * if we encountered any character except (.), check for the current character link. 
	   * if there is any link exist, we can start search from next positions. This is normal trie iteration but using recursion instead of iterative
	   * enumeration.
	   * If we encounter . then we have to try all possible link, we will search star from next position of the word.
	   * If it finds the word with "", return true,
	   * if all case fails then return false;
	   */
	  if(word[startPosition]!='.') {
		  return startNode.children[word[startPosition]-'a'] != null && searchHelper(word, startPosition+1,startNode.children[word[startPosition]-'a']);
	  }
	  else {
		  
		  for(int i=0;i<startNode.children.length;i++) {
			  if(startNode.children[i] != null) {
				  if(searchHelper(word,startPosition+1,startNode.children[i]))
					  return true;
			  }
		  }
	  }
	  
	  //if we encounter . at this positions
	  return false;
  }
  
		
  
  public static void main(String args[]) {
	  
	  AddSearchWord211 obj =new AddSearchWord211();
	  /*obj.addWord("bad");
	  obj.addWord("dad");
	  obj.addWord("mad");
	  
	  System.out.println(obj.search("pad"));
	  System.out.println(obj.search("bad"));
	  System.out.println(obj.search(".ad"));
	  System.out.println(obj.search("b.."));*/
	  
	  obj.addWord("a");
	  obj.addWord("a");
	  System.out.println(obj.search("."));
	  
	  System.out.println(obj.search("a"));
	  System.out.println(obj.search("aa"));
	  System.out.println(obj.search("a"));
	  System.out.println(obj.search(".a"));
	  System.out.println(obj.search("a."));
	  
	  
  }
}
