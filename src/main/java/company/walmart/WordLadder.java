package company.walmart;
import java.util.*;

public class WordLadder {
   
   public static class StringWithDis{
   private String candidateString;
   public int distance;
   
   public StringWithDis(String s, int d) {
	   this.candidateString=s;
	   this.distance=d;
   } 
   
   
   }
   
   public static int transformation(Set<String> D, String s, String e) {
	   Queue<StringWithDis> q=new LinkedList<>();
	   D.remove(s);
	   q.add(new StringWithDis(s,0));
	   
	   StringWithDis f;
	   
	   while(!q.isEmpty()) {
		   f=q.poll();
		   if(f.candidateString.equals(e)) {
			   return f.distance;
		   }
		   
		   String str=f.candidateString;
		   for(int i=0;i<str.length();i++) {
			   String strStart=i==0 ? "":str.substring(0, i);
			   String strEnd=i==str.length()-1 ? "": str.substring(i+1);
			   
			   for(int j=0;j<26;j++) {
				   String modString= strStart + (char)('a'+j) + strEnd;
				   if(D.remove(modString)) {
					   q.add(new StringWithDis(modString,f.distance+1));
				   }
			   }
		   }
	   }
	   return -1;
   }
   
   public static void main(String args[]) {
	   Set<String> D=new HashSet<>();
	   D.add("bat");
	   D.add("cot");
	   D.add("dog");
	   D.add("dag");
	   D.add("dot");
	   D.add("cat");
	   //D.add("bat");
	   System.out.println(transformation(D,"cat","dog"));
   }
	
}
