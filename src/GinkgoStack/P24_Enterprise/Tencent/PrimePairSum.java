package GinkgoStack.P24_Enterprise.Tencent;

import java.util.Arrays;

/**
 *  腾讯2017秋招笔试编程题 企业提供原题00:26:38
 * 3/4
 * [编程题]素数对
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 给定一个正整数，编写程序计算有多少对质数的和等于输入的这个正整数，并输出结果。输入值小于1000。
 * 如，输入为10, 程序应该输出结果为2。（共有两对质数的和为10,分别为(5,5),(3,7)）
 *
 * 输入描述:
 * 输入包括一个整数n,(3 ≤ n < 1000)
 *
 * 输出描述:
 * 输出对数
 *
 * 输入例子1:
 * 10
 *
 * 输出例子1:
 * 2
 */
public class PrimePairSum {

    int countPrimes(int n) {//计算以内有多少个素数
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]){
                //可以稍微优化一下，让 j 从 i 的平方开始遍历，而不是从 2 * i 开始。
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }
}
