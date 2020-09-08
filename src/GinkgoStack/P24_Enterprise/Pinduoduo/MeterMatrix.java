package GinkgoStack.P24_Enterprise.Pinduoduo;

import java.util.HashMap;
import java.util.Scanner;


/**
 * AC代码
 * 打印一个米字矩阵0，米字以外的位置是逆时针1到8
 *
 * 10
 * 0 2 2 2 2 1 1 1 1 0
 * 3 0 2 2 2 1 1 1 0 8
 * 3 3 0 2 2 1 1 0 8 8
 * 3 3 3 0 2 1 0 8 8 8
 * 3 3 3 3 0 0 8 8 8 8
 * 4 4 4 4 0 0 7 7 7 7
 * 4 4 4 0 5 6 0 7 7 7
 * 4 4 0 5 5 6 6 0 7 7
 * 4 0 5 5 5 6 6 6 0 7
 * 0 5 5 5 5 6 6 6 6 0
 *
 *
 * 7
 * 0 2 2 0 1 1 0
 * 3 0 2 0 1 0 8
 * 3 3 0 0 0 8 8
 * 0 0 0 0 0 0 0
 * 4 4 0 0 0 7 7
 * 4 0 5 0 6 0 7
 * 0 5 5 0 6 6 0
 *
 */
public class MeterMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int n = scanner.nextInt();

        solution(n);
    }

    static void solution(int n) {
        // 排除一些基本的边界情况

        // 委托 dp 函数执行具体的算法逻辑
        int[][] res = fun(n);
        // 负责输出结果

        for(int i = 0;i < n;i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d%c", res[i][j], j == n - 1 ? '\n' : ' ');
            }
        }

    }


    static int[][] fun(int n) {
        // 具体的算法逻辑
        int[][] arr = new int[n][n];
        if(n % 2 == 0){//偶数
            for(int i = 0; i < n;i++){
                int x = n-1-i;
                for(int j = 0;j < n;j++){

                    if(i < n/2 && j < i){
                        arr[i][j] = 3;
                    }
                    if(j < n/2 && i < j){
                        arr[i][j] = 2;
                    }

                    if(j >= n/2 && j < x){
                        arr[i][j] = 1;
                    }

                    if(j > x && i < n/2){
                        arr[i][j] = 8;
                    }

                    //下半边
                    if(i > n/2 && j > x && j < n/2){
                        arr[i][j] = 5;
                    }
                    if(i >= n/2 && j < x){
                        arr[i][j] = 4;
                    }

                    if(i >= n/2 && j > i){
                        arr[i][j] = 7;
                    }
                    if(i > n/2 && j >= n/2 && j < i){
                        arr[i][j] = 6;
                    }

                }
                arr[i][i] = 0;
                arr[i][n-1-i] = 0;
            }
        }else {//奇数
            for(int i = 0; i < n;i++){
                int x = n-1-i;
                for(int j = 0;j < n;j++){

                    if(i < n/2 && j < i){
                        arr[i][j] = 3;
                    }
                    if(j < n/2 && i < j){
                        arr[i][j] = 2;
                    }

                    if(j > n/2 && j < x){
                        arr[i][j] = 1;
                    }

                    if(j > x && i < n/2){
                        arr[i][j] = 8;
                    }

                    //下半边
                    if(i > n/2 && j > x && j < n/2){
                        arr[i][j] = 5;
                    }
                    if(i > n/2 && j < x){
                        arr[i][j] = 4;
                    }

                    if(i > n/2 && j > i){
                        arr[i][j] = 7;
                    }
                    if(i > n/2 && j > n/2 && j < i){
                        arr[i][j] = 6;
                    }

                }
                arr[i][i] = 0;
                arr[i][n-1-i] = 0;
            }
        }
        return  arr;
    }
}

