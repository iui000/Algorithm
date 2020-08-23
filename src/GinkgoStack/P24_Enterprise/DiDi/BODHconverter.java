package GinkgoStack.P24_Enterprise.DiDi;

import java.util.Scanner;

/**
 * 进制转换
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
 *
 * 输入描述:
 * 输入为一行，M(32位整数)、N(2 ≤ N ≤ 16)，以空格隔开。
 *
 * 输出描述:
 * 为每个测试实例输出转换后的数，每个输出占一行。如果N大于9，则对应的数字规则参考16进制（比如，10用A表示，等等）
 *
 * 输入例子1:
 * 7 2
 *
 * 输出例子1:
 * 111
 */
public class BODHconverter {

    /**
     * 十进制转换为X进制
     * @param m
     * @param x
     * @return
     */
    private static String DtoX(int m,int x){
        String ans = "";
        String table="0123456789ABCDEF";
        if(m == 0){
            return "0";
        }

        boolean negative = false;
        if(m<0){
            m = -m;
            negative = true;
        }

        while (m != 0){
            ans = table.charAt(m%x) + ans;
            m /=  x;
        }

        return negative? "-"+ans:ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int x = sc.nextInt();
        System.out.println(DtoX(m,x));
    }

}
