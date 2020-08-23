package GinkgoStack.P20_DynamicProgramming.LinearDP.knapsackProblem;

/**
 * 三种背包问题
 *
 * 以及他们的变形转化
 */
public class MaxValueKnapsack {

    public static void main(String[] args) {

        int[][] items = new int[][]{
                {3000,4},//价值，大小
                {1500,1},
                {2000,3},
                {2000,1}
        };
        //0-1背包问题
        System.out.println( Knapsack01(items,4));//4000
        System.out.println( Knapsack01Optimize(items,4));//4000

        float[][] floatItems = new float[][]{
                {3000f,4.0f},//大小为小数，可能是0.5
                {1500f,1.0f},
                {2000f,3.0f},
                {2000f,1.0f}
        };
        //物品大小为浮点数的0-1背包问题
        System.out.println( Knapsack01ButFloatSize(floatItems,4.5f));//4000.0


        //完全背包问题
        System.out.println( CompleteKnapsack(items,4));//应该为8000

        //多重背包问题
        int[][] itemsWithCount = new int[][]{
                {3000,4,3},//价值，大小,数量
                {1500,1,1},
                {2000,3,5},
                {2000,1,2}
        };
        System.out.println( MultipleKnapsack(itemsWithCount,4));//应该为5500

    }

    /**
     * 0/1背包问题 每个物品都有选择和不选择两种情况，并且不能重复选某一个物品
     * 第一种解法，dp[][]二维数组 时间复杂度O(n*m),空间复杂度O(n*m)
     * @param items 每个物品的价值和占据的容量,[val,size]
     * @param m 背包的容量
     * @return
     */
    public static int Knapsack01(int[][] items,int m) {
        int n = items.length;
        if(n == 0 || m == 0){
            return 0;
        }
        int[][] dp = new int[n+1][m+1];
        int remainingCapacity = 0;
        int theValueIfSelected = 0;
        for(int i = 1;i<=n;i++){//每个物品
            for(int j=1;j <= m;j++){//背包的大小
                /**
                 * 背包当前大小j，
                 * 如果偷这个物品，则最大的价值就是：物品的价值 + 剩余容量能够装下的最大价值；
                 * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                 */
                if(j >= items[i-1][1]){//如果当前的背包大小大于等于该物品的大小，才有机会选择这个物品
                    remainingCapacity =  j-items[i-1][1];//剩余容量
                    theValueIfSelected = items[i-1][0] + dp[i-1][remainingCapacity];
                }else {
                    theValueIfSelected = 0;
                }
                //dp[i-1][j]表示不偷这个物品能获得的最大价值
                dp[i][j] = Integer.max(dp[i-1][j],theValueIfSelected);
            }
        }
        return dp[n][m];
    }

    /**
     * 0/1背包问题 每个物品都有选择和不选择两种情况，并且不能重复选某一个物品
     * 小进阶 假设物品的重量或者体积不是整数，而是小数，该怎么办？
     * 时间复杂度O(n* m/unit),空间复杂度O(n* m/unit)
     * （第一个算法中，价值的何种类型不影响算法结果(假设价值是浮点数，改相应类型就行)，但是背包的大小和物品的大小是浮点数就有影响了，因此要稍微改一下）
     * 解决办法是首先确定最小粒度(最小单元)，比如，假设物品的重量(体积)都是0.5的倍数，那么dp的每一列j就表示j个单元重量(体积)
     */
    public static float Knapsack01ButFloatSize(float[][] items,float m) {
        int n = items.length;
        if(n == 0 || m == 0){
            return 0;
        }

        float unit = 0.5f;
        int M = (int)(m/unit*1.0f);
        float[][] dp = new float[n+1][M+1];

        int remainingCapacity = 0;
        float theValueIfSelected = 0.0f;

        for(int i = 1;i<=n;i++){//每个物品
            for(int j=1;j <= M;j++){//背包的大小
                /**
                 * 背包当前大小j，
                 * 如果偷这个物品，则最大的价值就是：物品的价值 + 剩余容量能够装下的最大价值；
                 * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                 */
                if(j >= (int)(items[i-1][1]/unit*1.0f)){//如果当前的背包大小大于等于该物品的大小，才有机会选择这个物品
                    remainingCapacity =  (int)(j - items[i-1][1]/unit*1.0f);//剩余多少个单元容量
                    theValueIfSelected = items[i-1][0] + dp[i-1][remainingCapacity];
                }else {
                    theValueIfSelected = 0.0f;
                }
                //dp[i-1][j]表示不偷这个物品能获得的最大价值
                dp[i][j] = Float.max(dp[i-1][j],theValueIfSelected);
            }
        }
        return dp[n][M];
    }

