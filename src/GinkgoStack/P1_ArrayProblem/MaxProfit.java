package GinkgoStack.P1_ArrayProblem;

/**
 * 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit {

    /**
     * dp
     * 空间压缩
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        int profile = prices[1] - prices[0]>= 0?prices[1] - prices[0]:0;
        int res = profile;
        for(int i = 2;i<prices.length;i++){
            int diff = prices[i] - prices[i-1];//表示与前一个价格之间的差值
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

    /**
     * 面试奇安信的代码
     * 可以进行空间压缩
     */
    public static int getProfile(int[] prices){
        if(prices.length <= 1) return 0;
        int[] dp = new int[prices.length];
        int ans = Integer.MIN_VALUE;
        for (int i = 1;i< prices.length;i++){
            dp[i] = prices[i]- prices[i-1] + dp[i-1];
            if(dp[i] < 0) dp[i] = 0;
            ans = ans < dp[i]?dp[i]:ans;
        }

        return ans;
    }


}
