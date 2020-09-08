package GinkgoStack.P12_Math.Power;

/**
 * 剑指 Offer 16. 数值的整数次方
 * 实现函数double Power(double base, int exponent)，
 * 求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 */
public class DesignPower {

    /**
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        /**
         * 快速幂解析（二分法角度）
         * @param x
         * @param n
         * @return
         */
        public double myPow(double x, int n) {
            if(x == 0)
                return 0;

            /**
             * Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，
             * 因此当 n = -2147483648n=−2147483648 时执行 n = -n
             * 会因越界而赋值出错。解决方法是先将 n 存入 long 变量 b ，
             * 后面用 b操作即可。
             *
             * 作者：jyd
             * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
             * 来源：力扣（LeetCode）
             * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
             */
            long b = n;
            double res = 1.0;

            //考虑负数
            if(b < 0) {
                x = 1 / x;
                b = -b;
            }
            while(b > 0) {
                if((b & 1) == 1) //取余数 n % 2等价于 判断二进制最右一位值 n & 1；
                    res *= x;

                x *= x;
                b >>= 1;//向下整除 n // 2 等价于 右移一位 n >> 1；
            }
            return res;
        }
    }



}
