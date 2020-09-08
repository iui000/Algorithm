package GinkgoStack.P12_Math;

import java.util.Scanner;

/**
 * 京东笔试
 * AC代码
 * 求一个数列：
 * primeFactor(n) = 1/5 - 1/10 + 1/15 -1/20 + ... + 1/(5*(2*n-1)) - 1/(5*2*n)
 */
public class sequenceSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        double ans = 0.0;
        for(int i = 1; i <= 2*n; i++){
            if(i%2 != 0){//
                ans += 1.0/(5.0*i);
            }else {
                ans -= 1.0/(5.0*i);
            }
        }

        System.out.print(String.format("%.4f", ans));
    }
}
