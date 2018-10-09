package leetcode;

public class ShortestPalindrom214 {
  public static String getShortestPalindrom(String s) {
	  
	  /**
	   * The problem will allow to add as many character as need to make the string a palindrome.
	   * First we can try to find what is the maximum palindrom we can find from the existing one and which starts at first char as we can only add to first of the string
	   * aacecaaa, we also can iterate only half of the string as at most half string will be good enough for a palindrom starts at first character, aacecaa, so, from e we can calculate most
	   * so, checking for c at pos 4 is redundant 
	   * as we have to check for palindromocity at first character, we will check the condition of lower point as -1 in maxPalindrom
	   * the remaining part from the last of the string will be added at first to give use the results.
	   * 
	   */
	  if(s.length()==1)
		  return s;
	  
	  int maxLen=0;
	  for(int i=0;i<=s.length()/2;i++) {
		  maxLen=Math.max(maxLen, maxPalindrome(s,i,i));
		  maxLen=Math.max(maxLen,maxPalindrome(s,i,i+1));
	  }
	  
	  StringBuilder sb=new StringBuilder(s);
	  for(int i=0;i<s.length()-maxLen;i++) {
		  sb.insert(i,s.charAt(s.length()-1-i));
	  }
	  return sb.toString();
  }
  
  static int maxPalindrome(String s,int i, int j) {
	  int max=0, pos=0;
	  while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
		  i--;
		  j++;
	  }
	  if(i==-1)
		  max=j;
	  return max;
  }
  
  public static void main(String args[]) {
	  System.out.println(getShortestPalindrom("aacecaaa"));
	  System.out.println(getShortestPalindrom("abcd"));
	  System.out.println(getShortestPalindrom("aadd"));
	  System.out.println(getShortestPalindrom("a"));
	  System.out.println(getShortestPalindrom("aa"));
	  System.out.println(getShortestPalindrom("aba"));
  }
}
