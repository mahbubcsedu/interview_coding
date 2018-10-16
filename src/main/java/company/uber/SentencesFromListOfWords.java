package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentencesFromListOfWords {

	public static void main(String[] args) {
		
		
		
		List<String> l1 = Arrays.asList(new String[]{"you","are"});
		List<String> l2 = Arrays.asList(new String[]{"1","2","3","5"});
		List<String> l3 = Arrays.asList(new String[]{"@","#","$"});
		List<List<String>> l4 = new ArrayList<List<String>>(3);
		
		
		
		l4.add(l1);l4.add(l2);l4.add(l3);
		List<String> output=new ArrayList<>();
		print(l4,output,0,"");
		System.out.println(Arrays.deepToString(output.toArray()));
	}

	
	public static void print(List<List<String>> list, List<String> output, int listIndex,String temp) {
		if(listIndex == list.size()) {
			System.out.println(temp);
			output.add(new String(temp));
			//temp="";
			return;
		}
		//start from the first list, do for all list and take words one by one
		List<String> curr = list.get(listIndex);
		for(String str: curr) {
			print(list, output, listIndex+1,temp+" "+str);
		}
	}
}
