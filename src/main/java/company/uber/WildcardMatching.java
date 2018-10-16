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
  
  public static void main(String args[]) {
	  String[] s= {"xaylmz", "xbyz","xzyllllllllllllz","","xaymmmm","xayyyyyz"};
	  String p= "x?y*z";
	  
	  for(int i=0;i<s.length;i++) {
		  System.out.println(isMatch(s[i],p) ? " Matched":"MisMatch");
	     }
	  }
	  //isMatch()
  }

