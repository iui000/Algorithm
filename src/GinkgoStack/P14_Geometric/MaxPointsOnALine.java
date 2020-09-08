package GinkgoStack.P14_Geometric;

import java.util.HashMap;
import java.util.Map;


/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 *
 * 示例 1:
 *
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 *
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
public class MaxPointsOnALine {

    /**
     *
     * 官方题解，枚举
     * 思路非常简单：画一条通过点 i 和之后出现的点的直线，
     * 在哈希表中存储这条边并计数为 2 = 当前这条直线上有两个点。
     */
    class Solution {
        public int maxPoints(int[][] points) {
            if (points.length < 3) return points.length; // none\one\two points
            int maxCount = 1;
            for (int i = 0; i < points.length - 1; i++)
                maxCount = Math.max(computeMaxPointsThrougthIndexPoint(points, i), maxCount);
            return maxCount;
        }

        private int horizontalLines; // 与指定点i在同一水平线点数
        private Map<String, Integer> linePointsMap = new HashMap<>(); // 与指定点i在同一斜线点数
        private int count; // 指定点i在同一直线最大点数
        private int duplicates; // 与指定点i重复的点数

        public int computeMaxPointsThrougthIndexPoint(int[][] points, int i) {
            clearStatisticData();
            for (int j = i + 1; j < points.length; j++)
                statistic(points, i, j);
            return this.count + this.duplicates;
        }

        private void clearStatisticData() {
            this.linePointsMap.clear(); // 清空，重新统计
            this.horizontalLines = 1;  // 水平线
            this.count = 1;  // 当前在一条线上的点，初始化为1，自己
            this.duplicates = 0; // 重复点统计
        }

        private void statistic(int[][] points, int i, int j) {
            int x1 = points[i][0], y1 = points[i][1];
            int x2 = points[j][0], y2 = points[j][1];

            if ((x1 == x2) && (y1 == y2)) duplicates++; // 重复点统计
            else if (y1 == y2) {  // 水平线
                this.horizontalLines += 1;
                this.count = Math.max(horizontalLines, this.count);
            }
            else {  // 斜线统计
                String slope = buildSlope(x1 - x2, y1 - y2);
                this.linePointsMap.put(slope, this.linePointsMap.getOrDefault(slope, 1) + 1);
                this.count = Math.max(this.linePointsMap.get(slope), count);
            }
        }

        private String buildSlope(int p, int q) {
            int gcd = computeGcd(p, q);
            return p / gcd + "_" + q / gcd;
        }

        private int computeGcd(int p, int q) {
            if (q == 0) return p;
            int r = p % q;
            return computeGcd(q, r);
        }
    }
}
