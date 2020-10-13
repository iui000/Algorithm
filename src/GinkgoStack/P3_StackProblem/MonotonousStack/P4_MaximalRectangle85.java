package GinkgoStack.P3_StackProblem.MonotonousStack;

import java.util.Stack;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class P4_MaximalRectangle85 {
    /**
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xuwahd/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public class Solution {
        public int maximalRectangle(char[][] matrix){
            if(matrix==null || matrix.length == 0){
                return 0;
            }

            int[][] heights = new int[matrix.length][matrix[0].length];

            for (int row = 0; row < matrix.length; row++){
                for(int col=0; col<matrix[0].length;col++){
                    if(row==0){
                        heights[row][col]=matrix[row][col]=='0' ? 0:1;
                    }
                    else
                    {
                        heights[row][col]=matrix[row][col]=='0' ? 0:heights[row-1][col]+1;
                    }
                }
            }
            int max = 0;
            for(int row=0; row<heights.length; row++){
                max = Math.max(max, maxArea(heights[row]));
            }
            return max;
        }

        private int maxArea(int[] heights){
            if(heights==null||heights.length ==0){
                return 0;
            }
            Stack<Integer> stack = new Stack<Integer>();
            int max = 0;

            int i = 0;
            while(i<heights.length){
                if(stack.isEmpty() || heights[stack.peek()] <= heights[i]){
                    stack.push(i++);
                } else {
                    int h = heights[stack.pop()];
                    int width = stack.isEmpty()?i:i-stack.peek()-1;
                    max = Math.max(max, h*width);
                }
            }

            //这一点是与雨水题的唯一区别
            while(!stack.isEmpty()){
                int height=heights[stack.pop()];

                int width=stack.isEmpty()?i:i-stack.peek()-1;
                max=Math.max(max, height*width);
            }
            return max;
        }
    }


}
