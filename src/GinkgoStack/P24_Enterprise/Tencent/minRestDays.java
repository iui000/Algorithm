package GinkgoStack.P24_Enterprise.Tencent;

import java.util.Scanner;


/**
 *  腾讯2020校园招聘-后台 企业提供原题00:03:03
 * 4/5
 * [编程题]假期
 * 时间限制：C/C++ 2秒，其他语言4秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 由于业绩优秀，公司给小Q放了 n 天的假，身为工作狂的小Q打算在在假期中工作、
 * 锻炼或者休息。他有个奇怪的习惯：不会连续两天工作或锻炼。只有当公司营业时，
 * 小Q才能去工作，只有当健身房营业时，小Q才能去健身，小Q一天只能干一件事。
 * 给出假期中公司，健身房的营业情况，求小Q最少需要休息几天。
 *
 * 输入描述:
 * 第一行一个整数  表示放假天数
 * 第二行 n 个数 每个数为0或1,第 i 个数表示公司在第 i 天是否营业
 * 第三行 n 个数 每个数为0或1,第 i 个数表示健身房在第 i 天是否营业
 * （1为营业 0为不营业）
 *
 * 输出描述:
 * 一个整数，表示小Q休息的最少天数
 *
 * 输入例子1:
 * 4
 * 1 1 0 0
 * 0 1 1 0
 *
 * 输出例子1:
 * 2
 *
 * 例子说明1:
 * 小Q可以在第一天工作，第二天或第三天健身，小Q最少休息2天
 */
public class minRestDays {

    /**
     * 反过来求最大的不休息的天数。
     * @param args
     */
    public static void main(String[] args) {
        //第一步数据处理：三行，就是三个字符串，后两行进行一个分割处理，然后再调用函数转换成为整型数据
        Scanner in = new Scanner(System.in);
        String day_str = in.nextLine();
        String [] work_str = in.nextLine().split(" ");
        String [] gym_str = in.nextLine().split(" ");

        int day = Integer.parseInt(day_str);  //日期

        int [] works = new int[day+1];
        int [] gyms = new int[day+1];

        for(int i = 1;i < day+1;i++) {
            works[i] = Integer.parseInt(work_str[i - 1]);
            gyms[i] = Integer.parseInt(gym_str[i-1]);
        }
        int res = holiday(day, works, gyms);
        System.out.println(res);
    }

    //策略：使用dp[i][0] dp[i][1] dp[i][2]分别记录在第i天如果是休息、工作、健身情况下，前i天有事做（也就是没休息）的最大天数
    //如果第i天休息，那么前i天有事做的最大天数，实际就是dp[i-1][0] dp[i-1][1] dp[i-1][2]中的最大值
    //如果第i天工作，那么前i天有事做的最大天数，就是前一天休息、健身中的最大值 + 1（因为第i天工作了，没有休息）
    //如果第i天健身，那么前i天有事做的最大天数，就是前一天休息、工作中的最大值 + 1（因为第i天健身了，没有休息）
    //最后的结果，就用day减去最大的做事天数，就是最少的休息时间了
    public static int holiday(int day, int [] works, int [] gyms){
        int res;
        int [][] dp = new int[day+1][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;

        for (int i = 1; i < day+1; i++) {
            if(works[i] == 1){
                //第i天可以选择工作
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]) + 1;
            }
            if(gyms[i] == 1){
                //第i天可以选择健身
                dp[i][2] = Math.max(dp[i-1][0], dp[i-1][1]) + 1;
            }
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        }
        res = day - Math.max(dp[day][0], Math.max(dp[day][1], dp[day][2]));
        return res;
    }


}
