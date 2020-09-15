package GinkgoStack.P21_ZuoBook.C8_ArrayAndMatrix;

/**
 * תȦ��ӡ����
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
     * ��ӡ�����
     * @param m
     * @param leftUpRow
     * @param leftUpCol
     * @param rightBottomRow
     * @param rightBottomCol
     */
	public static void printEdge(int[][] m, int leftUpRow, int leftUpCol, int rightBottomRow, int rightBottomCol) {
		if (leftUpRow == rightBottomRow) {
		    // �Ӿ���ֻ��һ��ʱ
			for (int i = leftUpCol; i <= rightBottomCol; i++) {
				System.out.print(m[leftUpRow][i] + " ");
			}
		} else if (leftUpCol == rightBottomCol) {
		    // �Ӿ���ֻ��һ��ʱ
			for (int i = leftUpRow; i <= rightBottomRow; i++) {
				System.out.print(m[i][leftUpCol] + " ");
			}
		} else { // һ�����
			int curCol = leftUpCol;
			int curRow = leftUpRow;
			//��ӡ����һ��
			while (curCol != rightBottomCol) {
				System.out.print(m[leftUpRow][curCol] + " ");
				curCol++;
			}
			//�ұ�һ��
			while (curRow != rightBottomRow) {
				System.out.print(m[curRow][rightBottomCol] + " ");
				curRow++;
			}
			//�±�һ�У�ǰ���ӡ����һ��ʱ��curCol�Ѿ��ߵ����ұ��ˣ������ٴ����ߵ������
			while (curCol != leftUpCol) {
				System.out.print(m[rightBottomRow][curCol] + " ");
				curCol--;
			}
			//��ӡ���һ�У�ǰ���ӡ�ұ�һ��ʱ��curRow�Ѿ��ߵ����±��ˣ������ٴ����ߵ��Ͼ���
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
