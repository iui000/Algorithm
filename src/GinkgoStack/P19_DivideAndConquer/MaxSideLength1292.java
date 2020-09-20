package GinkgoStack.P19_DivideAndConquer;

/**
 * 1292. 元素和小于等于阈值的正方形的最大边长
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 *
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于 4 的正方形的最大边长为 2，如图所示。
 * 示例 2：
 *
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * 输出：0
 * 示例 3：
 *
 * 输入：mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * 输出：3
 * 示例 4：
 *
 * 输入：mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 */
public class MaxSideLength1292 {

    /**
     * 二分，计算矩形的技巧
     *
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x3ojz3/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        int threshold;
        public int maxSideLength(int[][] mat, int threshold) {
            this.threshold = threshold;
            int result = 0;
            int m = mat.length, n = mat[0].length;
            //用来存储以零点为起点的矩形元素和
            int[][] area = new int[m + 1][n + 1];
            //循环宽度从1到m
            for (int x = 1; x <= m; x++) {
                //循环高度从1到n
                for (int y = 1; y <= n; y++) {
                    //注意area里面的数值是排好序的，所以我们可以考虑用二分法
                    area[x][y] = mat[x - 1][y - 1] + area[x - 1][y] + area[x][y - 1] - area[x - 1][y - 1];
                }
            }

            int low = 1, high = Math.min(m, n);//注意我们要找的是正方形
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (existSqure(mat, area, mid)) {
                    result = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
            return result;
        }

        private boolean existSqure(int[][] mat, int[][] area, int side) {
            int m = mat.length, n = mat[0].length;
            for (int x = side; x <= m; x++) {
                for (int y = side; y <= n; y++) {
                    //计算所有可能的正方形元素和
                    if (area[x][y] - area[x - side][y] - area[x][y - side] + area[x - side][y - side] <= threshold)
                        return true;
                }
            }
            return false;
        }
    }


}
