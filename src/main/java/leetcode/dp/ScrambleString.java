package leetcode.dp;

/**
 * The question itself is very complicated. You have to understand the scramble string and which is not scramble string
 * 
 * @author mahbub
 *
 */
public class ScrambleString {
 /*
  * So, the first concept is, if the string s2 is the scramble of s1, it is confirm that if we divide the string at some
  * point k, then at least one part of the both string will be same. lets say, at k, (0,k) of s1 will be equal to (0,k) of s2 or (n-k,0) of s2
  * we will process all possible condition using recursive solutions
  */
	
	static boolean isScramble(String s1, String s2) {
		//System.out.println(s1+" "+s2);
		
		if(s1.length()!=s2.length())
			return false;
		if(s1.equals(s2))
			return true;
		
		for(int i=1;i<s1.length();i++)
		{
			//System.out.println(s1.substring(0, i)+" 1st"+s2.substring(0, i));
			//System.out.println(s1.substring(i)+" 2d"+s2.substring(i));
			//System.out.println(s1.substring(0, i)+" 1st"+s2.substring(s2.length()-i));
			//System.out.println(s1.substring(i)+" 1st"+s2.substring(0, i));
			
			if(isScramble(s1.substring(0, i),s2.substring(0, i)) && isScramble(s1.substring(i),s2.substring(i)))
				return true;
			if(isScramble(s1.substring(0, i),s2.substring(s2.length()-i)) && isScramble(s1.substring(i),s2.substring(0,s2.length()-i)))
				return true;
		}
		return false;
	}
	/**
	 * TODO: 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isScrambleDp(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		int len = s1.length();
		/**
		 * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
		 * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
		 * Let q be the length of a cut (hence, q < k), then we are in the following situation:
		 * 
		 * S1 [   x1    |         x2         ]
		 *    i         i + q                i + k - 1
		 * 
		 * here we have two possibilities:
		 *      
		 * S2 [   y1    |         y2         ]
		 *    j         j + q                j + k - 1
		 *    
		 * or 
		 * 
		 * S2 [       y1        |     y2     ]
		 *    j                 j + k - q    j + k - 1
		 * 
		 * which in terms of F means:
		 * 
		 * F(i, j, k) = for some 1 <= q < k we have:
		 *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
		 *  
		 * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal 
		 * */
		boolean [][][] F = new boolean[len][len][len + 1];
		for (int k = 1; k <= len; ++k)
			for (int i = 0; i + k <= len; ++i)
				for (int j = 0; j + k <= len; ++j)
					if (k == 1)
						F[i][j][k] = s1.charAt(i) == s2.charAt(j);
					else for (int q = 1; q < k && !F[i][j][k]; ++q) {
						F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
					}
		return F[0][0][len];
	}
	
	public static void main(String args[]) {
		String s1="abc";
		String s2="bca";
		System.out.println(isScramble(s1,s2));
		s1 = "abcde"; s2 = "caebd";
		System.out.println(isScramble(s1,s2));
		s1 = "abb"; s2 = "bba";
		System.out.println(isScramble(s1,s2));
	}
}
