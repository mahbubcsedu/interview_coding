package company.uber;

import java.util.Arrays;

/**
 * The problem is explained here very clearly and you can solve many problems which falls into this category
 * @link https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 * @author mahbub
 *
 */
public class LongestCommonSubsequence 
{
	/*
	 * 
	 */
  static int lcsRecursive(String s, String p) 
  {
	  return lcsRecHelper(s,s.length()-1,p,p.length()-1);
  }
  
  static int lcsRecHelper(String s, int n, String p, int m){
	  if(n==0 || m==0)
		  return 0;
	  if(s.charAt(n)==p.charAt(m))
		  return 1+ lcsRecHelper(s,n-1,p,m-1);
	  else
		  return Math.max(lcsRecHelper(s,n-1,p,m),lcsRecHelper(s,n,p,m-1));
  }
  
  
  static int lcsDP(String s, String p) {
	  int[][] lcs=new int[s.length()][p.length()];
	  
	  //initialize the first row and first column as 0
	  for(int i=0;i<lcs.length;i++) {
		  for(int j=0;j<lcs[0].length;j++)
		  {
			  if(i==0 || j==0) {
				  lcs[i][j]=0;
			  }
			  
			  else if(s.charAt(i)==p.charAt(j))
				  lcs[i][j]=lcs[i-1][j-1]+1;
			  else lcs[i][j]=Math.max(lcs[i][j-1], lcs[i-1][j]);
		  }
			  
		  
	  }
	  
	  return lcs[s.length()-1][p.length()-1];
	  
  }
  
  public static void main(String args[]) {
	  System.out.println(lcsRecursive("mahbuburrahman","tazimanur"));
	  
	  System.out.println(lcsDP("mahbuburrahman", "tazimanur"));
  }
}
