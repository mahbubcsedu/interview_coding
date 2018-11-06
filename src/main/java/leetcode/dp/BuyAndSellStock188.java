package leetcode.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * There is a good explanation of different situation of buy and sale stock except cooldown
 * {@link http://wlcoding.blogspot.com/2015/03/best-time-to-buy-and-sell-stock.html}
 * @author mahbub
 *
 */
public class BuyAndSellStock188 {
 
	/*
	 * Brute force algorithm is to iterate over k times and find the maximum profit of transactions
	 * 
	 * We have two obvisous situation when we decide to sale. As, sale will only define the profit
	 * a. We may decide to sale a share and b. we decide to not sale today
	 * 
	 * lets say p[t][i] is the max profit at ith day with at most t transactions
	 * 
	 * so we can update todays profit by
	 *  1. p[t][i]=price[i]-price[j]+p[t-1][j] if we sale the share at ith day. This is because, we will sale at ith day and price is price[i]
	 *     and we bought it at jth day price[j] and at jth day with one less transaction the max profit was p[t-1][j]. Here J could be any day
	 *     from 0 to ith day
	 *  2. p[t][i]=p[t][i-1], we decided not to sale today and max profit from yesterday will not change at all as well as max transaction t
	 *
	 *   time complexity of O(kn^2) and space O(kn) Leet code memory limit exceeded with 2d
	 */
	
	
	public static int getMaxProfitK(int[] prices, int k) {
		
		if(prices.length==0 || k==0)
			return 0;
		
		int[][] p=new int[k+1][prices.length+1];
		
		//at day 0, whatever transaction you do, you will earn nothing
		for(int i=0;i<=k;i++)
		{
			p[i][0]=0; 
		}
		
		for(int j=0;j<=prices.length;j++) {
			p[0][j]=0;// no transaction no profit
		}
		
		for(int t=1;t<=k;t++) {
			for(int j=1;j<prices.length;j++) {
				int cur_profit=0;
				
				for(int day=0;day<j;day++) {
					cur_profit=Math.max(cur_profit, prices[j]-prices[day]+p[t-1][day]);
				}
				//also check whether todays transaction improves profit than yesterday
				p[t][j]=Math.max(cur_profit,p[t][j-1]);
			}
		}
		
		return p[k][prices.length-1];
	}
	
	/** 
	 * see the optimization formula here but for more clearer to understand given here
	 * {@link https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/}
	 * 
	 * In the first dp problem, we have the formula for dp[t][i]=max(dp[t-1][i-1], max(prices[i]-prices[j]+p[t-1][j])
	 * 
	 * so, the second part we need to calculate for all j 0~i-1 and it requires to calculate everytime even if i just increases by 1.
	 * 
	 * 1. so, if we write the second part as max(prices[i]-prices[j]+p[t-1][j]) = prices[i]+max(p[t-1][j]-prices[j] as we changed the sign so as
	 * the parameters inside, or just multiplied by -1,
	 * 
	 * 2. Now, p[t-1][j]-price[j] explains that only j is the variable and it is the profit at day j minus the price at day j. Or we can just say
	 * that the difference of profit-prices at day j. 
	 * 
	 * 3. So, each time, the loop actually calculate difference from day 0 to day i-1. So, inside max we actually calculate yesterday difference
	 * and todays difference and and select max.
	 * 4. So, if we have yesterday difference, we can compare it to todays difference and get the max
	 * 5. The formula will be then prices[i]+max(p[t-1][j]-prices[j])=prices[i]+max(prevDiffrence, p[t-1][j]-prices[j])
	 */
	
	public static int buyAndSellStockKtimesOpt(int[] prices, int k) {
		
		int n=prices.length;
		int profit=0;
		
		
		if(n==0 || k==0)
			return 0;
		
		
		if (k > n / 2) {
            for (int i = 0; i < n - 1; i++) {
                int diff = prices[i + 1] - prices[i];
                if (diff > 0)   profit += diff;
            }
            return profit;
        }
		
		int p[][] =new int[k+1][n+1];
		
		for(int i=0; i<=k;i++) {
			p[i][0]=0;//at day 0, no profit
		}
		
		for(int j=0;j<=n;j++) {
			p[0][j]=0; // 0 transaction always return 0;
		}
		
		for(int i=1; i<=k;i++) {
			int preDiff=Integer.MIN_VALUE;
			for(int j=1;j<n;j++) {
				preDiff=Math.max(preDiff, p[i-1][j-1]-prices[j-1]);
				p[i][j]=Math.max(p[i][j-1], prices[j]+preDiff);
			}
		}
		
		return p[k][n-1];
	}
	
	/**
	 * Explained and coded in william codebook link given above
	 * @param prices
	 * @param k
	 * @return
	 */
	
	public static double buyAndSellStockKTimes(int[] prices, int k) {

		int len = prices.length;
        int profit = 0;
        
        // as many transactions as we want as number of transaction is more than half, and we have options of only hafl
        //as a transaction requires at least two days
        if (k > len / 2) {
            for (int i = 0; i < len - 1; i++) {
                int diff = prices[i + 1] - prices[i];
                if (diff > 0)   profit += diff;
            }
            return profit;
        }
        
        // at most k transactions
        // d(i, j) = max{d(i, j - 1), max_{1<=l<=j} {d(i - 1, l - 1) - p[l] (buy) + p[j] (sell)}}
        int[][] d = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax = d[i - 1][0] - prices[0];
            for (int j = 1; j < len; j++) {
                tmpMax = Math.max(tmpMax, d[i - 1][j - 1] - prices[j]);
                d[i][j] = Math.max(d[i][j - 1], prices[j] + tmpMax);
            }
        }
        return d[k][len - 1];
	  }
	/**
	 * EPI books solutions, /TODO did not work, will have to see later
	 * getMaxProfitOptDp based on books
	 * buyAndSellStockKTimes based on the implementation of their website
	 * @param prices
	 * @param k
	 * @return
	 */
	public static int getMaxProfitEPI(int[] prices, int k ) {
		int[] dp=new int[k*2];
		for(int i=0;i<dp.length;i++) {
			dp[i]=Integer.MIN_VALUE;
		}
		
		for(int i=0;i<prices.length;i++) {
			int[] preksum=new int[dp.length];
			for(int j=0,sign=-1;j<dp.length && j<=i;++j,sign*=-1) {
				int diff=sign*(prices[i]+(j==0?0:preksum[j-1]));
				dp[j]=Math.max(diff, preksum[j]);
			}
		}
		return  dp[dp.length-1];
	}
	
	
	 
	
	public static void smallTestDp() {
		int[] p= {10,12,9,15,2,19,17};
		System.out.println(buyAndSellStockKTimes(p,2));
		int[] p2= {};
		System.out.println(getMaxProfitK(p2,2));
	}
	public static void smallTestRec() {
		int[] p= {10,12,9,15,2,19,17};
		System.out.println(getMaxProfitK(p,2));
		int[] p2= {};
		System.out.println(getMaxProfitK(p2,2));
		int[] leetin= {106,373,495,46,359,919,906,440,783,583,784,73,238,701,972,308,165,774,990,675,737,990,713,
				157,211,880,961,132,980,136,285,239,628,221,948,939,28,541,414,180,171,640,297,873,59,814,832,611,868,633,
				101,67,396,264,445,548,257,656,624,71,607,67,836,14,373,205,434,203,661,793,45,623,140,67,177,885,155,
				764,363,269,599,32,228,111,102,565,918,592,604,244,982,533,781,604,115,429,33,894,778,885,145,
				888,577,275,644,824,277,302,182,94,479,563,52,771,544,794,964,827,744,366,548,761,477,
				434,999,86,1000,5,99,311,346,609,778,937,372,793,754,191,592,860,748,297,610,386,146,220,
				7,113,657,438,482,700,158,884,877,964,777,139,809,489,383,92,581,970,899,947,864,443,490,
				825,674,906,402,270,416,611,949,476,775,899,837,796,227,232,226,11,266,889,215,6,182,430,5,706,
				994,128,359,841,439,263,491,689,638,485,763,695,135,800,763,54,569,387,112,316,193,675,546,531,954,
				571,208,282,557,892,469,875,765,592,374,276,892,843,625,180,249,292,477,882,837,112,46,667,187,93,418,
				790,903,12,978,510,647,446,597,958,678,897,420,907,256,170,669,920,711,635,995,259,994,634,583,175,380,435,
				942,739,921,132,455,986,567,464,301,10,579,84,745,717,588,414,375,319,770,310,510,521,88,445,59,460,120,
				765,480,441,169,374,180,947,179,346,490,417,149,140,577,624,427,238,341,686,623,228,672,859,372,938,567,
				141,133,671,255,997,272,591,115,340,692,531,235,123,677,980,31,774,135,194,956,723,779,375,546,59,695,
				616,416,362,38,145,782,184,418,806,444,177,360,485,941,998,85,840,740,545,49,570,17,824,845,749,177,727,238,656,787,
				425,473,323,683,578,442,436,444,595,367,44,467,93,507,949,598,579,471,1,347,982,232,878,217,845,777,
				284,527,529,100,482,456,814,457,251,494,419,922,139,706,384,954,365,680,70,810,764,820,992,622,29,
				697,294,553,655,63,934,827,157,680,812,729,486,403,151,988,926,460,193,294,423,774,715,906,957,598,929,339,
				119,686,88,228,803,806,743,430,315,224,712,724,69,606,411,271,700,520,179,916,490,652,319,69,245,827,185,200,
				911,363,335,50,353,551,737,15,429,966,766,307,829,379,184,779,239,254,904,262,719,321,380,253,564,348,
				878,570,470,313,752,563,164,301,239,856,491,154,795,640,199,940,420,201,254,400,865,886,819,424,292,257,572,112,
				590,984,421,639,705,707,779,660,4,817,265,465,737,56,564,797,178,552,988,621,98,665,379,607,300,439,269,196,
				94,860,540,830,756,294,806,321,930,623,206,440,730,829,566,420,488,49,438,447,294,548,804,514,45,383,
				431,373,424,11,377,868,559,316,831,464,211,710,803,680,665,39,523,951,219,293,909,838,708,663,627,220,
				100,565,269,982,236,185,194,697,556,767,541,360,103,497,271,919,19,206,73,393,50,421,466,970,329,105,618,
				17,687,578,260,759,366,334,686,613,616,893,351,847,861,452,454,454,88,135,357,194,220,504,36,916,246,718,172,395,292
				,613,533,662,983,701,877,842,445,263,529,679,526,31,385,918,898,584,846,474,648,67,331,890,174,766,274,476,414};
	
	
		System.out.println(getMaxProfitK(leetin,100));
	}
	public static void smallTestDpOpt() {
		int[] p= {10,12,9,15,2,19,17};
		System.out.println(buyAndSellStockKtimesOpt(p,2));
		int[] p2= {};
		System.out.println(buyAndSellStockKtimesOpt(p2,2));
	}
	public static void main(String args[]) {
		//smallTestDp();
		smallTestDpOpt();
	}
}
