package company.zillow;



import java.nio.charset.Charset;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;


public class RandomObjectCreate {

	public static Date getRandomeDate() 
	{
		GregorianCalendar gc = new GregorianCalendar();
		int year = randBetween(1900, 2010);

		gc.set(GregorianCalendar.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
		gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
		return gc.getTime();
		//return gc.get(GregorianCalendar.YEAR) + "-" + (gc.get(GregorianCalendar.MONTH) + 1) + "-" + gc.get(GregorianCalendar.DAY_OF_MONTH);

	}

	public static int randBetween(int start, int end) {
		return start + (int)Math.round(Math.random() * (end - start));
	}


	public String getRandomeString() {
		String uuid = UUID.randomUUID().toString();
        return uuid;
        
		//byte[] array = new byte[20]; // length is bounded by 7
		//new Random().nextBytes(array);
		//String generatedString = new String(array, Charset.forName("UTF-8"));
		//return generatedString;
	}

	public double getRandomeNumber() {

		Random n=new Random();
		return 20+200*n.nextDouble();

	}
	
	public double getRandomeInteger() {

		Random n=new Random();
		return 20000*n.nextInt();

	}
}