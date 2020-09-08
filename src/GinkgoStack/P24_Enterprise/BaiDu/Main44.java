package GinkgoStack.P24_Enterprise.BaiDu;

import java.util.*;


/*


1
10 2
3
1 2
4 5
8 8
2
1 4
6 8

输出：
4
1 2 4 8

 */
public class Main44 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据

        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();//区间范围最大值
            int M = scanner.nextInt();//m个区间列表

            int[][][] orders = new int[M][N][2];


            for (int i = 0; i < M; i++) {
                int k = scanner.nextInt();

                for (int j = 0; j < k; j++) {
                    orders[i][j][0] = scanner.nextInt();
                    orders[i][j][1] = scanner.nextInt();
                }
            }

            List<Integer> res = solution1(orders);
            System.out.println(res.size());

            for(int i = 0;i < res.size();i++){
                System.out.printf("%d%c", res.get(i), i==res.size()-1 ? '\n' : ' ');
            }

        }
    }

    /**
     * 当时写的两两合并：
     * 986. 区间列表的交集
     * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
     *
     * 返回这两个区间列表的交集。
     *
     * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。
     * 两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
     *
     *
     * @param a
     * @param b
     * @return
     */
    static int[][] merge(int[][] a,int[][] b) {
        List<int[]> ans  = new ArrayList<>();
        int i = 0;
        int j = 0;
//第一个已经有序，不用排序
//        Arrays.sort(a, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] == o2[0]?o1[1] - o2[1]:o1[0] - o2[0];
//            }
//        });

        Arrays.sort(b, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0]?o1[1] - o2[1]:o1[0] - o2[0];
            }
        });

        while ( i <a.length && j < b.length){
            int low = Math.max(a[i][0],b[j][0]);
            int high = Math.min(a[i][1],b[j][1]);

            if(low <= high){
                ans.add(new int[]{low,high});

            }

            if(a[i][1] < b[j][1]){
                i++;
            }
            else {
                j++;
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }


    static List<Integer> solution1(int[][][] orders) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(orders[0], new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0]?o1[1] - o2[1]:o1[0] - o2[0];
            }
        });


        int[][] tmp = orders[0];
        int m = orders.length;
        for (int i = 1; i < m; i++) {
            tmp = merge(tmp,orders[i]);
            boolean f = false;
            for (int[] ar: tmp){
                if(ar[0] != 0){
                    f = true;
                }
            }

            if(!f){
                break;
            }
        }

        for (int[] e : tmp) {
            for (int b = e[0];b<= e[1];b++){
                if(b != 0){
                    res.add(b);
                }

            }
        }

        return res;
    }







}

