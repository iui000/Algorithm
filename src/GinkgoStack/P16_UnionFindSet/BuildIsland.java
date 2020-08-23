package GinkgoStack.P16_UnionFindSet;

/**
 * 几个岛
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 64M，其他语言128M
 *
 * 给定一个m行n列的二维地图, 初始化每个单元都是水.
 * 操作addLand 把单元格(row,col)变成陆地.
 * 岛屿定义为一系列相连的被水单元包围的陆地单元, 横向或纵向相邻的陆地称为相连(斜对角不算).
 * 在一系列addLand的操作过程中, 给出每次addLand操作后岛屿的个数.
 * 二维地图的每条边界外侧假定都是水.
 *
 * 输入描述:
 * 多组测试数据，请参考例题处理 每组数据k+3行, k表示addLand操作次数
 * 第一行:表示行数m
 * 第二行:表示列数n
 * 第三行:表示addLand操作次数k
 * 第4~k+3行:
 * row col 表示addLand的坐标。
 *
 * 注意超过边界的坐标是无效的。
 *
 * 输出描述:
 * 一行,k个整数, 表示每次addLand操作后岛屿的个数, 用空格隔开，结尾无空格
 *
 * 输入例子1:
 * 3
 * 3
 * 4
 * 0 0
 * 0 1
 * 1 2
 * 2 1
 *
 * 输出例子1:
 * 1 1 2 3
 */
public class BuildIsland {

    // 4个方向
    final static int[][] dir = {{1, -1, 0, 0}, {0, 0, 1, -1}};
    // 将二维信息行号r、列号c根据总列数cor映射成一维,是为了用并查集
    static int hashCor(int r, int c, int col) {
        return r*col + c + 1;// 加一是因为要将0当作水，加上1避免和0冲突
    }

    /**
     *     并查集模板
     */
    // 如果par[x]为0，则代表该点是水
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


    /**
     * 在一系列addLand的操作过程中, 给出每次addLand操作后岛屿的个数.
     * @param args
     */
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int isNum, row, col, i, r, c, opNum, nearR, nearC;
        while (sc.hasNext()) {
            row = sc.nextInt();
            col = sc.nextInt();
            opNum = sc.nextInt();
            isNum = 0;// 岛的个数
            par = new int[row*col+1];//一开始每个点的父节点均为0，代表水
            rank = new int[row*col+1];
            while (opNum-- > 0) {
                r = sc.nextInt();
                c = sc.nextInt();
                // 如果行号r和列号c不越界且该点当前是水
                if (r>=0 && r<row && c>=0 && c<col && find(hashCor(r,c,col))==0){
                    //操作addLand 把单元格(r,c)变成陆地，它暂时自成一个岛
                    isNum++;
                    par[hashCor(r, c, col)] = hashCor(r, c, col);

                    for (i = 0; i < 4; ++i) {//遍历临近4个点的坐标，看能否进行合并
                        nearR = r+dir[0][i];
                        nearC = c+dir[1][i];
                        // 如果该点不越界且是一块陆地 且 和当前的这一点（r，c）不属于同一个岛
                        if (nearR>=0 && nearR<row && nearC>=0 && nearC<col &&
                                find(hashCor(nearR, nearC, col)) != 0 &&
                                !same(hashCor(nearR, nearC, col), hashCor(r, c, col))) {
                            // 那么把它们连成同一个岛
                            union(hashCor(nearR, nearC, col), hashCor(r, c, col));
                            isNum--;// 因为把两块不同的岛连成同一个岛了，所以岛的数目减一
                        }
                    }
                }

                //一次操作addLand结束，输出岛屿数量
                System.out.printf("%d%c", isNum, opNum==0 ? '\n' : ' ');
            }
        }
    }


}
