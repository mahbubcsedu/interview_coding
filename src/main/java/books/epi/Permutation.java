package books.epi;
import java.util.*;
public class Permutation {


	public static List<int[]> getPerm(int[] nums){
		List<int[]>result=new ArrayList<>();
		List<Integer> temp=new ArrayList<>();

		permSwapHelper(nums,0,result);

		return result;
	}

	public static void permSwapHelper(int[] nums, int start, List<int[]> res) {
		if(nums.length==start) {
			//List<int[]> l=Arrays.asList(nums);
			int[] n=Arrays.copyOf(nums, nums.length);
			res.add(n);
			return;
		}

		for(int i=start;i<nums.length;i++) {
			int t=nums[i];
			nums[i]=nums[start];
			nums[start]=t;

			permSwapHelper(nums,start+1,res);

			t=nums[i];
			nums[i]=nums[start];
			nums[start]=t;

		}
	}


	public static List<List<Integer>> getPermBack(int[] nums){
		List<List<Integer>> result=new ArrayList<>();
		List<Integer> temp=new ArrayList<>();

		permSwapHelperBack(nums,0,temp,result);

		return result;
	}

	public static void permSwapHelperBack(int[] nums,int start, List<Integer> temp,List<List<Integer>> res) {
		if(nums.length==temp.size()) {
			res.add(new ArrayList<>(temp));
			return;
		}

		for(int i=0;i<nums.length;i++) {
			if(temp.contains(nums[i]))
				continue;
			temp.add(nums[i]);
			permSwapHelperBack(nums,start+1,temp,res);
			temp.remove(temp.size()-1);

		}
	}

	public static void test1() {
		int[] nums= {1,2,3};
		List<int[]> res=getPerm(nums);
		System.out.println(Arrays.deepToString(res.toArray()));
	}

	public static void test2() {
		int[] nums= {1,2,3};
		List<List<Integer>> res=getPermBack(nums);
		System.out.println(Arrays.deepToString(res.toArray()));
	}

	public static void main(String args[]) {
		//test1();
		test2();
	}
}
