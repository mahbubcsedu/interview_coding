package books.epi;

public class CoinChangeMinimum {

	
	
	
	
	//what is the minimum number of coins that can be exchanged with given value
	/*
	 * if the coin is {30,24,12,6,3} and the value 48, greedy will give us 30+12+6 but optimal would be 24+24
	 */
	public static int getMinCoins(int v) {
		final int[] coins= {100,50,25,10,5,1};
		int numOfCoins=0;
		
		for(int i=0;i< coins.length;i++) {
			numOfCoins+=v/coins[i];
			v=v%coins[i];
		}
		return numOfCoins;
	}
	
	public static void main(String args[]) {
		System.out.println(getMinCoins(987));
	}
}
