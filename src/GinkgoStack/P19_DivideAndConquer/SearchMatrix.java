package GinkgoStack.P19_DivideAndConquer;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 */
public class SearchMatrix {
    /**
     * 这道题有多种方法
     * 要掌握分治算法
     */

    private int[][] matrix;
    private int target;

    private boolean searchRec(int left, int up, int right, int down) {

        //判断上下左右位置，基本条件
        if (left > right || up > down) {
            return false;
        } else if (target < matrix[up][left] || target > matrix[down][right]) {
            //如果左上角的元素大于target，那么target一定不在这个矩阵内；
            //如果右下角的元素小于target，那么target也一定不在这个矩阵内；
            return false;
        }

        //沿着索引行的矩阵中间列
        int mid = left + (right-left)/2;

        //现在，在中间列上，从上到下，去定位满足关系matrix[row-1][mid] < target < matrix[row][mid]的那个row
        //同时，判断matrix[row][mid] == target
        int row = up;
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        //这里的递归要当心
        //当找到row后，现在处于matrix[row][mid]这个位置， 那么target就只可能处于左下方的矩形区域 或者 右上方的矩形区域；（不包括中间列）
        /**
         * 举例：target =10；那么第一次划分就是这样：
         *  * [
         *  *   [1,   4,  7,|11, 15],
         *  *   [2,   5,  8,|12, 19],
         *  *   [3,   6,  9,|16, 22],
         *      --------------------
         *  *   [10, 13,|14,17, 24],
         *  *   [18, 21,|23,26, 30]
         *  * ]
         */
        return searchRec(left, row, mid-1, down) ||
                searchRec(mid+1, up, right, row-1);
    }

    public boolean searchMatrix(int[][] mat, int targ) {
        // cache input values in object to avoid passing them unnecessarily
        // to `searchRec`
        matrix = mat;
        target = targ;

        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        return searchRec(0, 0, matrix[0].length-1, matrix.length-1);
    }

}
