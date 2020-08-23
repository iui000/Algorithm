package GinkgoStack.P16_UnionFindSet;

public class SampleUnionFindSet {
    /**
     *     并查集模板
     */
    static int[] par, rank;
    static int find(int x) {
        return x==par[x] ? x : (par[x] = find(par[x]));
    }
    static void union(int x, int y) {
        if ((x=find(x)) == (y=find(y)))
            return;
        if (rank[x] < rank[y]) {
            par[x] = y;
        } else {
            par[y] = x;
            if (rank[x] == rank[y])
                rank[x]++;
        }
    }
    static boolean same(int x, int y) {
        return find(x) == find(y);
    }
}
