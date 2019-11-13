package books.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudukoSolver {

	private static final int EMPTY_ENTRY=0;
	//for leetcode the board is char and empty is "."
	public static boolean solveSudoku(List<List<Integer>> board) {

		if(board==null || board.size()==0)
			return false;


		for(int i=0;i<board.size();i++) {
			for(int j=0;j<board.get(0).size();j++) {
				if(board.get(i).get(j)==EMPTY_ENTRY) {
					for(int n=1;n<=9;n++) {
						if(isValid(board,i,j,n)) {
							board.get(i).set(j, n);

							if(solveSudoku(board))
								return true;
							else board.get(i).set(j, EMPTY_ENTRY);
						}
					}
					return false;
				}
			}
		}
		return true;
	}


	private static boolean isValid(List<List<Integer>> board, int x, int y, int val) {
		for(int i=0;i<9;i++) {
			//check for the current row
			if(board.get(x).get(i)!=EMPTY_ENTRY && board.get(x).get(i)==val)
				return false;
			//check for the current col
			if(board.get(i).get(y)!=EMPTY_ENTRY && board.get(i).get(y)==val)
				return false;
			//check for the current regional
			int r=3*(x/3)+i/3;
			int c=3*(y/3)+i%3;
			if(board.get(r).get(c)!=EMPTY_ENTRY && board.get(r).get(c)==val)
				return false;
		}
		return true;
	}


	public static void main(String args[]) {
		List<List<Integer>> board=new ArrayList<>();
		for(int i=0;i<9;i++)
		{
			List<Integer> t=new ArrayList<>();
			for(int j=0;j<9;j++)
				t.add(0);
			board.add(t);
			t = new ArrayList<>();
		}

		board.get(0).set(7, 7);
		boolean is=solveSudoku(board);
		System.out.println(is);
	}
}
