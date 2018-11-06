package leetcode;
import java.util.*;
/**
 * 
 * @author mahbub
 * Don't forget to analyze the complexity, its must
 * This is different in a sense that it has a dictionary of words to search the matching which is costly and
 * trie could be a good data structure
 *{@link https://leetcode.com/problems/word-search-ii/discuss/59784/My-simple-and-clean-Java-code-using-DFS-and-Trie a good solution trie based}
 */
public class WordSearchII_412 {

	
	static Set<String> res=new TreeSet<>();
	//nowhere to be said sorted, so check while submit
	
	public static class Trie{
		String word=null;
		Trie[] children=new Trie[26];
		boolean isWord=false;
	}
	
	
	static Trie root=new Trie();
	
	public static void addWord(String word) {
		Trie p=root;
	    for(int i=0;i<word.length();i++) {
	    	int c=(int)word.charAt(i)-'a';
	    	
	    	if(p.children[c]==null) {
	    		p.children[c]=new Trie();
	    	}
	    	p=p.children[c];
	    }
	    p.isWord=true;
	}
	
	
	static boolean isPrefix(String prefix) {
		Trie p=root;
		for(int i=0;i<prefix.length();i++) {
			int c=(int)prefix.charAt(i)-'a';
			if(p.children[c]==null)
				return false;
			else p=p.children[c];
		}
		return true;
	}
	
	static boolean search(String word) {
		Trie p=root;
		for(int i=0;i<word.length();i++) {
			int c=(int)word.charAt(i)-'a';
			if(p.children[c]==null)
				return false;
			else p=p.children[c];
		}
		return p.isWord;
	}
	public static List<String> findWords(char[][] board, String[] words){
	  	for(String word:words) {
	  		addWord(word);
	  	}
	  	
	  	int m=board.length;
	  	int n=board[0].length;
	  	
	  	boolean[][] visited=new boolean[m][n];
	  	
	  	for(int i=0;i<m;i++) {
	  		for(int j=0;j<n;j++) {
	  			dfs(board,visited,"", i,j);
	  		}
	  	}
	  	
	  	return new ArrayList<String>(res);
	}
	
	
	
	public static void dfs(char[][] board,boolean[][] visited,String str,int x, int y) {
		if(x<0 || y <0 || x>=board.length || y >= board[0].length) 
			return;
		
		if(visited[x][y])
			return;
		str+=board[x][y];
		
		if(!isPrefix(str))
			return;
		if(search(str))
			res.add(str);
		
		visited[x][y]=true;
		
		final int[][] DIRS= {{0,1},{1,0},{-1,0},{0,-1}};
		
		for(int[] dir: DIRS) {
			dfs(board,visited,str,x+dir[0],y+dir[1]);
			
		}
		visited[x][y]=false;//so for another search word this will be available;
		
	}
	
	static void  smallBoundaryTest() {
		String[] words = {"oath","pea","eat","rain"};
		char[][] board ={{'o','a','a','n'},{'e','t','a','e'},
				  {'i','h','k','r'},
				  {'i','f','l','v'}};
						
		List<String> res=findWords(board,words);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	
	static void  smallBoundaryTest2() {
		String[] words = {""};
		char[][] board ={{'o'}};
						
		List<String> res=findWords(board,words);
		
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		smallBoundaryTest2();
		
	}
	
}
