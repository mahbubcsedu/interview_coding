package books.epi;
import java.util.*;
public class PhoneMnemonics {
	
	private static final String[] mapping= {"0","1","ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};
	
	static List<String> getPhoneMnemonics(String phoneNumber){
		
		char[] partialMnemonics=new char[phoneNumber.length()];
		List<String> result=new ArrayList<>();
		phoneMnemonicsHelper(phoneNumber,0,partialMnemonics,result);
		return result;
		
	}
	
	private  static void phoneMnemonicsHelper(String pNumber, int digit, char[] pResult,List<String>res) {
		if(digit==pResult.length) {
			res.add(new String(pResult));
		}
		else {
			
			for(int i=0;i<mapping[pNumber.charAt(digit)-'0'].length();i++) {
				//find the ith character from the current digit
				char c=mapping[pNumber.charAt(digit)-'0'].charAt(i);
				pResult[digit]=c;
				phoneMnemonicsHelper(pNumber,digit+1,pResult,res);
			}
		}
		
		
	}
	public static void main(String args[]) {
		
		
		List<String> res=getPhoneMnemonics("7793");
		System.out.println(Arrays.deepToString(res.toArray()));
	}

}
