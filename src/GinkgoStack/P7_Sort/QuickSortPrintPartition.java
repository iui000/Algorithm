package GinkgoStack.P7_Sort;

import java.util.Scanner;

/**
 * 打印出每次划分的结果
 */
public class QuickSortPrintPartition {

    //arr 需要排序的数组
    //low 开始时最左边的索引=0
    //high 开始时最右边的索引=arr.length-1
    public static void quickSort(int[] arr,int low,int high){
        int i,j,pivot,t;

        if(low>high){
            return;
        }
        i=low;//左边哨兵的索引
        j=high;//右边哨兵的索引
        //temp就是基准位
        pivot = arr[low];//以最左边为  基准位

        while (i<j) {
            //先看右边，依次往左递减
            //先从右往左找一个小于 基准位的数
            //当右边的哨兵位置所在的数 > 基准位的数 时
            //继续从右往左找（同时 j 索引-1）
            //找到后会跳出 while循环
            while (pivot <= arr[j] && i<j ) {
                j--;
            }

            //再看左边，依次往右递增
            //步骤和上面类似
            while (pivot >= arr[i] && i<j) {
                i++;
            }

            //如果满足条件则交换
            if (i<j) {
                // 左右哨兵 交换数据（互相持有对方的数据）
                int temp =  arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

        //这时 跳出了 “while (i<j) {}” 循环
        //说明 i=j 左右在同一位置
        //最后将基准为与i和j相等位置的数字交换

        arr[low] = arr[i];//或 arr[low] = arr[j];
        arr[i] = pivot;//或 arr[j] = temp;


        //i=j
        //这时  左半数组<(i或j所在索引的数)<右半数组
        //也就是说(i或j所在索引的数)已经确定排序位置， 所以就不用再排序了，
        // 只要用相同的方法 分别处理  左右数组就可以了
        for (int m = 0; m < arr.length; m++) {
            System.out.print(arr[m]);
            if(m != arr.length-1){
                System.out.print(" ");
            }
        }
        System.out.println("");
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        int i = 0;
        while (i<n){
            arr[i++] = scanner.nextInt();
        }
        quickSort(arr,0,arr.length-1);
    }
}
