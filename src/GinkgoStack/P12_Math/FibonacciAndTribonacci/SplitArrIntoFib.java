package GinkgoStack.P12_Math.FibonacciAndTribonacci;

import java.util.ArrayList;
import java.util.List;


/**
 * 842. 将数组拆分成斐波那契序列
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 *
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 *
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 *
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 *
 *
 *
 * 示例 1：
 *
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 *
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 *
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 *
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 *
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 */
public class SplitArrIntoFib {

    /**
     * 回溯解法，性能没有DFS高
     * @param S
     * @param start
     * @param ans
     * @return
     */
    // 循环：从start 往后拆分
    // 剪枝：满足斐波那契数列条件、满足不以0开头（除非是0）
    // 结束：start==height ans内元素大于2个
    private boolean backtracking(String S, int start, List<Integer> ans) {
        if (start == S.length() && ans.size() > 2) {
            return true;
        }
        for (int i = start; i < S.length(); i++) {
            String segment = S.substring(start, i+1);
            // 剪枝
            // 数值超过范围
            if (Long.parseLong(segment) > Integer.MAX_VALUE) break;
            // 防止开头为 0
            if (!"0".equals(segment) && segment.startsWith("0")) break;
            if (isFibonacciSequence(Integer.valueOf(segment), ans)) {
                ans.add(Integer.valueOf(segment));
                // 找到一个结果就返回
                if (backtracking(S, i+1, ans)) return true;
                ans.remove(ans.size()-1);
            }
        }
        return false;
    }
    // 判断是否能组成斐波那契序列
    private boolean isFibonacciSequence(Integer num, List<Integer> ans) {
        int size = ans.size();
        if (size < 2) return true;
        return (ans.get(size-2) + ans.get(size-1) == num);
    }
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans =  new ArrayList<>();
        backtracking(S, 0, ans);
        return ans;
    }

    public static void main(String[] args) {
        String s = "123456579";
        SplitArrIntoFib demo = new SplitArrIntoFib();
        System.out.println(demo.splitIntoFibonacci(s));

    }
}
