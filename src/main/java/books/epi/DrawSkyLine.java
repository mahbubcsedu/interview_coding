package books.epi;
import java.util.*;

public class DrawSkyLine 
{
	public static List<List<Integer>> getSkyline(int[][] buildings){
		
		if(buildings==null || buildings.length==0)
			return new ArrayList<>();
		
		List<List<Integer>> result=new ArrayList<>();
		
		//first we will find the min left and max right from all buildings
		int minLeft=Integer.MAX_VALUE;
		int maxRight=0;
		/** if the buildings are sorted, don't need this part
		 * just get the first buildins left and last buildings right
		 * */
		for(int i=0;i<buildings.length;i++) {
			minLeft=Math.min(minLeft,buildings[i][0]);
			maxRight=Math.max(maxRight,buildings[i][1]);
		}
		
		//so we know the final boundary of the buildings, we can create a results array
		List<Integer> height=new ArrayList<>(Collections.nCopies(maxRight-minLeft+1, 0));
		
		//now incrementaly update for each building
		
		for(int b=0;b<buildings.length;b++) {
			//for each building, we start from its left and enumerate until its right
			for(int i=buildings[b][0];i<buildings[b][1];i++) {
				height.set(i-minLeft, Math.max(height.get(i-minLeft), buildings[b][2])); //record which one has the best height
			}
		}
		
		//Now we have all the skyline but with all points, we need to return only the crossing points
		
		int left=0;
		result.add(Arrays.asList(new Integer[] {minLeft,height.get(0)}));
		
		for(int i=1; i<height.size();i++) {
			
			if(i>0 && height.get(i-1)!=height.get(i)) {
				//changing positions, we will store the top height
				result.add(Arrays.asList(new Integer[] {minLeft+i,height.get(i)}));
			}
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		int[][] b= {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
		
		List<List<Integer>> res=getSkyline(b);
		System.out.println(Arrays.deepToString(res.toArray()));
	}

}
