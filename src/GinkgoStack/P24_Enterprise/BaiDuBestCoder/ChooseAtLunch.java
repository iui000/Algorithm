package GinkgoStack.P24_Enterprise.BaiDuBestCoder;

import java.util.*;

/**
 * 2020测试赛
 * 这是一个01背包问题
 * 但是要记录算法结束后背包的背包剩余空间以及选择了哪些物品
 */
public class ChooseAtLunch {
    /**
     * 0/1背包问题 每个物品都有选择和不选择两种情况，并且不能重复选某一个物品
     * 优化解法，dp[]一维数组 ,时间复杂度O(n*m),空间复杂度O(m)
     * 在一种解法中，我们可以看到状态转移方程是与dp[i-1][...]有关，也就是上一行有关(这一行就是表示重量(体积)不超过j时能获得的最大价值)，而与更前面的其他行无关，因此可以将状态压缩，节省空间。
     *
     * @param items 每个物品的价值和占据的容量,[val,size]
     * @param m 背包的容量
     * @return
     */
    public  int chooseAtLunch(int[][] items,int m, Deque<Integer> result) {
        int n = items.length-1;
        if(n == 0 || m == 0){
            return 0;
        }
        int[] dp = new int[m+1];
        int[][] choosed = new int[n+1][m+1];
        int remainingCapacity = 0;
        int theValueIfSelected = 0;

        for(int i = 1;i<=n;i++){//每个物品

            //请注意！！，这里必须从后往前，这样才能保证这个物品只选了一次，并且只需要更新那些j大于当前物品size的位置
            //如果当前的背包大小大于等于该物品的大小，才有机会选择这个物品
            for(int j=m;j>=items[i][1];j--){
                /**
                 * 背包当前大小j，
                 * 如果偷这个物品，则最大的价值就是：物品的价值 + 剩余容量能够装下的最大价值；
                 * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                 */
                remainingCapacity =  j-items[i][1];//剩余容量
                theValueIfSelected = items[i][0] + dp[remainingCapacity];

                /**
                 * 这里记录一下选择了哪些物品
                 * choosed[][]
                 */
                choosed[i][j] =theValueIfSelected >= dp[j] ? theValueIfSelected:0;
                //旧的dp[j]表示不偷这个物品能获得的最大价值，也就是容量总和在不大于j的情况下(可选物品是前面i-1个物品)能获得的最大价值
                dp[j] = Integer.max(dp[j],theValueIfSelected);
            }
        }

        /**
         * 然后根据dp[m]倒着找出所有被选择的物品
         */
        int index = m;
        int totalCost = 0;
        if(dp[m] != 0) {
            for (int num = n; num >= 1; num--) {
                if (choosed[num][index] != 0) {//说明最终的结果选择了第num个物品
                    totalCost += items[num][1];
                    result.addLast(num);
                    index = index - items[num][1];
                }
            }
            result.addLast(totalCost);
        }else {
            result.addLast(0);
        }

        return dp[m];
    }


    /**
     * 测试数据
     * 2
     *
     * 29
     * 6
     * 9 10
     * 3 4
     * 6 5
     * 7 20
     * 10 9
     * 15 11
     *
     * 0
     * 2
     * 2 23
     * 10 12
     *
     * 输出
     * Case #1:
     * 34 29
     * 2 3 5 6
     * Case #2:
     * 0 0
     *
     * @param args
     */
    /**
     * AC代码
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChooseAtLunch bear = new ChooseAtLunch();
        int T= scanner.nextInt();
        int t = 1;
        while (t<=T && scanner.hasNextInt()){

            int B = scanner.nextInt();
            int N = scanner.nextInt();
            int[][] items = new int[N+1][2];
            Deque<Integer> stack = new LinkedList<>();//用于存储结果

            for(int i = 1;i<=N;i++){
                items[i][0] = scanner.nextInt();//得分
                items[i][1] = scanner.nextInt();//价格cost
            }

            int res = bear.chooseAtLunch(items,B,stack);
            System.out.println("Case #"+t+":");
            System.out.print(res);
            System.out.print(" ");
            if(stack.isEmpty()){
                System.out.println(0);
            }else {
                System.out.println(stack.pollLast());
            }
            while (!stack.isEmpty()){
                System.out.print(stack.pollLast());
                if(!stack.isEmpty()){
                    System.out.print(" ");
                }
            }
            t++;
        }
        scanner.close();
    }

}
