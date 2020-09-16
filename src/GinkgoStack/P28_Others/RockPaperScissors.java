package GinkgoStack.P28_Others;

import java.util.Scanner;

/**
 * 小米笔试
 */
public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            String[] arr1 = new String[4];
            arr1[0] = scanner.next();
            arr1[1] = scanner.next();
            arr1[2] = scanner.next();
            arr1[3] = scanner.next();
            solution(arr1);
        }

    }

    static void solution(String[] arr) {
        int left = 0;
        left =  func(arr[0],arr[2]) > 0 ? left+1:left;
        left =  func(arr[0],arr[3]) > 0 ? left+1:left;

        int right = 0;
        right =  func(arr[1],arr[2]) > 0 ? right+1:right;
        right =  func(arr[1],arr[3]) > 0 ? right+1:right;

        String ans = left == right ? "same":(left > right ? "left":"right");
        System.out.println(ans);
    }

    /**
     * B J S   布 剪刀 石头
     * @param s
     * @param t
     * @return
     */
    static int func(String s,String t) {
        if (s.equals("B") && t.equals("S"))
            return 1;

        if (s.equals("S") && t.equals("B"))
            return -1;

        return s.compareTo(t);
    }
}

