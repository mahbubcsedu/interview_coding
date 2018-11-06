package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreyCode {
 //generate algorithm or brute force
	
	public static List<Integer> getGrayCode(int n){
		/**
		 * if n=4, 
		 * N=1<<4=10000=16
		 */
		List<Integer> res=new ArrayList<>();
		int N=1 << n;
		for(int i=0;i<N;i++) 
		{
			//don't think wrong way, like i >> 1 is the only one step right shift of i
			// 10 >> 1 = 1, 100 >> 1=10 and so on
			//int y=i>>1;
		    //System.out.println(Integer.toBinaryString(y));
			int x=i^(i>>1);
			/**
			 * 0 >> 1 = 0 ^ 0=0
			 * 1 >> 1 = 0 ^ 1=1
			 * 2 >> 1 = 1 ^ 10=11
			 * 3 >> 1 = 01 ^ 11 = 10
			 * 4 >> 1 = 10 ^ 100 = 110
			 */
			res.add(x);
			//System.out.println(Integer.toBinaryString(x));
		}
		
		return res;
	}
	/**
	 * Recursive formula 
	 * @param n
	 * @return
	 */
	public static List<Integer> getGrayCodeBF(int numOfBits){
		/**
		 * if n=4, 
		 * N=1<<4=10000=16
		 */
		
		List<Integer> res=new ArrayList<>(Arrays.asList(0));
		Set<Integer> history=new HashSet<>();
		//add 0 as number of bits cannot be lower than 0;
		history.add(0);
		grayRecursiveHelper(numOfBits,history,res);
		return res;
	}
	
	
	public static boolean grayRecursiveHelper(int numOfBits,Set<Integer> history,List<Integer> res) {
		
		if(res.size()==(1<<numOfBits)) {
			//we will check that the last and first number has one bit difference
			return differenceByOneBit(res.get(0),res.get(res.size()-1));
		}
		
		for(int i=0; i< numOfBits; i++) 
		{
			int prevCode=res.get(res.size()-1);//check last added entry
			int candidateNextCode= prevCode ^ (1 << i);
			
			if(!history.contains(candidateNextCode)) {
				history.add(candidateNextCode);
				res.add(candidateNextCode);
				
				if(grayRecursiveHelper(numOfBits,history,res)) {
					return true;
				}
				
				//if return false that means starting from this recursion loop does not produce results
				history.remove(candidateNextCode);
				res.remove(res.size()-1);
			}
		}
		return false;
	}
	
	public static boolean differenceByOneBit(int x, int y) {
		int bitDiff=x^y;
		return bitDiff !=0 && (bitDiff & (bitDiff-1))==0;
	}
	
	
	
	
	/**
	 * Grey's algorithm
	 * 1. First start using (0) (1) and 
	 * 2. copy the same elements and add but in reverse order
	 * 3. last half which starts at i until 2*i will be added 1 in fron by i | val
	 * 4. 0 1 1 0 so, last two 1 and 0, i=2 means 10 and 1 | 10 and 0 | 10 results 11 10
	 * 5. loop incremented by i=i<<1 so, from 10 to 100
	 */
	
	public static List<Integer> getGrayAlgo(int nbits){
		List<Integer> res=new ArrayList<>();
		res.add(0);
		res.add(1);
		
		//count all the value for 3 2^3=8
		for(int i=2;i<(1<<nbits);i=i<<1) {
			
			for(int j=i-1;j>=0;j--) {
				res.add(res.get(j));
			}
			
			//for(int j=0;j<i;j++) {
			//	res.set(j,"0"+res.get(j));
			//}
			
			for(int j=i;j<2*i;j++) {
				res.set(j, i|res.get(j));
			}
			
		}
		return res;
	}
	
	/*
	 * The same algorithm we can write recursively
	 */
	
	public static List<Integer> getGrayAlgoRec(int nbits){
		
		
		if(nbits==0) {
			return Arrays.asList(0);
		}
		else if(nbits==1) {
			return Arrays.asList(0,1);
		}
		
		//keep copy of the current number which is implicitly adding 0 in fron of each number
		//also this is bottom up approach and it will call until nbits become 1 and then will have the list (0,1) and then revert
		//back to stack call
		List<Integer> grayMinus1Bits=getGrayAlgoRec(nbits-1);
		//now we need to add 1 bit in front of each item but in reverse order. So, we can first create a reflection of the existing array or list
		//and at the same time add 1 infront of them using OR operator
		List<Integer> reflection= new ArrayList<>();
		
		//now create the mast to add 1 bit using OR
		int leading1Bits=1<<(nbits-1);
		
		for(int i=grayMinus1Bits.size()-1;i>=0;i--) {
			reflection.add(leading1Bits | grayMinus1Bits.get(i));
			
		}
		
		//now we have both list, and requires to concate 
		//List<Integer> res=new ArrayList<>();
		List<Integer> res=new ArrayList<>();
		
		res.addAll(grayMinus1Bits);
		res.addAll(reflection);
		return res;
	}
	public static void test1() {
		List<Integer> res=getGrayCodeBF(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	
	public static void test2() {
		List<Integer> res=getGrayCode(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void test3() {
		List<Integer> res=getGrayAlgo(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	
	
	public static void test4() {
		List<Integer> res=getGrayAlgoRec(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}
	public static void main(String args[]) {
		//test1();
		test4();
	}
}
