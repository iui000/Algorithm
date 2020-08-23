package GinkgoStack.P24_Enterprise.Tencent.Background2020;


/**
 * 链接：https://www.nowcoder.com/questionTerminal/61e1e66e39f348cdb6495de91ac36a41
 * 来源：牛客网
 *
 * 类似题：1326. 灌溉花园的最少水龙头数目
 *
 * 视野争夺
 * 热度指数：3127时间限制：C/C++ 1秒，其他语言2秒空间限制：C/C++ 256M，其他语言512M
 * 算法知识视频讲解
 * 小Q在进行一场竞技游戏,这场游戏的胜负关键就在于能否能争夺一条长度为L的河道,即可以看作是[0,L]的一条数轴。
 * 这款竞技游戏当中有n个可以提供视野的道具−真视守卫,第i个真视守卫能够覆盖区间[xi,yi]。
 * 现在小Q想知道至少用几个真视守卫就可以覆盖整段河道。
 *
 * 输入描述:
 * 输入包括n+1行。
 *
 * 第一行包括两个正整数n和L(1<=n<=105,1<=L<=109)
 *
 * 接下来的n行,每行两个正整数xi,yi(0<=xi<=yi<=109),表示第i个真视守卫覆盖的区间。
 *
 *
 *
 * 输出描述:
 * 一个整数，表示最少需要的真视守卫数量, 如果无解, 输出-1。
 * 示例1
 * 输入
 * 4 6
 * 3 6
 * 2 4
 * 0 2
 * 4 7
 * 输出
 * 3
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class intervalCoverage {

    /**
     * 先排序，再贪心
     * @param args
     */
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int L=in.nextInt();
        int[][] intervals =new int[n][2];
        for(int i=0;i<n;i++) {
            intervals[i][0]=in.nextInt();
            intervals[i][1]=in.nextInt();
        }

        //按照起始点第一属性，结束点第二属性，进行升序排序
        Arrays.sort(intervals ,new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
            }
        });


        int index=0;
        int ans = 0;
        int leftRange =0;   //0~L之间尚未被覆盖的左边界
        while(leftRange<L) {
            //如果最小的开始位置覆盖不到左边界，那就直接返回-1
            if(intervals[index][0]>leftRange) {
                System.out.println(-1);
                return;
            }

            //贪心
            //找一个起始位置小于左边界，而且结束位置尽可能最大的那个区间
            int maxEnd=0;
            while(index<n && intervals[index][0]<=leftRange) {
                maxEnd = Math.max(maxEnd, intervals [index][1]);
                index++;
            }
            ans++;//区间数量+1，也就是选择这个区间
            leftRange=maxEnd;//更新左边界

            if(leftRange>=L) {//0~L已经覆盖完
                System.out.println(ans);
                return;
            }

            if(index >= n) {//如果已经把所有的小区间都遍历完，仍然没有将0~L覆盖
                System.out.println(-1);
                return;
            }
        }

    }

}
