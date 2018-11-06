package leetcode.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString394 {
	
  public static String decodeString(String s) 
  {
	  if(s.length()<1)
		  return s;
	  StringBuilder sb=new StringBuilder();
	  
	  int start=s.charAt(0);
	  
	  Deque<Character> stack=new LinkedList<>();
	  
	  int count=0;
	  
	  while(count<s.length()) {
		  
		  if(s.charAt(count)==']') {
			  
			   StringBuilder temp=new StringBuilder();
			   StringBuilder untilNowString=new StringBuilder();
			   
			   untilNowString.append(sb);
			   while(stack.peek()!='[') {
				   untilNowString.insert(0, stack.pop());
			   }
			   
			   stack.pop();//remove left brace
			   
			   StringBuilder times=new StringBuilder();
					   
			   while(!stack.isEmpty() && isdigit(stack.peek())) 
			   {
				   times.append(stack.pop());
			   }
			   
			   int repeat=Integer.parseInt(times.toString());
			   
			   sb=new StringBuilder();
			   for(int i=0;i<repeat;i++) {
				   sb.insert(0,untilNowString);
			   }
			   
			   untilNowString=sb;
			   sb=new StringBuilder(untilNowString);
			   //sb.append()
			   
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
  
  
  
  public static boolean isdigit(char c) {
	  if((int)(c-'0') >=0 && (int)(c-'0') <=9)
		  return true;
	  return false;
  }
  
  
  public static boolean isAlphabet(char c) {
	  if(c>='a' && c <='z')
		  return true;
	  return false;
  }
  public static void main(String args[]) {
	  String s="3[a2[bc]]";
	  s="3[a]2[bc]";
	  //String sr=decodeString(s);
	  //System.out.println(sr);
	  
	  /*s="2[abc]3[cd]ef";
	  String sr2=decodeString(s);
	  System.out.println(sr2.equals("abcabccdcdcdef"));
	  
	  s="abcdeabced";
	  String sr3=decodeString(s);
	  System.out.println(sr3);
	  
	  
	  s="";
	  String sr4=decodeString(s);
	  System.out.println(sr4);
	  */
	  s="3[a2[c3[r]]]";
	  String sr5=decodeString(s);
	  System.out.println(sr5);
  }
}
