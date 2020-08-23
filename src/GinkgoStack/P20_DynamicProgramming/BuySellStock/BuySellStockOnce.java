package GinkgoStack.P20_DynamicProgramming.BuySellStock;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 */
public class BuySellStockOnce {

    public int maxProfit(int[] prices) {
        // k == 1
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }


    public int maxProfit1(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public int maxProfit2(int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        int profile = prices[1] - prices[0]>= 0?prices[1] - prices[0]:0;
        int res = profile;
        for(int i = 2;i<prices.length;i++){
            int diff = prices[i] - prices[i-1];
            if(profile + diff >= 0){
                profile = profile + diff;
                if(profile >= res){
                    res = profile;
                }
            }else {
                profile = 0;
            }
        }
        return res;
    }


}
