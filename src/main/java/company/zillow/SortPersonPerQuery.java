package company.zillow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import company.zillow.Person.SortOrder;

public class SortPersonPerQuery {

	public enum SortOrder {ASCENDING, DESCENDING}
	
	public static class FNameComparator implements Comparator<Person> 
	{         
		@Override         
		public int compare(Person p1, Person p2) {             
			int compare= (int) (p1.getFirstName().compareTo(p2.getFirstName()));  
			if (p1.getSortOrder() == Person.SortOrder.DESCENDING) {
			      return compare;
			    } else {
			      return compare * (-1);
			    }

		}     
	}

	public static void main(String args[] ) {
		RandomObjectCreate r=new RandomObjectCreate();

		
        List<Person> personList=new ArrayList<>();
        
		try {
			//List<Person> l=new ArrayList<>();
			//Person obj;
			Person p;
			for(int i=0;i<20;i++) 
			{
				r=new RandomObjectCreate();
				p=new Person(r.getRandomeString(),r.getRandomeDate(),r.getRandomeString(),r.getRandomeString(),r.getRandomeNumber(),r.getRandomeNumber());
				
				personList.add(p);
			}

			
			Collections.sort(personList, new FNameComparator());
			for(int i=0; i<personList.size();i++) {
				System.out.println(personList.get(i).getFirstName());
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
