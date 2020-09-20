package GinkgoStack.P12_Math;

/**
 * 剑指 Offer 43. 1～n整数中1出现的次数
 * 233. 数字 1 的个数
 *
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：6
 *
 *
 * 限制：
 *
 * 1 <= n < 2^31
 */
public class OccurrencesOf1IntegerFrom1ToN233andOffer43 {

    /**
     * 解法1：数学找规律
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution {
        public int countDigitOne(int n) {

            //设计按照 “个位、十位、...” 的顺序计算，则 high / cur / low / digit应初始化为：
            int digit = 1, res = 0;
            int high = n / 10, cur = n % 10, low = 0;

            /**
             * while high != 0 or cur != 0: # 当 high 和 cur 同时为 0 时，说明已经越过最高位，因此跳出
             *    low += cur * digit # 将 cur 加入 low ，组成下轮 low
             *    cur = high % 10 # 下轮 cur 是本轮 high 的最低位
             *    high //= 10 # 将本轮 high 最低位删除，得到下轮 high
             *    digit *= 10 # 位因子每轮 × 10
             */

            while(high != 0 || cur != 0) {
                //当 cur = 0时： 此位 1的出现次数只由高位 high决定，计算公式为：high * digit
                if(cur == 0)
                    res += high * digit;
                //当 cur = 1 时： 此位 1的出现次数由高位 high和低位 low决定，计算公式为high * digit + low + 1
                else if(cur == 1)
                    res += high * digit + low + 1;
                //当cur=2,3,⋯,9 时： 此位 1的出现次数只由高位 high决定，计算公式为(high + 1) * digit
                else
                    res += (high + 1) * digit;


                low += cur * digit;
                cur = high % 10;
                high /= 10;
                digit *= 10;
            }
            return res;
        }
    }

    /**
     * 解法2：
     * 数位dp
     * 先来个一模一样的题：求 1-n中含有49的数的个数（其实还是有点区别的，可以改成统计49出现的次数）
     *
     * 因此我们需要一个叫作数位dp的东西。
     *
     * 数位dp可以用来求区间 [l,r]内满足某一性质的数的个数。
     * 一般这个范围会很大，暴力做法过不了，所以要在此基础上进行一些优化。
     *
     * 作者：weierstras
     * 链接：https://leetcode-cn.com/problems/number-of-digit-one/solution/shu-wei-dp-by-weierstras/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */




}
