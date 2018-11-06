package books.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalCover {

	static class Interval{
		int start;
		int end;
		Interval(int s,int e){
			start=s;
			end=e;
		}
	}
	
	/** 
	 * So, many confusion in implementing, don't know why,
	 * because  need to take each problem seriously
	 * @param t
	 * @return
	 */
	
	public static List<Integer> getListTimes(List<Interval> t)
	{
		if(t.isEmpty())
			return Collections.EMPTY_LIST;
		
		Collections.sort(t, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				return Integer.compare(i1.end, i2.end);
			}
		});
		
		List<Integer> res=new ArrayList<>();
		
		res.add(t.get(0).end);
		int lastAddedEnd=t.get(0).end;
		
		for(int i=1; i<t.size();i++) {
			if(lastAddedEnd < t.get(i).start)
			{
				res.add(t.get(i).end);
				lastAddedEnd=t.get(i).end;
			}
		}
		
		return res;
	}
	
	
	public static void main(String args[]) {
		
		int[][] endpoints= {{1,2},{2,3},{3,4},{2,3},{3,4},{4,5}};
		
		List<Interval> input=new ArrayList<>();
		
		for(int[] inter: endpoints) {
			Interval i=new Interval(inter[0],inter[1]);
			input.add(i);
		}
		
		List<Integer> t=getListTimes(input);
		
		System.out.println(Arrays.deepToString(t.toArray()));
	}
}
