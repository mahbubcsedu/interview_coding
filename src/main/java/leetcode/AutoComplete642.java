package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoComplete642 {

	class Trie{
		int times;//to keep track to number of times typed
		Trie[] child=new Trie[27]; //26 for character a-z and 1 for space
	}
	
	/*
	 * As we have extra character space, we can define a function to get the index from character
	 */
	
	public int getIndex(char c) {
		return c==' '? 26: c-'a';
	}
	
	/**
	 * define a function to insert a sentence
	 * @param t
	 * @param s
	 * @param times
	 */
	public void insert(Trie t, String s, int times) {
		for(int i=0;i<s.length();i++) {
			//if new entry, create a node else proceed
			int index=getIndex(s.charAt(i));
			if(t.child[index]==null) {
				t.child[index]=new Trie();
			}
			t=t.child[index];
		}
		//when we complete inserting, increment that last node 
		t.times++;
	}
	
	/**
	 * we need to define a function which will lookup with a prefix and return all the sentences with that prefix.
	 * But we need both sentence and their corresponding ranking, so can define a class to keep the information encapsulated
	 */
	
	class Node{
		String sentence;
		int times;
		Node(String st, int times){
			this.sentence=st;
			this.times=times;
		}
	}
	
	
	/**
	 * Now define the lookup functions which will return list of Nodes
	 */
	
	public List<Node> lookup(Trie t, String prefix){
		for(int i=0;i<prefix.length();i++) {
			int index=getIndex(prefix.charAt(i));
			if(t.child[index]==null) {
				return new ArrayList<Node>(); 
				//if this sentence is longer that the prefixes it has, we will return empty list
			}
			t=t.child[index];//we will go all the way to the end of prefix
		}
		//now we are at a node where all the sentences starts with current prefix, so, we will add all the setences tranversing this information
		List<Node> list=new ArrayList<>();
		traverse(prefix,t,list);
		return list;
	}
	
	
	public void traverse(String s, Trie t, List<Node> list) {
		//t is the TrieNode where prefix ended
		if(t.times>0) {
			//this means, this node is the end marker of one of the sentences and we will add the sentence
			list.add(new Node(s,t.times));
		}
		//now we will recursively search for this condition
		for(char i='a'; i<='z';i++) {
			int index=i-'a';
			if(t.child[index]!=null) {
				traverse(s+i,t.child[index],list);
			}
		}
		//after all traverse of character, we still have to traverse for space
		
		if(t.child[26]!=null) {
			traverse(s+' ',t.child[26],list);
		}
	}
	
	//now using lookup we will be able to get the list of sentences and times, but we need to return them in sorted order
	
	public List<String> getTop3(List<Node> list){
		Collections.sort(list,(a,b)->a.times==b.times ? a.sentence.compareTo(b.sentence):b.times-a.times);
		List<Node> response=list.size()>=3 ? list:list.subList(0, 3);
		List<String> resHist=new ArrayList<>();
		
		for(Node n:response) {
			resHist.add(n.sentence);
		}
		return resHist;
	}
	
	Trie dict;
	public AutoComplete642(String[] sentences, int[] times) {
		dict=new Trie();
		
		for(int i=0;i<sentences.length;i++) {
			insert(dict,sentences[i],times[i]);
		}
	}
	
	String curTyped="";
	
	public List<String>  input(char c){
		List<String> res=new ArrayList<>();
		if(c=='#') {
			insert(dict,curTyped,1);
		}else {
			curTyped+=c;
			List<Node> list=lookup(dict,curTyped);
			res=getTop3(list);
		}
		
		return res;
	}
	
	/**
	 * cost is very important to calculate
	 * AutoComplete has the cost of O(k*l) as k sized l sentences need to insert
	 * 
	 * input() has the cost of O(p+q+mlogm) where p is cost of length of sentece formed until the curnode+p is the number of nodes after prefix 
	 * and we need to sort the arraylist to get top3 mlogm
	 */
}
