package GinkgoStack.P21_ZuoBook.C8_ArrayAndMatrix;

/**
 * 转圈打印矩阵
 */
public class Problem_01_PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matrix) {
		int leftUpRow = 0;
		int leftUpCol = 0;

		int rightBottomRow = matrix.length - 1;
		int rightBottomCol = matrix[0].length - 1;

		while (leftUpRow <= rightBottomRow && leftUpCol <= rightBottomCol) {
			printEdge(matrix,
                    leftUpRow++, leftUpCol++,
                    rightBottomRow--, rightBottomCol--);
		}
	}

    /**
     * 打印最外层
     * @param m
     * @param leftUpRow
     * @param leftUpCol
     * @param rightBottomRow
     * @param rightBottomCol
     */
	public static void printEdge(int[][] m, int leftUpRow, int leftUpCol, int rightBottomRow, int rightBottomCol) {
		if (leftUpRow == rightBottomRow) {
		    // 子矩阵只有一行时
			for (int i = leftUpCol; i <= rightBottomCol; i++) {
				System.out.print(m[leftUpRow][i] + " ");
			}
		} else if (leftUpCol == rightBottomCol) {
		    // 子矩阵只有一列时
			for (int i = leftUpRow; i <= rightBottomRow; i++) {
				System.out.print(m[i][leftUpCol] + " ");
			}
		} else { // 一般情况
			int curCol = leftUpCol;
			int curRow = leftUpRow;
			//打印上面一行
			while (curCol != rightBottomCol) {
				System.out.print(m[leftUpRow][curCol] + " ");
				curCol++;
			}
			//右边一列
			while (curRow != rightBottomRow) {
				System.out.print(m[curRow][rightBottomCol] + " ");
				curRow++;
			}
			//下边一行，前面打印上面一行时，curCol已经走到最右边了，现在再从右走到左就行
			while (curCol != leftUpCol) {
				System.out.print(m[rightBottomRow][curCol] + " ");
				curCol--;
			}
			//打印左边一列，前面打印右边一列时，curRow已经走到最下边了，现在再从下走到上就行
			while (curRow != leftUpRow) {
				System.out.print(m[curRow][leftUpCol] + " ");
				curRow--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = {
		        { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 }
		};
		spiralOrderPrint(matrix);

	}

}
