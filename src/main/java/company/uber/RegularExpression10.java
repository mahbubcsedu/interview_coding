package company.uber;

public class RegularExpression10 
{
	public static boolean isMatch(String text, String pattern) 
	{
		//if pattern is emtpy and text is emtpy then true, but text not empty with empty patther does not match
        if (pattern.isEmpty()) return text.isEmpty();
        
        //We will check always first match
        boolean first_match = (!text.isEmpty() &&
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
	
	/**
	 * From the recursive solution , we can directily extend the dp table and capture the repeated computation 
	 * @param args
	 */
	
	public static boolean isMatchDp(String text, String pattern) {
		boolean[][] dp=new boolean[text.length()+1][pattern.length()+1];
		dp[0][0]=true; //empty pattern with empty string
		
		for(int i=1; i<pattern.length();i++) {
			if(pattern.charAt(i-1)=='*') {
				dp[0][i]=dp[0][i-2]; // if current character is * and is matched until skipping one character, it is also true
				
			}
		}
		
	  for(int i=1;i<text.length();i++) 
	  {
		  for(int j=1;j<pattern.length();j++)
		  {
			  //String is is 0 based index but dp has one extra
		  if(pattern.charAt(j-1)=='.' || pattern.charAt(j-1)==text.charAt(i-1)) {
			  dp[i][j]=dp[i-1][j-1];
		  }else if(pattern.charAt(j-1)=='*') 
		  { //if current character is kleen closure
			  dp[i][j]=dp[i][j-2]; //0 occur
			  if(pattern.charAt(j-2)=='.' || pattern.charAt(j-2)==text.charAt(i-1)) 
			  {
				  dp[i][j]=dp[i][j] | dp[i-1][j];
			  }
		  }else {
			  dp[i][j]=false;
		  }
	    }
	  }
	  return dp[text.length()][pattern.length()];
	}
	
	//direct usage of the recursion
	 public boolean isMatchDp2(String text, String pattern) {
	        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
	        dp[text.length()][pattern.length()] = true;

	        for (int i = text.length(); i >= 0; i--){
	            for (int j = pattern.length() - 1; j >= 0; j--){
	                boolean first_match = (i < text.length() &&
	                                       (pattern.charAt(j) == text.charAt(i) ||
	                                        pattern.charAt(j) == '.'));
	                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
	                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
	                } else {
	                    dp[i][j] = first_match && dp[i+1][j+1];
	                }
	            }
	        }
	        return dp[0][0];
	    }
	
	public static void main(String args[]) {
		String text="ab";
		String p="*ab";
		System.out.println(isMatch(text,p));
	}
}
