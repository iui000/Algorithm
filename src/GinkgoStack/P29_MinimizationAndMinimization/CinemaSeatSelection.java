package GinkgoStack.P29_MinimizationAndMinimization;

import java.util.Scanner;

/**
 * 网易笔试
 * AC
 * //1 0 0 0 1 0 1
 * //1 0 1 0 1
 * //0 0 0 0 1 0 1 0 1
 *
 * 电影院选座位1表示已经有人，0表示空位
 *
 * 求与1的距离最远是多少？
 */
public class CinemaSeatSelection {
    public static int fun(String[] arr) {
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        int[] left = new int[n];//left[i]表示的是该位置i距离左边最近的值1的距离
        left[0] = arr[0].equals("0")? n:0;

        int[] right = new int[n];//right[j]表示的是该位置j距离右边最近的值1的距离
        right[n-1] = arr[n-1].equals("0")? n:0;

        for (int i = 1; i < arr.length; i++) {
            int j = n - 1 - i;
            if(arr[i].equals("1")){
                left[i] = 0;
            }
            if(arr[i].equals("0")){
                left[i] = left[i-1]+1;
            }

            if(arr[j].equals("1")){
                right[j] = 0;
            }
            if(arr[j].equals("0")){
                right[j] = right[j+1] +1;
            }

        }
        for (int i = 0; i < n; i++) {
            int minDistance = Math.min(left[i],right[i]);//该位置距离1的最近距离
            if(minDistance > max){//求一个最大值，尽可能远离左右的观众
                max = minDistance;
            }
        }
        return max;
    }

//1 0 0 0 1 0 1
//1 0 1 0 1
//0 0 0 0 1 0 1 0 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] arr = s.split(" ");
        System.out.println(fun(arr));
    }
}
