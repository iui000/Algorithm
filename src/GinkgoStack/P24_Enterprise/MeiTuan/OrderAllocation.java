package GinkgoStack.P24_Enterprise.MeiTuan;

import java.util.Scanner;

/**
 * 美团点评2020校招后台开发方向笔试题
 * 订单分配
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 打车派单场景, 假定有N个订单， 待分配给N个司机。
 * 每个订单在匹配司机前，会对候选司机进行打分，打分的结果保存在N*N的矩阵A，
 * 其中Aij 代表订单i司机j匹配的分值。
 *
 * 假定每个订单只能派给一位司机，司机只能分配到一个订单。
 * 求最终的派单结果，使得匹配的订单和司机的分值累加起来最大，并且所有订单得到分配。
 *
 *
 * 输入描述:
 * 第一行包含一个整数N，2≤N≤10。
 *
 * 第二行至第N+1行包含N*N的矩阵。
 *
 *
 * 输出描述:
 * 输出分值累加结果和匹配列表，结果四舍五入保留小数点后两位
 * （注意如果有多组派单方式得到的结果相同，则有限为编号小的司机分配编号小的订单，
 * 比如：司机1得到1号单，司机2得到2号单，就比司机1得到2号单，司机2得到1号单要好）
 *
 * 输入例子1:
 * 3
 * 1.08 1.25 1.5
 * 1.5 1.35  1.75
 * 1.22 1.48 2.5
 *
 * 输出例子1:
 * 5.25
 * 1 2
 * 2 1
 * 3 3
 *
 * 例子说明1:
 * 第一行代表得到的最大分值累加结果5.25，四舍五入保留两位小数；
 *
 * 第二行至第四行代表匹配的结果[i j],其中i按行递增：
 *
 * 订单1被派给司机2，订单2被派给司机1，订单3被派给司机3。
 * 使得A12+ A21+ A33= 1.25 + 1.5 + 2.5 = 5.25在所有的组合中最大。
 */
import java.util.*;
public class OrderAllocation {
    /**
     * //每行每列选一个，且不重复，类似于N皇后问题与全排列问题，回溯法解决.
     *
     */
    private double curProfit = 0.00;
    private boolean[] visited;
    private double maxProfit = Integer.MIN_VALUE;
    private ArrayList<Integer> ans;

    private void Solution(double[][] nums)
    {
        int n = nums.length;
        visited = new boolean[n];
        ArrayList<Integer> tmpAns = new ArrayList<>();
        backTracking(tmpAns, nums, 0);

        //输出结果
        System.out.println(String.format("%.2f", maxProfit));
        for(int i = 0; i< ans.size(); i++) {
            System.out.println(i+1 +" "+ ans.get(i));
        }
    }


    public void backTracking(ArrayList<Integer> distribution, double[][] nums, int row)
    {
        //单子已经分配完毕，计算此时的收益
        if(row == nums.length)
        {
            if(curProfit > maxProfit)
            {////更新最大和与每行对应的列
                maxProfit = curProfit;
                ans = (ArrayList<Integer>) distribution.clone();
            }
        }

        //考虑当前row行，轮流选取每一列的可能性
        for(int i = 0;i<nums.length;i++)
        {
            //该列还没有被选取，也就是这个司机还没有被分派订单
            if(!visited[i])
            {
                //假设将该订单分配给他
                visited[i] = true;
                curProfit += nums[row][i];//
                distribution.add(i+1);//因为结果集合是从1开始数起的

                //回溯
                backTracking(distribution, nums, row+1);

                //撤销
                distribution.remove(distribution.size()-1);
                curProfit -= nums[row][i];
                visited[i] = false;
            }
        }
    }

    /**
     3
     1.08 1.25 1.5
     1.5 1.35  1.75
     1.22 1.48 2.5
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        double[][] nums = new double[n][n];

        for(int i = 0;i<n;i++)
            for(int j = 0;j<n;j++)
                nums[i][j] = in.nextDouble();

        OrderAllocation s = new OrderAllocation();
        s.Solution(nums);
    }

}
