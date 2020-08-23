package GinkgoStack.Alibaba;

import java.util.Scanner;

/**
 * 作者：毫克迈斯
 * 链接：https://www.nowcoder.com/discuss/464062?source_id=profile_create&channel=666
 * 来源：牛客网
 *
 * 小强是一个农场主，农场里有n头牛，每头牛有着独一无二的体重，每一头牛的颜色可能跟是m种颜色其中的一种，小强带了一些牛（可能为0个）出来吃草。你需要回答出小强带出来的牛的组合一共有多少种可能？
 *
 * 注意：因为一头牛有自己的体重（没有两头牛体重相等），所以如果四头牛的体重分别是1,2,3,4，颜色分别是y1,y2,y3,y4和另一种方案：四头牛的体重分别是1,2,3,4颜色分别是y1,y3,y2,y4即使两个方案的颜色的种类对应的数量是相同的，但是因为颜色对应的体重不同，所以是两个不同的方案。
 * 由于方案书可能很大，请对1e9+7取模。
 * 输入描述：
 * 两个整数n,m(1≤n,m≤10^9)
 * 输入： 3,2
 *
 * 输出： 27
 */
public class CattleCombination {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            int n = in.nextInt();
            int m = in.nextInt();
            System.out.printf("%.0f\n", slice(m + 1, n));

        }

        private static double slice(int m, int n) {
            if (n == 1) {
                return m;
            }
            double temp = slice(m, n / 2);
            return ((n % 2 == 0 ? 1 : m) * temp * temp) % 1000000007;
        }

}
