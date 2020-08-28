package GinkgoStack.P24_Enterprise.Tencent;

/**
 * 腾讯2018春招技术类编程题汇总 企业提供原题00:06:43
 * 3/6
 * [编程题]贪吃的小Q
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 小Q的父母要出差N天，走之前给小Q留下了M块巧克力。
 * 小Q决定每天吃的巧克力数量不少于前一天吃的一半，
 * 但是他又不想在父母回来之前的某一天没有巧克力吃，请问他第一天最多能吃多少块巧克力
 *
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数，
 * 表示父母出差的天数N(N<=50000)和巧克力的数量M(N<=M<=100000)。
 *
 * 输出描述:
 * 输出一个数表示小Q第一天最多能吃多少块巧克力。
 *
 * 输入例子1:
 * 3 7
 *
 * 输出例子1:
 * 4
 */
public class chocolate {

    //计算第一天吃s个巧克力,n天一共需要多少个多少个巧克力
     private static int  sum(int s,int n){
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=s;
            s=(s+1)>>1;//向上取整
        }
        return sum;
    }
    //二分查找
    int bSearch(int n,int m){
        if(n==1) return m;
        int low=1;
        int high=m;//第一天的巧克力一定是大于等于1，小于等于m的
        while(low<high){
            int mid=(low+high+1)>>1;//向上取整
            //如果第一天吃mid个巧克力，刚刚好吃完所有巧克力，那么直接返回
            if(sum(mid,n)==m)
                return mid;
            else if(sum(mid,n)<m){
                low=mid;
            }else{
                high=mid-1;
            }
        }
        return high;
    }

    public static void main(String[] args) {

    }


}
