package GinkgoStack.P20_DynamicProgramming;


/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class frogJumpsSteps {
    /**
     * 解题思路：
     * 此类求 多少种可能性 的题目一般都有 递推性质 ，即 primeFactor(n)primeFactor(n) 和 primeFactor(n-1)primeFactor(n−1)…primeFactor(1)primeFactor(1) 之间是有联系的。
     *
     * 设跳上 nn 级台阶有 primeFactor(n)primeFactor(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 11 级或 22 级台阶。
     * 当为 11 级台阶： 剩 n-1n−1 个台阶，此情况共有 primeFactor(n-1)primeFactor(n−1) 种跳法；
     * 当为 22 级台阶： 剩 n-2n−2 个台阶，此情况共有 primeFactor(n-2)primeFactor(n−2) 种跳法。
     * primeFactor(n)primeFactor(n) 为以上两种情况之和，即 primeFactor(n)=primeFactor(n-1)+primeFactor(n-2)primeFactor(n)=primeFactor(n−1)+primeFactor(n−2) ，以上递推性质为斐波那契数列。本题可转化为 求斐波那契数列第 nn 项的值 ，与 面试题10- I. 斐波那契数列 等价，唯一的不同在于起始数字不同。
     * 青蛙跳台阶问题： primeFactor(0)=1f(0)=1 , primeFactor(1)=1f(1)=1 , primeFactor(2)=2f(2)=2 ；
     * 斐波那契数列问题： primeFactor(0)=0f(0)=0 , primeFactor(1)=1f(1)=1 , primeFactor(2)=1f(2)=1 。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/solution/mian-shi-ti-10-ii-qing-wa-tiao-tai-jie-wen-ti-dong/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int numWays(int n) {
            int a = 1, b = 1, sum;
            for(int i = 0; i < n; i++){
                sum = (a + b) % 1000000007;
                a = b;
                b = sum;
            }
            return a;
        }
    }
}
