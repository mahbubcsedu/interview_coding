package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordsParts {

	public static List<String> getWordParts(List<String> words, List<String> parts){
		
		Collections.sort(parts, new Comparator<String>() {
			@Override
			public
			int compare(String s1, String s2) {
				return s2.length()-s1.length();
			}
		});
		
		List<String> result=new ArrayList<>();
		
		for(String word:words) {
			for(String part:parts) 
			{
			   int ind=word.indexOf(part);
			   if(ind>=0) 
			   {
			      String modString=word.substring(0,ind)+"["+part+"]"+word.substring(ind+part.length());
				  result.add(modString);
				  break;
				 
			    }
			}
		}
		
		//TODO:
		
		
		return result;
	}
	//cost is O(n^3)
	public static void main(String args[]) {
		String[] words= {"Apple","Melon","Orange","Watermelon"};
		String[] parts= {"a","mel","lon","el","An"};
		List<String> res=getWordParts(Arrays.asList(words), Arrays.asList(parts));
		System.out.println(Arrays.deepToString(res.toArray()));
	}
}
