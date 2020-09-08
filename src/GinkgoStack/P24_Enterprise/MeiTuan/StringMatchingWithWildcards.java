package GinkgoStack.P24_Enterprise.MeiTuan;

/**
 * 字符串模式匹配
 * 类似题目：剑指 Offer 19. 正则表达式匹配
 * 相同题目：
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 给出两个字符串，分别是模式串P和目标串T，判断模式串和目标串是否匹配，匹配输出 1，不匹配输出 0。
 * 模式串中‘？’可以匹配目标串中的任何字符，模式串中的 ’*’可以匹配目标串中的任何长度的串，
 * 模式串的其它字符必须和目标串的字符匹配。例如P=a?b，T=acb，则P 和 T 匹配。
 *
 *
 * 输入描述:
 * 输入第一行包含一个字符串p， (1 ≤ |p| ≤ 20).
 *
 * 输入第二行包含一个字符串t， (1 ≤ |t| ≤ 20).
 *
 *
 * 输出描述:
 * 输出仅包含0和1的整数，0表示p和t不匹配，1表示p和t匹配。
 *
 *
 * 输入例子1:
 * a?b
 * ab
 *
 * 输出例子1:
 * 0
 *
 * 输入例子2:
 * a*b
 * ab
 *
 * 输出例子2:
 * 1
 *
 * 输入例子3:
 * a*b
 * a(cb
 *
 * 输出例子3:
 * 1
 */
import java.util.regex.Pattern;
import java.util.*;
public class StringMatchingWithWildcards {

    /**
     *
     * 先将模式串按照？和*进行分割
     * 1.KMP 找出所有通配符前面的子串 在 目标串中的多个起始位置
     * 2.然后在其多个结束位置之后，分别去匹配后序的字符
     * 遇到？就直接下一个字母；
     * 遇到*就将，模式串中*后面的子串截取subString，KMP去找subString，
     * 如果找到的的子串的起始位置不在当前位置之后，那么这个子串就不算，继续下一个子串
     */


    /**
     * 思路2
     * 应该可以根据模式串，写出对应的正则表达式
     * 然后直接根据正则表达式调用API来判断
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String p = scanner.nextLine();
        String t = scanner.nextLine();

        if (Pattern.matches(p.replace("*",".*").replace("?","."),t)){
            System.out.println(1);
        }else {
            System.out.println(0);
        }
    }

    /**
     * 思路3，AC
     * dp
     */
    public static void dp(String[] args){
        Scanner sc = new Scanner(System.in);
        String p = sc.next();
        String s = sc.next();
        // System.out.println(s + " - " + p);
        int m = s.length(), n = p.length();

        //dp[i][j]表示长度为j的模式串 能否 与长度为i的目标串匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for(int i = 1; i <= n; i++) //初始化第一行，根据模式串自身的性质
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';

        for(int i = 1; i <= m; i++){//原串
            for(int j = 1; j <= n; j++){//模式串
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        System.out.println((dp[m][n] ? 1 : 0));
    }


}


