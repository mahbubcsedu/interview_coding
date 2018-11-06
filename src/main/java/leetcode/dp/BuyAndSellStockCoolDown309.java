package leetcode.dp;

public class BuyAndSellStockCoolDown309 {

	/**
	 * A good explanation is given here 
	 * @link https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/167290/Routine-DP-solution
	 * 
	 */

	public static int getMaxProfitCooldown(int[] prices) {

		int n=prices.length;

		if(prices.length==0)
			return 0;

		//we can create 4 arrays to keep track of all four status of the stocks

		int[] buy=new int[n];
		int[] hold=new int[n];
		int[] sell=new int[n];
		int[] cool=new int[n];

		//initialize the variables

		buy[0]=-prices[0];
		hold[0]=0;
		cool[0]=Integer.MIN_VALUE;
		sell[0]=0;

		for(int i=1;i<prices.length;i++) {
			buy[i]=cool[i-1]-prices[i];// last time a transaction happend and then a cooldown
			sell[i]=Math.max(buy[i-1]+prices[i], hold[i-1]+prices[i]); //if we sell, then last time buy and sell now will be the money
			//but if we have hold and sale now, addition will be the money and we will chose the best one
			hold[i]=Math.max(buy[i-1], hold[i-1]);
			cool[i]=Math.max(sell[i-1], cool[i-1]);// cool will be the max if we sell last time or cool last time
		}
		return Math.max(sell[n-1], cool[n-1]); //which one is greater not selling or selling at last day
	}

	//We can simply see that we need variables instead of array and can avoid O(n) space
	public static int getMaxProfitCooldownOpt(int[] prices) {

		int n=prices.length;

		if(prices.length==0)
			return 0;

		//we can create 4 arrays to keep track of all four status of the stocks

		int buy=0;
		int hold=0;
		int sell=0;
		int cool=0;

		//initialize the variables

		int prevbuy=-prices[0];
		int prevhold=Integer.MIN_VALUE;
		int prevcool=0;
		int prevsell=0;

		for(int i=1;i<prices.length;i++) {
			buy=prevcool-prices[i];// last time a transaction happend and then a cooldown
			sell=Math.max(prevbuy+prices[i], prevhold+prices[i]); //if we sell, then last time buy and sell now will be the money
			//but if we have hold and sale now, addition will be the money and we will chose the best one
			hold=Math.max(prevbuy, prevhold);
			cool=Math.max(prevsell, prevcool);// cool will be the max if we sell last time or cool last time

			prevbuy=buy;
			prevsell=sell;
			prevhold=hold;
			prevcool=cool;
		}
		return Math.max(sell, cool); //which one is greater not selling or selling at last day
	}

	//very hard to understand, later will see
	public static int maxProfit(int[] prices) {
		int buy = Integer.MIN_VALUE, prev_buy = 0, sell = 0, prev_sell = 0;

		for (int price : prices) {
			prev_buy = buy;
			buy = Math.max(buy, prev_sell-price);

			prev_sell = sell;
			sell = Math.max(sell, prev_buy+price);
		}


		return sell;
	}
	// if the saling before cooldown time is less than current cooldown period buy and saling ith day then chose this one, 
	//you can spend more time later
	public int maxProfitDp(int[] prices) {
        if(prices==null||prices.length==0)return 0;
        int[]dp = new int[prices.length];
        int tmp = 0;
        for(int i=1;i<prices.length;i++){
            tmp = Math.max(tmp, i>=3?dp[i-3]:0)+prices[i]-prices[i-1];
            dp[i]=Math.max(dp[i-1], tmp);
        }
        return dp[prices.length-1];        
    }

	public static void main(String args[]) {
		int[] input= {1,2,3,0,2};
		System.out.println(getMaxProfitCooldownOpt(input));
		System.out.println(maxProfit(input));
	}
}
