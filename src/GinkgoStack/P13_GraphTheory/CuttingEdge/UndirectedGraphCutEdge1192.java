package GinkgoStack.P13_GraphTheory.CuttingEdge;

import java.util.*;

/**
 * 1192. 查找集群内的「关键连接」
 * 力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
 *
 * 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。
 *
 * 从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
 *
 * 「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 *
 * 请你以任意顺序返回该集群内的所有 「关键连接」。
 *
 *
 */
public class UndirectedGraphCutEdge1192 {


    /**
     *     作者：tarira19
     *     链接：https://leetcode-cn.com/problems/critical-connections-in-a-network/solution/1192-java-dfstarjansuan-fa-shi-jian-fu-za-du-ove-b/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * 思路：
     * DFS Tarjan算法（缩点）
     */
    class Solution {
        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            // 构建一个map，存放每个节点的相邻节点有哪些
            Map<Integer, Set<Integer>> map = new HashMap<>();
            buildMap(connections, map);

            // 创建一个数组，存放每个节点的id是什么
            int[] id = new int[n];
            Arrays.fill(id, -1);

            // 选取一个点作为根节点，dfs向下递归，过程中识别出哪个边是critical connection
            List<List<Integer>> res = new ArrayList<>();
            dfs(0, 0, -1, id, map, res);    // 假设根节点有一个编号是-1的父节点

            return res;
        }

        /**
         *
         * @param node
         * @param nodeID 暂时是父节点的编号 +1
         * @param par 父节点的id
         * @param id 每个节点的id值
         * @param map 存放每个节点的相邻节点有哪些
         * @param res
         * @return
         */
        public int dfs(int node, int nodeID, int par, int[] id, Map<Integer, Set<Integer>> map, List<List<Integer>> res){
            id[node] = nodeID;

            Set<Integer> set = map.get(node);
            for(Integer neighbor: set){//相邻有三种情况
                /**
                 * 缩点操作，也就是处于一个环中的点，使用相同的Id号，当成一个合并的点
                 */
                if(neighbor == par){//如果遇到父节点就直接跳过
                    continue;
                }else if(id[neighbor] == -1){//该节点还没有编号，也就是没有被访问过
                    id[node] = Math.min(id[node], dfs(neighbor, nodeID + 1, node, id, map, res));
                }else{//剩下这种情况就是走到了某个已经编号的节点，说明出现回路了
                    id[node] = Math.min(id[node], id[neighbor]);
                }
            }

            //这里很关键，如果这个节点的编号经过计算，还是等于父节点的编号+1，
            // 那么说明该节点与父节点之前的确没有第二条通路，所以它与父节点之间的边就是割边
            if(id[node] == nodeID && node != 0){
                res.add(Arrays.asList(par, node));
            }

            return id[node];
        }

        public void buildMap(List<List<Integer>> con, Map<Integer, Set<Integer>> map){
            for(List<Integer> edge : con){
                int n1 = edge.get(0);
                int n2 = edge.get(1);

                Set<Integer> n1n = map.getOrDefault(n1, new HashSet<>());
                Set<Integer> n2n = map.getOrDefault(n2, new HashSet<>());

                n1n.add(n2);
                n2n.add(n1);

                map.put(n1, n1n);
                map.put(n2, n2n);
            }
        }
    }


}
