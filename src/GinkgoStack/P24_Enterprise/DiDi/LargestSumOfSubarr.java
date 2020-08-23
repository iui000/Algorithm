package GinkgoStack.P24_Enterprise.DiDi;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/5a304c109a544aef9b583dce23f5f5db
 * 来源：牛客网
 *
 * 连续最大和
 * 热度指数：39956时间限制：C/C++ 1秒，其他语言2秒空间限制：C/C++ 32M，其他语言64M
 * 算法知识视频讲解
 * 一个数组有 N 个元素，求连续子数组的最大和。 例如：[-1,2,1]，和最大的连续子数组为[2,1]，其和为 3
 *
 * 输入描述:
 * 输入为两行。 第一行一个整数n(1 <= n <= 100000)，表示一共有n个元素 第二行为n个数，即每个元素,每个整数都在32位int范围内。以空格分隔。
 *
 *
 * 输出描述:
 * 所有连续子数组中和最大的值。
 * 示例1
 * 输入
 * 3 -1 2 1
 * 输出
 * 3
 */
public class LargestSumOfSubarr {

    // 经典dp问题
    // 假设dp[n]表示以n为最后一个元素的子数组和的最大值，
    // 因此， dp[n] = max(dp[n-1],0)+num[n];
    // 当然实现的时候，没有必要设置一个数组保存所有的情况，因为只是用到了前一个位置的计算结果。
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while(sc.hasNext()){
            n = sc.nextInt();
            int[] num = new int[n];
            for(int i=0;i<n;i++){
                num[i] = sc.nextInt();
            }
            int max = num[0];
            int sum = num[0];
            for(int i=1;i<n;i++){
                if(sum>=0){
                    sum += num[i];
                }else{
                    sum=num[i];
                }
                if(sum>max)max=sum;
            }
            System.out.println(max);
        }
    }

}
