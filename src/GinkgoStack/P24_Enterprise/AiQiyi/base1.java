package GinkgoStack.P24_Enterprise.AiQiyi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * n! 末尾0的个数
 */
public class base1 {

    public static int Zeroes(int n) {
        int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print(Zeroes(n));
    }
}
