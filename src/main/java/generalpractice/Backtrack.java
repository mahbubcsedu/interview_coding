package generalpractice;
import java.util.*;
/** This class is to just general backtrack technique with wellknown problems and their variations
 * 
 * @author mahbub
 *
 */
public class Backtrack {

	public static List<List<Integer>> getPermutations(int[] nums){
		
		
		/*
		 * The general rule of backtrack is to try all possible way and prune the path if it does not lead to solutions.
		 * This will always keep a result set, a temporary set to keep the building block of solutions and the input itself
		 */
		//Create a list for final results 
		List<List<Integer>> result=new ArrayList<>();
		//Create a temporary list
		List<Integer> building=new ArrayList<>();
		
		permuteBackTrack(nums, building, result);
		
		return result;
		
	}
	
	public static void permuteBackTrack(int[] nums,List<Integer> temp, List<List<Integer>> res) {
		if(temp.size()==nums.length) {
			res.add(new ArrayList<>(temp));
		}
		else
		{
			for(int i=0; i<nums.length;i++) {
				if(temp.contains(nums[i])) {
					continue;
				}
				temp.add(nums[i]);//add next elements and try
				permuteBackTrack(nums,temp,res);
				temp.remove(temp.size()-1);//remove last added elements after trying all possible condidtion
				
			}
		}
	}
	
	/**
	 * Now if we want to use the backtrack to find the subset or powerset or subset sum, suduko etc
	 */
	public static List<List<Integer>> getSubSet(int[] nums){
		List<List<Integer>> res=new ArrayList<>();
		subSetBackTrack(nums, new TreeSet<Integer>(), res, 0);
		return res;
	}
	
	public static void subSetBackTrack(int[] nums, TreeSet<Integer> temp, List<List<Integer>> res, int start) {
		//we will add the generated subset, dont use just res.add(temp) as this will not create new list instead, same list will added again and again
		
		res.add(new ArrayList<Integer>(temp));
		//System.out.println(Arrays.deepToString(res.toArray()));
		
		for(int i=start; i<nums.length;i++) {
			temp.add(nums[i]);//with ith element
			subSetBackTrack(nums, temp,res,i+1);
			temp.remove(temp.size()-1);//without ith elements
		}
	}
	
	
	
	
	
	public static void main(String args[]) {
		List<List<Integer>> res=getPermutations(new int[] {1,2,3});
		System.out.println(Arrays.deepToString(res.toArray()));
		
		List<List<Integer>> sset=getSubSet(new int[] {1,4,2,3});
		System.out.println(Arrays.deepToString(sset.toArray()));
	}
	
}
