package leetcode.bfs;
import java.util.*;

public class InvalidParenthesis301 {

	//backtracking to iterate all the options
	//static int minimumCount=Integer.MIN_VALUE;
	int min=Integer.MAX_VALUE;

	public List<String> removeInvalidParentheses(String s){

		//List<String> validParen=new ArrayList<>();
		Set<String> validParen=new HashSet<>();

		StringBuilder sb=new StringBuilder();

		parenBackTrack(s,0,0,0,sb,0,validParen);

		return new ArrayList<String>(validParen);
	}


	public  void parenBackTrack(String s,int index, int leftCount, int rightCount,StringBuilder sb,int removeCount,Set<String>res) {

		if(index==s.length()) {
			//all are processed, so at least we are somewhere to a solutions
			if(leftCount==rightCount) {

				//System.out.println(this.min);
				if(removeCount <= min) 
				{
					//check whether both are same or not
					if(removeCount < min) {
						//res=new HashSet<>();
						res.clear();
						min=removeCount;
						//System.out.println(min);
					}
					System.out.println(res.toString());
					res.add(sb.toString());
				}

			}
		}

		else {
			
			char c=s.charAt(index);
			int length=sb.length();
			//we will process all the options
			if(s.charAt(index)!='(' && s.charAt(index)!=')') 
			{
				sb.append(c);
				parenBackTrack(s,index+1,leftCount,rightCount,sb,removeCount,res);
				sb.deleteCharAt(length);
			}else {
				//if we delete the current character
				parenBackTrack(s,index+1,leftCount,rightCount,sb,removeCount+1,res);

				sb.append(c);

				if(s.charAt(index)=='(') {
					parenBackTrack(s,index+1,leftCount+1,rightCount,sb,removeCount,res);
				}else if(rightCount < leftCount) {
					parenBackTrack(s,index+1,leftCount,rightCount+1,sb,removeCount,res);
				}

				sb.deleteCharAt(length);
			}
		}
	}

	
	/**
	 * The backtrack algorithm that we already written, does not care how many invalid paren we have to delete and we have to test all the way to
	 * the end to know this. If we can scan the input once, we can easily get the imbalance parenthesis count and see whether left or right count
	 * has more and if so, how many. 
	 * 
	 * Next step would be to just find out whether we have deleted or avoided enough parenthesis to make balance. This will not give us asymtopic 
	 * speed up but pruning will give huge computation advantage
	 * @param args
	 */

	/**
	 * This is still no giving all output correctly
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParenthesesPruning(String s){
		
		Set<String> res=new HashSet<>();
		
		int left=0, right=0;
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='(') {
				left++;
			}else if(s.charAt(i)==')') {
				//if left is zero, then right one is invalid and increment by one otherwise, no change on right
				right=left==0 ? right+1:right;
				//if left is greater than zero, then right one is the match and will decrement left by one
				left=left>0 ? left-1: left;
			}
		}
		
		//now we know the difference of left and right invalid parentheses and backtracking will check this
		StringBuilder sb=new StringBuilder();
		backtrackPrune(s,0,0,0,sb,left,right,res);
		
		return new ArrayList<String>(res);
	}
	
	
	public void backtrackPrune(String s, int index, int leftCount, int rightCount, StringBuilder sb,int leftRemaining,int rightRemaining,Set<String>res) {
		
		if(index==s.length()) {
			//if all invalid has been removed, just add this arrangement to the result
			if(leftRemaining==0 && rightRemaining==0) {
				res.add(sb.toString());
			}
		}
		else {
			
			char c=s.charAt(index);
			int length=sb.length();
			//else we still have characters to process
			if((c=='(' && leftRemaining>0) || (c==')' && rightRemaining>0)) {
				//we still have left or right invalid parentheses, 
				if(c=='(')
					leftRemaining--;
				if(c==')')
					rightRemaining--;
				
						
				backtrackPrune(s,index+1,leftCount,rightCount,sb,leftRemaining,rightRemaining,res);
			}
			//next character to be considered
			sb.append(c);
            
			if(c !='(' && c !=')') {
				backtrackPrune(s,index+1,leftCount,rightCount,sb,leftRemaining,rightRemaining,res);
			}else if(c =='(') {
				//if the character is left paren
				backtrackPrune(s,index+1,leftCount+1,rightCount,sb,leftRemaining,rightRemaining,res);
			}else if(rightCount < leftCount) {
				//if the character is left paren
				backtrackPrune(s,index+1,leftCount,rightCount+1,sb,leftRemaining,rightRemaining,res);
			}
			//remove last added character
			sb.deleteCharAt(length);
			
		}
	}
	
	
	public static void main(String args[]) {
		InvalidParenthesis301 sp=new InvalidParenthesis301();
		String s="((a()()*)";
		//List<String> res=removeInvalidParentheses(s);
		//System.out.println(Arrays.deepToString(res.toArray()));

		s="()())()";
		//List<String> res=sp.removeInvalidParentheses(s);
		//System.out.println(Arrays.deepToString(res.toArray()));
		
		
		//res=sp.removeInvalidParenthesesPruning(s);
		//System.out.println(Arrays.deepToString(res.toArray()));
		
		s="()())()";
		List<String> res2=sp.removeInvalidParenthesesPruning(s);
		System.out.println(Arrays.deepToString(res2.toArray()));
	}
}
