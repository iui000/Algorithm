package GinkgoStack.P20_DynamicProgramming.MinimumEditCost;

/**
 * 最小编辑代价
 * 给定两个字符串str1，str2，再给定三个整数ic，dc,rc，分别代表插入、删除、替换一个字符的代价，
 * 返回str1编辑成str2的最小代价
 */
public class Problem_13_EditCost {

    /**
     * 经典二维DP
     *
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
	public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int row = chs1.length + 1;
		int col = chs2.length + 1;
		int[][] dp = new int[row][col];//dp[i][j]表示将str1[0..i-1]编辑成str2[0..j-1]的最小代价
        //初始化第一列
        //把str1[0..i-1]编辑成空串的最小代价
		for (int i = 1; i < row; i++) {
			dp[i][0] = dc * i;//删除操作
		}
		//初始化第一行
        //把空串编辑成str2[0..j-1]的最小代价
		for (int j = 1; j < col; j++) {
			dp[0][j] = ic * j;//插入操作
		}

        /**
         * 接下来分四种情况
         * 从上到下，从左到右
         */
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (chs1[i - 1] == chs2[j - 1]) {//如果当前这两个字符相同，就不用编辑了
					dp[i][j] = dp[i - 1][j - 1];
				} else {//否则当前两个字符不同，可以选择替换字符
					dp[i][j] = dp[i - 1][j - 1] + rc;
				}
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
			}
		}
		return dp[row - 1][col - 1];
	}


    /**
     * 空间压缩
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */

	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) { // str2�ϳ��ͽ���ic��dc��ֵ
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int dp_i_1_j_1 = dp[0]; // dp_i_1_j_1表示左边上角的值dp[i-1][j-1]
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j]; // dp[j]û����ǰ�ȱ�������
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = dp_i_1_j_1;
				} else {
					dp[j] = dp_i_1_j_1 + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				dp_i_1_j_1 = tmp; // pre���dp[j]û����ǰ��ֵ
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(minCost1(str1, str2, 5, 3, 2));
		System.out.println(minCost2(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 3, 2, 4));
		System.out.println(minCost2(str1, str2, 3, 2, 4));

		str1 = "";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 1, 7, 5));
		System.out.println(minCost2(str1, str2, 1, 7, 5));

		str1 = "abcdf";
		str2 = "";
		System.out.println(minCost1(str1, str2, 2, 9, 8));
		System.out.println(minCost2(str1, str2, 2, 9, 8));

	}

}
