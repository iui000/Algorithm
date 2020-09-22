package GinkgoStack.Temporary;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        BigInteger m = BigInteger.valueOf(1_000_000_007);
        BigInteger aa =  BigInteger.valueOf(a).pow(b).mod(m);
        System.out.println(aa);

    }
}
