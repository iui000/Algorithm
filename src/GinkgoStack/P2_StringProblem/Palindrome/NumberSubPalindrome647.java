package GinkgoStack.P2_StringProblem.Palindrome;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 *
 * 输入的字符串长度不会超过 1000 。
 */
public class NumberSubPalindrome647 {

    /**
     * 方法一：中心拓展
     * 枚举每一个可能的回文中心，然后用两个指针分别向左右两边拓展，
     * 当两个指针指向的元素相同的时候就拓展，否则停止拓展。
     * 枚举回文中心的是 O(n) 的，对于每个回文中心拓展的次数也是 O(n)的，
     * 所以时间复杂度是 O(n^2)
     */

    /**
     * 中心拓展，说白了，就是挨个遍历，只不过，中心可能是1个字符也可能是2个字符而已.
     * 在实现的时候，我们需要处理一个问题，即如何有序地枚举所有可能的回文中心，
     * 我们需要考虑回文长度是奇数和回文长度是偶数的两种情况。如果回文长度是奇数，
     * 那么回文中心是一个字符；如果回文长度是偶数，那么中心是两个字符。
     * 当然你可以做两次循环来分别枚举奇数长度和偶数长度的回文，
     * 但是我们也可以用一个循环搞定。
     *我们不妨写一组出来观察观察，假设 n = 4n=4，我们可以把可能的回文中心列出来：
     * 编号 i	回文中心左起始位置 l_i 回文中心右起始位置 r_ir
     * ​
     *
     * 0	0	0
     * 1	0	1
     * 2	1	1
     * 3	1	2
     * 4	2	2
     * 5	2	3
     * 6	3	3
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/palindromic-substrings/solution/hui-wen-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */


    public int countSubstrings1(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }


    /**
     * 中心拓展，说白了，就是挨个遍历，只不过，中心可能是1个字符也可能是2个字符而已，
     *
     * 不可能出现3个字符作为中心的情况，因为3个字符作为中心的话，他就是回文了，等于1个字符作为中心的情况
     *
     * 我觉得下面的代码更好理解一点
     * @param s
     * @return
     */

    int countSubstrings2(String s) {
        int num = 0;
        int n = s.length();
        for(int i=0;i<n;i++)//遍历回文中心点
        {
            for(int j=0;j<=1;j++)//j=0,中心是一个点，j=1,中心是两个点
            {
                int l = i;
                int r = i+j;
                while(l>=0 && r<n && s.charAt(l--)==s.charAt(r++))
                    num++;
            }
        }
        return num;
    }


    /**
     *
     */
    class Solution {
        public int countSubstrings(String s) {
            int n = s.length();
            StringBuffer t = new StringBuffer("$#");
            for (int i = 0; i < n; ++i) {
                t.append(s.charAt(i));
                t.append('#');
            }
            n = t.length();
            t.append('!');

            int[] f = new int[n];
            int iMax = 0, rMax = 0, ans = 0;
            for (int i = 1; i < n; ++i) {
                // 初始化 f[i]
                f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
                // 中心拓展
                while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                    ++f[i];
                }
                // 动态维护 iMax 和 rMax
                if (i + f[i] - 1 > rMax) {
                    iMax = i;
                    rMax = i + f[i] - 1;
                }
                // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
                ans += f[i] / 2;
            }

            return ans;
        }
    }




}
