package GinkgoStack.P13_GraphTheory.SearchCyclic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**

 *
 * 不论是有向图还是无向图，都可以用DFS
 */
public class HasCyclicIntV {

    /**
     * 参考这个帖子
     * https://www.cnblogs.com/wangkundentisy/p/9320499.html
     * 无论是有向图还是无向图，这个算法通用。
     * 注意，无向图的边初始化时要成对
     * 顶点是整型表示
     *
     *
     *     对图中的所有顶点定义三种状态：顶点未被访问过、顶点刚开始被访问、顶点被访问过并且其所有邻接点也被访问过。
     *     这三种状态，在visited数组中分别用0、1、2来表示。
     */

    List<List<Integer>> adjList;
    int[] visited;
    int[] parent;
    boolean existCyc = false;

    public boolean hasCyc(int nV, int[][] edges) {
        adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < nV; ++i) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }


        visited = new int[nV];
        parent = new int[nV];
        Arrays.fill(parent,-1);
        for (int i = 0; i < nV && !existCyc; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return existCyc;
    }

    public void dfs(int u) {
        visited[u] = 1;
        //邻接矩阵中u的邻接点
        for (int v: adjList.get(u)) {
            //v不是父节点，而且还访问过(而且为状态1，说明不是回溯过来的顶点)，
            // 因此存在环(判断v不是u的父节点)
            if (visited[v] == 1 && parent[u] != v) {

                existCyc = true;
                /**
                 * 打印完整的环：
                 *                 int temp = u;
                 *                 while(temp != v)
                 *                 {
                 *                     System.out.print(temp + "<-");
                 *
                 *                     temp = parent[temp];
                 *                 }
                 *                 System.out.println(temp);
                 */

                return;
            }else if (visited[v] == 0) {
                parent[v] = u;
                dfs(v);
                if (existCyc) {
                    return;
                }
            }
        }
        //遍历完所有的邻接点才变为状态2
        visited[u] = 2;
    }

    public static void main(String[] args) {
        HasCyclicIntV graph = new HasCyclicIntV();

        /**
         * 假设有向图
         */
        int[][] edges = new int[3][2];
        edges[0] = new int[]{0,1};
        edges[1] = new int[]{1,2};
        edges[2] = new int[]{0,2};//只有前三条边，应该输出false
//        edges[2] = new int[]{2,1};//加上这条边应该输出true

        /**
         * 假设无向图
         */
//        int[][] edges = new int[6][2];
//        edges[0] = new int[]{0,1};
//        edges[1] = new int[]{1,0};
//
//        edges[2] = new int[]{1,2};
//        edges[3] = new int[]{2,1};

//        edges[4] = new int[]{0,2};
//        edges[5] = new int[]{2,0};

        System.out.println(graph.hasCyc(3,edges));
    }
}
