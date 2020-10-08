package GinkgoStack.P28_Others;

import java.util.Scanner;

/**
 * 3月20日
 * 最少出牌次数
 * https://blog.nowcoder.net/n/5ea080a94e52472e8b74e1e120ab0a3f
 *
 * 相似题目：NOIP 2015提高组 Day1 第三题斗地主
 *
 * 题目来源
 * 阿里巴巴2020实习生招聘在线笔试（3月20日场）
 *
 * 题目描述
 * 有一叠扑克牌，每张牌介于1和10之间，有四种出牌方法：
 *
 * 单牌
 * 对子
 * 顺子：如12345
 * 连对：如112233
 * 给10个数，表示1-10每种牌有几张，问最少要多少次能出完
 *
 * 输入样例
 * 1 1 1 2 2 2 2 2 1 1
 *
 * 输出样例
 * 3
 *
 * 样例说明：出三个顺子：A2345，45678，678910
 */
public class FightLandlord {

    private final static int MAX_COUNT = 40;

    private static int recursivePoke(int[] poke, int totalPoke) {
        if (totalPoke <= 0) {
            return 0;
        }
        if (totalPoke == 1) {
            return 1;
        }

        int ans = MAX_COUNT;
        int recursiveResult;
        boolean flag;

        // 顺子
        if (totalPoke >= 5) {
            for (int i = 0; i < 10; ++i) {
                flag = true;    // 有顺子
                for (int j = i; j < i + 5; ++j) {
                    if ((j < 10 && poke[j] == 0) || j >= 10) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = i; j < i + 5; ++j) {
                        --poke[j];
                    }
                    recursiveResult = recursivePoke(poke, totalPoke - 5);
                    if (recursiveResult + 1 < ans) {
                        ans = recursiveResult + 1;
                    }
                    for (int j = i; j < i + 5; ++j) {
                        ++poke[j];
                    }
                }
            }
        }

        // 连对
        if (totalPoke >= 6) {
            for (int i = 0; i < 10; ++i) {
                flag = true;    // 有连对
                for (int j = i; j < i + 3; ++j) {
                    if ((j < 10 && poke[j] < 2) || j >= 10) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = i; j < i + 3; ++j) {
                        poke[j] -= 2;
                    }
                    recursiveResult = recursivePoke(poke, totalPoke - 6);
                    if (recursiveResult + 1 < ans) {
                        ans = recursiveResult + 1;
                    }
                    for (int j = i; j < i + 3; ++j) {
                        poke[j] += 2;
                    }
                }
            }
        }

        // 对子
        if (ans == MAX_COUNT) {
            recursiveResult = 0;
            for (int num : poke) {
                recursiveResult += (num / 2);
                num %= 2;
                recursiveResult += num;
            }
            ans = recursiveResult;
        }

        // 单牌
        if (ans == MAX_COUNT) {
            ans = 0;
            for (int num : poke) {
                ans += num;
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] poke = new int[10];

        Scanner scanner = new Scanner(System.in);
        int totalPoke = 0;
        for (int i = 0;i<10;i++) {
            poke[i] = scanner.nextInt();
            totalPoke += poke[i];
        }

        System.out.println(recursivePoke(poke, totalPoke));
    }

}
