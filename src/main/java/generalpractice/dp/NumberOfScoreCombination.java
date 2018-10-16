package generalpractice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * in american football, a score N can be made using 2, 3, and 7 points, we have to calculate the number of ways to score
 * @author mahbub
 *
 */
public class NumberOfScoreCombination {

	/**
	 * Brute force algorithm is to follow 3 different way for each score,
	 * lets say, N is the final score, if the score is 7 and include that, then way=1+N-7, but if it is 3, way=1+N-3 and same is for way=1+N-2
	 * so, the total way will be total_way=
	 */
	
	public static int getWayScore(List<Integer> play, int score) {
		
		List<Integer> temp=new ArrayList<>();
		List<List<Integer>> ways=new ArrayList<>();
		
		//int count=0;
		//for(int i=0;i<play.size();i++) {
		Map<String, Integer> res=new HashMap<>();
		int count=getWayScoreHelper(play,score,temp,ways,res);
		//}
		int uniqueCount=0;
		for(Map.Entry<String, Integer> e: res.entrySet()) {
			uniqueCount++;
		}
		return uniqueCount;
	}
	
   public static int getWayScoreHelper(List<Integer> play, int score, List<Integer> tempComb, List<List<Integer>> w, Map<String, Integer> h) {
	   int count=0; 
	   if(score==0)
	    {
	    	w.add(new ArrayList(tempComb));
	    	List<Integer> partialRes=new ArrayList<>(tempComb);
	    	Collections.sort(partialRes);
	    	String pstr=partialRes.toString();
	    	h.put(pstr, 1);
	    	
	    	return 1;
	    }
	    else if(score < 0)
	    {
	    	tempComb=new ArrayList<>(); 
	    	return 0;
	    }
	    
	    for(int i=0;i<play.size();i++) {
	    	String item=String.valueOf(play.get(i));
	    	//tempComb.add(item);
	    	tempComb.add(play.get(i));
	    	count+=getWayScoreHelper(play, score-play.get(i), tempComb,w,h);
	    	tempComb.remove(tempComb.size()-1);
	    	//tempComb.remove(item);
	    }
	    
		return count;
	}
   
   
   /**
    * The above problem uses backtrack thus the cost is high O(2^n)
    * We can solve these problems in lower cost using memory or dynamic problem. But for that we have to define the optimal substructure and optimal substructure
    * From the recursion, we can see that we have lot of repetition, if we already know the combination of N=6, calculating from N=12 we come to N=6 several times, where we can 
    * use the dp algorithm and avoid calculations
    * @param args
    * 
    * for clarity see EPI book page 313 problem 17.1
    * 
    * 1. create a dp table from 0 to score columns and number of play rows.
    * 2. fillup the first column to 1 as reaching score 0 is always only one way
    * 3. for each play fillup the table using the rules as a) if we include the current play, then first check that it does not cross the current score, then find current_score-currentplay position and find the number of ways
    * b) if we don't include this play, then just put the previous score position results. 
    * 4.always check that score does not cross both boundary 0 to score
    */
   
   public static int countNumberOfWays(List<Integer> play, int score) {
	   int[][] dp=new int[play.size()][score+1];
	   
	   for(int i=0;i< play.size();i++) {
		   dp[i][0]=1;
		   for(int j=1;j<= score;j++) {
			   int withoutCurrentPlay=i-1>=0 ? dp[i-1][j]:0;
			   int withCurrentPlay=j>=play.get(i) ? dp[i][j-play.get(i)] : 0;
			   dp[i][j]=withoutCurrentPlay+withCurrentPlay;
		   }
	   }
	   return dp[play.size()-1][score];
   }
   
   /**
    * The problem can be solve recursively as
    * 1. we can include the current play, and create another node without current play at all
    * 2. If we want to include, main score will be less than the play score value
    * 3. we will do that one by one for example if the play (2,3,7), first we will try a path excluding 7 and another using 7, so, next path will have to branch
    * a) one with only (2,3) and score will be same, b)another one will include 7, so, all play will be available (2,3,7) and we will substruct score-7
    * 4. if we reach 0, we will have a way, if score fall bellow 0 or there is no individual play available, there is no way on this path.
    * @param play
    * @param score
    * @return
    * {@link https://www.geeksforgeeks.org/coin-change-dp-7/}
    */
   public static int countWaysRec2(List<Integer> play, int score) {
	   
	   int count=countWaysRec2Helper(play,play.size(),score);
	   return count;
   }
   
   public static int countWaysRec2Helper(List<Integer> play, int numberOfPlay,int score) {
	   if(score==0)
		   return 1;
	   if(score<0)
		   return 0;
	   if(numberOfPlay<=0 && score>0)
		   return 0;
	   return countWaysRec2Helper(play,numberOfPlay,score-play.get(numberOfPlay-1))+countWaysRec2Helper(play,numberOfPlay-1,score);
   }
   
   /**
    * Now the individual play uses 2-D tables, we can try using 1-D to minimize the memory usage
    * we are doing different steps for different play, but if we combine the count, we can easily fill up just a 1-D table
    * @param args
    * The space is now optimized to O(n) only instead of O(sn)
    */
   public static int countNumberOfWays1D(List<Integer> play, int score) {
	   int[] dp=new int[score+1];
	   
	   //for(int i=0;i< play.size();i++) {
		   dp[0]=1;
		   for(int i=0;i<play.size();i++) 
		   {
		   for(int j=play.get(i);j<= score;j++) {
			   //int withoutCurrentPlay=0;//dp[j-1];
			   //int withCurrentPlay=0;
			   
			   dp[j]+=dp[j-play.get(i)];
			   //withCurrentPlay+=j>=play.get(i) ? dp[j-play.get(i)] : 0;
			   
			   }
			   
		   }
	   //}
	   return dp[score];
   }
   
   public static void main(String args[]) {
	   //int count=getWayScore(Arrays.asList(new Integer[] {2,3,7}),12);
	   
	   //int count=countNumberOfWays(Arrays.asList(new Integer[] {2,3,7}),12);
	   int countRec=countWaysRec2(Arrays.asList(new Integer[] {2,3,7}),12);
	   System.out.println(countRec);
	   int count1D=countNumberOfWays1D(Arrays.asList(new Integer[] {2,3,7}),12);
	   System.out.println(count1D);
	   
   }
}
