package GinkgoStack.P13_GraphTheory.ShortedPath;

import java.util.Arrays;
import java.util.Scanner;

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

            for (int j = 1;j<n;j++){
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

            //更新当前最短路径以及距离
            for (int j = 1;j<n;j++){
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
        for (int i = 1;i<n;i++){
            System.out.println(hasFindV[i]?shortDisTable[i]:9999);
        }

    }

    /*
0 1 12 -1 -1 -1
-1 0 9 3 -1 -1
-1 -1 0 -1 5 -1
-1 -1 4 0 13 15
-1 -1 -1 -1 0 4
-1 -1 -1 -1 -1 0
     */
    public static void main(String[] args) {
        int[][] weight = new int[6][];

        String[] pointsStr = { "V1", "V2", "V3", "V4", "V5","V6"};
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < pointsStr.length; i++) {
            String[] valuesStr = input.nextLine().split(" ");
            int[] values = new int[valuesStr.length];
            for (int j = 0; j < valuesStr.length; j++) {
                values[j] = Integer.parseInt(valuesStr[j]);
            }
            weight[i] = values;
        }
        input.close();

        dijstra(weight, 0);
    }
}
