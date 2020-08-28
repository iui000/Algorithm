package GinkgoStack.P24_Enterprise.Tencent;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/f3ab6fe72af34b71a2fd1d83304cbbb3
 * 来源：牛客网
 *
 * 小Q有X首长度为A的不同的歌和Y首长度为B的不同的歌，现在小Q想用这些歌组成一个总长度正好为K的歌单，每首歌最多只能在歌单中出现一次，在不考虑歌单内歌曲的先后顺序的情况下，请问有多少种组成歌单的方法。
 *
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含一个整数，表示歌单的总长度K(1<=K<=1000)。
 * 接下来的一行包含四个正整数，分别表示歌的第一种长度A(A<=10)和
 * 数量X(X<=100)以及歌的第二种长度B(B<=10)和数量Y(Y<=100)。保证A不等于B。
 *
 *
 * 输出描述:
 * 输出一个整数,表示组成歌单的方法取模。因为答案可能会很大,输出对1000000007取模的结果。
 * 示例1
 * 输入
 * 5
 * 2 3 3 3
 * 输出
 * 9
 */
public class songList {


    /**
     * 01背包变形
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        int a = sc.nextInt();
        int x = sc.nextInt();
        int b = sc.nextInt();
        int y = sc.nextInt();

        int mod = 1_000_000_007;
        int len = x+y;
        int[] arr = new int[len];
        Arrays.fill(arr,0,x,a);
        Arrays.fill(arr,x,len,b);

        int[] dp = new int[k+1];//dp[i]表示选取前i首歌的方案数量
        dp[0] = 1;
        for (int i = 0;i<len;i++){
            for (int j = k;j>= arr[i];j--){
                dp[j] = (dp[j]%mod + dp[j-arr[i]]%mod )%mod;
            }
        }


        System.out.println(dp[k]);
    }
}
