package company.uber;

public class RotateSortedDuplicateRange {

	//sorted array
	static int getFirstOccuranceOfK(int[] A, int k) {
		
		int l=0,r=A.length-1, result=-1;
		
		while(l<=r) {
			int m=l+(r-l)/2;
			if(A[m] > k) {
				r=m-1;//k is in the left side
			}else if(A[m]==k) {
				//first occurance should be on the left
				result=m;
				r=m-1;
			}
			else {
				l=m+1;
			}
		}
		return result;
	}
	
	//sorted rotated array
	
	static int findTargetElement(int[]A, int target) {

		int l=0, r=A.length-1,result=-1;

		//the rules is same but have to process in different way considering the target

		while(l<r) {

			int mid=l+(r-l)/2;

			if(A[mid]==target) {
				result=mid;
				//r=mid-1;
				return result;
			}
			
			if(A[l] <= A[mid]) 
			{ //pivot is on the right
				//if the mid element is less than or equal to target, 
				//so
				if(target >=A[l] && target <= A[mid])
					r=mid-1;
				else l=mid+1;

			}
			//so, here we are because, pivot is on the left, so if the item is between mid to right, we can search right

			else {
				
			if(target >= A[mid] && target <=A[r]) 
			{
				l=mid+1;
			}else 
			{
				r=mid-1;
			}

		}
		}

		
		//we can search for the elements equal or not which is always o(n). but there is a trade of if we want to use k vs n while searching for the range
		return result;
	}
	
	static void smallTestfindTargetRepeated() {
		int[][] rarr= {{1,2,3},{8,1,3,5},{5,6,7,8,9,1,2,3,4},{3,4,8,9,10,11,12,2},{1},{4,5,6,6,6,6,6,6,7}};
		int[] target= {3,3,7,9,1,6};

		for(int i=0;i<rarr.length;i++) {
			int pos=findTargetElement(rarr[i],target[i]);
			System.out.printf("piovt elements positions %d",pos);
			System.out.println();
		}

	}
	public static void main(String args[]) {
		smallTestfindTargetRepeated();	
	}
	
}
