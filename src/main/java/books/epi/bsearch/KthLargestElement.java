package books.epi.bsearch;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestElement {

	public static int findKthLargest(List<Integer> A, int k) {
		if(A.size()<k)
			return -1;
		
		int left=0;
		int right=A.size()-1;
		
		Random r=new Random();
		while(left<=right) {
		    
			
		    int pivot=r.nextInt(right-left+1)+left;
		    //now we have random pivot index and we will partition the list based on the pivot index
		
		    int pos=partition(A,left,pivot,right);
		
		
		    if(pos==k-1)
			    return A.get(pos);
		    else if(pos<k-1) {
			  left=pos+1;
		    }else if(pos > k-1) {
		    	right=pos-1;
		    }
		}
		return A.get(left);
	}
	
	//see this with the example, 
	/**
	 * 1. select a pivot randomly
	 * 2. partition the array in two parts less than pivot inclusive and greater than exclusive
	 * 3. Partition can be done by swaping the pivot to rightmost to the sublist
	 * 4. start the comparison from the leftmost element of the desired sublist and compare with pivot value,
	 * 5. if less than pivot value, swap with left and increase the left
	 * 6. after process all elements of the sublist, swap the rightmost with the last element of the partition list and return that index
	 * @param l
	 * @param leftOfList
	 * @param pivotIndex
	 * @param rightOfList
	 * @return
	 */
	public static int partition(List<Integer> l, int leftOfList, int pivotIndex,int rightOfList) {
		
		int lastOfPartition=leftOfList;
		Collections.swap(l, pivotIndex, rightOfList);
		int pVal=l.get(pivotIndex);
		
		for(int i=leftOfList;i<rightOfList;i++) {
			if(l.get(i)<pVal) {
				Collections.swap(l, i, lastOfPartition++);
			}
		}
		Collections.swap(l, rightOfList, lastOfPartition);
		return lastOfPartition;
	}
	
	
	public static void main(String args[]) {
		Integer[] n= {3,2,1,4,5};
		System.out.println(findKthLargest(Arrays.asList(n),3));
	}
}
