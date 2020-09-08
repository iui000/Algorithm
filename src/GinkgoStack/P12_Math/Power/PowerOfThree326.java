package GinkgoStack.P12_Math.Power;


/**
 * 326. 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 27
 * 输出: true
 * 示例 2:
 *
 * 输入: 0
 * 输出: false
 * 示例 3:
 *
 * 输入: 9
 * 输出: true
 * 示例 4:
 *
 * 输入: 45
 * 输出: false
 */
public class PowerOfThree326 {
    /**
     * 方法一：循环迭代
     * 找出数字 n 是否是数字 b 的幂的一个简单方法是，n%3 只要余数为 0，就一直将 n 除以 b。
     *
     * 因此，应该可以将 n 除以 b x 次，每次都有 0 的余数，最终结果是 1。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public class Solution1 {
        public boolean isPowerOfThree(int n) {
            if (n < 1) {
                return false;
            }

            while (n % 3 == 0) {
                n /= 3;
            }

            return n == 1;
        }
    }


    /**
     * 在 Java 中，我们通过取小数部分（利用 % 1）来检查数字是否是整数，并检查它是否是 0。
     */
    public class Solution {
        final double epsilon =  Math.E;
        public boolean isPowerOfThree(int n) {
//            return (Math.log10(n) / Math.log10(3)) % 1 == 0;
            return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
        }
    }




}
