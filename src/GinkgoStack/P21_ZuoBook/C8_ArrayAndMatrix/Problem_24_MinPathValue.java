package GinkgoStack.P21_ZuoBook.C8_ArrayAndMatrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 01矩阵中从左上角走到右下角的最短通路
 */

public class Problem_24_MinPathValue {

	public static int minPathValue(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0
                || matrix[0][0] != 1
				|| matrix[matrix.length - 1][matrix[0].length - 1] != 1) {
		    
			return 0;
		}
		
		
		int res = 0;
		//map表示从位置(0,0)到(i,j)的最短距离
		int[][] map = new int[matrix.length][matrix[0].length];
		map[0][0] = 1;

		//行队列和列队列
		Queue<Integer> rQ = new LinkedList<Integer>();
		Queue<Integer> cQ = new LinkedList<Integer>();
		rQ.add(0);
		cQ.add(0);

		int r = 0;
		int c = 0;
		while (!rQ.isEmpty()) {
			r = rQ.poll();
			c = cQ.poll();
			//走到右下角了
			if (r == matrix.length - 1 && c == matrix[0].length - 1) {
				return map[r][c];
			}
			//此位置的相邻四个方向
			walkTo(map[r][c], r - 1, c, matrix, map, rQ, cQ); // pre
			walkTo(map[r][c], r + 1, c, matrix, map, rQ, cQ); // next
			walkTo(map[r][c], r, c - 1, matrix, map, rQ, cQ); // left
			walkTo(map[r][c], r, c + 1, matrix, map, rQ, cQ); // right
		}
		return res;
	}

    /**
     *
     * @param pre 表示前一个位置的路径距离（距离点（0,0）的距离）
     * @param toR
     * @param toC
     * @param matrix
     * @param map
     * @param rQ
     * @param cQ
     */
	public static void walkTo(int pre,
                              int toR, int toC, int[][] matrix,
			                   int[][] map, Queue<Integer> rQ, Queue<Integer> cQ) {

		if (toR < 0 || toR == matrix.length
                || toC < 0 || toC == matrix[0].length
				|| matrix[toR][toC] != 1
                || map[toR][toC] != 0) {// map[toR][toC] != 0 说明这个位置已经访问过了

			return;
		}
		//路径在前一个位置的基础上+1
		map[toR][toC] = pre + 1;
		rQ.add(toR);
		cQ.add(toC);
	}

	public static void main(String[] args) {
		int[][] matrix = {
		        { 1, 0, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 0, 1 } };
		System.out.println(minPathValue(matrix));

	}
}
