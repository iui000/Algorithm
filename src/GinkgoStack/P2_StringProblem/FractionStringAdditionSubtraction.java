package GinkgoStack.P2_StringProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * 592. 分数加减运算
 * 给定一个表示分数加减运算表达式的字符串，你需要返回一个字符串形式的计算结果。 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 *
 * 示例 1:
 *
 * 输入:"-1/2+1/2"
 * 输出: "0/1"
 *  示例 2:
 *
 * 输入:"-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 *
 * 输入:"1/3-1/2"
 * 输出: "-1/6"
 * 示例 4:
 *
 * 输入:"5/3+1/3"
 * 输出: "2/1"
 * 说明:
 *
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 */
public class FractionStringAdditionSubtraction {
    /**
     * 方法二：逐个通分
     * 方法一存在一个缺点，就是当分母的范围较大时，将所有分数通分会导致得到的分母整数溢出
     * （虽然在这道题中，分母的范围是 [1, 10] 的正整数，不会出现溢出的情况），
     * 因此我们可以考虑每次只对两个分数进行通分，计算出它们相加或相减的最简结果后，
     * 再跟下一个分数进行通分并计算，以此类推。
     */
    public String fractionAddition(String expression) {
        List< Character > sign = new ArrayList< >();
        if (expression.charAt(0) != '-')
            sign.add('+');

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-')
                sign.add(expression.charAt(i));
        }

        //分子分母
        int prev_num = 0, prev_den = 1, i = 0;
        for (String sub: expression.split("(\\+)|(-)")) {
            if (sub.length() > 0) {
                String[] fraction = sub.split("/");
                int num = (Integer.parseInt(fraction[0]));
                int den = (Integer.parseInt(fraction[1]));
                int g = Math.abs(gcd(den, prev_den));

                //通分，计算分子
                if (sign.get(i++) == '+')
                    prev_num = prev_num * den / g + num * prev_den / g;
                else
                    prev_num = prev_num * den / g - num * prev_den / g;

                prev_den = den * prev_den / g;//计算分母

                //化简结果
                g = Math.abs(gcd(prev_den, prev_num));
                prev_num /= g;
                prev_den /= g;
            }
        }
        return prev_num + "/" + prev_den;
    }


    public int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

}
