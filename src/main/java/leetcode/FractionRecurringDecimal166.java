package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FractionRecurringDecimal166 {
	
/**
 * This is still not possible as logic is wrong 71428571	
 * @param numerator
 * @param denominator
 * @return
 */
public String fractionToDecimal(int numerator, long denominator) 
{
	
	long quetient=numerator/denominator;
	long r=numerator%denominator;
	StringBuilder sb=new StringBuilder();
	//sb.append(quetient);
	
	Map<String, Integer> memo= new HashMap<>();
	
	//StringBuilder sb=new StringBuilder();
	int startRec=0;
	int countLoop=0;
	
	int sign=1;
	if(denominator<0)
		sign*=-1;
	if(numerator<0)
		sign*=-1;
	
	numerator=Math.abs(numerator);
	denominator=Math.abs(denominator);
	
	while(true) {
		
		
		if(countLoop>19)
			break;
		
		int howmanytimes=0;
		while(r<denominator)
		{
			
			r=r*10;
			if(howmanytimes>0) {
				sb.append('0');
			}
			howmanytimes++;
		}
		long q=r/denominator;
		r=r%denominator;
		
		
		//sb.append(r);
		
		countLoop++;
		sb.append(q);
		
		if(r==0)
			break;
		
		
		for(int i=0;i<sb.length();i++) {
			    String suffix=sb.subSequence(i, sb.length()).toString();
			    
				if(memo.containsKey(suffix)) {
					startRec=memo.get(suffix);
					countLoop=Integer.MAX_VALUE;
					break;
				}else {
					memo.put(suffix,i);
				}
			}
		
		
	}
	
	if(r!=0) {
	sb.deleteCharAt(sb.length()-1);
	sb.insert(startRec, '(');
	sb.append(')');
	//break;
	}
	
	if(sb.length()>0)
		sb.insert(0, '.');
	sb.insert(0, quetient);
	
	if(sign<0)
		sb.insert(0, '-');
	
	return sb.toString();
        
    }
/**
 * just to test cases and follow lead, but very hecktik to process
 * @return
 */
public  String fractionToDecimal2(int numerator, int denominator) {
    if (numerator == 0) {
        return "0";
    }
    StringBuilder fraction = new StringBuilder();
    // If either one is negative (not both)
    if (numerator < 0 ^ denominator < 0) {
        fraction.append("-");
    }
    // Convert to Long or else abs(-2147483648) overflows
    long dividend = Math.abs(Long.valueOf(numerator));
    long divisor = Math.abs(Long.valueOf(denominator));
    fraction.append(String.valueOf(dividend / divisor));
    long remainder = dividend % divisor;
    if (remainder == 0) {
        return fraction.toString();
    }
    fraction.append(".");
    Map<Long, Integer> map = new HashMap<>();
    while (remainder != 0) {
        if (map.containsKey(remainder)) {
            fraction.insert(map.get(remainder), "(");
            fraction.append(")");
            break;
        }
        map.put(remainder, fraction.length());
        remainder *= 10;
        fraction.append(String.valueOf(remainder / divisor));
        remainder %= divisor;
    }
    return fraction.toString();
}
void test1() {
	FractionRecurringDecimal166 obj = new FractionRecurringDecimal166();
   // int n=19, d=7;
    
    int n=7, d=12;
	String res=obj.fractionToDecimal2( n,  d);

	System.out.println(res);
}

void test2() {
	
	FractionRecurringDecimal166 obj = new FractionRecurringDecimal166();
    int n=3, d=-22;
	String res=obj.fractionToDecimal(n,d);

	System.out.println(res);
}

void test3() {
	FractionRecurringDecimal166 obj = new FractionRecurringDecimal166();
    int d=19, n=1;
	String res=obj.fractionToDecimal2( n,  d);

	System.out.println(res);
}

public static void main(String args[]) {
	FractionRecurringDecimal166 obj = new FractionRecurringDecimal166();
	//obj.test1();
	obj.test1();
}
}
