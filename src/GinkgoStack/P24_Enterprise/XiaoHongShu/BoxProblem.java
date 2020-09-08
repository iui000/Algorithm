package GinkgoStack.P24_Enterprise.XiaoHongShu;

import java.util.Arrays;
import java.util.Comparator;

public class BoxProblem {

	public static class Box {
		public int height;
		public int dia;

        public Box(int height, int dia) {
            this.height = height;
            this.dia = dia;
        }
    }

	public static class BoxComparator implements Comparator<Box> {
		@Override
		public int compare(Box o1, Box o2) {
			return o1.height != o2.height ?
                    o1.height - o2.height : o2.dia - o1.dia;
		}
	}

	public static Box[] getSortedBox(int[][] matrix) {
		Box[] res = new Box[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			res[i] = new Box(matrix[i][0], matrix[i][1]);
		}
		//排序
		Arrays.sort(res, new BoxComparator());
		return res;
	}

	public static int maxBoxs(int[][] matrix) {
		Box[] boxs = getSortedBox(matrix);
		int[] ends = new int[matrix.length];
		ends[0] = boxs[0].dia;
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < boxs.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (boxs[i].dia > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = boxs[i].dia;
		}
		return right + 1;
	}

	public static void main(String[] args) {
		int[][] test = { { 100, 65 }, { 150, 70 }, { 90, 56 }, { 190, 75 }, { 95, 60 },
				{ 110, 68 }};
		System.out.println(maxBoxs(test));
	}
}
