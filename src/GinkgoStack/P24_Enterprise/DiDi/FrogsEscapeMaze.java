package GinkgoStack.P24_Enterprise.DiDi;

/**
 * 地下迷宫
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 小青蛙有一天不小心落入了一个地下迷宫,小青蛙希望用自己仅剩的体力值P跳出这个地下迷宫。
 * 为了让问题简单,假设这是一个n*m的格子迷宫,迷宫每个位置为0或者1,0代表这个位置有障碍物,
 * 小青蛙达到不了这个位置;1代表小青蛙可以达到的位置。小青蛙初始在(0,0)位置,地下迷宫的出口在(0,m-1)(保证这两个位置都是1,
 * 并且保证一定有起点到终点可达的路径),小青蛙在迷宫中水平移动一个单位距离需要消耗1点体力值,
 * 向上爬一个单位距离需要消耗3个单位的体力值,向下移动不消耗体力值,当小青蛙的体力值等于0的时候还没有到达出口,
 * 小青蛙将无法逃离迷宫。现在需要你帮助小青蛙计算出能否用仅剩的体力值跳出迷宫(即达到(0,m-1)位置)。
 *
 * 输入描述:
 * 输入包括n+1行:
 *  第一行为三个整数n,m(3 <= m,n <= 10),P(1 <= P <= 100)
 *  接下来的n行:
 *  每行m个0或者1,以空格分隔
 *
 * 输出描述:
 * 如果能逃离迷宫,则输出一行体力消耗最小的路径,输出格式见样例所示;如果不能逃离迷宫,则输出"Can not escape!"。 测试数据保证答案唯一
 *
 * 输入例子1:
 * 4 4 10
 *
 * 1 0 0 1
 * 1 1 0 1
 * 0 1 1 1
 * 0 0 1 1
 *
 * 输出例子1:
 * [0,0],[1,0],[1,1],[2,1],[2,2],[2,3],[1,3],[0,3]
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class FrogsEscapeMaze {

    /**
     * DFS
     */
    static int n, m, maxRemainEnergy = 0;
    static int[][] maze;
    static boolean canEscape = false;
    static String ans = "";
    static LinkedList<String> pathList = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int P = sc.nextInt();

        maze = new int[n][m];//迷宫
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        //搜索路径
        dfs(0, 0, P);

        //输出
        if (!canEscape)
            System.out.println("Can not escape!");
        else
            System.out.println(ans);

    }

    public static  void dfs(int x, int y, int energy) {
        if (energy < 0 || x<0 || y<0 || x>=n || y>= m || maze[x][y] != 1)
            return;

        pathList.offer("[" + x + "," + y + "]");//将这一步加入结果路径
        maze[x][y] = 0;//0代表不能达到
        if (x == 0 && y == m - 1) {//可能有多条路径达到出口，题目要求找到消耗能量最小的那条路径
            if (energy >= maxRemainEnergy) {
                maxRemainEnergy = energy;
                updateAns();//这里已经将结果写到ans中了
            }
            maze[x][y] = 1;//将这个位置复原
            pathList.removeLast();//将这一步删除
            canEscape = true;
            return;
        }

        dfs(x, y+1, energy-1);//右边
        dfs(x+1, y, energy);//下边
        dfs(x-1, y, energy-3);//上边
        dfs(x, y-1, energy-1);//左边

        maze[x][y] = 1;//然后将这个位置复原
        pathList.removeLast();//并将这一步从结果中删除

    }

    public static void updateAns() {
        StringBuilder s = new StringBuilder();
        Iterator<String> iterator = pathList.iterator();

        while (iterator.hasNext())
            s.append(iterator.next() + ",");

        if (s.length() > 0)//删除最后的逗号
            s.deleteCharAt(s.length() - 1);

        ans = s.toString();
    }
}
