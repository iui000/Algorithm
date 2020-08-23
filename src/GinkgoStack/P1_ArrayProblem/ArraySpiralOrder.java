package GinkgoStack.P1_ArrayProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋数组 输出
 */
public class ArraySpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        //设置最外层左上角和右下角的坐标
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom) {
            //上边，包含右上角的点
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            //右边，包含右下角的点
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            //先判断有没有下边界 和 左边界
            if (left < right && top < bottom) {
                //下边，不包含右下角的点
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                //左边，包含左下角的点
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }

            //向内收缩一层
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

}
