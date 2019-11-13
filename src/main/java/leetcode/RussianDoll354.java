package leetcode;

import java.util.Arrays;
import java.util.Comparator;


/**
 * Box stacking and LIS
 * @author mahbub
 *
 */
public class RussianDoll354 {


	public static int maxEnvelops(int[][] envelopes) {

		if(envelopes==null || envelopes.length==0)
			return 0;
		
		Arrays.sort(envelopes, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub

				if(o1[0]==o2[0])
					return o2[1]-o1[1]; //this is the technique to avoid equal first dimensions, by reverse ordering
				else return o1[0]-o2[0];

			}
		});

		int result[] = new int[envelopes.length];
		int len=0; //right limit is 0


		//int len = 0;
		for(int[] envelope : envelopes){
			int index = Arrays.binarySearch(result, 0, len, envelope[1]);
			if(index < 0)
				index = -(index + 1);
			result[index] = envelope[1];
			if(index == len)
				len++;
		}


		//for(int i=0;i<envelops.length;i++) {
		//Arrays.binarySearch(result, 0,len,envelops[1]);
		/*index of the search key, if it is contained in the array within the specified range; otherwise, (-(insertion point) - 1). 
		 * The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the 
		 * range greater than the key, or toIndex if all elements in the range are less than the specified key. 
		 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.
		 * 
		 * so, if the current array [2, 3, 5, ...] and the key is 6, it will return -4, last searched position, 
		 * which is very important if we want to insert a value if failed to find
		 * 
		 * Keep in mind this return index is 1 baesd not 0 based, 
		 */
		/*int index=Arrays.binarySearch(result,0,len,envelops[i][1]);
			if(index <0) //not found, so, have the position in negative value-1
				index=-(index+1);
			result[index]=envelops[i][1];
			if(index==len)
				len++;
		}*/

		return len;
	}

	/**
	 * leetcode solutions to practice
	 * @param envelopes
	 * @return
	 */
	public static int maxEnvelopes(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0 
				|| envelopes[0] == null || envelopes[0].length != 2)
			return 0;
		Arrays.sort(envelopes, new Comparator<int[]>(){
			public int compare(int[] arr1, int[] arr2){
				if(arr1[0] == arr2[0])
					return arr2[1] - arr1[1];
				else
					return arr1[0] - arr2[0];
			} 
		});
		int dp[] = new int[envelopes.length];
		int len = 0;
		for(int[] envelope : envelopes){
			int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
			if(index < 0)
				index = -(index + 1);
			dp[index] = envelope[1];
			if(index == len)
				len++;
		}
		return len;
	}
	/**
	 * do we really have any constraint of not sorting, because without sorting, we have to find out the LIS
	 * 
	 * @param envelops
	 * @return
	 */

	public static int maxEnvelopsSort(int[][] envelops) {

		if(envelops==null || envelops.length==0)
			return 0;
		Arrays.sort(envelops, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub

				if(o1[0]==o2[0])
					return o1[1]-o2[1];
				else return o1[0]-o2[0];

			}
		});

		int result[] = new int[envelops.length];
		int len=1; //right limit is 0
		int[] prevEnv=envelops[0];
		for(int i=1;i<envelops.length;i++) {
			if(prevEnv[0]<envelops[i][0] && prevEnv[1]<envelops[i][1] )
			{
				len++;
				prevEnv=envelops[i];
			}
		}
		return len;
	}

	static void test2() {
		//int[][] en= {{5,4},{6,4},{6,7},{2,3}};
		int[][] ens= {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
		System.out.println(maxEnvelopsSort(ens));
		
	}

	static void test1() {
		//int[][] en= {{5,4},{6,4},{6,7},{2,3}};
		int[][] ens= {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
		System.out.println(maxEnvelops(ens));
		assert maxEnvelopes(ens) == 6;
	}

	static void test3() {
		//int[][] en= {{5,4},{6,4},{6,7},{2,3}};
		int[][] ens= {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
		System.out.println(maxEnvelopes(ens));
		assert maxEnvelopes(ens) == 5;
	}
	public static void main(String args[]) {
		test3();
		test1();

	}
}
