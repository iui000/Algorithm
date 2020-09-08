package GinkgoStack.P13_GraphTheory.BFS;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 994. 腐烂的橘子
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 *
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 *
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *  
 *
 * 提示：
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotting-oranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OrangesRotting994 {

    /**
     * 方法二：多源广度优先搜索
     *
     * 思路
     *
     * 观察到对于所有的腐烂橘子，其实它们在广度优先搜索上是等价于同一层的节点的。
     *
     * 假设这些腐烂橘子刚开始是新鲜的，而有一个腐烂橘子(我们令其为超级源点)
     * 会在下一秒把这些橘子都变腐烂，而这个腐烂橘子刚开始在的时间是 -1−1 ，那么按照广度优先搜索的算法，
     * 下一分钟也就是第 00 分钟的时候，这个腐烂橘子会把它们都变成腐烂橘子，然后继续向外拓展，
     * 所以其实这些腐烂橘子是同一层的节点。那么在广度优先搜索的时候，我们将这些腐烂橘子都放进队列里进行广度优先搜索即可，
     * 最后每个新鲜橘子被腐烂的最短时间 dis[x][y]dis[x][y] 其实是以这个超级源点的腐烂橘子为起点的广度优先搜索得到的结果。
     *
     * 为了确认是否所有新鲜橘子都被腐烂，可以记录一个变量 cntcnt 表示当前网格中的新鲜橘子数，
     * 广度优先搜索的时候如果有新鲜橘子被腐烂，则 cnt-=1 ，最后搜索结束时如果 cntcnt 大于 00 ，
     * 说明有新鲜橘子没被腐烂，返回 -1−1 ，否则返回所有新鲜橘子被腐烂的时间的最大值即可，
     * 也可以在广度优先搜索的过程中把已腐烂的新鲜橘子的值由 11 改为 22，最后看网格中是否由值为 11 即新鲜的橘子即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotting-oranges/solution/fu-lan-de-ju-zi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Solution {

        //四个方向的行坐标增量和列坐标增量
        int[] directionIncreR = new int[]{-1, 0, 1, 0};
        int[] directionIncreC = new int[]{0, -1, 0, 1};

        public int orangesRotting(int[][] grid) {
            int R = grid.length, C = grid[0].length;

            // queue : all starting cells with rotten oranges
            Queue<Integer> queue = new ArrayDeque();
            Map<Integer, Integer> depth = new HashMap();
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c)
                    if (grid[r][c] == 2) {//2表示腐烂的橘子
                        int code = r * C + c;//把二维转为一维
                        queue.add(code);
                        depth.put(code, 0);//腐烂的橘子的深度为0
                    }


            int ans = 0;
            while (!queue.isEmpty()) {
                int code = queue.remove();
                int r = code / C, c = code % C;//计算还原出行和列的坐标
                for (int k = 0; k < 4; ++k) {//四个方向
                    int nr = r + directionIncreR[k];
                    int nc = c + directionIncreC[k];
                    //感染新鲜的橘子
                    if (0 <= nr && nr < R && 0 <= nc && nc < C &&
                            grid[nr][nc] == 1) {

                        grid[nr][nc] = 2;//将新鲜橘子变为腐烂橘子
                        //然后把它加入到队列中
                        int ncode = nr * C + nc;
                        queue.add(ncode);
                        depth.put(ncode, depth.get(code) + 1);//计算深度，也就是腐烂的时间
                        ans = depth.get(ncode);
                    }
                }
            }

            //有可能有一个新鲜橘子自成一堆，与其他橘子不连通，因此经过前面的DFS，他们还是保持新鲜
            //因此需要扫描一遍矩阵，如果存在新鲜橘子，那就返回-1
            for (int[] row: grid)
                for (int v: row)
                    if (v == 1)
                        return -1;
            return ans;

        }
    }

}
