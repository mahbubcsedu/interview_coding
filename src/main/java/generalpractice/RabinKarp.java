package generalpractice;

public class RabinKarp {

	//in Rabin Karp, the Hash function need to be chosen carefully for collision avoidance
	
	//One hash coud be prime number or chose a base
	
	static final int BASE=101;
	
	public static int patterSearchFirst(char[] text, char[] pattern) {
		int t=text.length;
		int p=pattern.length;
		
		long pHash=createHash(pattern, p-1);
		long tHash=createHash(text,p-1);
		
		for(int i=1 ; i<=t-p+1;i++) {
			if(tHash==pHash && checkEqual(text,i-1,i+p-2,pattern)) {
				return i-1;
				//System.out.println(i-1);
			}
			if(i < t-p+1) {
				tHash=rollingHash(text,i-1,i+p-1,tHash,p);
			}
		}
		return -1;
	}
	
	public static void patterSearchAll(char[] text, char[] pattern) {
		int t=text.length;
		int p=pattern.length;
		
		long pHash=createHash(pattern, p-1);
		long tHash=createHash(text,p-1);
		
		for(int i=1 ; i<=t-p+1;i++) {
			if(tHash==pHash && checkEqual(text,i-1,i+p-2,pattern)) {
				//return i-1;
				System.out.println(i-1);
			}
			if(i < t-p+1) {
				tHash=rollingHash(text,i-1,i+p-1,tHash,p);
			}
		}
		//return -1;
	}
	
	public static boolean checkEqual(char[] t, int start, int end, char[] p) {
		
		if(p.length!=end-start+1) return false;
		
		for(int i=start,j=0;i<=end;i++,j++) {
			if(t[i]!=p[j])
				return false;
		}
		return true;
	}
	//This will create hash for only the first m character where m is the length of pattern
	public static long createHash(char[] str, int end) {
		long hash=0;
		//final int BASE=26;
		//So, BASE^|m-1| where power=power*BASE,
		//lets say, 26, so, for first one, 1+1*BASE+1*BASE^2 ......
		//int powerS=1; // if you see corement, the hash function is d^|m-1|
		
		for(int i=0; i<=end;i++) {
			hash+=str[i]*Math.pow(BASE, i);
		}
		return hash;
		
	}
	/**
	 * 
	 * @param str text string
	 * @param oldIndex first index of old string
	 * @param newIndex next index which will be added to rolling hash
	 * @param oldHash previous hash code
	 * @param pLen pattern length
	 * @return new hash code
	 * 
	 * just consider this, if the pattern len =3, 
	 * and patter is cde, where code for correspondng chara c=2, d=3, e=4
	 * so, the 
	 * then hash= 2*BASE^0+3*BASE^1+4*BASE^2
	 * 
	 * now if we are here with old has as above hash, and we will remove the first one and will add next char
	 * Lets say, next char is f=5, so, newHash= hash- {value of c which is 2} as BASE^0 is 1
	 * So, now the remaining oldHash = 3*BASE^1+4*BASE^2
	 * 
	 * we can divide them as newHash=newHash/BASE= 3*BASE^0+4*BASE^1
	 * 
	 * Now, we will add new char f, which will add 4*BASE^2 as len =3, 
	 */
	
	public static long rollingHash(char[] str, int oldIndex, int newIndex, long oldHash, int pLen) {
		long newHash=oldHash-str[oldIndex];
		newHash=newHash/BASE;
		newHash+=str[newIndex]*Math.pow(BASE,pLen-1);
		return newHash;
	}
	
	
	public static void smallTest() 
	{
		System.out.println(checkEqual(new char[] {'a','b','c','d'},0,2, new char[] {'a','b','c'}));
		
		System.out.println(patterSearchFirst(new char[] {'a','b','c','d','e','f','c','d'}, new char[] {'c','d'}));
		
		patterSearchAll(new char[] {'a','b','c','d','e','f','c','d'}, new char[] {'c','d'});
	}
	
	public static void main(String args[]) {
		smallTest();
	}
}
