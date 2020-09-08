package GinkgoStack.P24_Enterprise.BaiDu;

import java.util.HashMap;
import java.util.Scanner;


/**
 * 数论题
 * 从那张卡片中（只有0和5），随意选取任意张0和5的卡片，形成一个数，能被90整除，要求这个数是最大的.
 *
 * 这道题的来源：codeForces 和 51nod
 * http://www.51nod.com/Challenge/Problem.html#problemId=1433
 * 小K手中有n张牌，每张牌上有一个一位数的数，这个字数不是0就是5。小K从这些牌在抽出任意张（不能抽0张），
 * 排成一行这样就组成了一个数。使得这个数尽可能大，而且可以被90整除。
 *
 * 注意：
 *
 * 1.这个数没有前导0，
 *
 * 2.小K不需要使用所有的牌。
 *
 *
 * Input每个测试数据输入共2行。
 * 第一行给出一个n，表示n张牌。(1<=n<=1000)
 * 第二行给出n个整数a,a,a,…,a (a是0或5 ) 表示牌上的数字。Output共一行，
 * 表示由所给牌组成的可以被90整除的最大的数，如果没有答案则输出”-1”（没有引号）
 * Sample Input
 * 4
 * 5 0 5 0
 * Sample Output
 * 0
 *
 * 帖子解答：
 * https://www.cnblogs.com/kirai/p/5998445.html
 * https://blog.csdn.net/obsorb_knowledge/article/details/79960301
 */

public class ExactlyDivisibleBy90 {


    /**
     * 数学知识：
     *
     * 某数字的数位和是3或9的倍数，那么它就可以被3或9整除。
     * 原因：
     * 假设这是两位数ab,把它写成10a+b,它的各位数字之和是a+b,两式相减,得9a,
     * 因为9a能被3整除,所以如果a+b能被3整除,那么ab也能被3整除.
     *
     * 假设这是三位数abc,把它写成100a+10b+c,它的各位数字之和是a+b+c,
     * 两式相减,得99a+9b,因为99a+9b能被3整除,所以如果a+b+c能被3整除,那么abc也能被3整除.
     * 多位数以此类推就行，
     *
     * 9和3一个道理;
     * 对于这道题：
     * 找到尽可能多的5，其和是9倍数，后面全补0，也就是有多少个0就补多少个0。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int N = scanner.nextInt();
        int[] arr = new int[N];

        int sum = 0;
        int count0 = 0;//0的个数
        int k = 0,memSum = -1;

        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];

            if(arr[i] == 0){
                count0++;
            }

            if(sum % 9 == 0){
                memSum = sum;
            }
        }

        if(memSum > 0 && count0 > 0){
            k = memSum/5;
            for (int i = 0; i < k; i++) {
                System.out.print("5");
            }
            for (int i = 0; i < count0; i++) {
                System.out.print("0");
            }
        }else if(count0 > 0){
            System.out.println("0");
        }else {
            System.out.println("-1\n");
        }

    }




}

