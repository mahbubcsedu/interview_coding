package generalpractice.dp;
/**
 * The problem contain two string source and target. The string source need to be converted to target
 * It can be done by modifying character if they are different, we can add, delete and substitute to reach to target
 * 
 * @author mahbub
 *
 */
public class LavenshteinDistance {
/**
 * The brute force algorithm would be to find source word to all the list of words which has 
 * different 1,2,3 ....and when we reach the target, we can see the cost of it. it requires to 
 * enumerate all words of same length from the dictionary and cost will be high. I am not implementing itright now
 * 
 */
	
 //We can solve this using well know algorithm using Dynamic programming
 /**
  * 1. If the last character of both source and destination are same, we don't need to do anything
  * 2. If they are differ, we have to wait processed all the previous conversion and as a last step do one
  * of the operations
  */
	
  public static int costToConvertRec(String s, String t) {
	  //we can see if last character is similar, if so, go for next
	  //if(s.charAt(index))
	  return costToConvertRecHelper(s,s.length()-1,t,t.length()-1);
	  //return 0;
  }
  
  public static int costToConvertRecHelper(String s, int sIdx, String t, int tIdx) {
	  
	  int cost=0;
	  if(sIdx<0) {
		  //all need to add
		  return tIdx+1;
	  }else if(tIdx<0) {
		  //target is emtpy, means, we have to delete all character from s
		  return sIdx+1;
	  }
	  
	  if(s.charAt(sIdx)==t.charAt(tIdx)) {
		  cost=costToConvertRecHelper(s,sIdx-1,t,tIdx-1);// you have to calculate the count here as well
	  }else {
		  int subCost=costToConvertRecHelper(s,sIdx-1,t,tIdx-1);
		  int delCost=costToConvertRecHelper(s,sIdx-1,t,tIdx);
		  int addCost =costToConvertRecHelper(s,sIdx,t,tIdx-1);
		  cost=1+Math.min(subCost,Math.min(delCost, addCost));
	  }
	   
	  return cost;
  }
  
  
  public static void main(String args[] ) {
	  //System.out.println(costToConvertRec("Carthorse","Orchestra"));
	  System.out.println(costToConvertRec("abc","cbd"));
  }
}
