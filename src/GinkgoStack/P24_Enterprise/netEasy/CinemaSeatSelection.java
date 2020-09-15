package GinkgoStack.P24_Enterprise.netEasy;

import java.util.Scanner;

/**
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
        int[] left = new int[n];
        left[0] = arr[0].equals("0")? n:0;

        int[] right = new int[n];
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
            int minDistance = Math.min(left[i],right[i]);
            if(minDistance > max){
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
