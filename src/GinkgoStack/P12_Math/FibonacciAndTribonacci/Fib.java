package GinkgoStack.P12_Math.FibonacciAndTribonacci;

/**
 * 动态规划
 * 空间压缩
 */
public class Fib {

    public int FibN(int n) {
        int first = 0;
        int second = 1;
        int cur = 1;

        for(int i = 0;i<n;i++){
            cur = (first + second) % 1_000_000_007;
            first = second;
            second = cur;
        }

        return first;
    }

}
