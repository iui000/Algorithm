package GinkgoStack.P7_Sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 973. 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 *
 * （这里，平面上两点之间的距离是欧几里德距离。）
 *
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 *
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 *
 * 提示：
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */


/**
 * 也可以直接用快排和堆排序，取出前k个即可
 * 有个测试用例，貌似官方的预期输出有问题
 * 输入：
 * [[3,3],[5,-1],[-2,4]]
 * 2
 * 输出：
 * [[-2,4],[5,-1]]
 * 预期：
 * [[3,3],[-2,4]]  纳尼？？？
 */
public class KclosestPointsToOrigin973 {

    /**
     * 正确答案
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x86tih/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution1 {
        public int[][] kClosest(int[][] points, int K) {
            int l = 0, r = points.length - 1;
            sort(points, l, r, K);
            return Arrays.copyOfRange(points, 0, K);
        }

        private void sort(int[][] points, int l, int r, int K) {
            while (l <= r) {
                int pivot = partition(points, l, r);
                if (pivot == K) break;
                if (pivot < K) {
                    l = pivot + 1;
                } else {
                    r = pivot - 1;
                }
            }
        }

        private int partition(int[][] points, int l, int r) {
            int[] pivot = points[l];
            while (l < r) {
                while (l < r && dist(points[r], pivot) > 0) r--;
                points[l] = points[r];
                while (l < r && dist(points[l], pivot) <= 0) l++;
                points[r] = points[l];

            }
            points[l] = pivot;
            return l;
        }

        private int dist(int[] p1, int[] p2) {
            return p1[0] * p1[0] + p1[1] * p1[1] -
                    p2[0] * p2[0] - p2[1] * p2[1];
        }
    }



    /**
     * 官方题解
     * 这个题解有点问题，会超时
     *     作者：LeetCode
     *     链接：https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/zui-jie-jin-yuan-dian-de-k-ge-dian-by-leetcode/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution2 {
        int[][] points;
        public int[][] kClosest(int[][] points, int K) {
            this.points = points;
            work(0, points.length - 1, K);
            return Arrays.copyOfRange(points, 0, K);
        }

        public void work(int i, int j, int K) {
            if (i >= j) return;
            int oi = i, oj = j;
            //枢轴是随机选的，这样理论上，时间复杂度才是O（n）
            int pivot = dist(ThreadLocalRandom.current().nextInt(i, j));

            while (i < j) {
                while (i < j && dist(i) < pivot) i++;
                while (i < j && dist(j) > pivot) j--;
                swap(i, j);
            }

            if (K <= i - oi + 1)//
                work(oi, i, K);
            else
                work(i+1, oj, K - (i - oi + 1));
        }

        public int dist(int i) {
            return points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        public void swap(int i, int j) {
            int t0 = points[i][0], t1 = points[i][1];
            points[i][0] = points[j][0];
            points[i][1] = points[j][1];
            points[j][0] = t0;
            points[j][1] = t1;
        }
    }





//    public static void main(String[] args) {
//        kClosestPoint973 kClosestPoint = new kClosestPoint973();
//
//        kClosestPoint.kClosest(new int[][]{{-2,10},{-4,-8},{10,7},{-4,-7}},3);
//    }
}
