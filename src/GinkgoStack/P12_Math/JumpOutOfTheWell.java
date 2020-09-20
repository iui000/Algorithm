package GinkgoStack.P12_Math;

import java.util.Scanner;

public class JumpOutOfTheWell {

    /**
     * 京东笔试题
     * 虫子跳出枯井，白天上升n厘米，第i天晚上下降n/2 + ... + n/(2^i)
     *
     * 思路：
     * 把每天的实际上升量加起来得到公式：
     *
     * 注意，思考一下，最后一次跳出肯定是白天，因此是1 + 1 - 1/(2^x),即为2- 1/(2^x)
     *
     * n * (2 - 1/(2^x)) >= 100*m;
     * 然后二分这个x;
     *
     * x 的上限是 Math.sqrt(100*m - n)
     * 最后的答案就是 x +1
     *
     * @param n
     * @param m
     * @return
     */
    public static int fun(int n,int m) {
        double tar = m*100;
        int left = 1;
        int right = (int) Math.sqrt(tar - n);

        while (left <= right){
            int mid = (left + right)/2;
            double x = 2 - Math.pow(0.5,mid);
            if(n * x > tar)
                right = mid-1;
            if(n * x < tar)
                left = mid+1;
            if(n * x == tar)
                return left+1;
        }
        return left +1;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//厘米
        int m = scanner.nextInt();//米

        System.out.println(fun(n,m));
    }
}
