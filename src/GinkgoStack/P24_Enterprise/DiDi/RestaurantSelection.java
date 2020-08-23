package GinkgoStack.P24_Enterprise.DiDi;

/**
 * 某餐馆有n张桌子，每张桌子有一个参数：a 可容纳的最大人数；
 * 有m批客人，每批客人有两个参数:b人数，c预计消费金额。
 * 在不允许拼桌的情况下，请实现一个算法选择其中一部分客人，使得总预计消费金额最大
 *
 * 输入描述:
 * 输入包括m+2行。 第一行两个整数n(1 <= n <= 50000),m(1 <= m <= 50000)
 * 第二行为n个参数a,即每个桌子可容纳的最大人数,以空格分隔,范围均在32位int范围内。
 * 接下来m行，每行两个参数b,c。分别表示第i批客人的人数和预计消费金额,以空格分隔,范围均在32位int范围内。
 *
 * 输出描述:
 * 输出一个整数,表示最大的总预计消费金额
 *
 * 输入例子1:
 * 3 5
 *
 * 2 4 2
 *
 * 1 3
 * 3 5
 * 3 7
 * 5 9
 * 1 10
 *
 * 输出例子1:
 * 20
 */
import java.util.*;
public class RestaurantSelection {
    /**
     * 题目意思是，不拼桌，就是一批客人要求必须用一张桌子容纳，容纳不下就只能放弃这一批客人；
     * 排序 + 贪心
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] disk = new int[n]; //桌子数组
            for (int i = 0; i < n; i ++) {
                disk[i] = sc.nextInt();
            }

            Arrays.sort(disk); // 桌子容纳量从小到大排序

            PriorityQueue<Customer> queue = new PriorityQueue<>(); // 将客人按消费额降序加入优先级队列
            for (int i = 0; i < m; i ++) {
                int b = sc.nextInt();//人数
                int c = sc.nextInt();//消费金额
                if(b <= disk[n - 1]) // 如果人数小于桌子最大容纳量,加入队列
                    queue.add(new Customer(b, c));
            }

            boolean[] visited = new boolean[n]; // 记录桌子是否被占用
            long sum = 0; // 记录总盈利
            int count = 0; // 记录已使用的桌子数
            while (!queue.isEmpty()) {//接待客人
                Customer customer = queue.poll();
                /**
                 * // 为客人分配桌子，也就是找到一个能容纳这一批客人的最小桌子
                 * 这里可以优化为二分查找，找到第一个未使用的，仅大于customer.peopleCount的桌子
                 */
                for (int i = 0; i < n; i ++) {
                    if(customer.peopleCount <= disk[i] && ! visited[i]) {
                        sum += customer.moneyCount;
                        visited[i] = true;
                        count ++;
                        break;
                    }
                }
                if(count == n) break;//桌子用完了
            }
            System.out.println(sum);
        }
    }

    static class Customer implements Comparable<Customer> {
        private int peopleCount;
        private int moneyCount;

        public Customer(int peopleCount, int moneyCount) {
            this.peopleCount = peopleCount;
            this.moneyCount = moneyCount;
        }

        @Override
        public int compareTo(Customer o) {
            if(o.moneyCount > this.moneyCount) return 1;
            else if(o.moneyCount < this.moneyCount) return - 1;
            return 0;
        }
    }



}
