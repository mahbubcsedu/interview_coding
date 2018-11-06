package books.epi;

import java.util.*;

public class NQueens {
// this will return a all placement, 
	
	public static List<List<Integer>> getNQueens(int n){
		List<List<Integer>> result=new ArrayList<>();
		
		List<Integer> temp=new ArrayList<>();
		
		nQueenHelper(n,0,temp, result);
		return result;
	}
	
	public static void nQueenHelper(int n, int row,List<Integer> temp, List<List<Integer>> res) {
		//we will try to put queens at each row and with all options of columsn, if the number of queen and rows are same, we can add the result
		
		if(n==row) {
			res.add(new ArrayList<>(temp));
		
		}
		else {
			for(int col=0;col<n;col++) {
				temp.add(col);
				if(isValid(temp)) {
					nQueenHelper(n,row+1,temp,res);
				}
				temp.remove(temp.size()-1);
			}
		}
	}
	//cost of this method is n!/c^n where c=2.54, and it depends on the non attaking places in the grid
	
	public static boolean isValid(List<Integer> colPlacement) {
		//colplacement is organized as
		//0th element 0th row, so val will be the column number, 1st element first row, second element second row
		
		int rowId=colPlacement.size()-1;
		
		for(int i=0;i<rowId;i++) {
			int diff=Math.abs(colPlacement.get(i)-colPlacement.get(rowId));
			if(diff==0||diff==rowId-i) {
				return false;
			}
		}
		
		return true;
	}
	
	//This is another way to check if you forget the logic but for this we have to desin and nxn board first and based on the 2d grid have check
	//using backtracking
	
	/* A utility function to check if a queen can 
    be placed on board[row][col]. Note that this 
    function is called when "col" queens are already 
    placeed in columns from 0 to col -1. So we need 
    to check only left side for attacking queens */
 /*static boolean isSafe(int board[][], int row, int col) 
 { 
     int i, j; 

     // Check this row on left side 
     for (i = 0; i < col; i++) 
         if (board[row][i] == 1) 
             return false; 

     // Check upper diagonal on left side 
     for (i=row, j=col; i>=0 && j>=0; i--, j--) 
         if (board[i][j] == 1) 
             return false; 

     // Check lower diagonal on left side 
     for (i=row, j=col; j>=0 && i<N; i++, j--) 
         if (board[i][j] == 1) 
             return false; 

     return true; 
 } */
	
	public static void main(String args[]) {
		List<List<Integer>> res=getNQueens(4);
		System.out.println(Arrays.deepToString(res.toArray()));
	}

}
