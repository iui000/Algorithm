package GinkgoStack.P24_Enterprise.Tencent.chun2018;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/42e7ff5c5696445ab907caff17fc9e15
 * 来源：牛客网
 *
 * 小Q的公司最近接到m个任务, 第i个任务需要xi的时间去完成, 难度等级为yi。
 * 小Q拥有n台机器, 每台机器最长工作时间zi, 机器等级wi。
 * 对于一个任务,它只能交由一台机器来完成, 如果安排给它的机器的最长工作时间小于任务需要的时间,
 * 则不能完成,如果完成这个任务将获得200 * xi + 3 * yi收益。
 *
 * 对于一台机器,它一天只能完成一个任务, 如果它的机器等级小于安排给它的任务难度等级, 则不能完成。
 *
 * 小Q想在今天尽可能的去完成任务, 即完成的任务数量最大。如果有多种安排方案,小Q还想找到收益最大的那个方案。小Q需要你来帮助他计算一下。
 *
 *
 * 输入描述:
 * 输入包括N + M + 1行,
 * 输入的第一行为两个正整数n和m(1 <= n, m <= 100000), 表示机器的数量和任务的数量。
 * 接下来n行,每行两个整数zi和wi(0 < zi < 1000, 0 <= wi <= 100), 表示每台机器的最大工作时间和机器等级。
 * 接下来的m行,每行两个整数xi和yi(0 < xi < 1000, 0 <= yi<= 100), 表示每个任务需要的完成时间和任务的难度等级。
 *
 *
 * 输出描述:
 * 输出两个整数, 分别表示最大能完成的任务数量和获取的收益。
 * 示例1
 * 输入
 * 1 2
 * 100 3
 * 100 2
 * 100 1
 * 输出
 * 1 20006
 */
public class MachinesPerformTasks {

    static class Pair {
        int time;
        int level;

        public Pair(int time, int diff) {
            this.time = time;
            this.level = diff;
        }
    }

    /**
     * 注意是先要找到完成任务量最大，其次是收益最大
     * 思路：
     * https://blog.csdn.net/qq_39883358/article/details/88683264
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int machineNum = sc.nextInt();
            int taskNum = sc.nextInt();
            Pair[] machines = new Pair[machineNum];
            Pair[] tasks = new Pair[taskNum];
            for (int i = 0; i < machineNum; i++) {
                machines[i] = new Pair(sc.nextInt(), sc.nextInt());
            }
            for (int i = 0; i < taskNum; i++) {
                tasks[i] = new Pair(sc.nextInt(), sc.nextInt());
            }

            //对机器和任务排序，时间属性优先，等级次之，降序
            Comparator<Pair> cmp = new Comparator<Pair>() {
                public int compare(Pair p1, Pair p2) {
                    if (p1.time == p2.time) {
                        return p2.level - p1.level;
                    } else {
                        return p2.time - p1.time;
                    }
                }
            };
            Arrays.sort(machines, cmp);
            Arrays.sort(tasks, cmp);


            long profile = 0;
            int tasksCompleted = 0;
            int j = 0;
            int[] dp = new int[101];//dp[level]表示能够完成等级为level的任务的机器数量，等级最高100
            for (int i = 0; i < taskNum; i++) {
                //时间维度考虑，dp表示能够完成当前任务的机器数量，按照level分开计数
                while (j < machineNum && machines[j].time >= tasks[i].time) {
                    dp[machines[j].level]++;
                    j++;
                }

                //接下来，从等级维度去考虑，去找到一个能够完成该任务的等级相对最低的机器
                for (int k = tasks[i].level; k < 101; k++) {
                    if (dp[k] != 0) {
                        dp[k]--;
                        profile += 200 * tasks[i].time + 3 * tasks[i].level;
                        tasksCompleted++;
                        break;//注意，别忘了
                    }
                }
            }

            System.out.println(tasksCompleted + " " + profile);
        }
        sc.close();

    }

}

