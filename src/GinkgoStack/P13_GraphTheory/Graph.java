package GinkgoStack.P13_GraphTheory;

import java.util.LinkedList;

/**
 * 图，图的构建，深度优先遍历，广度优先遍历，prim算法最小生成树
 */
public class Graph {

    private int vertexSize;                 // 顶点数量
    private int[][] edges;                 // 存放数据的二维数组,i到j的权值
    private static final int MAX_WEIGHT = Integer.MAX_VALUE;        // 无穷大
    private boolean[] isVisited;            // 存放被访问的节点


    public Graph(int vertexSize) {
        this.vertexSize = vertexSize;
        edges = new int[vertexSize][vertexSize];
        isVisited = new boolean[vertexSize];
    }


    /**
     * 获取某个顶点的出度
     *
     * @param index 顶点序号
     * @return 出度
     */
    public int getOutDegree(int index) {
        int degree = 0;
        for (int i = 0; i < vertexSize; i++) {
            int weight = edges[index][i];
            if (weight > 0 && weight < MAX_WEIGHT) {
                degree++;
            }
        }
        return degree;
    }

    /**
     * 获取某个顶点的入度
     *
     * @param index 顶点序号
     * @return 入度
     */
    public int getInDegree(int index) {
        int degree = 0;
        for (int i = 0; i < vertexSize; i++) {
            int weight = edges[i][index];
            if (weight > 0 && weight < MAX_WEIGHT) {
                degree++;
            }
        }
        return degree;
    }


    /**
     * 获取某顶点的第一个邻接点的权值
     *
     * @param index 顶点
     * @return 第一个邻接点
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexSize; i++) {
            int weight = edges[index][i];
            if (weight > 0 && weight < MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 获取顶点v，从index开始的后续邻接顶点
     *
     * @param v     顶点v
     * @param index 开始的邻接点
     * @return 后续点
     */
    public int getNextNeighbor(int v, int index) {
        for (int i = index + 1; i < vertexSize; i++) {
            int weight = edges[v][i];
            if (weight > 0 && weight < MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 图的深度优先遍历
     *
     * @param index 遍历的顶点
     */
    public void depthFirstSearch(int index) {
        isVisited[index] = true;
        int w = getFirstNeighbor(index);
        while (w != -1) {
            if (!isVisited[w]) {
                System.out.println("访问到了节点：" + w + "，权值：" + edges[index][w]);
                depthFirstSearch(w);
            }
            w = getNextNeighbor(index, w);
        }
    }


    /**
     * 图的广度优先遍历，利用队列存储要遍历的起始顶点
     *
     * @param index 遍历开始的顶点
     */
    public void broadFirstSearch(int index) {
        isVisited[index] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.println("访问到了节点：" + index);
        queue.add(index);

        while (!queue.isEmpty()) {
            int next = queue.removeFirst();
            int w = getFirstNeighbor(next);
            while (w != -1) {
                if (!isVisited[w]) {
                    queue.add(w);
                    isVisited[w] = true;
                    System.out.println("访问到了节点：" + w + "，权值：" + edges[next][w]);
                }
                w = getNextNeighbor(next, w);
            }
        }
    }


    /**
     * 最小生成树，普里姆(prim)算法
     */
    public void createMinSpanTreePrim() {
        /**
         * 记录从顶点集合U到V-U的代价最小的边，需要一个辅助数组
          */
        int[] lowCostEdge = new int[vertexSize];

        // 初始化辅助数组为第一个顶点到其他所有顶点的权值
        System.arraycopy(edges[0], 0,
                lowCostEdge, 0,
                vertexSize);

        int sum = 0;
        // 循环比较
        for (int i = 0; i < vertexSize; i++) {

            //先找出最小（边权值）的邻接节点,找最小值的过程显然可以优化，堆排序，或者快排
            int minAdjVex = -1;
            for (int j = 0; j < vertexSize; j++) {
                if (0 < lowCostEdge[j] && lowCostEdge[j] < MAX_WEIGHT) {
                    if (minAdjVex == -1 || lowCostEdge[j] < lowCostEdge[minAdjVex]) {
                        minAdjVex = j;
                    }
                }
            }

            // 判断是否全部为0，找不到最小值
            if (minAdjVex == -1) {
                break;
            }

            System.out.println("访问到了节点：" + minAdjVex + "，权值：" + lowCostEdge[minAdjVex]);
            sum += lowCostEdge[minAdjVex];

            //i到minAdjVex的权值修改为0，相当于将此节点并入到U集合
            lowCostEdge[minAdjVex] = 0;

            //将新的顶点并入到U集合之后，得重新选择最小边。找最小值的过程显然可以优化
            for (int j = 0; j < vertexSize; j++) {
                if (edges[minAdjVex][j] < lowCostEdge[j]) {
                    lowCostEdge[j] = edges[minAdjVex][j];
                }
            }
        }
        System.out.println("最小生成树的权值总和：" + sum);
    }


    public static void main(String[] args) {

        Graph graph = new Graph(9);
        // 初始化图
        int[] graph0 = new int[]{0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        int[] graph1 = new int[]{10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12};
        int[] graph2 = new int[]{MAX_WEIGHT, 18, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8};
        int[] graph3 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21};
        int[] graph4 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT};
        int[] graph5 = new int[]{11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT};
        int[] graph6 = new int[]{MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT};
        int[] graph7 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT};
        int[] graph8 = new int[]{MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0};

        graph.edges[0] = graph0;
        graph.edges[1] = graph1;
        graph.edges[2] = graph2;
        graph.edges[3] = graph3;
        graph.edges[4] = graph4;
        graph.edges[5] = graph5;
        graph.edges[6] = graph6;
        graph.edges[7] = graph7;
        graph.edges[8] = graph8;


        // 获取入度
        int outDegree = graph.getOutDegree(1);
        int inDegree = graph.getInDegree(1);
        System.out.println("出度：" + outDegree + "，入度：" + inDegree);

        // 深度优先遍历
        graph.depthFirstSearch(0);
        // 广度优先遍历
        graph.broadFirstSearch(0);

        // 普里姆算法获取最小生成树
        graph.createMinSpanTreePrim();

    }


}
