package company.zillow;

import java.util.ArrayList;
import java.util.List;

public class SortPersonPerQuery {


	public static void main(String args[] ) {
		RandomObjectCreate r=new RandomObjectCreate();


		try {
			List<Person> l=new ArrayList<>();
			Person obj;
			for(int i=0;i<100;i++) {
				obj=r.createAndFill(Person.class);
				l.add(obj);
			}

			for(int i=0; i<l.size();i++) {
				System.out.println(l.get(i).getSsn());
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
