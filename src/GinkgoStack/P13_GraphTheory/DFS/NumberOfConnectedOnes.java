package GinkgoStack.P13_GraphTheory.DFS;

import java.util.HashMap;
import java.util.Scanner;


/**
 * 拼多多笔试题
 * 这道题已经有思路，一看就是DFS，当时没时间了，差点就能写出来，不该手敲打，应该用板子啊
 *
 *
 * 矩阵1表示该位置有一个士兵，0表示没有；
 * 假设可以将任意一个士兵挪动到任意一个位置；
 * 那么，能够使得连通的士兵的数量最多位多少？
 *
 * 也就是能够挪动一个1到其他位置；
 * 连通的1最多有多少个？
 *
 */
public class NumberOfConnectedOnes {
    /**
     输入：
     4 4
     1 0 1 1
     1 1 0 1
     0 0 0 0
     1 1 1 1
     输出：8


     输入：
     4 4
     1 0 1 1
     1 1 1 1
     1 0 0 0
     1 1 1 1
     输出：12

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0;i < n;i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int res = fun(n,m, arr);
        System.out.println(res);
    }


    static int fun(int n,int m,int[][] arr) {
        // 具体的算法逻辑
        int max = Integer.MIN_VALUE;

        int n1 = 0;
        for(int i = 0;i < n;i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 0){
                    boolean[][] v = new boolean[n][m];
                    arr[i][j] = 1;//把0变成1
                    int tmp = dfs(n,m,i,j,arr,v);
                    arr[i][j] = 0;//还原
                    if(tmp > max)
                        max = tmp;
                }

                if(arr[i][j] == 1){//统计1的数量
                    n1++;
                }
            }
        }

        if(n1 == n*m){//如果全部为1
            return n1;
        }

        if(n1 == 0){//如果没有1
            return 0;
        }

        //注意，如果最终得出max比n1更大，说明通过一个0能够把所有的1连接起来
        //dfs之前将0变成了1，这个1其实是从其他地方借过来的，也就是说此1其实本不属于目前这个联通分量
        //现在max计算出来比n1更大的话（准确的说是大1），那么就说明：
        // 没办法从其他地方借，而只能从自己的联通分量的边缘移动一个过来。
        //因此，最终得max减去1
        return  max > n1? max-1:max;
    }

    static int dfs(int n,int m,int i,int j ,int[][] arr,boolean[][] v) {
        if( i < 0 || j < 0 || i == n || j == m){
            return 0;
        }

        if(v[i][j] || arr[i][j] == 0){
            return 0;
        }

        v[i][j] = true;
        int res = 1+ dfs(n,m,i+1,j,arr,v)+
                     dfs(n,m,i-1,j,arr,v)+
                     dfs(n,m,i,j+1,arr,v)+
                     dfs(n,m,i,j-1,arr,v);

        return res;
    }

}

