package leetcode;

import java.util.Arrays;

public class CPUSchedule {
	public static int leastInterval(char[] tasks, int n) {

		int[] map=new int[26];//as only uppercase letter

		for(char c: tasks) {
			map[c-'A']++;
		}

		Arrays.sort(map);//sort by the frequency
		//this will sort increasing order, so highest freq eement will be at the last spot, 


		int time=0; //we will start counting interval/cpu cycle from 0
		//we check last spot for highest freq item and it this is empty we are done
		while(map[25]>0)
		{
			int i=0;

			while(i<=n) 
			{
				if(map[25]==0) {
					break;
				}

				if(i<26 && map[25-i] >0) 
				{
					//if we have anything left to schedule without violating the cooling period
					map[25-i]--;
				}
				//whether we schedule one or not, we can not stop time, may be another one or idle
				time++;
				i++;
			}
			//very very important that we always sorting the remaining, so that if time the most frequent one already covered more time, it should not at place of last spot
			Arrays.sort(map);
		}
		return time;
	}

	/**
	 * leetcode , this is work in progress condition and is not developed, need to check the optimize algorithm from leetcode
	 * @param tasks
	 * @param n
	 * @return
	 */

	public static int intervalOpt(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c: tasks)
			map[c - 'A']++;
		Arrays.sort(map);
		int max_val = map[25] - 1, idle_slots = max_val * n;
		for (int i = 24; i >= 0 && map[i] > 0; i--) {
			idle_slots -= Math.min(map[i], max_val);
		}
		return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
	}
	public static void test1() {
		char[] tasks= {'A','A','A','A','A','A','B','C','D','E'};
		System.out.println(leastInterval(tasks,2));
	}

	public static void test2() {
		char[] tasks= {'A','A','A','A','A','A','B','B','B','B','C','C','C','C','D'};
		System.out.println(intervalOpt(tasks,2));
	}
	public static void main(String args[]) {
		test1();
		test2();

	}
}
