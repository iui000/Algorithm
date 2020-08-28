package GinkgoStack.P24_Enterprise.Tencent;
import java.util.*;
public class reverseArrAndSum {


    /**

     * 思路: 单纯数学规律，从第一个数字开始，每 2m 个数字之和为 m^2，
     * 总共有 n/2m 个这样的组合，因此和为 m*n/2

     */

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();
        System.out.println(process(n, m));
    }

    public static long process(long n, long m){
        if(n % (2* m) != 0)
            return-1;
        return m * (n / 2);
    }

}
