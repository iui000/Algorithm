package GinkgoStack.P17_BitOperation;

import java.util.HashMap;
import java.util.Scanner;

/**
 * xor
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 64M，其他语言128M
 *
 * 给出n个数字 a_1,...,a_n，问最多有多少不重叠的非空区间，使得每个区间内数字的xor都等于0。
 *
 * 输入描述:
 * 第一行一个整数ｎ； 第二行ｎ个整数　a_1,...,a_n； 对于30%的数据，n<=20；
 * 对于100%的数据，n<=100000, a_i<=100000；
 *
 * 输出描述:
 * 一个整数表示最多的区间个数；
 *
 * 输入例子1:
 * 4
 * 3 0 2 2
 *
 * 输出例子1:
 * 2
 *
 * 说明
 * [0] xor = 0，[2,2] 2 xor 2 = 0，所以总共是2个不重叠的非空区间
 */
public class subArrXORisZero {


    /**
     * 自己的AC解法（也是正解）
     * 解法：前缀异或值 + HashMap （利用一个性质：x异或0 = x ,而且异或运算 有交换律和结合律）
     * 类似：560 和为K的子数组的个数（前缀和+ HashMap）
     * @param arr
     * @return
     */
    private static int XORisZero(int[] arr){
        if(arr == null || arr.length == 0){
            return  0;
        }
        /**
         * 用map存异或值，记录与当前异或值相同的最长前缀的位置，用空间换时间;
         * 因为题目要求非重叠区间（可以借助dp或者设置一个边界值，来保证这一点）,而且n的取值范围为100000，必须用Hash才行
         */
        HashMap<Integer,Integer> XORandIndexMap = new HashMap<>();
        int range = -1;//当前最后一个 异或值为0的子数组 的右边界
        XORandIndexMap.put(0,range);//这样做是为了应对，第一个异或值为0的子数组，如果不设置为-1，就会漏掉第一个。
        if(arr[0] == 0) //必须要考虑第一个数就0的情况
            XORandIndexMap.put(arr[0],0);

        int xor = 0;//前缀异或值
        int ans = 0;
        for(int i = 0;i<arr.length;i++){
            xor ^= arr[i];
            if(XORandIndexMap.containsKey(xor)){
                if(XORandIndexMap.get(xor) >= range){
                    ans++;
                    range = i;
                }
            }
            XORandIndexMap.put(xor,i);
        }

        return ans;
    }

    /**
     * 下面用DP辅助 的解法,也能AC
     * 参考自：https://www.nowcoder.com/questionTerminal/7cffea0c097c4337821ab3ba25447c1c
     * @param arr
     * @return
     */
    private static int XORisZeroWithDP(int[] arr){
        if(arr == null || arr.length == 0){
            return  0;
        }
        /**
         * 用map存异或值，记录与当前异或值相同的最长前缀的位置，用空间换时间;
         * 因为题目要求非重叠区间（可以借助dp或者设置一个边界值，来保证这一点）,而且n的取值范围为100000，必须用Hash才行
         */
        HashMap<Integer,Integer> XORandIndexMap = new HashMap<>();

        XORandIndexMap.put(0,-1);
        XORandIndexMap.put(arr[0],0);
        int[] dp = new int[arr.length];//dp[i]表示在下标i及之前，有多少个子数组，其异或值为0
        dp[0] =  arr[0] == 0 ? 1 : 0;

        int xor = 0;//前缀异或值
        for(int i = 1;i<arr.length;i++){
            xor ^= arr[i];
            if(XORandIndexMap.containsKey(xor)){
                  int preEorIndex =XORandIndexMap.get(xor);
                  dp[i] = preEorIndex == -1 ? 1 : (dp[preEorIndex] + 1);
            }
            dp[i] = Math.max(dp[i - 1], dp[i]);
            XORandIndexMap.put(xor,i);
        }

        return dp[arr.length-1];
    }

    /**
     * 4
     * 3 0 2 2
     *
     * 测试用例
     * 10
     * 9 1 6 10 4 8 5 0 3 10
     * <br/>对应输出应该为:<br/>
     * 2
     *
     *
20
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new  int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(XORisZero(arr));
        System.out.println(XORisZeroWithDP(arr));
    }


}
