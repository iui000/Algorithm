package GinkgoStack.P20_DynamicProgramming.ClimbStairs;

import java.util.Scanner;

/**
 * 字节笔试
 * n步台阶
 *
 * 一次可以跳一步或者两步，但是不能连续跳两步
 * 多少种跳法？
 *
 * f(n) = f(n-1) + f(n-3)
 * 注意long类型
 *
 * 知乎解答：
 * https://www.zhihu.com/question/275079633?sort=created
 */
public class ClimbStairsNotTwoSteps {

    /**
     * 从描述知道，这个操作是“有后效性”的。一步的操作会影响到下一步（但是只有下一步）
     * 所以，我们定义这么两个函数。
     * g(n)表示最后一步不是走两阶的走法数（也包括根本没有“最后一步”的），
     * 而h(n)则是最后一步走了两阶的。
     * 我们要求的f(n)=g(n)+h(n)
     *
     * 而h(n)=g(n-2);
     * g(n)=g(n-1)+h(n-1);
     * 把前面的代入后面的，于是知道g(n)=g(n-1)+g(n-3);
     *
     * 于是f(n)=f(n-1)+f(n-3);
     *
     * f(0)=0,f(1)=1,f(2)=2，f(3)=3，
     *
     * 作者：w2014
     * 链接：https://www.zhihu.com/question/275079633/answer/378454890
     * 来源：知乎
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int n = scanner.nextInt();
        // 委托 solution 进行求解
        System.out.println(solution(n));
    }

    public static long solution(int n) {
        if(n < 4){
            return n;
        }
        long pre3 = 1;
        long pre2 = 2;
        long pre1 = 3;
        long cur = 0;
        for (int i = 4; i <=  n; i++) {
            cur = pre1 + pre3;
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
