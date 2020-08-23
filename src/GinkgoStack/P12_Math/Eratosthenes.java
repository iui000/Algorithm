package GinkgoStack.P12_Math;

import java.util.Arrays;

/**
 * 埃拉托斯特尼筛法，简称埃氏筛或爱氏筛，是一种由希腊数学家埃拉托斯特尼所提出的一种简单检定素数的算法。
 * 要得到自然数n以内的全部素数，必须把不大于根号n的所有素数的倍数剔除，剩下的就是素数。
 *
 * 给出要筛数值的范围n，找出以内的素数。先用2去筛，即把2留下，把2的倍数剔除掉；再用下一个质数，也就是3筛，把3留下，把3的倍数剔除掉；
 * 接下去用下一个质数5筛，把5留下，把5的倍数剔除掉；不断重复下去......。
 */
public class Eratosthenes {

    /**
     * 按照算法思路实现，然后内层循环可以优化一下
     *
     * 内层的 for 循环也可以优化。我们之前的做法是：
     *
     *
     * for (int j = 2 * i; j < n; j += i)
     *     isPrim[j] = false;
     * 这样可以把 i 的整数倍都标记为 false，但是仍然存在计算冗余。
     *
     * 比如 n = 25，i = 4 时算法会标记 4 × 2 = 8，4 × 3 = 12 等等数字，但是这两个数字已经被 i = 2 和 i = 3 的 2 × 4 和 3 × 4 标记了。
     *
     * 我们可以稍微优化一下，让 j 从 i 的平方开始遍历，而不是从 2 * i 开始。
     *
     * @param n
     * @return
     */
    int countPrimes(int n) {//计算以内有多少个素数
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)//可以稍微优化一下，让 j 从 i 的平方开始遍历，而不是从 2 * i 开始。
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

}
