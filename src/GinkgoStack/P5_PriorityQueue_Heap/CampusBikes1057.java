package GinkgoStack.P5_PriorityQueue_Heap;

import java.util.*;

/**
 * 1057. 校园自行车分配
 * 在由 2D 网格表示的校园里有 n 位工人（worker）和 m 辆自行车（bike），n <= m。
 * 所有工人和自行车的位置都用网格上的 2D 坐标表示。
 *
 * 我们需要为每位工人分配一辆自行车。在所有可用的自行车和工人中，
 * 我们选取彼此之间曼哈顿距离最短的工人自行车对  (worker, bike) ，
 * 并将其中的自行车分配給工人。如果有多个 (worker, bike) 对之间的曼哈顿距离相同，
 * 那么我们选择工人索引最小的那对。类似地，如果有多种不同的分配方法，
 * 则选择自行车索引最小的一对。不断重复这一过程，直到所有工人都分配到自行车为止。
 *
 * 给定两点 p1 和 p2 之间的曼哈顿距离为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。
 *
 * 返回长度为 n 的向量 ans，其中 a[i] 是第 i 位工人分配到的自行车的索引（从 0 开始）。
 *
 * 输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * 输出：[1,0]
 * 解释：
 * 工人 1 分配到自行车 0，因为他们最接近且不存在冲突，工人 0 分配到自行车 1 。
 * 所以输出是 [1,0]。
 *
 * 输入：workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * 输出：[0,2,1]
 * 解释：
 * 工人 0 首先分配到自行车 0 。工人 1 和工人 2 与自行车 2 距离相同，
 * 因此工人 1 分配到自行车 2，工人 2 将分配到自行车 1 。因此输出为 [0,2,1]。
 *  
 *
 * 提示：
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * 所有工人和自行车的位置都不相同。
 * 1 <= workers.length <= bikes.length <= 1000
 *
 */
public class CampusBikes1057 {

    /**
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x8uf6j/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        /**
         * 这是一道排序题
         * @param workers 工人坐标
         * @param bikes 自行车坐标
         * @return
         */
        public int[] assignBikes(int[][] workers, int[][] bikes) {

            //对距离对象排序
            Queue<int[]> queue = new PriorityQueue<int[]>((a, b) -> {
                if(a[0] != b[0]) {//距离
                    return a[0] - b[0];
                } else {//如果距离相等，就优先根据工人编号排序，最后根据车的编号排序
                    return a[1] == b[1] ? a[2] - b[2] : a[1] - b[1];
                }
            });

            //计算每个人到每一辆车的距离，以及人的编号 和 车的编号
            for(int i = 0; i < workers.length; i++) {//工人的编号
                for(int j = 0; j < bikes.length; j++) {//自行车的编号
                    queue.add(new int[]{//将这个数组看成一个距离对象
                            Math.abs(bikes[j][0] - workers[i][0]) +
                                    Math.abs(bikes[j][1] - workers[i][1]),
                            i, j});
                }
            }

            //为每一个工人分配单车
            int[] result = new int[workers.length];
            Arrays.fill(result, -1);

            Set<Integer> inQueued = new HashSet<>();//记录已经被分配的单车，放置重复分配
            while(inQueued.size() < workers.length) {
                int[] dist = queue.poll();//从小到大弹出距离对象
                //dist[1]是工人编号，dist[2]是单车的编号
                if(result[dist[1]] == -1 && !inQueued.contains(dist[2])) {
                    result[dist[1]] = dist[2];
                    inQueued.add(dist[2]);//将该单车加入到已分配队列
                }
            }
            return result;
        }
    }


}
