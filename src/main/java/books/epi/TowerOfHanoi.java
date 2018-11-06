package books.epi;
import java.util.*;

public class TowerOfHanoi {

	public void computeTowerOfHonoi(int numberOfRings) {
		//there will be a number of rings n, which are place to from peg and will be transcfered to toPeg and a auxilary usePeg will be used
		//We will pick the top disc from each peg, so data structure for this will Deque. We can create a 3 Deque or List of Deque size three
		//Then each Deque will represent each peg, lets 0th is the from, 1 is the to and 2 is the use
		
		List<Deque<Integer>> peg =new ArrayList<>();
		//all the discs will be at from peg at first
		for(int i=0;i<3;i++) {
			peg.add(new LinkedList<>());
		}
		
		//now we have the pegs, we can place the discs now
		for(int i=0;i<numberOfRings;i++) {
			peg.get(0).add(i);
		}
		//now define a recursive function to calculate the tower of hanoi
		
		TowerOfHanoiHelper(numberOfRings,0,1,2,peg);
	}
	
	public void TowerOfHanoiHelper(int discToMove, int fromPeg, int toPeg, int usePeg, List<Deque<Integer>> pegs) {
		//first move n-1 discs from fromPeg to usePeg, and move the last on to fromPeg to toPeg
		//then move the n-1 from usePeg to topeg using frompeg
		//always check for recursion break or base condition
		if(discToMove>0) {
		TowerOfHanoiHelper(discToMove-1,fromPeg,usePeg,toPeg,pegs);
		pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
		System.out.println("Move "+ fromPeg +" from fromPeg to :"+toPeg);
		TowerOfHanoiHelper(discToMove-1,usePeg,toPeg,fromPeg,pegs);
		}
	}
	
	public static void main(String args[]) {
		TowerOfHanoi o=new TowerOfHanoi();
		o.computeTowerOfHonoi(3);
		
	}
}
