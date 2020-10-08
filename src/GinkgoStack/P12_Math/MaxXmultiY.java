package GinkgoStack.P12_Math;


import java.util.Scanner;

public class MaxXmultiY {

    /**
     *
     * 1 <= x <= A;（A，B,a,b均<=2e9 ）
     * 1 <= y <= B;
     * x,y满足条件:
     * x/y = a/b;
     * 求x*y最大的x和y值。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        if(a > b*A){
            System.out.print(0 + " "+0);
            return;
        }
        int left = 1;
        int right = A/a;
        while (left < right){
            int mid = left + (right - left)/2;
            if(mid * a > A || mid * b > B){
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        if(left * a > A || left * b > B){
            System.out.print(0 + " "+0);
            return;
        }
        System.out.println(left * a +" "+ left * b);
    }
}
