package GinkgoStack.P22_AnalysisInJava3Third.C10_AlgorithmDesign.DynamicProgramming;

public class eval {
    public static void main(String[] args){
        System.out.println(eval1(6));
        System.out.println(eval2(6));
        System.out.println(eval3(6));

    }

    /**
     * 递归解法
     * @param n
     * @return
     */
    public static double eval1(int n){
        if(n == 0){
            return 1.0;
        }
        else
        {
            double sum = 0.0;
            for(int i = 0;i<n;i++){
                sum += eval1(i);
            }
            return 2.0 * sum/n + n;
        }
    }

    /**
     * 动态规划
     * O（n^2）
     */
    public static double eval2(int n){
        double[] c = new double[n+1];
        c[0] = 1.0;
        for(int i=1;i <= n; i++)
        {
            double sum = 0.0;
            for(int j = 0;j<i;j++){
                sum += c[j];
            }
            c[i] = 2.0 * sum/i + i;
        }
        return c[n];
    }

    /**
     * 动态规划优化
     * O(N)
     */
    public static double eval3(int n){
        if(n == 0){
            return 1.0;
        }

        if(n == 1){
            return 2.0 * 1.0 + 1.0;
        }

        double sum = 1.0;//前面i-1的数据的和
        double current = 2.0 * 1.0 + 1.0;//当前值
        for(int i=2;i <= n; i++)
        {
            sum = sum + current;
            current = 2.0 * sum/i + i;
        }

        return current;
    }
}
