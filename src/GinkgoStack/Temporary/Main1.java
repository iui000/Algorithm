package GinkgoStack.Temporary;

import java.util.Scanner;

public class Main1 {

    static int xt ;
    static int yt ;
    static Pair[][] arr;


    static int min = Integer.MAX_VALUE;

    public static int movingCount(int n,int m,int xs,int ys,int t) {
        //临时变量visited记录格子是否被访问过
        boolean[][] visited = new boolean[n][m];


         dfs(xs, ys, n, m, t, visited);

         return min;
    }

    public static void dfs(int i, int j,int n,int m,  int t, boolean[][] visited) {

        if (i >= n || i < 0 || j >= m || j < 0 || visited[i][j])
            return ;

        //标注这个格子被访问过
        visited[i][j] = true;

        if(i == xt && j ==  yt){
            if(t < min){
                min = t;
            }
            return;
        }


        if(sum(arr[i][j].a,arr[i][j].b,t) == 1){
            dfs(i + 1, j, m, n, t+1, visited);
            dfs(i - 1, j, m, n, t+1, visited);
            visited[i][j] = false;
        }

        if(sum(arr[i][j].a,arr[i][j].b,t) == 2){
            dfs(i , j+1, m, n, t+1, visited);
            dfs(i , j-1, m, n, t+1, visited);
            visited[i][j] = false;
        }

    }


    private static int sum(int a, int b,int t) {
        int ans = 0;

        if(0 <= t % (a + b)   &&  t % (a + b) < a){
            ans = 1;
        }else if(a <= t % (a + b)   &&  t % (a + b) < a + b){
            ans = 2;
        }
        return ans;
    }



    static class Pair {
        int a;
        int b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        public String toString() {
            return "(" + a + "," + b + ")";
        }
    }

    /*
5 5 2 4 4 3
2 1 1 3 1
1 4 2 3 1
4 4 4 2 1
3 1 1 2 4
5 1 5 5 1
5 3 4 1 3
1 1 2 2 2
2 1 4 4 5
1 1 5 3 3
3 2 1 3 3

     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        int n = sc.nextInt();
        int m = sc.nextInt();

        int xs = sc.nextInt();
        int ys = sc.nextInt();

         xt = sc.nextInt();
         yt = sc.nextInt();

        arr = new Pair[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tmp = sc.nextInt();
                Pair pair = new Pair(tmp,0);
                arr[i][j] = pair;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Pair pair = arr[i][j];
                pair.b = sc.nextInt();
            }
        }



        System.out.println(movingCount(n,m,xs,ys,0));
    }
}
