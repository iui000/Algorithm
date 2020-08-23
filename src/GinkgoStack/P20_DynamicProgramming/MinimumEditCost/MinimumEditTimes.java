package GinkgoStack.P20_DynamicProgramming.MinimumEditCost;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class MinimumEditTimes {

    /**
     * 看左神代码就行，插入，替换、删除的代价改为1就行
     * @param str1
     * @param str2
     * @return
     */
    public int minDistance(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = chs1.length + 1;
        int col = chs2.length + 1;
        int[][] dp = new int[row][col];//dp[i][j]表示将str1[0..i-1]编辑成str2[0..j-1]的最小代价(等于次数)
        //初始化第一列
        //把str1[0..i-1]编辑成空串的最小代价(等于次数)
        for (int i = 1; i < row; i++) {
            dp[i][0] = i;//删除操作
        }
        //初始化第一行
        //把空串编辑成str2[0..j-1]的最小代价(等于次数)
        for (int j = 1; j < col; j++) {
            dp[0][j] = j;//插入操作
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
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
            }
        }
        return dp[row - 1][col - 1];
    }
}
