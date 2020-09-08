package GinkgoStack.P13_GraphTheory.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class RangeOfMotion {

    /**
     * 这道题用DFS是最优解
     */

    /**
     * 方法1：dfs,有些优化。
     */
    class SolutionDFS1 {
        int m, n, k;
        boolean[][] visited;
        public int movingCount(int m, int n, int k) {
            this.m = m;
            this.n = n;
            this.k = k;

            this.visited = new boolean[m][n];

            return dfs(0, 0, 0, 0);
        }

        /**
         *
         * @param i
         * @param j
         * @param sumi sumi是i的数位和
         * @param sumj sumj是j的数位和
         * @return
         */
        public int dfs(int i, int j, int sumi, int sumj) {
            if(i >= m || j >= n || k < sumi + sumj || visited[i][j])
                return 0;

            visited[i][j] = true;

            //其实，只需要向右边和向下两个方向移动，证明过程见官方题解.
            //另外，数位和进行了简化计算。
            //例如 19变到20，数位和分别为 10, 2 ；例如 1, 2的数位和分别为 1, 2。
            int sumiadd1 = (i + 1) % 10 != 0 ? sumi + 1 : sumi - 8;
            int sumjadd1 = (j + 1) % 10 != 0 ? sumj + 1 : sumj - 8;

            return 1 + dfs(i + 1, j, sumiadd1, sumj) +
                    dfs(i, j + 1, sumi, sumjadd1);
        }
    }

    /**
     * 正常的DFS
     */
    class SolutionDFS2 {
        public int movingCount(int m, int n, int k) {
            //临时变量visited记录格子是否被访问过
            boolean[][] visited = new boolean[m][n];
            return dfs(0, 0, m, n, k, visited);
        }

        public int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
            //i >= m || j >= n是边界条件的判断，k < sum(i, j)判断当前格子坐标是否
            // 满足条件，visited[i][j]判断这个格子是否被访问过
            if (i >= m || j >= n || k < sum(i, j) || visited[i][j])
                return 0;
            //标注这个格子被访问过
            visited[i][j] = true;
            //沿着当前格子的右边和下边继续访问
            return 1 + dfs(i + 1, j, m, n, k, visited) +
                    dfs(i, j + 1, m, n, k, visited);
        }

        //计算两个坐标数字的和
        private int sum(int i, int j) {
            int sum = 0;
            while (i != 0) {
                sum += i % 10;
                i /= 10;
            }
            while (j != 0) {
                sum += j % 10;
                j /= 10;
            }
            return sum;
        }

    }


    /**
     * 方法2：Bfs
     *
     * BFS/DFS ： 两者目标都是遍历整个矩阵，不同点在于搜索顺序不同。
     * DFS 是朝一个方向走到底，再回退，以此类推；BFS 则是按照“平推”的方式向前搜索。
     * BFS 实现： 通常利用队列实现广度优先遍历。
     * 算法解析：
     * 初始化： 将机器人初始点 (0, 0)(0,0) 加入队列 queue ；
     * 迭代终止条件： queue 为空。代表已遍历完所有可达解。
     * 迭代工作：
     * 单元格出队： 将队首单元格的 索引、数位和 弹出，作为当前搜索单元格。
     * 判断是否跳过： 若 ① 行列索引越界 或 ② 数位和超出目标值 k 或 ③ 当前元素已访问过 时，执行 continue 。
     * 标记当前单元格 ：将单元格索引 (i, j) 存入 Set visited 中，代表此单元格 已被访问过 。
     * 单元格入队： 将当前元素的 下方、右方 单元格的 索引、数位和 加入 queue 。
     * 返回值： Set visited 的长度 len(visited) ，即可达解的数量。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class SolutionBFS1 {
        public int movingCount(int m, int n, int k) {
            boolean[][] visited = new boolean[m][n];
            int res = 0;
            Queue<int[]> queue= new LinkedList<int[]>();
            queue.add(new int[] { 0, 0, 0, 0 });

            while(queue.size() > 0) {
                int[] x = queue.poll();
                int i = x[0], j = x[1], si = x[2], sj = x[3];

                if(i >= m || j >= n || k < si + sj || visited[i][j])
                    continue;

                visited[i][j] = true;
                res++;

                //其实，只需要向右边和向下两个方向移动，证明过程见官方题解.
                //另外，数位和进行了简化计算。
                //例如 19变到20，数位和分别为 10, 2 ；例如 1, 2的数位和分别为 1, 2。
                int sumiadd1 = (i + 1) % 10 != 0 ? si + 1 : si - 8;
                int sumjadd1 = (j + 1) % 10 != 0 ? sj + 1 : sj - 8;

                queue.add(new int[] { i + 1, j, sumiadd1, sj });
                queue.add(new int[] { i, j + 1, si, sumjadd1});
            }
            return res;
        }
    }


    /**
     * 常规的BFS
     */
    class SolutionBFS2 {
        public int movingCount(int m, int n, int k) {
            //临时变量visited记录格子是否被访问过
            boolean[][] visited = new boolean[m][n];
            int res = 0;
            //创建一个队列，保存的是访问到的格子坐标，是个二维数组
            Queue<int[]> queue = new LinkedList<>();
            //从左上角坐标[0,0]点开始访问，add方法表示把坐标
            // 点加入到队列的队尾
            queue.add(new int[]{0, 0});
            while (queue.size() > 0) {
                //这里的poll()函数表示的是移除队列头部元素，因为队列
                // 是先进先出，从尾部添加，从头部移除
                int[] x = queue.poll();
                int i = x[0], j = x[1];
                //i >= m || j >= n是边界条件的判断，k < sum(i, j)判断当前格子坐标是否
                // 满足条件，visited[i][j]判断这个格子是否被访问过
                if (i >= m || j >= n || k < sum(i, j) || visited[i][j])
                    continue;
                //标注这个格子被访问过
                visited[i][j] = true;
                res++;
                //把当前格子右边格子的坐标加入到队列中
                queue.add(new int[]{i + 1, j});
                //把当前格子下边格子的坐标加入到队列中
                queue.add(new int[]{i, j + 1});
            }
            return res;
        }

        //计算两个坐标数字的和
        private int sum(int i, int j) {
            int sum = 0;
            while (i != 0) {
                sum += i % 10;
                i /= 10;
            }
            while (j != 0) {
                sum += j % 10;
                j /= 10;
            }
            return sum;
        }

    }



}
