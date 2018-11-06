package books.epi;
import java.util.*;
public class TrappedWaterBetweenTowbar {

	
	public static int getMaxArea(List<Integer> height) {
		int i=0,j=height.size()-1;
		int maxWater=0;
		
		while(i<j) {
			int width=j-i;
			maxWater=Math.max(maxWater, width*Math.min(height.get(i), height.get(j)));
			if(height.get(i) > height.get(j)) {
				j--;
			}else if(height.get(i) < height.get(j)){
				i++;
			}else {
				i++;
				j--;
			}
		}
		return maxWater;
	}
	
	public static void main(String args[]) {
		Integer[] height= {2,2,3,4,5,8,2,19,20,3,4,5,56,3,4};
		int maxw=getMaxArea(Arrays.asList(height));
		System.out.println(maxw);
	}
	
}
