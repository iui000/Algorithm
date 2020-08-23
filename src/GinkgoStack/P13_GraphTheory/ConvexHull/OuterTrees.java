package GinkgoStack.P13_GraphTheory.ConvexHull;


import java.util.*;

/**
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 *
 *  注意:
 *
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/erect-the-fence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *     作者：LeetCode
 *     链接：https://leetcode-cn.com/problems/erect-the-fence/solution/an-zhuang-zha-lan-by-leetcode/
 *     来源：力扣（LeetCode）
 *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class OuterTrees {
    class Point{
         int x;
         int y;
    }

    /**
     * 叉乘 pq × qr 右手螺旋守则
     * 根据结果的正负关系，我们可以推出pq向量与qr向量的关系（这里的顺逆时针是指q、r点围绕极点p的顺逆关系）：
     * ①若结果大于0，则pq在qr的顺时针方向
     * ②若结果小于0，则pq在qr的逆时针方向
     * ③若结果等于0，则pq与qr平行（其实是共线，但是可能朝向相反）
     * 记忆线的关系，不大好记住，可以这样来记住点的相对位置：
     * 如果叉乘：
     * pq × qr > 0 ,那么q这个点在pr的逆时针方向；
     * pq × qr < 0 ,那么q这个点在pr的顺时针方向；
     * pq × qr = 0 ,那么q这个点在pr的直线上；
     * @param p
     * @param q
     * @param r
     * @return
     */
    private int orientation(Point p, Point q, Point r) {
        //计算二阶行列式
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }

    /**
     * 如果存在 2 个点相对点 p 在同一条线上，我们使用 inBetween() 函数，将 q 和 pp同一线段上的边界点都考虑进来。
     * @param p
     * @param i
     * @param q
     * @return
     */
    private boolean inBetween(Point p, Point i, Point q) {
        boolean a = i.x >= p.x && i.x <= q.x || i.x <= p.x && i.x >= q.x;
        boolean b = i.y >= p.y && i.y <= q.y || i.y <= p.y && i.y >= q.y;
        return a && b;
    }

    /**
     * 第一种
     * Jarvis 算法
     * 找外围的树，就是一个凸包
     * @param points
     * @return
     */
    public List<Point> outerTreesByJarvis(Point[] points) {
        HashSet<Point> hull = new HashSet<>();

        if (points.length < 4) {
            for (Point p: points)
                hull.add(p);

            return new ArrayList<Point>(hull);
        }
        int left_most = 0;
        for (int i = 0; i < points.length; i++)
            if (points[i].x < points[left_most].x)
                left_most = i;
        int p = left_most;
        do {
            int q = (p + 1) % points.length;
            for (int i = 0; i < points.length; i++) {
                if (orientation(points[p], points[i], points[q]) < 0) {
                    q = i;
                }
            }
            for (int i = 0; i < points.length; i++) {
                if (i != p && i != q && orientation(points[p], points[i], points[q]) == 0 && inBetween(points[p], points[i], points[q])) {
                    hull.add(points[i]);
                }
            }
            hull.add(points[q]);
            p = q;
        }
        while (p != left_most);
        return new ArrayList<Point>(hull);
    }


    /**
     * 第二种
     * Graham 算法
     * @param p
     * @param q
     * @return
     */
    private int distance(Point p, Point q) {
        return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
    }
    private static Point bottomLeft(Point[] points) {
        Point bottomLeft = points[0];
        for (Point p: points)
            if (p.y < bottomLeft.y)
                bottomLeft = p;
        return bottomLeft;
    }
    public List < Point > outerTreesByGraham(Point[] points) {
        if (points.length <= 1)
            return Arrays.asList(points);
        Point bm = bottomLeft(points);
        Arrays.sort(points, new Comparator<Point>() {
            public int compare(Point p, Point q) {
                double diff = orientation(bm, p, q) - orientation(bm, q, p);
                if (diff == 0)
                    return distance(bm, p) - distance(bm, q);
                else
                    return diff > 0 ? 1 : -1;
            }
        });

        int i = points.length - 1;
        while (i >= 0 && orientation(bm, points[points.length - 1], points[i]) == 0)
            i--;
        for (int l = i + 1, h = points.length - 1; l < h; l++, h--) {
            Point temp = points[l];
            points[l] = points[h];
            points[h] = temp;
        }

        //运行结束以后，栈中的点就是凸包的边缘点
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        for (int j = 2; j < points.length; j++) {
            Point top = stack.pop();

            while (orientation(stack.peek(), top, points[j]) > 0)
                top = stack.pop();

            stack.push(top);
            stack.push(points[j]);
        }
        return new ArrayList<>(stack);
    }


    /**
     * 单调链算法
     *
     * 单调链算法的想法与 Graham 扫描算分类似。它们主要的不同点在于凸壳上点的顺序。
     * 与 Graham 扫描算法按照点计较顺序排序不同，我们按照点的 x 坐标排序。如果两个点又相同的 x 坐标，那么就按照它们的 y 坐标排序。背后的原因稍后会做解释。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/erect-the-fence/solution/an-zhuang-zha-lan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param points
     * @return
     */
    public List<Point> outerTrees(Point[] points) {
        Arrays.sort(points, new Comparator<Point>(){
            public int compare(Point p, Point q) {
                return q.x - p.x == 0 ? q.y - p.y : q.x - p.x;
            }
        });

        //运行结束以后，栈中的点就是凸包的边缘点
        Stack<Point> hull = new Stack<>();
        for (int i = 0; i < points.length; i++) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }

        hull.pop();

        for (int i = points.length - 1; i >= 0; i--) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }

        return new ArrayList<>(new HashSet<>(hull));
    }


}


