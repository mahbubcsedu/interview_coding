package company.zillow;

import java.util.Comparator;
import java.util.Date;

public class Person{

	public enum SortOrder {ASCENDING, DESCENDING}
	
	
	private String ssn;
	private Date dataOfBirth;
	private String firstName;
	private String lastname;
	private Double heightln;
	private Double weightLb;
	private SortOrder sortOrder;
	
	


	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Person(String ssn, Date dataOfBirth, String firstName, String lastname, Double heightln, Double weightLb) {
		super();
		this.ssn = ssn;
		this.dataOfBirth = dataOfBirth;
		this.firstName = firstName;
		this.lastname = lastname;
		this.heightln = heightln;
		this.weightLb = weightLb;
	}

	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Date getDataOfBirth() {
		return dataOfBirth;
	}
	public void setDataOfBirth(Date dataOfBirth) {
		this.dataOfBirth = dataOfBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Double getHeightln() {
		return heightln;
	}
	public void setHeightln(Double heightln) {
		this.heightln = heightln;
	}
	public Double getWeightLb() {
		return weightLb;
	}
	public void setWeightLb(Double weightLb) {
		this.weightLb = weightLb;
	}

	public static Comparator<Person> ssnComparator = new Comparator<Person>() {         
		@Override         
		public int compare(Person p1, Person p2) {             
			int compare= (int) (p1.getSsn().compareTo(p2.getSsn()));  
			if (p1.getSortOrder() == SortOrder.ASCENDING) {
			      return compare;
			    } else {
			      return compare * (-1);
			    }

		}     
	};    

	public static Comparator<Person> fnameComparator = new Comparator<Person>() {         
		@Override         
		public int compare(Person p1, Person p2) {             
			int compare= (int) (p1.getFirstName().compareTo(p2.getFirstName()));  
			if (p1.getSortOrder() == SortOrder.ASCENDING) {
			      return compare;
			    } else {
			      return compare * (-1);
			    }

		}     
	};   
	public static Comparator<Person> lnameComparator = new Comparator<Person>() {         
		@Override         
		public int compare(Person p1, Person p2) {             
			int compare= (int) (p1.getLastname().compareTo(p2.getLastname()));  
			if (p1.getSortOrder() == SortOrder.ASCENDING) {
			      return compare;
			    } else {
			      return compare * (-1);
			    }

		}     
	};  
	
	public static Comparator<Person> dobComparator = new Comparator<Person>() {         
		@Override         
		public int compare(Person p1, Person p2) {             
			int compare= (int) (p1.getDataOfBirth().compareTo(p2.getDataOfBirth())); 
			if (p1.getSortOrder() == SortOrder.ASCENDING) {
			      return compare;
			    } else {
			      return compare * (-1);
			    }

		}     
	};  
	
	public static Comparator<Person> heightComparator = new Comparator<Person>() {         
		@Override         
		public int compare(Person p1, Person p2) {             
			int compare = (int) (p1.getHeightln().compareTo(p2.getHeightln())); 
			if (p1.getSortOrder() == SortOrder.ASCENDING) {
			      return compare;
			    } else {
			      return compare * (-1);
			    }
		}     
	}; 
	
	public static Comparator<Person> weightComparator = new Comparator<Person>() {
		
		@Override
		public int compare(Person person1, Person person2) {
		    Double w1 = person1.getWeightLb();
		    Double w2 = person2.getWeightLb();
		    int compare = (int) Math.signum(w1.compareTo(w2));

		    if (person1.getSortOrder() == SortOrder.ASCENDING) {
		      return compare;
		    } else {
		      return compare * (-1);
		    }
		    
//		@Override         
//		public int compare(Person p1, Person p2) {             
//			return (int) (p1.getWeightLb().compareTo(p2.getWeightLb()));        
		}     
	}; 

}