    /**
     * 0/1背包问题 每个物品都有选择和不选择两种情况，并且不能重复选某一个物品
     * 优化解法，dp[]一维数组 ,时间复杂度O(n*m),空间复杂度O(m)
     * 在一种解法中，我们可以看到状态转移方程是与dp[i-1][...]有关，也就是上一行有关(这一行就是表示重量(体积)不超过j时能获得的最大价值)，而与更前面的其他行无关，因此可以将状态压缩，节省空间。
     *
     * @param items 每个物品的价值和占据的容量,[val,size]
     * @param m 背包的容量
     * @return
     */
    public static int Knapsack01Optimize(int[][] items,int m) {
        int n = items.length;
        if(n == 0 || m == 0){
            return 0;
        }
        int[] dp = new int[m+1];
        int remainingCapacity = 0;
        int theValueIfSelected = 0;

        for(int i = 1;i<=n;i++){//每个物品

            //请注意！！，这里必须从后往前，这样才能保证这个物品只选了一次，并且只需要更新那些j大于当前物品size的位置
            //如果当前的背包大小大于等于该物品的大小，才有机会选择这个物品
            for(int j=m;j>=items[i-1][1];j--){
                /**
                 * 背包当前大小j，
                 * 如果偷这个物品，则最大的价值就是：物品的价值 + 剩余容量能够装下的最大价值；
                 * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                 */
                remainingCapacity =  j-items[i-1][1];//剩余容量
                theValueIfSelected = items[i-1][0] + dp[remainingCapacity];

                //旧的dp[j]表示不偷这个物品能获得的最大价值，也就是容量总和在不大于j的情况下(可选物品是前面i-1个物品)能获得的最大价值
                dp[j] = Integer.max(dp[j],theValueIfSelected);
            }
        }
        return dp[m];
    }

    /**
     * 完全背包问题Complete knapsack problem
     * 在01背包问题基础上放宽一个条件：每个物品可以重复选用
     * 解决办法是在前面一个方法上修改一点点。
     *
     * 有一个变型练习题：链接：https://leetcode-cn.com/problems/coin-change-2
     * 518. 零钱兑换 II
     */
    public static int CompleteKnapsack(int[][] items,int m) {
        int n = items.length;
        if(n == 0 || m == 0){
            return 0;
        }
        int[] dp = new int[m+1];
        int remainingCapacity = 0;
        int theValueIfSelected = 0;

        for(int i = 1;i<=n;i++){//每个物品

            //请注意！！，这里必须从前往后，并且只需要更新那些j大于当前物品size的位置，这个过程中，某些物品会被重复选到
            //如果当前的背包大小大于等于该物品的大小，才有机会选择这个物品
            for(int j=items[i-1][1];j<=m;j++){
                /**
                 * 背包当前大小j，
                 * 如果偷这个物品，则最大的价值就是：物品的价值 + 剩余容量能够装下的最大价值；
                 * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                 */
                remainingCapacity =  j-items[i-1][1];//剩余容量
                theValueIfSelected = items[i-1][0] + dp[remainingCapacity];

                //旧的dp[j]表示不偷这个物品能获得的最大价值，也就是容量总和在不大于j的情况下(可选物品是前面i-1个物品)能获得的最大价值
                dp[j] = Integer.max(dp[j],theValueIfSelected);
            }
        }
        return dp[m];
    }

    /**
     * 多重背包问题
     * 每个物品自己都有一定的数量，假设是k,假设这k个物品已经选完，就不能再选它了
     */
    public static int MultipleKnapsack(int[][] items,int m) {
        int n = items.length;
        if(n == 0 || m == 0){
            return 0;
        }
        int[] dp = new int[m+1];
        int remainingCapacity = 0;
        int theValueIfSelected = 0;

        for(int i = 1;i<=n;i++){//每个物品

            //请注意！！，这里必须从后往前，这样才能保证这个物品items[i-1]最多只能选C次(其中C表示这个物品的总数量)，并且只需要更新那些j 大于 当前物品k*size的位置（k < C）
            //如果当前的背包大小大于等于该物品的大小items[i-1][1]，才有机会选择这个物品
            for(int j=m;j>=items[i-1][1];j--){
                //items[i-1][2]表示i这个物品的数量
                for(int k = 0;k <= items[i-1][2];k++){
                    if(j - k * items[i-1][1] < 0)//如果当前容量并不能容纳k个物品i,则结束这个循环，去看看容量增大一个单元后行不行
                        break;

                    /**
                     * 背包当前大小j，
                     * 如果偷这k个物品，则最大的价值就是：k*物品的价值 + 剩余容量能够装下的最大价值；
                     * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                     */
                    remainingCapacity =  j- k*items[i-1][1];//剩余容量
                    theValueIfSelected = k * items[i-1][0] + dp[remainingCapacity];

                    //旧的dp[j]表示不偷这个物品能获得的最大价值，也就是容量总和在不大于j的情况下(可选物品是前面i-1个物品)能获得的最大价值
                    dp[j] = Integer.max(dp[j],theValueIfSelected);
                }

            }
        }
        return dp[m];
    }

    /**
     * 重要，竞赛题
     * 前面的背包解法，只是适用于m不太大的情况，
     * 如果m <= 1e6,可以取到10万(没办法开辟巨大的数组)，而价值取值范围又不大；
     * 这时候就必须转化为等价的问题：
     * 在价值不大于v的情况下的最大体积
     */



    /**
     * 贪心的 背包问题
     * 就是可以选择这种商品的一部分，比如大米，食用油，大豆等等，告诉你每种商品的单价(比如每公斤25元)
     * 你有一个十公斤的袋子，问怎么裝价值最大？
     * 这个问题最好解决，但是不能用动态规划，因为它的粒度非常小，趋近于无穷小
     * 应该用贪心算法，每次裝性价比最大的那种商品，直到该商品没有剩余或者袋子没有空间，为止，如果袋子还有空间，就继续裝性价比次优的商品
     */
}
