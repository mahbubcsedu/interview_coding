package books.epi;
import java.util.*;

public class PermutationIIDup {

	
		public static List<int[]> getPermSwapDup(int[] nums){
			List<int[]>result=new ArrayList<>();
			List<Integer> temp=new ArrayList<>();
			
			permSwapHelper(nums,0,result);
			
			return result;
			}
		
		public static void permSwapHelper(int[] nums, int start, List<int[]> res) {
			if(nums.length<=start) {
				//List<int[]> l=Arrays.asList(nums);
				int[] n=Arrays.copyOf(nums, nums.length);
				res.add(n);
	            return;
			}
			
			for(int i=start;i<nums.length;i++) {
				
				if(swapRequired(nums,start,i)) {
				
				swap(nums,start,i);
				
				permSwapHelper(nums,start+1,res);
				//swap back
				swap(nums,start,i);
				}
				
			}
		}
		
		public static void swap(int[]nums, int x, int y) {
			int t=nums[x];
			nums[x]=nums[y];
			nums[y]=t;
		}
		//we will check that the current elements does not match with already included items, if matched, swaping will not generate new results
		/**
		 * see a example to be clearer,
		 * 1,2, and then 2, so swaping 2 will give the exact same result with swaping of previous 2
		 * @param nums
		 * @param start
		 * @param end
		 * @return
		 */
		public static boolean swapRequired(int[]nums, int start, int end) {
			
			for(int i=start;i<end;i++) {
				if(nums[i]==nums[end])
					return false;
			}
			return true;
		}
		
		public static List<List<Integer>> getPermBack(int[] nums){
			List<List<Integer>> result=new ArrayList<>();
			List<Integer> temp=new ArrayList<>();
			boolean[] used=new boolean[nums.length];
			Arrays.fill(used, false);
			//to handle duplicate requires to sort the array and search duplicate one after another
			Arrays.sort(nums);
			permHelperBack(nums,temp,result,used);
			
			return result;
			}
		
		/**
		 * @link https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
		 * The algorithm is backtrack and used in pretty clever way to avoid the duplicate,
		 * it used the variable used or visited to keep track of the item and and also the common item. Duplicate item should be considered only once at the same sequence
		 * 
		 * @param nums
		 * @param temp
		 * @param res
		 * @param used
		 */
		public static void permHelperBack(int[] nums, List<Integer> temp,List<List<Integer>> res,boolean[] used) {
			if(nums.length==temp.size()) {
				res.add(new ArrayList<>(temp));
	            return;
			}
			
  			for(int i=0;i<nums.length;i++) {
				if(used[i] || (i>0 && nums[i]==nums[i-1] && !used[i-1]))
					continue;
				
				used[i]=true;
				temp.add(nums[i]);
				permHelperBack(nums,temp,res,used);
				used[i]=false;
				temp.remove(temp.size()-1);
				
			}
		}
		
		public static void test1() {
			int[] nums= {1,2,2,3};
			List<int[]> res=getPermSwapDup(nums);
			System.out.println(Arrays.deepToString(res.toArray()));
		}
		
		public static void test2() {
			int[] nums= {1,2,2,3};
			List<List<Integer>> res=getPermBack(nums);
			System.out.println(Arrays.deepToString(res.toArray()));
		}
			
		public static void main(String args[]) {
			test1();
			//test2();
		}
	}


