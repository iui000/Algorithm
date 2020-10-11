package GinkgoStack.P18_BackTrackingAndRecursion.nQueen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 *
 * 提示：
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class NQueens51 {

    /**
     * 稍微修改下左神的代码就行了
     */

    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        //record[i] == j，表示位置（i,j）已经放置了皇后
        //也就是说record[i]表示的是第i行皇后的所在的列数
        int[] record = new int[n];
        recursive(0, record, n);

        return ans;
    }

    /**
     * 把递归过程直接设计成逐行放置皇后，这样就间接地实现了皇后不能处于同一行
     *
     * @param i 行
     * @param record
     * @param n
     * @return
     */
    public  int recursive(int i, int[] record, int n) {
        if (i == n) {//终止条件
            ans.add(generateBoard(record));//将该放置方案加入到结果集中
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {//列
            if (isValid(record, i, j)) {//判断位置（i,j）能不能放置
                record[i] = j;//放置皇后
                res += recursive(i + 1, record, n);//下一行
            }
        }
        return res;
    }

    /**
     *
     * @param record
     * @param i
     * @param j
     * @return
     */
    public static boolean isValid(int[] record, int i, int j) {
        //根据前i-1行的情况，决定当前行有效的位置
        for (int k = 0; k < i; k++) {//行
            //ֻ只要和已放置的皇后同行/同列/同斜线就不行
            //j == record[k] 说明与地k行同列了，不能再放置了
            //行列差值绝对值相等：Math.abs(record[k] - j) == Math.abs(i - k)，说明处于斜线上
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据record生成一个放置方案
     * @param record
     * @return
     */
    public List<String> generateBoard(int[] record) {
        List<String> solu = new ArrayList<>(record.length);
        for (Integer col:record){
            char[] row = new char[record.length];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            solu.add(new String(row));
        }

        return solu;
    }
}
