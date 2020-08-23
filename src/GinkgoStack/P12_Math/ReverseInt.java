package GinkgoStack.P12_Math;

public class ReverseInt {

    public int reverse(int x) {
        int res = 0;
        int positive = Integer.MAX_VALUE/10;
        int negative = Integer.MIN_VALUE/10;

        while(x!=0) {//注意条件，必须要考虑负数，因此为0
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            //因为Integer.MAX_VALUE = 2147483647
            if (res> positive || (res == positive && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            //Integer.MIN_VALUE = -2147483648
            if (res<negative|| (res==negative && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }

}
