package GinkgoStack.P20_DynamicProgramming;

/**
 * 剑指 Offer 60. n个骰子的点数（DP题，并不是简单题）
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。
 * 输入n，打印出s的所有可能的值出现的概率。
 *
 *
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中
 * 第 i 小的那个的概率。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,
 *          0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 *
 * 限制：
 *
 * 1 <= n <= 11
 */
public class ProbabilityOfSumOfNdiceOffer60 {

    /**
     * DP
     *
     * 根据动态规划的套路：从后往前考虑问题，假设已知前n-1的骰子的情况，那么第n个骰子的情况就好推导了。
     * 1.构造dp数组：tmpDP[]为n个骰子的点数和概率数组，dp[]为n-1个骰子的点数和概率数组，
     *              一个骰子的点数概率数组显然是6个六分之一,不需要另设数组。
     * 2.初始化dp数组：dp[]={1/6d,1/6d,1/6d,1/6d,1/6d,1/6d}，也就是一颗骰子的情况
     * 3.状态转移方程:tmpDP[x+y]+=dp[x]*num[y];

     * 我们假设n=2。
     * 则我们已知1的点数概率数组为{1/6d,1/6d,1/6d,1/6d,1/6d,1/6d}
     * 我们要求的2个骰子，可以分解为n-1个骰子和1个骰子
     * 同时易知2个骰子的点数概率数组长度为2*5+1。//i个骰子的点数和范围为[i,6i]，所以共有6i-i+1=5i+1个值
     *
     * 作者：zhi-xiong
     * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/solution/java-dong-tai-gui-hua-by-zhi-xiong/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     * @param n
     * @return
     */
    public double[] twoSum(int n) {
        //一个骰子的情况，也就是初值
        double dp[]={1/6d,1/6d,1/6d,1/6d,1/6d,1/6d};

        for(int i=2;i<=n;i++){
            //i个骰子的点数和范围为[i,6i]，所以共有6i-i+1=5i+1个值
            //由于骰子数量增加，因此点数和的个数也相应增加，重新开一个临时dp数组
            double tmpDP[]=new double[5*i  + 1];

            //直接从下标0开始
            for(int j = 0; j < dp.length;j++)
                for(int k=0;k<6;k++)//第i个骰子的取值有六种情况
                    //这里的1.0/6其实是num[k],只是一个骰子的情况都是1/6没必要按下标去索引。
                    tmpDP[j+k] += dp[j]*1.0/6;//点数和j+k

            //更新dp数组
            dp = tmpDP;
        }
        return dp;
    }


}