package leetcode;

import java.util.HashMap;
import java.util.Map;



public class  TrieNode{
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