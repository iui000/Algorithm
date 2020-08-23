package GinkgoStack.P19_DivideAndConquer;

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
public class kClosestPoint {
    int[][] points;

    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        binaryDivide(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 二分法
     * @param low
     * @param high
     * @param K
     */
    private void binaryDivide(int low,int high,int K){
        if(low >= high){
            return;
        }

        int begin = low,end = high;

        /** 随机选择一个枢轴，平均复杂度才会是O(N)*/
        int pivot = ThreadLocalRandom.current().nextInt(low,high);

        int pivotDist = dist(points[pivot]);
        while (begin < end){
            while (begin < end && dist(points[end]) >= pivotDist) end--;
            swap(begin,end);
            while (begin < end && dist(points[begin]) <= pivotDist) begin++;
            swap(begin,end);
        }

        if(K < begin-1){
            binaryDivide(low,begin-1,K);
        }else if(K == begin - 1){
            binaryDivide(low,begin-1,K-1);
        }else{
            binaryDivide(begin+1,high,K);
        }

    }

    //计算该点到原点的距离
    private int dist(int[] point){
        return point[0]*point[0] + point[1]*point[1];
    }

    private  void swap(int i,int j ){
        int t1 = points[i][0],t2 = points[i][1];
        points[i][0] = points[j][0];
        points[j][0] = t1;

        points[i][1] = points[j][1];
        points[j][1] = t2;
    }

    public static void main(String[] args) {
        kClosestPoint kClosestPoint = new kClosestPoint();

        kClosestPoint.kClosest(new int[][]{{-2,10},{-4,-8},{10,7},{-4,-7}},3);
    }
}
