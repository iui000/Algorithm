package GinkgoStack.P24_Enterprise.BaiDuBestCoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * AC代码
 */
public class Permutation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T= scanner.nextInt();
        int t = 1;
        List<BigDecimal> list = new ArrayList<>(T);
        while (t<=T && scanner.hasNextInt()){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            BigDecimal res = new BigDecimal(0L);
            if(n != 1 || m!=0){
                int c = Math.min(n/2,m);

                BigDecimal total = new BigDecimal(n).multiply(new BigDecimal(n-1)).divide(new BigDecimal(2));
                n = n-c*2;
                BigDecimal s = new BigDecimal(n).multiply(new BigDecimal(n-1)).divide(new BigDecimal(2));
                res = total.subtract(s);
            }
            list.add(res);
            t++;
        }

        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }


}
