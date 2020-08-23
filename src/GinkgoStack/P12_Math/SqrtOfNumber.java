package GinkgoStack.P12_Math;

/**
 * 百度面试题
 */
public class SqrtOfNumber {

    /**
     * 第一，dota神牛
     * r1 跳跃
     * r2 技能伤害半径
     * x y自己的坐标
     *
     * 五个对方英雄的坐标
     * x[5]
     * y[5]
     *
     * 问最多能打到几个对方英雄
     *
     * @param
     */


    /**
     * 第二道算法，求一个数的平方根，精度0.01
     * @param n
     * @return
     */
    private static float getSqrt(int n){
        int left = 0;
        int right =  n;
        float  i = 0.0F;

        //先用二分确定一个起始数
        while (left <right){
            int mid = left + (right - left)/2;
            if(mid * mid <= n) {
                i = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        //然后再以精度增量来计算 其平方最接近目标值的那个数
        //如果精度很高，可能还要进一步二分
        float ans = i;
        while (ans * ans < (float) n){
            ans += 0.01;
        }

        return (int)(ans*100)/(float)100;
    }

    public static void main(String[] args)
    {
        System.out.println(Math.sqrt(5));
        System.out.println(getSqrt(5));
    }
}
