package GinkgoStack.P20_DynamicProgramming.IntervalDP;

/**
 * 区间类型动态规划是线性动态规划的拓展，它在分阶段划分问题时，
 * 与阶段中元素出现的顺序和由前一阶段的哪些元素合并而来有很大的关系。（例：primeFactor[i][j]=primeFactor[i][k]+primeFactor[k+1][j]）
 *
 * 区间类动态规划的特点：
 *
 * 合并：即将两个或多个部分进行整合。
 * 特征：能将问题分解成为两两合并的形式。
 * 求解：对整个问题设最优值，枚举合并点，将问题分解成为左右两个部分，
 * 最后将左右两个部分的最优值进行合并得到原问题的最优值。
 */

import java.util.Scanner;

/**
 *
 * 区间DP的做法较为固定，即枚举区间长度，再枚举左端点，之后枚举区间的断点进行转移。
 * 【例题一】石子合并：
 * 【问题描述】
 *
 * 将n(1≤n≤200)堆石子绕圆形操场摆放，现要将石子有次序地合并成一堆。
 * 规定每次只能选相邻的两堆石子合并成新的一堆，并将新的一堆的石子数，记为该次合并的得分。
 * (1)选择一种合并石子的方案，使得做n-1次合并，得分的总和最小。
 * (2)选择一种合并石子的方案，使得做n-1次合并，得分的总和最大。
 *
 * 【样例输入】
 *
 * 4
 *
 * 4 5 9 4
 *
 * 【样例输出】
 *
 * 43
 *
 * 54
 */
public class StoneMerge {

    /**
     * 第一，假设不是环，是排成一排的话
     * 无环正解： 对应到动态规划中,就是两个长度较小的区间上的信息向一个更长的区间发生了转移,
     * 划分点k就是转移的决策，区间长度len就是DP的阶段。根据动态规划“选择最小的能覆盖状态空间的维度集合”的思想,可以只用左、右端点表示DP的状态。
     *
     * sum[i]：从第1堆到第i堆石子数总和。
     *
     * Fmax[i][j]：将从第i堆石子合并到第j堆石子的最大得分；
     *
     * Fmin[i][j]：将从第i堆石子合并到第j堆石子的最小得分；
     *
     * 初始条件：Fmax[i][i]=0，Fmin[i][i]=INF
     *
     * 则状态转移方程为：（其中i<=k<j）
     * Fmax[i][j] = max{Fmax[i][k] + Fmax[k+1][j] + sum[j] - sum[i-1]}
     * Fmin[i][j] = max{Fmin[i][k] + Fmin[k+1][j] + sum[j] - sum[i-1]}
     *
     * 时间复杂度O（N^3）
     */

    /**
     * 如果是环，那么就要转换一下，破环成链
     *
     * 注意到：题目中石子是围成一个圈，而不是一条线。
     *
     * 方法1：由于石子堆是围成一个圈，因此我们可以枚举分开的位置，首先将这个圈转化为链，
     * 因此要做n次，这样时间复杂度为 。
     * 方法2：将这条链延长2倍，扩展成2n-1堆，其中第1堆与n+1堆完全相同，第i堆与n+i堆完全相同，
     * 这样只要对这2n堆动态规划后，枚举f(1,n),primeFactor(2,n+1),…,primeFactor(n,2n-1)取最优值即可。
     */
    int min,max;
    public void mergeStoneHeap(int[] a,int n){
        int N = a.length-1;//N = 2*n
        int[] sum = new int[N+1];
        int[][] Fmax = new int[N+1][N+1];
        int[][] Fmin = new int[N+1][N+1];

        //求前缀和，以及预处理
        for(int i = 1;i<=N;i++){
            sum[i] = sum[i-1]+a[i];
            Fmax[i][i] = 0;
            Fmin[i][i] = 0;
        }

        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;

        //对长度进行dp
        for (int len = 2;len <= n;len++){//因为是破环成链，所以长度是原本n的长度
            for (int i = 1; i <= N-len;i++){//左边起始点
                int j = i+len-1;//右端点
                //初始化
                Fmin[i][j] = Integer.MAX_VALUE;
                Fmax[i][j] = Integer.MIN_VALUE;
                for (int k = i;k<j;k++){//枚举分割点，取最优值
                    Fmin[i][j] = Math.min(Fmin[i][j],Fmin[i][k] + Fmin[k+1][j]);//最小
                    Fmax[i][j] = Math.max(Fmax[i][j],Fmax[i][k] + Fmax[k+1][j]);//最大
                }
                int mValue = sum[j] - sum[i-1];//将合并后的得分加起来
                Fmin[i][j] += mValue;
                Fmax[i][j] += mValue;
            }
        }

        for(int i = 1;i<= n;i++){
            min = Math.min(min,Fmin[i][i+n-1]);
            max = Math.max(max,Fmax[i][i+n-1]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[2*n+1];

        //破环成链
        for(int i = 1;i<=n ;i++){
            int tmp = scanner.nextInt();
            a[i] = tmp;
            a[i+n] = tmp;
        }

        StoneMerge st = new StoneMerge();
        st.mergeStoneHeap(a,n);
        System.out.println(st.min);
        System.out.println(st.max);
    }

}
