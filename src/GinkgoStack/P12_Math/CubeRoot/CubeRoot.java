package GinkgoStack.P12_Math.CubeRoot;
import java.util.Scanner;

/**
 * 求立方根
 *
 * 题目描述
 * •计算一个数字的立方根，不使用库函数
 *
 * 详细描述：
 *
 * •接口说明
 *
 * 原型：
 *
 * public static double getCubeRoot(double input)
 *
 * 输入:double 待求解参数
 *
 * 返回值:double  输入参数的立方根，保留一位小数
 *
 *
 * 输入描述:
 * 待求解参数 double类型
 *
 * 输出描述:
 * 输入参数的立方根 也是double类型
 *
 * 示例1
 * 输入
 * 复制
 * 216
 * 输出
 * 复制
 * 6.0
 */
public class CubeRoot {

    /**
     * 牛顿迭代法
     */

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while (input.hasNextDouble()){
            double num = input.nextDouble();
            double x = 1.0;
            for (; Math.abs(Math.pow(x,3)-num) > 1e-3;

                 x = x- ((Math.pow(x,3)-num) / (3*Math.pow(x,2))));


            System.out.println(String.format("%.1f", x));
        }
    }


    /**
     * 二分法,这个不太好
     */

    public static void fun(String[] args){
        Scanner scanner = new Scanner(System.in);
        double d = scanner.nextDouble();
        //0.1*0.1*0.1=0.0001
        double l = 0.0, r = d;
        while (r - l > 0.0001) {
            double mid = (r + l) / 2;
            if(Math.pow(mid, 3) < d){
                l = mid;
            }else{
                r = mid;
            }
        }

        if(Math.pow(Math.round(r),3) == d){ //216的开方 6.0，精益求精，5.9
            System.out.printf("%.1f", (double) Math.round(r));
        }else{
            System.out.printf("%.1f", r);
        }
    }



}
