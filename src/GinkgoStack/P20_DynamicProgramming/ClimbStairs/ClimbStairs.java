package GinkgoStack.P20_DynamicProgramming.ClimbStairs;


/**
 * 简单的青蛙跳台阶
 */
public class ClimbStairs {
    public static void main(String[] args) {

    }
    public static int climbStairs(int n) {
        if(n == 0 || n==1){
            return 1;
        }
        if(n== 2){
            return 2;
        }

        int pre2 = 1;
        int pre1 = 2;
        int ways = 0;
        int mo = 1000000007;
        for(int i = 3; i <= n;i++){
            ways = pre2 + pre1;
            pre2 = pre1;
            pre1 = ways;
        }

        return ways;
    }
}
