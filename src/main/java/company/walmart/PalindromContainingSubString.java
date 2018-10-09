package company.walmart;
/**
 * 
 * 
 * @author mahbub
 *
 *Question 3 : given string s1 and s2, we have to transform s1 to palindrome such that s1 contain s2 as substring
in minimum opeartion, where operation is we can convert any character to an other character . print -1 if not possible .
* Ex. : s1 = arbcd, s2 = ar
* output : 2
* s1 = aaaaa, s2 = bbb
* output : 3
*
*/
public class PalindromContainingSubString {

	public static int minMumOp(String s1, String s2) {
		if(s1.length()< s2.length()) return -1;
		
		if(s1==s2 && isPalindrom(s1) )
			return s1.length();
		
		int index=s1.indexOf(s2);
		
		if(index==-1) return index;
		
		int i=0,j=s1.length()-1;
		int count=0;
		char[] sarray=s1.toCharArray();
		
		while(i<j) {
		  if(i>=index && i<=index+s2.length() && j>index+s2.length() && sarray[i]!=sarray[j]) {
			  sarray[j]=sarray[i];
			  count++;
		  }else if(j>=index && j<=index+s2.length() && i < index && sarray[i]!=sarray[j]) {
			  sarray[i]=sarray[j];
			  count++;
		  }else if(sarray[i]!=sarray[j] && i < index && j >index+s2.length()){
			  sarray[i]=sarray[j];
			  count++;
		  }
		  i++;
		  j--;
		}
		//TODO
		
		return isPalindrom(new String(sarray)) ? count : -1;
	}
	
	public static boolean isPalindrom(String s) {
		int i=0,j=s.length()-1;
		
		while(i<j) {
			if(s.charAt(i)!=s.charAt(j))
			{
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	public static void main(String args[]) {
		String s1="carbrfd";
		String s2="arb";
		System.out.println(minMumOp(s1,s2));
	}
}
