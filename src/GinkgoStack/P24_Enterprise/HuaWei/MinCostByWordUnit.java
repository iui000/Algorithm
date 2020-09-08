package GinkgoStack.P24_Enterprise.HuaWei;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
I need book.;I need book 2.
 */

/**
 * 以单词为单位，将一个字符串编辑为另一个字符串的最小代价
 *
 * 讨厌的是有标点符号
 */
public class MinCostByWordUnit {


    public static int minCost(String str1, String str2, int ic, int dc, int rc) {


        if (str1 == null || str2 == null) {
            return 0;
        }

        String[] chs1 = str1.split(" ");
        String[] chs2 = str2.split(" ");

        List<String> list1 = new ArrayList<>();

        for (int i = 0; i < chs1.length; i++) {
            String s = chs1[i];
            chs1[i]  = s.trim().toLowerCase();
        }

        for (int i = 0; i < chs2.length; i++) {
            String s = chs2[i];
            chs2[i]  = s.trim().toLowerCase();
        }


        int row = chs1.length + 1;
        int col = chs2.length + 1;
        int[][] dp = new int[row][col];//dp[i][j]表示将str1[0..i-1]编辑成str2[0..j-1]的最小代价

        for (int i = 1; i < row; i++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = ic * j;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {

                if (chs1[i - 1].equals(chs2[j - 1]) ) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[row - 1][col - 1];
    }



/*
I need book 1;I need book 2
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] arr = s.split(";");

        String or = arr[0].trim().toLowerCase()
                .replace(","," a")//变成一个单词来处理,前面加一个空格
                .replace("."," a")
                .replace("!"," a")
                .replace("?"," b");
        String ta = arr[1].trim().toLowerCase()
                .replace(","," a")//变成一个单词来处理
                .replace("."," a")
                .replace("!"," a")
                .replace("?"," b");

        int co = minCost(or,ta,1,1,2);
        String[] chs2 = ta.split(" ");

        int len = chs2.length;
        System.out.println("(" + co + "," + len + ")");
    }


//     * 给定两个字符串str1，str2，再给定三个整数ic，dc,rc，分别代表插入、删除、替换一个字符的代价，
//            * 返回str1编辑成str2的最小代价

}

