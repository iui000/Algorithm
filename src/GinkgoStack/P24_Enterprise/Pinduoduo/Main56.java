package GinkgoStack.P24_Enterprise.Pinduoduo;

import java.util.HashMap;
import java.util.Scanner;


public class Main56 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] orders = new int[M];

        for (int i = 0; i < M; i++) {
            orders[i] = scanner.nextInt();
        }
        // 委托 solution 进行求解
        solution(orders,N);
    }

    static void solution(int[] orders,int n) {
        // 排除一些基本的边界情况
        if (orders.length == 0) {
            System.out.println(1);
            return;
        }
        // 委托 dp 函数执行具体的算法逻辑
        int res = fun(orders, n);
        // 负责输出结果
        System.out.println(res);
    }

    // 备忘录
    static int fun(int[] orders, int n) {
        // 具体的算法逻辑
        int m = orders.length;

        int res = 0;
        boolean[] arr  = new boolean[n+1];
        int co = 0;
        for(int i = 0;i<m;i++){
            for(int j = 1; j*orders[i] <= (long)n;j++){
                if(!arr[j*orders[i]]){
                    arr[j*orders[i]]  = true;
                    co++;
                }

            }
        }
        return  co;
    }
}

