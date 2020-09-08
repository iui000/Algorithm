package GinkgoStack.P12_Math;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，
 * 则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * 进阶问题：大数n,long类型也装不下
 */
public class numberOfNdigit {

    /**
     * 原问题
     */
    class Solution1 {
        public int[] printNumbers(int n) {
            int end = (int)Math.pow(10, n) - 1;
            int[] res = new int[end];
            for(int i = 0; i < end; i++)
                res[i] = i + 1;
            return res;
        }
    }

    /**
     *
     * 进阶问题
     * 大数打印解法：
     * 实际上，本题的主要考点是大数越界情况下的打印。需要解决以下三个问题：
     *
     * 1. 表示大数的变量类型：
     * 无论是 short / int / long ... 任意变量类型，数字的取值范围都是有限的。
     * 因此，大数的表示应用字符串 String 类型。
     * 2. 生成数字的字符串集：
     * 使用 int 类型时，每轮可通过 +1+1 生成下个数字，而此方法无法应用至 String 类型。
     * 并且， String 类型的数字的进位操作效率较低，例如 "9999" 至 "10000" 需要从个位到千位循环判断，进位 4 次。
     *
     * 观察可知，生成的列表实际上是 nn 位 00 - 99 的 全排列 ，因此可避开进位操作，
     * 通过递归生成数字的 String 列表。
     *
     * 3. 递归生成全排列：
     * 基于分治算法的思想，先固定高位，向低位递归，当个位已被固定时，添加数字的字符串。
     * 例如当 n = 2n=2 时（数字范围 1 - 991−99 ），固定十位为 00 - 99 ，
     * 按顺序依次开启递归，固定个位 00 - 99 ，终止递归并添加数字字符串。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1-dao-zui-da-de-n-wei-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        StringBuilder res;
        int nine = 0, count = 0, start, n;
        char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public String printNumbers(int n) {
            this.n = n;
            res = new StringBuilder();
            num = new char[n];
            start = n - 1;//声明变量 start规定字符串的左边界，以保证添加的数字字符串 num[start:] 中无高位多余的 0。
            dfs(0);
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }
        void dfs(int x) {
            if(x == n) {
                String s = String.valueOf(num).substring(start);
                if(!s.equals("0")) res.append(s + ",");
                if(n - start == nine) start--;
                return;
            }
            for(char i : loop) {
                if(i == '9') nine++;//9的数量
                num[x] = i;
                dfs(x + 1);
            }
            nine--;
        }
    }



}
