package GinkgoStack.P12_Math;

import java.util.Scanner;

/**
 * 网易笔试
 * 一个数拆分成数量最多的素数
 * AC
 */
public class SplitIntoPrime{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        long count = 0;

        for(int i = 0; i < n; i++){
            int tmp = sc.nextInt();
            if(tmp >= 2){
                count += tmp/2;
            }
        }

        System.out.print(count);

    }
}
