package GinkgoStack.P24_Enterprise.netEasy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Dijstra考了至少4次
 * 现在算法是正确的了，之前网易笔试坑了我一把
 * 内部的两个for循环必须从0开始，因为源点并不是固定为0，是用户外部输入的。
 *
 * 可参考
 * https://blog.csdn.net/qq_34842671/article/details/90083037
 *
 * Q&A
 * 问：为什么迪杰斯特拉算法只支持非负权的图？
 *
 * 答：迪杰斯特拉采用的贪心策略，S集合中是已经计算出最短路径的节点，
 * T集合中是未计算出最短路径的节点。假设存在负权，源点为A，已经计算出A->B的最短路径为m，
 * 若下一次将C添加进已计算出最短路径的节点集合，而A->C=m，C->B=-1，则会出现A->B的更短路径A->C->B，
 * 但迪杰斯特拉不会对已经计算出最短路径的节点重新计算，因此无法更新最短路径，
 * 即负权的出现导致无法保证S中节点计算的是最短路径，已经固定dis的点可能会被其它dis大于它的点更新。
 *
 * 问：为什么在代码实现中不能将节点之间不可达用Integer.MAX_VALUE代表？
 *
 * 答：因为两个Integer.MAX_VALUE相加会溢出导致出现负权，所以最好设置为一个比较大且不容易相加溢出的数。
 *
 * 问：迪杰斯特拉算法适用于什么场景？
 *
 * 答：在有些算法书上说，迪杰斯特拉适用于DAG（有向无环图）。但是个人觉得，它所谓的“适用于”，
 * 或许只是说可以在DAG上使用，并不代表无向图不能使用，也不能代表有环图不能使用。
 * 从迪杰斯特拉的算法原理上来说，无向图是没有问题的，
 * 只需要给matrix[source][target]和matrix[target][source]赋上相同的权值，
 * 因为它每次只会根据到源点的距离，选取距离最近的一个节点加入，所以有没有方向都无所谓，
 * 算法只关注可达点的距离；至于有环图，它对每个节点的距离计算只用了一层遍历去做，并不会陷入死循环，
 * 也不会出现重复计算的问题。因此迪杰斯特拉算法是可以用在无向图和有环图中的，适合于求单源最短路径。
 */
public class Dijstra {

    /**
     * 邻接矩阵存储图
     * 每个顶点到其他所有顶点的权值，不可达表示-1（或者无穷）
     * S表示已经求得最短路径的终点的集合；
     * V表示顶点全集;
     * 0 1 12 -1 -1 -1
     * -1 0 9 3 -1 -1
     * -1 -1 0 -1 5 -1
     * -1 -1 4 0 13 15
     * -1 -1 -1 -1 0 4
     * -1 -1 -1 -1 -1 0
     *
     * 其中-1表示没有路径，为了方便计算，可以设置为无穷
     * @param DirectedGraph
     * @param
     */
    public static void dijstra(int[][] DirectedGraph, int originV){

        int n = DirectedGraph.length;

        boolean[] hasFindV = new boolean[n];
        int[] shortDisTable =new  int[n];

        /**
         * 初始化
         */
        System.arraycopy(DirectedGraph[originV], 0,
                shortDisTable, 0,
                n);

        //源点到源点的最短路径当然已经找到，也就是将源点加入到S集合
        hasFindV[originV] = true;

        //preVof[k]表示节点k的前驱节点；
        //这个数组的作用就是记录这条最短路径实际怎么走的，有或没有不影响最短路径的值。
        int[] preVof = new int[n];
        Arrays.fill(preVof,originV);//所有节点的前驱节点初始设为源点


        //开始主循环，每次求得originV到某个顶点v的最短路径，并将其加入到S集合
        for(int i = 1;i<n;i++){//其余n-1个点
            int closedV = -1;//当前距离originV最近的点
            int minDistance = Integer.MAX_VALUE;//当前所知的离originV顶点的最近距离

            for (int j = 0;j<n;j++){//for循环必须从0开始，因为源点并不是固定为0，是用户外部输入的
                //V-S集合中的点
                if(!hasFindV[j] &&
                   shortDisTable[j] != -1 &&
                   shortDisTable[j] < minDistance ){

                    minDistance = shortDisTable[j];
                    closedV = j;
                }
            }

            //如果没找到一个距离自己最近的点
            if(closedV == -1){
                continue;
            }

            //否则，将找到的这个点加入到S集合
            hasFindV[closedV] = true;

            //更新从closedV跳到其它节点的较短路径
            for (int j = 0;j<n;j++){//for循环必须从0开始，因为源点并不是固定为0，是用户外部输入的
                if(!hasFindV[j] && DirectedGraph[closedV][j] != -1 &&
                   (shortDisTable[j] ==-1 || minDistance + DirectedGraph[closedV][j] < shortDisTable[j])){

                    shortDisTable[j] = minDistance + DirectedGraph[closedV][j];
                    //记录下这个节点的前驱
                    preVof[j] = closedV;
                }
            }
        }

        /**
         * 输出结果
         */
        for (int i = 0;i<n;i++){
            //-1表示不可达
            System.out.println(shortDisTable[i]);
        }

    }

    /*

6 1 5
1 0 1
1 5 2
0 2 3
2 3 1
5 4 2
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //顶点数
        int vertex = sc.nextInt();
        //单源最短路径，源点
        int originV = sc.nextInt();
        //边数
        int edge = sc.nextInt();

        int[][] matrix = new int[vertex][vertex];
        //初始化邻接矩阵
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                matrix[i][j] = -1;
            }
        }

        for (int i = 0; i < edge; i++) {
            int source = sc.nextInt();
            int target = sc.nextInt();
            int weight = sc.nextInt();
            matrix[source][target] = weight;
        }

        dijstra(matrix, originV);
    }
}
