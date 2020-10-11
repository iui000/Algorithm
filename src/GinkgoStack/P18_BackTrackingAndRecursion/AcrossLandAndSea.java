package GinkgoStack.P18_BackTrackingAndRecursion;

import java.util.Scanner;

/**
 *作者：毫克迈斯
 * 链接：https://www.nowcoder.com/discuss/464062?source_id=profile_create&channel=666
 * 来源：牛客网
 *
 * 小强最近在研究某个飘洋过海的游戏。
 *
 * 游戏可以看成一个n∗m的方格图，从左上角(1,1)到右下角的(n,m)有两种地面，
 * C表示为陆地SSS表示海洋，每次穿行只能到达上下左右四个格子之一，不能走到方格图之外。
 *
 * 在陆地之间穿行一格需要花费3点行动力，在海洋之间穿行一格需要花费2点行动力。
 * 但是从陆地和从海洋到陆地穿行则需要5点行动力。
 *
 * 输入描述：
 * 第一行输入两个整数n,m,q表示方格图的大小和询问次数。
 * 随后n行，每行m个元素每个元素为'C'或'S',详见样例。
 *
 * 随后q行每行四个数字bx,by,ex,ey分别代表起点的坐标和终点的坐标。
 *
 * 输入：
 * 4 4 2
 * CCCS
 * SSSS
 * CSCS
 * SSCC
 * 1 1 3 4
 * 3 1 1 3
 *
 * 输出
 * 13
 * 14
 */
public class AcrossLandAndSea {

    /**
     * 他的思路是DFS 剪枝
     */

    /**
     * 我觉得可以转换成一张带权图，然后求最短路径
     */

    private static boolean[][] isVisit;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int n, m, q, bx, by, ex, ey, nextX, nextY, currVal, result;
    private static String[] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.next();
        }
        while (q-- > 0) {
            isVisit = new boolean[n][m];
            result = Integer.MAX_VALUE;
            bx = sc.nextInt();
            by = sc.nextInt();
            ex = sc.nextInt();
            ey = sc.nextInt();
            currVal = 0;
            BackTrace(bx, by);
            System.out.println(result);
        }
    }

    public static boolean isOk(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && !isVisit[x][y];
    }

    public static void BackTrace(int x, int y) {
        if (currVal >= result) {
            return;
        }
        if (x == ex && y == ey) {
            if (currVal < result) {
                result = currVal;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            nextX = x + direction[i][0];
            nextY = y + direction[i][1];
            if (isOk(nextX, nextY)) {
                int add = 2;
                if (input[x].charAt(y) != input[nextX].charAt(nextY)) {
                    add = 5;
                } else if (input[nextX].charAt(nextY) == 'C') {
                    add = 3;
                }
                isVisit[x][y] = true;
                currVal += add;
                BackTrace(nextX, nextY);
                currVal -= add;
                isVisit[x][y] = false;
            }
        }
    }



}
