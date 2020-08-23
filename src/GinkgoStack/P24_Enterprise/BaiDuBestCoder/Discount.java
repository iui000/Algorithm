package GinkgoStack.P24_Enterprise.BaiDuBestCoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 算法应该是对的，
 * 当时提交时，输出格式忘记加小数点位数了，应该是因为这个判错的
 */
public class Discount {
    private static long gcd(long m,long n) {
        if(m<n) {
            long k=m;
            m=n;
            n=k;
        }
        return m%n == 0?n:gcd(n,m%n);
    }

    private static void getMax(long[] arr,long to,long bo){
        long g = gcd(to,bo);

        if(arr[0]*bo/g < to*arr[1]/g){
            arr[0] = to;
            arr[1] = bo;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T= scanner.nextInt();
        int t = 1;
        List<Double> list = new ArrayList<>(T);
        while (t<=T ){
            double max = 0.000000;
            long[] res = new long[2];
            int n = scanner.nextInt();
            int i = 0;
            int[] b = new int[n];
            double[] c = new double[n];
            while (i<n){
                b[i] = scanner.nextInt();
                c[i] = (double)(Math.round(scanner.nextFloat()*100))/100;
                long to = (long) ((1F-c[i])*100);
                long bo = (long) ((1.0F+(double)b[i]-c[i])*100);
                if(i == 0){
                    res[0] = to;
                    res[1] = bo;
                }else {
                    getMax(res,to,bo);
                }
                i++;
            }
            max = (double)(Math.round((double) res[0]/(double)res[1]*100000))/100000;

            list.add(max);
            t++;
        }
        for(int i = 0;i<list.size();i++){
            //
            System.out.println(String.format("%.5f", list.get(i)));
        }
    }
}
