package GinkgoStack.P24_Enterprise.DiDi;

import java.util.Scanner;

public class SpiralFibArr {
    static long[][] arr;
    public static long[] FibN(int n) {
        long first = 0;
        long second = 1;
        long cur = 1;

        int k = 0;
        long[] fib = new long[n];
        fib[0] = 0;
        fib[1] = 1;

        for(int i = 0;i<n;i++){ //题目要求矩阵最大为9*9, 第81个数为37_889_062_373_143_906，如果为10* 10 的话，会long溢出
            /**
             * 这里别加 % 1_000_000_007
             */
//            cur = (first + second)%1_000_000_007;
            cur = first + second;
            first = second;
            second = cur;
            fib[k++] = first;
        }

        return fib;
    }


    /**
     * 旋转数组，顺时针打印变成顺时针赋值
     * @param matrix
     * @param order
     */
    public static void spiralOrder(long[][] matrix,long[] order) {
        int k = order.length-1;

        int rows = matrix.length, columns = matrix[0].length;
        //设置最外层左上角和右下角的坐标
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom) {
            //上边，包含右上角的点
            for (int column = left; column <= right; column++) {
                matrix[top][column] = order[k--];
            }
            //右边，包含右下角的点
            for (int row = top + 1; row <= bottom; row++) {
                matrix[row][right] = order[k--];
            }
            //先判断有没有下边界 和 左边界
            if (left < right && top < bottom) {
                //下边，不包含右下角的点
                for (int column = right - 1; column > left; column--) {
                    matrix[bottom][column] = order[k--];
                }
                //左边，包含左下角的点
                for (int row = bottom; row > top; row--) {
                    matrix[row][left] = order[k--];
                }
            }

            //向内收缩一层
            left++;
            right--;
            top++;
            bottom--;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if(n <= 0){
            return;
        }

        if(n == 1){
            System.out.println(1);
            return;
        }

        arr = new long[n][n];
        int N = n*n;

        long[] feb = FibN(N);
//        for(int i =0;i < N;i++){
//            System.out.println(feb[i]);
//        }

        spiralOrder(arr,feb);
        for(int i =0;i < n;i++){
            for(int j =0;j < n;j++){
                System.out.printf("%d%c", arr[i][j], j==n-1 ? '\n' : ' ');
            }
        }
    }


}
