package GinkgoStack.P22_AnalysisInJava3Third.C9_GraphTheory;

import GinkgoStack.P22_AnalysisInJava3Third.C8_DisjointSet_UnionFindSet.DisjSets;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalByUnionFindSet {
    /**
     * 连接顶点的边
     */
    class Edge implements Comparable<Edge>{
         int start;
         int end;
         int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    private List<Edge> kruskal(List<Edge> edges,int numVertices){
        DisjSets ds = new DisjSets(numVertices);
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
        List<Edge> mst = new ArrayList<>();

        while (mst.size() != numVertices-1){
            Edge e = pq.poll();
            int set1 = ds.find(e.start);
            int set2 = ds.find(e.end);
            if(set1 != set2){
                //接受这条边
                mst.add(e);
                ds.union(set1,set2);
            }
        }

        return mst;
    }
}
