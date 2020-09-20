package GinkgoStack.P20_DynamicProgramming.GameTypeDP;

/**
 * 486. 预测赢家
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
 * 然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。
 * 直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 *
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1, 5, 2]
 * 输出：False
 * 解释：一开始，玩家1可以从1和2中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。
 * 如果玩家 2 选择了 5 ，
 * 那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 False 。
 * 示例 2：
 *
 * 输入：[1, 5, 233, 7]
 * 输出：True
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，
 * 玩家 1 都可以选择 233 。
 *      最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 *
 *
 * 提示：
 *
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于 10000000 。
 * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 */
public class PredictWinner486 {

    class Solution{
        public boolean PredictTheWinner(int[] nums){
            //dp[i][j] 表示i到j的子数组，玩家1能拿到的最大分数
            int[][] dp = new int[nums.length][nums.length];
            int sum = 0;//nums所有元素的和

            //初始状态，长度为1的最大分数
            for (int i = 0; i < nums.length; i++) {
                 dp[i][i] = nums[i];
                 sum += nums[i];//顺便求和
            }
            //初始状态，长度为2的最大分数
            for (int j = 0; j < nums.length-1; j++) {
                dp[j][j+1] = Math.max(nums[j],nums[j+1]);
            }

            for (int len = 3; len <= nums.length; len++) {
                for (int i = 0; i + len -1 < nums.length; i++) {
                    int j = i + len -1;
                    //ifSelectLeft表示，玩家1选择了左边i,留给玩家2的区间是 i+1...j。然后：
                    //Math.min(dp[i+1][j-1],dp[i+2][j])什么意思呢？
                    //是从玩家2的角度来思考的，因为他们要博弈，而且按照自己的最有策略来做选择，玩家2当然希望他选择之后，
                    //剩下的子数组对玩家1最不利，因此是求min
                    //假设玩家2选了右边j，也就是留下 i+1...j-1供玩家1选择;
                    //假设玩家2选了左边i+1，也就是留下 i+2...j供玩家1选择。
                    int ifSelectLeft = nums[i] + Math.min(dp[i+1][j-1],dp[i+2][j]);
                    //假设玩家1选择右边，同理，之后
                    //假设玩家2选了右边j-1，也就是留下 i...j-2供玩家1选择;
                    //假设玩家2选了左边i，也就是留下 i+1...j-1供玩家1选择。
                    int ifSelectRight = nums[j] +  Math.min(dp[i][j-2],dp[i+1][j-1]);

                    dp[i][j] = Math.max(ifSelectLeft,ifSelectRight);//玩家1比较选哪边最有利
                }
            }

            //因为平手也算玩家1赢
            return dp[0][nums.length-1] * 2 >= sum;
        }
    }
}
