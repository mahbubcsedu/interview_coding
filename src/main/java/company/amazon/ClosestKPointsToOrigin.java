package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestKPointsToOrigin {
 /**
  * 1. Of course we can get it by sorting based on distance, we can create object with x,y,d and 
  * sort those on distance and print to first k objects location
  * 2. We also can use selection sort using same procedure where we only can keep the top k, but 
  * the previous on will be able to perform O(nlogn) times while this one will take O(nk) times
  * 3. The best one is using Heap data structure with at most k extrac memory and will require
  *    O(nlogk)
  */
	
  public static class Points implements Comparable<Points>{
	  private int x,y;
	  //private double d;
	  
	  public Points(int x, int y) {
	   this.x=x;
	   this.y=y;
	   //this.d=Math.sqrt(x^2+y^2);
	  }
	  //it is compared to origin, if the origin is given, have to use (x1-x2)^2 as well
	  public double getDistance() {
		  //please please be carefull using x^2 as this is the XOR in java
		  return Math.sqrt(x*x+y*y);
	  }
	  
	  /*public List<Integer> getPoints(){
		  ArrayList<Integer> point=new ArrayList<>();
		  point.add(x,y);
		  return point;
	  }*/
    // this is very important working with any object sorting
	@Override
	public int compareTo(Points o) {
		// TODO Auto-generated method stub
		return Double.compare(this.getDistance(), o.getDistance());
	}
  }
  
  public static List<List<Integer>> getClosesPoints(int[][] points, int k){
	  //The default PriorityQueue is minHeap, so, we have to make it MaxHeap
	  PriorityQueue<Points> maxHeap=new PriorityQueue<>(k, Collections.reverseOrder());
	  
	  for(int[] point:points) {
		  Points p=new Points(point[0],point[1]);
		  System.out.println(p.getDistance());
		  maxHeap.add(p);
		  //if we crose the number k, should remove one if we want to add one
		  if(maxHeap.size()==k+1)
			  maxHeap.remove();
	  }
	 
	//we can covert the maxHeap to List as we are requires to return as a list
	 ArrayList<Points> kpoints=new ArrayList<Points>(maxHeap);
	 
	//now if we require to return the values in sorted (nearest first, we have to sort the heap again in on decreasing order
	 
	  Collections.sort(kpoints);
	  //now we are ready to return the list in sorted order
	  List<List<Integer>> res=new ArrayList<>();
	  for(Points p: kpoints)
		  res.add(Arrays.asList(p.x,p.y));
	return res;
	  //we will create a maxHeap to keep only the closes K points and discards others
  }
  
  public static void main(String args[]) {
	  int[][] point= {{1,0},{1,1},{2,1},{3,1},{2,2}};
	  System.out.println(getClosesPoints(point,3));
  }
  
}
