package GinkgoStack.P24_Enterprise.DJ;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 迪杰斯特拉算法求最短路径
 * https://blog.csdn.net/s1547156325/article/details/100555553
 * 只通过64%
 */
public class Dijkstra {

    private static int dijkstra(int[][] cost ,int v,int n,int target){

        int[] minDist = new int[n];
        int[] hasCu = new int[n];
        int[] vNumber = new int[n];
        int[][] path = new int[n][n];

        for(int i = 1;i<n;i++){
            hasCu[i] = 0;
            vNumber[i] = -1;
        }

        for(int i = 1;i<n;i++){
            minDist[i] = cost[v][i];
            if(minDist[i]  < Integer.MAX_VALUE){
                path[i][++vNumber[i]] = v;
                path[i][++vNumber[i]] = i;
            }
        }

        hasCu[v] = 1;
        int j =0;
        int min =0;
        int m =0;
        for(int i = 0;i<n-1;i++){
            min = Integer.MAX_VALUE;
            m = v;
            for(j = 1;j<n;j++){
                if(minDist[j] < min && hasCu[j] == 0){
                    min = minDist[j];
                    m = j;
                }
            }

            if(m != j){
                hasCu[m] = 1;
                for(int k = 0;k<= vNumber[m];k++){
                    if(k == vNumber[m]){
                       break;
                    }
                }

                for(int k = 1;k<n;k++){
                    if(minDist[k] > (cost[m][k] + minDist[m])
                    && hasCu[k] == 0
                    && cost[m][k] != Integer.MAX_VALUE){
                        minDist[k] = cost[m][k] + minDist[m];
                        for (int h = 0;h<=vNumber[m];h++){
                            path[k][h] = path[m][h];
                        }
                        vNumber[k] = vNumber[m];
                        path[k][++vNumber[k]] = k;
                    }
                }
            }
        }

        return minDist[target];

    }


    /**
     *
    4 5
    0 1 15
    1 2 15
    0 3 50
    1 3 30
    2 3 10
    3
     *
     * 样例输出
     * 40
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] cost = new int[n][n];
        for(int i = 0;i<n;i++){
            Arrays.fill(cost[i],Integer.MAX_VALUE);
        }

        for(int i = 0;i<m;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int t = sc.nextInt();
            cost[a][b] = t;
        }

        int x = sc.nextInt();


        if(m == 0){
            System.out.print(-1);
            return;
        }

        if(x >= n){
            System.out.print(-1);
            return;
        }

        System.out.print(dijkstra(cost,0,n,x));


    }

}
