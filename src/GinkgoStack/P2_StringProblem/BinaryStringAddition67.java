package GinkgoStack.P2_StringProblem;

/**
 *
 * 67. 二进制求和
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class BinaryStringAddition67 {

    /**
     *     作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    /**
     * 「列竖式」的方法，末尾对齐，逐位相加。在十进制的计算中「逢十进一」，二进制中我们需要「逢二进一」。
     */
    class Solution {
        public String addBinary(String a, String b) {
            StringBuffer ans = new StringBuffer();

            int n = Math.max(a.length(), b.length()), carry = 0;
            for (int i = 0; i < n; ++i) {
                carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
                carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
                ans.append((char) (carry % 2 + '0'));
                carry /= 2;
            }

            if (carry > 0) {
                ans.append('1');
            }
            ans.reverse();

            return ans.toString();
        }
    }

    /**
     * 这个方法不好，长度会溢出
     *
     */
    class Solution2 {
        public String addBinary(String a, String b) {
            return Integer.toBinaryString(
                    Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
            );
        }
    }

    /**
     * 位运算
     * 思路和算法
     *
     * 如果不允许使用加减乘除，则可以使用位运算替代上述运算中的一些加减乘除的操作。
     *
     * 如果不了解位运算，可以先了解位运算并尝试练习以下题目：
     *
     * 只出现一次的数字 II
     * 只出现一次的数字 III
     * 数组中两个数的最大异或值
     * 重复的DNA序列
     * 最大单词长度乘积
     * 我们可以设计这样的算法来计算：
     *
     * 把 aa 和 bb 转换成整型数字 xx 和 yy，在接下来的过程中，xx 保存结果，yy 保存进位。
     * 当进位不为 00 时
     * 计算当前 xx 和 yy 的无进位相加结果：answer = x ^ y
     * 计算当前 xx 和 yy 的进位：carry = (x & y) << 1
     * 完成本次循环，更新 x = answer，y = carry
     * 返回 xx 的二进制形式
     * 为什么这个方法是可行的呢？在第一轮计算中，answer 的最后一位是 xx 和 yy 相加之后的结果，carry 的倒数第二位是 xx 和 yy 最后一位相加的进位。接着每一轮中，由于 carry 是由 xx 和 yy 按位与并且左移得到的，那么最后会补零，所以在下面计算的过程中后面的数位不受影响，而每一轮都可以得到一个低 ii 位的答案和它向低 i + 1i+1 位的进位，也就模拟了加法的过程。
     *
     * 代码
     *
     * Python3
     *
     * class Solution:
     *     def addBinary(self, a, b) -> str:
     *         x, y = int(a, 2), int(b, 2)
     *         while y:
     *             answer = x ^ y
     *             carry = (x & y) << 1
     *             x, y = answer, carry
     *         return bin(x)[2:]
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */





}
