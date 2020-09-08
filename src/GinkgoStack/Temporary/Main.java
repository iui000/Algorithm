package GinkgoStack.Temporary;

import java.util.HashMap;
import java.util.Scanner;


public class  Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = scanner.nextInt();
            arr[i][1] = scanner.nextInt();
        }
        solution(arr);
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

