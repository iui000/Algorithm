package GinkgoStack.P7_Sort;

import java.util.Arrays;

/**
 * 853. 车队
 * N  辆车沿着一条车道驶向位于 target 英里之外的共同目的地。
 *
 * 每辆车 i 以恒定的速度 speed[i] （英里/小时），从初始位置 position[i] （英里）
 * 沿车道驶向目的地。
 *
 * 一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车以相同的速度紧接着行驶。
 *
 * 此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
 *
 * 车队 是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
 *
 * 即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
 *
 *
 *
 * 会有多少车队到达目的地?
 *
 *
 *
 * 示例：
 *
 * 输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * 输出：3
 * 解释：
 * 从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
 * 从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
 * 从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
 * 请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
 *
 * 提示：
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * 所有车的初始位置各不相同。
 */
public class CarFleet853 {
    /**
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x8znw1/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        class Car {
            int position;
            double time;//如果按照自己的速度行驶并可以超车的话，到达终点的时间
            Car(int p, double t) {
                position = p;
                time = t;
            }
        }


        public int carFleet(int target, int[] position, int[] speed) {
            int N = position.length, ans = 0, t = N;
            if (N == 0)
                return 0;

            Car[] cars = new Car[N];
            for (int i = 0; i < N; ++i)
                cars[i] = new Car(position[i],
                        (double) (target - position[i]) / speed[i]);

            //按照起始位置升序
            //nlogn时间复杂度
            Arrays.sort(cars, (a, b) -> {return a.position - b.position;});

            while (--t > 0) {//起始位置最远的开始，往前遍历
                //说明追不上起始位置较远的车
                if (cars[t].time < cars[t-1].time)
                    ans++; //t车更快，追不上
                //否则能够追上，就看作一个车队，也就是会同时到达终点
                else cars[t-1] = cars[t]; //组成车队，同时到达
            }

            //仔细思考上面的循环，不管最后一辆车有没有追上前面的车队，都少计算了一个车队，
            //所以，这里要加上1
            return ans + 1; //别忘记加上最后一个车队
        }
    }



}
