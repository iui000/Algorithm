package GinkgoStack.P21_ZuoBook.C1_StackAndQueue;

import java.util.Stack;

/**
 * 求最大子矩阵的大小(可以理解为面积)
 * 矩形int[n][m]
 * 用动态规划逐行计算出的height[m](代表m根柱子)；
 * 根据height[m]，并借住单调栈计算每根柱子能向左右扩展的区域curArea；
 *
 * 通过每根柱子的curArea得出以每行为底的maxArea；最后得出全局的maxArea
 */
public class Problem_09_MaximalRectangle {

    /**
     * 计算全局的最大矩形面积
     * @param map
     * @return
     */
	public static int maxRecSize(int[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}

		int maxArea = 0;
		int[] height = new int[map[0].length];
        /**
         * 对每一行遍历，得出该行的height数组(会用到上一行的height)；
         * 并根据该行的height数组计算：以该行为底的最大矩形面积maxArea；
         * 每一行遍历完，即可得到全局的maxArea
         */
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
			    //该行的height数组
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}

			//比较以每一行为的maxRec
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}
		return maxArea;
	}

    /**
     * 并根据该行的height数组计算出以该行为底的最大矩形面积maxArea；
     * @param height
     * @return
     */
	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
        /**
         * 遍历解阶段，根据出入栈规则，从头到尾遍历height数组；
         * 过程中，计算出所有被弹出的直方块它能向左右扩展的区域curArea；
         * (当然，与当前值相等的那个被弹出直方块(height[i] <= height[stack.peek()])，
         * 计算出的curArea偏小，但是等到i被弹出时，会得到准确值)
         */
		for (int i = 0; i < height.length; i++) {
            /**
             * 出栈规则：
             * 只有当前i位置的值 小于等于 当前栈顶位置所代表的值height[stack.peek()]时，
             * 则不断弹出栈顶元素(代表一个柱子)，出栈时，即可计算出该柱子能向左右扩展的curArea；
             * 直到某一个栈顶柱子的值小于当前值height[i]，再把位置i压入栈
             */
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				//根据单调栈的性质，height[k] 是 height[j]左边最近的那个小于height[j]的值
                //因此左边能扩展到k+1,栈为空时得取-1，因为左边没有柱子了
				int k = stack.isEmpty() ? -1 : stack.peek();

				//右边第一个 小于等于height[j]的是height[i]，因此右边能扩展到i-1；
                //计算出它能向左右扩展的curArea = [(i-1)-(k+1)+1]*height[j]
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}

            /**
             * 入栈规则：
             * 只有当前i位置的值 大于 当前栈顶位置所代表的值height[stack.peek()]时，才将i位置入栈
             * height[i] > height[stack.peek()]
             */
			stack.push(i);
		}

        /**
         * 清算阶段：
         * 遍历阶段结束以后，栈中还存在部分没有扩展的柱子，将它们接着清算完；
         *
         */
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();

			//需要注意的是，此阶段height[]已经不能再向右扩展了,
            // 因此可以认为当前柱子i为越界后的height.length，柱子的大小为极小值0
            //其他都一样，以此出栈计算即可
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[][] map = { 
				{ 1, 0, 1, 1 }, 
				{ 1, 1, 1, 1 }, 
				{ 1, 1, 1, 0 }, 
		};
		System.out.println(maxRecSize(map));
	}

}
