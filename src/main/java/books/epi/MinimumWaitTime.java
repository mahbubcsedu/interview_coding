package books.epi;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//import books.epi.OptimumTaskAssignment.PairedTasks;

public class MinimumWaitTime {
	public static int getMinWaitTime(List<Integer> t){
		Collections.sort(t);

		int numOfTasks=t.size();

		int waitTime=0;

		for(int i=0; i<t.size();i++) {
			waitTime+=t.get(i)*numOfTasks;
			numOfTasks--;
		}
		return waitTime;
	}


	public static void main(String args[]) {
		int t=getMinWaitTime(Arrays.asList(new Integer[]{5,2,1,6,4,4}));

		System.out.println(t);
	}
}
