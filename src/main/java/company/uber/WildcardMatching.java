package company.uber;

/**
 * This video link explains the DP as well as recursive problems
 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
 * @author mahbub
 *
 */
public class WildcardMatching {
  static boolean isMatch(String s, String p) {
	  //if pattern[i]== ? or pattern[i]==s[i], isMatch(s,n-1,p,m-1)
	  //if patter[i]=* then whether this match without any character at all or including another character
	  // isMatch(s,n-1,p,m) or isMatch(s,n,p,m-1)
	  return isMatch(s,s.length()-1,p,p.length()-1);
  }
  
  //we will require a helper function for recursion
  static boolean isMatch(String s, int sPos, String p, int pPos) 
  {
	  if(pPos==0 && sPos==0)
		  return true;
	  if(pPos <0 || sPos <0)
		  return false;
	  
	  if(s.charAt(sPos)==p.charAt(pPos) || p.charAt(pPos)=='?') 
	  {
		  return isMatch(s,sPos-1,p,pPos-1);
	  }
	  else if(p.charAt(pPos)=='*') {
		  return isMatch(s,sPos-1,p,pPos) | isMatch(s,sPos,p,pPos-1);
	  }
	  
	  return false;
  }
  
  
  static boolean isMatchDp(String t, String p) {
	  int m=t.length();
	  int n=p.length();
	  
	  boolean dp[][] =new boolean[n+1][m+1];
	  dp[0][0]=true;
	  for(int i=1; i<n;i++) {
		  if(t.charAt(i-1)=='*')
			  dp[0][i]=dp[0][i-1]; //previous is true, this will be true, dp is 1 based while stirng is 0 based
		  
	  }
	  
	  //now we will process bottom up process
	  
	  for(int i=1;i<m;i++) {//we will process row by row
		  for(int j=1;j<n;j++) {
			  if(t.charAt(j-1)==p.charAt(i-1) || p.charAt(i-1)=='?'){
				  dp[i][j]=dp[i-1][j-1];
			  }else if(p.charAt(i-1)=='*') {
				  dp[i][j]=dp[i-1][j] || dp[i][j-1]; //should draw the table before writing code
				  
			  }else {
				  dp[i][j]=false;
			  }
		  }
	  }
	  return dp[n][m];
	  //if pattern ="*" and text="", it match if starts 
  }
  public static void main(String args[]) {
	  String[] s= {"xaylmz", "xbyz","xzyllllllllllllz","","xaymmmm","xayyyyyz"};
	  String p= "x?y*z";
	  
	  for(int i=0;i<s.length;i++) {
		  System.out.println(isMatch(s[i],p) ? " Matched":"MisMatch");
	     }
	  }
	  //isMatch()
  }

