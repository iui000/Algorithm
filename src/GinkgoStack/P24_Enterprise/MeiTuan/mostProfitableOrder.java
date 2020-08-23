package GinkgoStack.P24_Enterprise.MeiTuan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class mostProfitableOrder {

    /*
    5 2
    5 10
    8 9
    1 4
    7 9
    6 10
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        if(m < 0 || n<0){
            return;
        }

        if(n < m){
            return;
        }

        if(m > 50000 || n > 50000){
            return;
        }

        int[][] arr = new int[n][3];

        for (int i = 0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = i+1;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o2[0] + o2[1]*2) == (o1[0] + o1[1]*2) ?
                        o1[2] - o2[2] :
                        (o2[0] + o2[1]*2) - (o1[0] + o1[1]*2);
            }
        });


        int x =  Math.min(m,n);
        for(int i = 0;i< x ;i++){//好像是输出从小到大序号，坑，所以应该还要再排序
            System.out.printf("%d%c", arr[i][2], i==x -1 ? '\n' : ' ');
        }

    }
}
