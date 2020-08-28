package GinkgoStack.P24_Enterprise.Tencent.qiu2020;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 暴力
 * a + b = n;
 * 使得a的数位之和 + b的数位之和， 最大值
 */
public class base2 {

    private static long bb(long n){
        long sum = 0;
        while (n>0){
            sum += n%10;
            n /= 10;
        }

        return sum;
    }

    private static long fun(long n){

        long max = Integer.MIN_VALUE;
        for (long i = 1;i <= n/2;i++){
            long tmp = bb(i) + bb(n-i);
            max = tmp > max?tmp:max;
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<Integer> res = new ArrayList<>(T);

        for (int i = 0;i<T;i++){
            long n = sc.nextLong();
            System.out.println(fun(n));
        }



    }

/**
 * C++
 * 找到小于N的最大的99……9然后拆分即可
 */
//    int n;
//
//    void solve() {
//        cin>>n;
//        int x = n;
//        int d = 0;
//        int ans = 0;
//        while(x>=10){
//            d *= 10;
//            d += 9;
//            x /= 10;
//            ans += 9;
//        }
//        int y = n-d;
//        while(y){
//            ans += y % 10;
//            y /= 10;
//        }
//        cout<<ans<<endl;
//
//    }
}
