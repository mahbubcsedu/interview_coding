package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPickWithWeight {

	//we will keep here the w1, w1+w2, w1+w2+w3, ....., so there will be a range based on the probability
	//if we are given probability, we should use Double
	List<Integer> probablity=new ArrayList<>();
	Random r=new Random();
	int cumalativeProbability=0;
	
	public RandomPickWithWeight(int[] w) {
		
		for(int x:w) {
			cumalativeProbability+=x;
			this.probablity.add(this.cumalativeProbability);
		}
	}
	
	public  int pickIndex() {
      int target=r.nextInt(this.cumalativeProbability);
      //now we search position of that number in the array to  find the index
     // int lo=0;
      //int hi=this.probablity.size()-1;
      Collections.binarySearch(this.probablity, target);
      
      if(target <0) {
    	  int intervalIndex=(Math.abs(target)-1)-1;
    	  return intervalIndex;
      }else {
    	  return target;
      }
	}
	
	public static void main(String args[]) {
		RandomPickWithWeight ob=new RandomPickWithWeight(new int[] {1,3});
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
		System.out.println(ob.pickIndex());
	}
}
