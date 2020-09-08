package GinkgoStack.P12_Math;


import java.util.HashMap;
import java.util.Map;

/**
 * 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 示例 1:
 *
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 *
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 *
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 */
public class fractionToDecimal166 {

    /**
     * 概要
     * 这是一道非常直观的代码题，但需要考虑很多细节。
     *
     * 要点
     * 不需要复杂的数学知识，只需要数学的基本知识。了解长除法的运算规则。
     * 使用长除法计算4/9，循环节很显然就会找到。那么计算4/333呢，能找到规律吗？
     * 注意边界情况！列出所有你可以想到的测试数据并验证你的代码。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/fen-shu-dao-xiao-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if ( (numerator < 0) ^ denominator < 0) {//布尔值居然可以和整型异或，很巧妙
            fraction.append("-");
        }


        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));//被除数，分子
        long divisor = Math.abs(Long.valueOf(denominator));//除数，分母

        fraction.append(dividend / divisor);//取得整数部分
        long remainder = dividend % divisor;//余数
        if (remainder == 0) {//整除了
            return fraction.toString();
        }

        /**
         * 需要用一个哈希表记录余数出现在小数部分的位置，当你发现已经出现的余数，
         * 就可以将重复出现的小数部分用括号括起来。
         *
         * 再出发过程中余数可能为 0，意味着不会出现循环小数，立刻停止程序。
         *
         * 就像 两数相除 问题一样，要考虑负分数以及极端情况
         *
         */
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();//余数 -> 出现的位置
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                //insert(int offset, char c) 在此序列中插入 char参数的字符串表示形式。
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            //为下一轮除法做准备
            remainder *= 10;
            fraction.append(remainder / divisor);
            remainder %= divisor;
        }

        return fraction.toString();
    }

}
