package books.epi;
import java.util.*;
public class OptimumTaskAssignment {

	static class PairedTasks{
	 int task;
	 int task2;
	
	public  PairedTasks(int t1, int t2) {
	   this.task=t1;
	   this.task2=t2;
	  }
	}
	
	
	public static List<PairedTasks> getPairTask(List<Integer> t){
		Collections.sort(t);
		List<PairedTasks> optPairs=new ArrayList<>();
		
		for(int i=0, j=t.size()-1; i<j;i++,j--) {
			optPairs.add(new PairedTasks(t.get(i),t.get(j)));
		}
		return optPairs;
	}
	
	
	public static void main(String args[]) {
		List<PairedTasks> t=getPairTask(Arrays.asList(new Integer[]{5,2,1,6,4,4}));
		
		System.out.println(Arrays.deepToString(t.toArray()));
	}
}
