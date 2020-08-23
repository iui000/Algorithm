package GinkgoStack.P24_Enterprise.BaiDuBestCoder;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 *
 */
public class Drink {

    /**
     * 总体积不小于V的前提下，物品的总价值最小是多少。
     */
    private static int  minValueKnapsack01(int[] volume,int[] value,int V,int valueSum){
        int n = volume.length-1;

        int[] dp = new int[valueSum+1];

        for(int i=1;i<=n;i++)
        {
            for(int j = value[i];j <= valueSum;j++)
            {
                dp[j]=Math.max(dp[j], dp[j-value[i]] + volume[i]);
            }
        }

        //找到第一个满足 体积不小于V 的价值
        for(int i=1;i<= valueSum;i++)
        {
            if(dp[i] >= V)
            {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int T= scanner.nextInt();
        int t = 1;
        List<Integer> list = new ArrayList<>(T);
        while (t<=T){
            int n = scanner.nextInt();//n个物品
            int V = scanner.nextInt();//体积

            int[] volume = new int[n+1];
            int[] value = new int[n+1];

            int valueSum = Integer.MAX_VALUE;
            for (int i = 1; i <= n; ++i) {
                volume[i] = scanner.nextInt();
                value[i] = scanner.nextInt();

                int tmp = Integer.MAX_VALUE;
                if(volume[i] < V){
                    tmp = (V/volume[i]+1)*value[i];
                }else if(volume[i] >= V){
                    tmp = value[i];
                }

                valueSum = Math.min(tmp,valueSum);
            }
            if(valueSum != Integer.MAX_VALUE){
                list.add(minValueKnapsack01(volume,value,V,valueSum));
            }
//            System.out.println();

            t++;
        }

        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

}
