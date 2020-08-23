package GinkgoStack.P20_DynamicProgramming.SubSequence;

import java.util.Scanner;

/**
 * 浪潮的笔试题
 * 原型来源于codeforces，只是把车改成了沙滩石头
 * codeforces C. Sorting Railway Cars
 * 题意: 有一个1-n的排列(排列是指全是1-n中的数字,并且各不相同),
 * 每次可以任意从里面拿出来一个放在最前面或者最后面,求最小的次数把这个数列排好序.
 *
 * 分析: 开始想的是求这个排列中的最长递增子序列, 找到最长的一部分, 这一部分不用动, 只动别的部分就行,
 * 然而是不对的, 比如1 2 4 5 3就不符合,
 * 后来在帖子上(https://blog.csdn.net/liyunlong41/article/details/50410725#reply)
 * 找到答案： 最长递增子序列的相邻两个必须是差值为一. 这样才能保持不动, 然后动别的数
 * Sample test(s)
 * Input
 * 5
 * 4 1 2 5 3
 * Output
 * 2
 * Input
 * 4
 * 4 1 3 2
 * Output
 * 2
 */
public class SortingRailwayCars {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] dp = new int[n+1];//石头的半径最多为100
        int maxSub = 0;

        int i = 0;
        while (i< n && scanner.hasNextInt()){
            int digit = scanner.nextInt();
            dp[digit] = dp[digit-1]+1;
            maxSub = Integer.max(dp[digit],maxSub);
            i++;
        }

        System.out.print(n-maxSub);

    }
}



