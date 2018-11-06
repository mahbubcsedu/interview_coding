package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII126 {

	int globalMin;
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
	{
		List<List<String>> result=new ArrayList<>();
		Set<String> temp=new LinkedHashSet<>();
		
		Queue<String> q=new LinkedList<>();
		Map<String, Integer> dict=new HashMap<>();
		for(String word:wordList) {
			dict.put(word, 1);
		}
		this.globalMin=Integer.MAX_VALUE;
		temp.add(beginWord);
		
		if(dict.containsKey(beginWord))
			dict.remove(beginWord);
		
		Set<String> temdict=new HashSet<>(dict.keySet());
		this.globalMin=getShortestDistance(beginWord,endWord,temdict);
		if(this.globalMin<0)
			return result;
		
		findLadderHelper(beginWord,endWord,dict,temp,result);
		
		return result;

	}

	/**
	 * time limit exceeded
	 * @param beginWord
	 * @param endWord
	 * @param dict
	 * @param curSeq
	 * @param res
	 */
	public void findLadderHelper(String beginWord, String endWord,Map<String,Integer> dict, Set<String> curSeq,List<List<String>> res) {
		if(beginWord.equals(endWord)) {
			//we found one sequence
			//System.out.println("set size:"+curSeq.size()+"globalsize:"+this.globalMin);
			//System.out.println(Arrays.deepToString(res.toArray()));
			if(curSeq.size()<this.globalMin) {
				//System.out.println("set size:"+curSeq.size()+"globalsize:"+this.globalMin);
				this.globalMin=curSeq.size();
				//creating new arraylist here creates problem, I need to investigate later
				res.clear();//discard all previous
				res.add(new ArrayList<String>(curSeq));
			}
			else if(curSeq.size()<=this.globalMin+1) {
			res.add(new ArrayList<String>(curSeq));
			}
			return;
		}
		
		if(curSeq.size()>this.globalMin) {
			System.out.println("set size:"+curSeq.size()+"globalsize:"+this.globalMin);
			return; //this will not lead to any results
		}
		for(int i=0;i<beginWord.length();i++) {
			//prepare new word with change one
			String start=i==0?"":beginWord.substring(0,i);//i exclusive
			String end=i==beginWord.length()-1 ? "": beginWord.substring(i+1);//in inclusive
			for(int j=25;j>=0;j--) {
				String newWord=start+(char)(j+'a')+end;
				if(dict.containsKey(newWord) && curSeq.add(newWord)) {
					//System.out.println(Arrays.deepToString(curSeq.toArray()));
					findLadderHelper(newWord,endWord,dict,curSeq,res);
					curSeq.remove(newWord);
				}
				
			}
		}
	}
	
	public int getShortestDistance(String beginWord, String endWord,Set<String> dict) {
		Map<String,Integer> distance=new HashMap<>();
		Queue<String> q=new LinkedList<>();
		q.add(beginWord);
		distance.put(beginWord, 0);
		
		while(!q.isEmpty()) {
			String str=q.poll();
		 if(str.equals(endWord)) {
			 return distance.get(endWord);
		 }	
		 
		 for(int i=0;i<str.length();i++) {
			 String being=i==0?"":str.substring(0, i);
			 String end=i==str.length()-1?"":str.substring(i+1);
			 
			for(int j=0;j<26;j++) {
				String modStr=being+(char)(j+'a')+end;
				if(dict.remove(modStr)) {
					distance.put(modStr, distance.get(str)+1);
					//dict.remove(modStr);
					q.add(modStr);
				}
			}
		 }
		 
		}
		
		
		return -1;
	}
	
	void test1() {
		WordLadderII126 obj = new WordLadderII126();
		String[] dict= {"hit","hot","dot","dog","lot","log","cog"};
		String begin="hit", end="cog";

		List<List<String>> res=obj.findLadders(begin, end,Arrays.asList(dict));

		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	void test2() {
		
		WordLadderII126 obj = new WordLadderII126();
		String[] dict= {"hot","dot","dog","lot","log"};
		String begin="hit", end="cog";

		List<List<String>> res=obj.findLadders(begin, end,Arrays.asList(dict));

		System.out.println(Arrays.deepToString(res.toArray()));
		
	}
	
	void test3() {
		WordLadderII126 obj = new WordLadderII126();
		String[] dict= {"a","b","c"};
		String begin="a", end="c";

		List<List<String>> res=obj.findLadders(begin, end,Arrays.asList(dict));

		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		WordLadderII126 obj = new WordLadderII126();
		obj.test1();
		//obj.test2();
		obj.test3();

	}
}
