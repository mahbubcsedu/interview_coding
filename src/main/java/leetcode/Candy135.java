package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Candy135 {

	/**
	 * We also observe here that, lower ranking child does not need to give more than one ticket. 
	 * So, if we process using lower ranking to higher ranking, the current processing child is at least as productive as previous processed child
	 * so, we don't need to consider them. But, if the current one has greater ratings than previous processed item, we need to give the child
	 * at least one more candy.
	 * 
	 * So, we need to sort the set based on ratings but need to keep the index as well. We can use min heap to get the current lowest ranking
	 * child or extract in nondecreasing order
	 * @param nums
	 * @return
	 */
	
	static class Ranking{
		public Integer ranking;
		public Integer index;
		
		public Ranking(int r, int i){
			this.ranking=r;
			this.index=i;
		}
	}
	final static int DEFAULT=16;
	// O(nlogn)
	static int getNumOfCandyHeap(int[] nums) {
		//Comparator<Ranking> rankComp=(r1,r2)-> r1.ranking==r1.ranking ? r1.index.compareTo(r2.index): r1.ranking.compareTo(r2.ranking);
		Comparator<Ranking> rankComp=(r1,r2)-> r1.ranking.compareTo(r2.ranking);
		PriorityQueue<Ranking> minHeap=new PriorityQueue<>(16, rankComp);
		
		
		/*PriorityQueue<Ranking> minHeap=new PriorityQueue<>(DEFAULT, new Comparator<Ranking>(){
			@Override
			public int compare(Ranking r1, Ranking r2) {
				int result=Integer.compare(r1.ranking, r2.ranking);
				
				if(result==0) {
					result=Integer.compare(r1.index, r2.index);
				}
				
				return result;
				
			}
		});
		*/
		for(int i=0;i<nums.length;i++) {
			minHeap.add(new Ranking(nums[i],i));
			
		}
		
		//initially assign one ticket to everyone
		
		List<Integer> candies = new ArrayList<>(Collections.nCopies(nums.length, 1));
		//fills ticket from left to right based on ranking
		while(!minHeap.isEmpty()) {
			Ranking p=minHeap.peek();
			int nextChild=minHeap.peek().index;
			
			//handle the left neighbours
			
			if(nextChild > 0) {
				if(nums[nextChild] > nums[nextChild-1]) {
					candies.set(nextChild,candies.get(nextChild-1)+1);
				}
			}
			
			//Handles the right neighbours
			
			if(nextChild+1 < nums.length) {
				if(nums[nextChild] > nums[nextChild+1]) {
					//very carefull one coding, because after max plus sign is inside max for second parameter
					candies.set(nextChild, Math.max(candies.get(nextChild), candies.get(nextChild+1)+1));
				}
			}
		  minHeap.remove(p);
		}
		
		int sum=0;
		for(int i=0;i<candies.size();i++) {
			sum+=candies.get(i);
		}
		return sum;
	}
	/*
	 * we can solve the problem as it is stated, 
	 * for all the children, check left and right and and their candy and ratings, and satisfy locally for the current iteration
	 * if left ranking is less or right child ranking is less, but candy are same or less, we will update the current child candy
	 * 
	 * We at most required the number of max ratings to  find the minimu requirement
	 * 
	 */
	static int getNumOfCandyBF(int[] ratings) {
		int[] candies =new int[ratings.length];
		Arrays.fill(candies, 1);
		
		boolean changeRequired=true;
		
		int sum=0;
		
		while(changeRequired) {
			
			changeRequired=false;
			
			for(int i=0;i<ratings.length;i++) 
			{   
				//we will fix current children fare shapre comparing right child, if current is the most right child,  we will skip
				if(i!=ratings.length-1 && ratings[i] > ratings[i+1] && candies[i] <=candies[i+1]) {
					candies[i]=candies[i+1]+1;
					changeRequired=true;
				}
				if(i>0 && ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]) {
					candies[i]=candies[i-1]+1;
					changeRequired=true;
				}
			}
		}
		
		for(int i=0;i<candies.length;i++) {
			sum+=candies[i];
		}
		
		return sum;
	}
	
	/**
	 * We can improve this using two pass from left two right using same mechanism, 
	 * from left to right, if increasing, give one candy from right to left, if increasing, and already not satisfied, then increase by one
	 * which can be done using Max(current, next+1)
	 * @param ratings
	 * @return
	 */
	static int getNumOfCandyOpt(int[] ratings) {
		int[] candies =new int[ratings.length];
		Arrays.fill(candies, 1);
		
		//first pass left to right
		for(int i=0;i<ratings.length;i++) 
			{   
				//we will fix current children fare shapre comparing right child, if current is the most right child,  we will skip
				if(i!=0 && ratings[i] > ratings[i-1] && candies[i] <=candies[i-1]) {
					candies[i]=candies[i-1]+1;
					//changeRequired=true;
			    }
			}
	   for(int i=ratings.length-2;i>=0;i--) {
		if( ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) 
		{
					candies[i]=Math.max(candies[i+1]+1, candies[i]);
					
				}
		}
		
		int sum=0;
		for(int i=0;i<candies.length;i++) {
			sum+=candies[i];
		}
		
		return sum;
	}
	
	static void test1() {
		int[] nums= {1,2,3,1,5,4,1,2};
		System.out.println(getNumOfCandyHeap(nums));
	}
	
	static void test2() {
		int[] nums= {1,2,3,1,5,4,1,2};
		System.out.println(getNumOfCandyBF(nums));
	}
	
	static void test3() {
		//int[] nums= {1,2,3,1,5,4,1,2};
		//int[] nums= {1,0,2};
		int[] nums= {1,3,2,2,1};
		System.out.println(getNumOfCandyOpt(nums));
	}
	public static void main(String args[]) {
		test3();
	}
}
