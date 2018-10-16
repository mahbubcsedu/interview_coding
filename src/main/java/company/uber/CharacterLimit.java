package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterLimit {

	final int LIMIT=25;
	
	public static List<String> getCharacterLimit(String s){
	 int i=0,j=25;
	 List<String> res=new ArrayList<>();
	 s=s.trim();//remove any space starting or eding side
	 
	 while(j<s.length()) 
	 {
		// int backtrack=0;
		  
		 do{
			 j--;
			// backtrack++;
		 }while(s.charAt(j)!=' ');
		 
		 res.add(s.substring(i, j));
		 
		 while(s.charAt(j)==' ') {
			 j++;//remove space
		 }
		 i=j;
		 
		 
		 //j+=backtrack;
		 //backtrack=0;
		 //i=j;
		 j+=25;
	 }
	 res.add(s.substring(i));
	 return res;
	}
	
	public static void main(String args[]) {
		List<String> r= getCharacterLimit("Given a character limit      and a message, split the message up into annotated chunks without cutting words as, for example when sending the SMS Hi Sivasrinivas, your Uber is arriving now! with char limit 25, you should get Hi Sivasrinivas, your Uber is arriving, now!" );
		
		
		for(int i=1;i<r.size()+1;i++) {
			System.out.println(r.get(i-1)+"("+i+"/"+r.size()+")");
		}
		//System.out.println(Arrays.deepToString(r.toArray()));
	}
}
