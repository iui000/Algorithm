package GinkgoStack.Temporary;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Main44 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();


        LinkedList<Pair<Integer,Integer>> list1 = new  LinkedList<Pair<Integer,Integer>>();
        LinkedList<Pair<Integer,Integer>> list2 = new  LinkedList<Pair<Integer,Integer>>();

        int[][] arr = new int[2][N*2];

        for (int i = 0; i < N*2; i++) {
            arr[0][i] = scanner.nextInt();
        }
        for (int i = 0; i < N*2; i++) {
            arr[1][i] = scanner.nextInt();
        }

        int ans = Math.min(arr[0][N-1],arr[0][N]) +1 ;
        int cur = ans;
        for (int i = N-1,j = N; i >= 0 && j<N*2 ;) {


            if(arr[0][i] < arr[0][j]){
                if(cur <= arr[0][i]){
                    ans += arr[0][i] - cur +1;
                    cur = 1 + arr[1][i];
                }else {
                    cur -= arr[0][i];
                    cur += arr[1][i];
                }
                i--;
            }else if(arr[0][i] > arr[0][j]){
                if(cur <= arr[0][j]){
                    ans += arr[0][j] - cur +1;
                    cur = 1 + arr[1][j];
                }else {
                    cur -= arr[0][j];
                    cur += arr[1][j];
                }
                j++;
            }else {
                //如果两只怪兽血量相等，就看谁的收益最大

                if(arr[0][i] < arr[0][j]){
                    if(cur <= arr[0][i]){
                        ans += arr[0][i] - cur +1;
                        cur = 1 + arr[1][i];
                    }else {
                        cur -= arr[0][i];
                        cur += arr[1][i];
                    }
                    i--;
                }else if(arr[0][i] > arr[0][j]){
                    if(cur <= arr[0][j]){
                        ans += arr[0][j] - cur +1;
                        cur = 1 + arr[1][j];
                    }else {
                        cur -= arr[0][j];
                        cur += arr[1][j];
                    }
                    j++;
                }


            }



        }

        /*
        2
        6 5 8 9
        1 20 1 0
        */

    }

    static void solution(int[][] arr) {
        if (arr.length == 0) {
            System.out.println("None");
            return;
        }
        int res = func(arr, 0);
        System.out.println(res);
    }

    static int func(int[][] arr, int start) {
        return  0;
    }
}

