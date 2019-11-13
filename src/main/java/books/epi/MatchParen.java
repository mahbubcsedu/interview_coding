package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchParen {

	public static List<String> getMatchParen(int n){
		List<String> result=new ArrayList<>();
		StringBuilder temp=new StringBuilder();
		//so we cannot use stringbuilder here as it will keep same references and exiting a loop to return to caller stack point will not return its state on that 
		//stack but use global references
		String st="";
		matchParenHelper(n,n,st,result);
		return result;
	}



	public static void matchParenHelper(int leftRemaining, int rightRemaining, String tempSet, List<String> paren) {
		//when we completed adding n number of left and right with valid way, we can add to result and recurse for other options
		if(leftRemaining==0 && rightRemaining==0) {
			paren.add(tempSet);
			return;
		}

		if(leftRemaining>0) {
			//we can add easily the leftParen if we still have options and does not reach the max allowed of n
			matchParenHelper(leftRemaining-1,rightRemaining,tempSet+"(",paren);
		}
		//you cannot use else here as we have to fill right paren after left is complete or at some place check all combination
		//you cannot use rightremaining > 0 as this will add without validation of same right paren when there are at least that number of left is added
		//if left remain 1, n=3, means left paren 2 already added , i f 2 of the right paren added, left==right and no right can be added anymore
		if(leftRemaining < rightRemaining) {
			//we can add easily the leftParen if we still have options and does not reach the max allowed of n
			matchParenHelper(leftRemaining,rightRemaining-1,tempSet+")",paren);
		}

	}
	/**
	 * This is same backtracking but if someone interested in keeping the max allowed instead of remaining
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList();
		backtrack(ans, "", 0, 0, n);
		return ans;
	}

	public void backtrack(List<String> ans, String cur, int open, int close, int max){
		if (cur.length() == max * 2) {
			ans.add(cur);
			return;
		}

		if (open < max)
			backtrack(ans, cur+"(", open+1, close, max);
			if (close < open)
				backtrack(ans, cur+")", open, close+1, max);
	}


	/**
	 * Brute force method, generate all combination of the parenthesis and later check validity before add to result
	 * so the combination is 2^2n and check n times so, at least O(2^2n*n), we need to store those sequences as well and requires same memory
	 *

	 *Leetcode has another solution based on closure number, will try later to understand
    //@link https://leetcode.com/articles/generate-parentheses/
	 * 
	 * @param n
	 * @return
	 */
	public static List<String> getAllParenthesis(int n){
		List<String> r=new ArrayList<>();
		generateAll(new char[n*2],0,r);
		return r;
	}

	public static void generateAll(char[] temp, int pos, List<String> res) {
		if(pos==temp.length) {
			if(isValid(temp)) {
				res.add(new String(temp));
			}
		}

		else {
			temp[pos]='(';
			generateAll(temp,pos+1,res);
			temp[pos]=')';
			generateAll(temp,pos+1,res);
		}
	}

	public static boolean isValid(char[] paren) {
		int balance=0;//keep track of left and right
		for(int i=0;i<paren.length;i++) {
			if(paren[i]=='(')
				balance++;
			else balance--;

			if(balance <0) return false;
		}

		return balance==0;//very important to check here as if we have left=3 and right=0, its not valid
	}

	public static void test1() {

		List<String> res=getMatchParen(3);

		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void test2() {
		//we can remove duplicate and then go for that

		List<String> res=getAllParenthesis(2);

		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		test2();
	}
}
