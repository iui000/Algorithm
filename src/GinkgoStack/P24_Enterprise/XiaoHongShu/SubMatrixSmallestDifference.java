package GinkgoStack.P24_Enterprise.XiaoHongShu;

import java.util.Scanner;

/**
 * NOIP 普及组 T4 子矩阵(--洛谷P2258)
 * 题目描述
 * 给出如下定义：
 *
 * 子矩阵：从一个矩阵当中选取某些行和某些列交叉位置所组成的新矩阵（保持行与列的相对顺序）被称为原矩阵的一个子矩阵。
 * 例如，下面左图中选取第2、4行和第2、4、5列交叉位置的元素得到一个2*3的子矩阵如右图所示。
 *
 * 9 3 3 3 9
 *
 * 9 4 8 7 4
 *
 * 1 7 4 6 6
 *
 * 6 8 5 6 9
 *
 * 7 4 5 6 1
 *
 * 的其中一个2*3的子矩阵是
 *
 * 4 7 4
 *
 * 8 6 9
 *
 * 相邻的元素：矩阵中的某个元素与其上下左右四个元素（如果存在的话）是相邻的。
 *
 * 矩阵的分值：矩阵中每一对相邻元素之差的绝对值之和。
 * 本题任务：给定一个n行m列的正整数矩阵，请你从这个矩阵中选出一个r行c列的子矩阵，
 * 使得这个子矩阵的分值最小，并输出这个分值。
 *
 * (本题目为2014NOIP普及T4)
 *
 * 输入输出格式
 * 输入格式：
 *
 *
 *
 * 第一行包含用空格隔开的四个整数n，m，r，c，意义如问题描述中所述，每两个整数之间用一个空格隔开。
 *
 * 接下来的n行，每行包含m个用空格隔开的整数，用来表示问题描述中那个n行m列的矩阵。
 *
 *
 *
 * 输出格式：
 *
 *
 *
 * 输出共1行，包含1个整数，表示满足题目描述的子矩阵的最小分值。
 *
 *
 *
 * 输入输出样例
 * 输入样例#1：
 * 5 5 2 3
 * 9 3 3 3 9
 * 9 4 8 7 4
 * 1 7 4 6 6
 * 6 8 5 6 9
 * 7 4 5 6 1
 * 输出样例#1：
 * 6
 * 输入样例#2：
 * 7 7 3 3
 * 7 7 7 6 2 10 5
 * 5 8 8 2 1 6 2
 * 2 9 5 5 6 1 7
 * 7 9 3 6 1 7 8
 * 1 9 1 4 7 8 8
 * 10 5 9 1 1 8 10
 * 1 3 1 5 4 8 6
 * 输出样例#2：
 * 16
 *
 */
public class SubMatrixSmallestDifference {

    /**
     * 解法和思路1：
     * https://blog.csdn.net/WhiStLenA/article/details/78240164
     *
     */



    /**
     * 解法2
     * https://blog.csdn.net/SSL_guyixin/article/details/108130513
     */

    static int n,m,r,c;

    static int[][] cost = new int[20][20]  ;
    static int[][] f = new int[20][20]  ;

    static int[] xc = new int[20]  ;
    static int[][] yc = new int[20][20]  ;

    static int[] value = new int[20];
    static int y;

    static int res = 19919191;

    public static void dfs(int x)
    {
        if(y==r)
        {
            dp();
            return;
        }
        for(int i=x+1;i<=n;i++)
        {
            y++;
            value[y]=i;
            dfs(i);
            y--;
        }
    }

    public static void dp()
    {
        f = new int[20][20];
        xc = new int[20];
        yc = new int[20][20];

        value[r+1]= value[r];
        for(int i=1;i<=m;i++)
            for(int j=1;j<=r;j++)
                xc[i]+= Math.abs(cost[value[j]][i]- cost[value[j+1]][i]);


        for(int i=1;i<=m;i++)
            for(int j=i+1;j<=m;j++)
                for(int k=1;k<=r;k++)
                    yc[i][j]+=Math.abs(cost[value[k]][i]- cost[value[k]][j]);

        for(int i=1;i<=m;i++)
            f[1][i]=xc[i];

        for(int i=2;i<=c;i++)
            for(int j=i;j<=m;j++)
            {
                int ans=101001010;
                for(int k=i-1;k<j;k++)
                    ans=Math.min(ans,f[i-1][k]+yc[k][j]);

                f[i][j]=ans+xc[j];
            }

        for(int i=c;i<=m;i++)
            res =Math.min(f[c][i], res);

    }


    /*
4 4  2 2
5 4 2 6
4 10 1 8
1 9 3 10
6 4 8 3
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
         n = scanner.nextInt();
         m = scanner.nextInt();
         r = scanner.nextInt();
         c = scanner.nextInt();


        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <= m; j++) {
                cost[i][j] = scanner.nextInt();
            }
        }

        dfs(0);
        System.out.println(res);

    }



}

