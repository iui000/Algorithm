package GinkgoStack.P20_DynamicProgramming;


/**
 * 265. 粉刷房子 II
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；
 * costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。
 * 请你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 *
 * 所有花费均为正整数。
 *
 * 示例：
 *
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 *      或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 * 进阶：
 * 您能否在 O(nk) 的时间复杂度下解决此问题？
 */
public class PaintHouseII265 {

    /**
     * 序列型DP，有个明显得特征，就是前i个什么什么最小值最大值
     *
     * 思路：DP
     * 状态dp[i][j]表示粉刷前i个房子，并且最后一个房子粉刷成颜色j的最小花费
     *
     * 转移方程：
     * dp[i][j] = 粉刷前i-1个房子，并且最后一个房子不是颜色j的最小花费 + 第i个房子选择颜色j的花费，即costs[i-1][j]
     *
     * 也就是：
     *
     * dp[i][j] = Math.min(dp[i-1][x] + costs[i-1][j]) ,其中x != j;
     *
     * 初始状态：
     *
     * dp[0][j] = 0: 前0个房子粉刷各种颜色的最小花费当然是0
     * dp[1][j] = cost[0][j],前1个房子粉刷各种颜色的最小花费，当然就是costs数组的第一行。
     *
     * 先按照这个思路写出DP，再来空间优化，因为这里明显看出，dp[][]只依赖于上一行的状态，因此可以用滚动数组优化空间
     *
     */

    /**
     * 时空复杂度O（n k^2） O(nk)
     */
    class Solution1{
        public int minCostII(int[][] costs){
            if(costs.length == 0){
                return 0;
            }
            int n = costs.length;
            int k = costs[0].length;

            int result = Integer.MAX_VALUE;
            int[][] dp = new int[n+1][k];

            //初始状态
            for (int i = 0; i < k; i++) {
                dp[0][i] = 0;
                dp[1][i] = costs[0][i];
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < k; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int x = 0; x < k; x++) {
                        if(x != j){
                            //costs[i-1][j]就是第i个房子刷颜色j的花费
                            min = Math.min(min,dp[i-1][x] + costs[i-1][j]);
                        }
                    }
                    dp[i][j] = min;
                }
            }

            //最后，再来找出粉刷前n个房子，且最后一个房子刷哪种颜色的花费是最小的，就是最后的答案
            for (int i = 0; i < k; i++) {
                result = Math.min(result,dp[n][i]);
            }

            return result;
        }
    }


    /**
     * 时间复杂度还是并没有到O（nk），时间复杂度还是O（n k^2）
     * 更好的解，看方法三
     */
    class Solution2{
        public int minCostII(int[][] costs){
            if(costs.length == 0){
                return 0;
            }
            int n = costs.length;
            int k = costs[0].length;

            int result = Integer.MAX_VALUE;
            int[][] dp = new int[2][k];//因为只依赖于上一行状态

            //初始状态
            for (int i = 0; i < k; i++) {
                dp[0][i] = 0;
                dp[1][i] = costs[0][i];
            }

            int m = 1;//用于滚动数组的交替
            for (int i = 2; i <= n; i++) {
                m = 1- m;//交替
                for (int j = 0; j < k; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int x = 0; x < k; x++) {
                        if(x != j){
                            //costs[i-1][j]就是第i个房子刷颜色j的花费
                            min = Math.min(min,dp[1-m][x] + costs[i-1][j]);//dp[i-1][x]改为dp[1-m][x]
                        }
                    }
                    dp[m][j] = min;//这里就是m而不是i了
                }
            }

            //最后，再来找出粉刷前n个房子，且最后一个房子刷哪种颜色的花费是最小的，就是最后的答案
            for (int i = 0; i < k; i++) {
                result = Math.min(result,dp[m][i]);//dp[n][i]改为dp[m][i]
            }

            return result;
        }
    }

    /**
     *     作者：hawi-2
     *     链接：https://leetcode-cn.com/problems/paint-house-ii/solution/java-shi-jian-onk-kong-jian-o1-by-hawi-2/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution3 {
        /**
         * 动态规划，可以用滚动数组来省空间，甚至更省一点，
         * 只记录 [最小花费，第二小花费，最小花费对应的颜色]，看代码：
         * （注：很多人的做法是用传进来的形参来做dp数组，从软件工程的角度上看太不规范了）
         * @param costs
         * @return
         */
        public int minCostII(int[][] costs) {
            if (costs.length == 0) {
                return 0;
            } else if (costs[0].length == 1) {
                return costs[0][0];
            }

            int k = costs[0].length;

            int minColour = -1;
            int minCost = 0;
            int secondMinCost = 0;
            for (int[] cost : costs) {
                int tmpMinColour = -1;
                int tmpMinCost = Integer.MAX_VALUE;
                int tmpSecondMinCost = Integer.MAX_VALUE;
                for (int j = 0; j < k; j++) {
                    int thisMinCost = cost[j] + (j == minColour ? secondMinCost : minCost);

                    if (thisMinCost < tmpMinCost) {
                        tmpSecondMinCost = tmpMinCost;
                        tmpMinCost = thisMinCost;
                        tmpMinColour = j;
                    } else if (thisMinCost < tmpSecondMinCost) {
                        tmpSecondMinCost = thisMinCost;
                    }
                }
                minCost = tmpMinCost;
                minColour = tmpMinColour;
                secondMinCost = tmpSecondMinCost;
            }

            return minCost;
        }
    }




}
