package leetcode;

import java.util.Stack;

public class Decoder394 {
	
	public String decodeString(String s) {
        if(s.length()<1)
		  return s;
	  StringBuilder sb=new StringBuilder();
	  
	  int start=s.charAt(0);
	  
	  Stack<Character> stack=new Stack<>();
	  
	  int count=0;
	  
	  while(count<s.length()) {
		  
		  if(s.charAt(count)==']') {
			  
			   StringBuilder temp=new StringBuilder();
			   
			   while(stack.peek()!='[') {
				temp.insert(0, stack.pop());
			   }
			   
			   stack.pop();//remove left brace
			   
			   StringBuilder times=new StringBuilder();
					   
			   while(!stack.isEmpty() && isdigit(stack.peek())) 
			   {
				   times.append(stack.pop());
			   }
			   
			   int repeat=Integer.parseInt(times.reverse().toString());
			   //temp.append(sb);
			   //sb = new StringBuilder();
			   StringBuilder sbTimes = new StringBuilder();
			   
			   for(int i=0;i<repeat;i++) {
				   sbTimes.append(temp);
			   }
			   //sbTimes.reverse();
			   for(int i=0; i < sbTimes.length(); i++)
			   {
				   stack.push(sbTimes.charAt(i));
			   }
			   count++;
			   
		  }
		  else {
			  stack.push(s.charAt(count++));
		  }
	  }
	  //decodeHelper(s,1,sb,start);
	  int whereToAdd=sb.length();
	  while(!stack.isEmpty()) {
		  sb.insert(whereToAdd,stack.pop());
	  }
	  return sb.toString();
    }
    
    public  boolean isdigit(char c) {
	  if(c-'0' >=0 && c-'0' <=9)
		  return true;
	  return false;
  }
    
    public static void main(String args[]) {
    	Decoder394 decoder394 = new Decoder394();
    	System.out.println(decoder394.decodeString("3[a]2[bc]"));
    	System.out.println(decoder394.decodeString("3[a2[c]]"));
    	System.out.println(decoder394.decodeString("2[abc]3[cd]ef"));
    	System.out.println(decoder394.decodeString("100[leetcode]"));
    }

}
