package leetcode.dp;

public class StoneGame877 {
	
	public static boolean stoneGame(int[] piles) {
	
		int[][] maxStonePick=new int[piles.length][piles.length];
		int aliceStone=pickMaxStone(piles,0,piles.length-1,maxStonePick);
		
		int totalStone=0;
		for(int i=0;i<piles.length;i++) {
			totalStone+=piles[i];
			
		}
		return totalStone/2 < aliceStone ? true : false;
	}
	
	
	public static int pickMaxStone(int[] piles, int a, int b, int[][] totalStone) {
		if(a>b) {
			return 0;
		}
		
		int maxStone=0;
		//if(totalStone[a][b]==0) {
			//make sure that if alice pick piles[a], other player must optimized to pick minimum from the rest of the play
			//this means that, if alice pick a, and other player pick a+1, then a+2 to b should be minimum or if other player pick
			//b, a+1 to b-1 should be minimum
			int maxStoneA=piles[a]+Math.min(pickMaxStone(piles,a+2,b,totalStone), pickMaxStone(piles,a+1,b-1,totalStone));
			//same is here for second case
			int maxStoneB=piles[b]+Math.min(pickMaxStone(piles,a+1,b-1,totalStone), pickMaxStone(piles,a,b-2,totalStone));
			maxStone=Math.max(maxStoneA, maxStoneB);
		//}
		 return maxStone;
	}
	
	/**
	 * for dp solutions just store the middle computation to a table, so that repeated calculations are not done 
	 * @param args
	 */
	
	public static boolean stoneGameDp(int[] piles) {
		
		int[][] maxStonePick=new int[piles.length][piles.length];
		int aliceStone=pickMaxStoneDp(piles,0,piles.length-1,maxStonePick);
		
		int totalStone=0;
		for(int i=0;i<piles.length;i++) {
			totalStone+=piles[i];
			
		}
		return totalStone/2 < aliceStone ? true : false;
	}
	
	
	public static int pickMaxStoneDp(int[] piles, int a, int b, int[][] totalStone) {
		if(a>b) {
			return 0;
		}
		
		//int maxStone=0;
		if(totalStone[a][b]==0) {
			//make sure that if alice pick piles[a], other player must optimized to pick minimum from the rest of the play
			//this means that, if alice pick a, and other player pick a+1, then a+2 to b should be minimum or if other player pick
			//b, a+1 to b-1 should be minimum
			int maxStoneA=piles[a]+Math.min(pickMaxStoneDp(piles,a+2,b,totalStone), pickMaxStoneDp(piles,a+1,b-1,totalStone));
			//same is here for second case
			int maxStoneB=piles[b]+Math.min(pickMaxStoneDp(piles,a+1,b-1,totalStone), pickMaxStoneDp(piles,a,b-2,totalStone));
			totalStone[a][b]=Math.max(maxStoneA, maxStoneB);
		}
		 return totalStone[a][b];
	}
	
	public static void main(String args[]) {
		int[] input= {5,3,4,5};
		System.out.println(stoneGame(input));
	
 }

}