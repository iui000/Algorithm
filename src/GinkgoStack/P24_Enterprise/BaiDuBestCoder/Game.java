package GinkgoStack.P24_Enterprise.BaiDuBestCoder;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T= scanner.nextInt();
        int t = 1;
        double a = 0.000000;

        while (t<=T){
            a = (double)(Math.round(scanner.nextFloat()*100000))/100000;
            if(1.25 * a > a){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
            t++;
        }
    }
}
