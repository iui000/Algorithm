package GinkgoStack.P24_Enterprise.MeiTuan;

import java.util.Scanner;

/**
 * 大数加法
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 以字符串的形式读入两个数字，再以字符串的形式输出两个数字的和。
 *
 * 输入描述:
 * 输入两行，表示两个数字a和b，-109 <= a , b <= 109  ，用双引号括起。
 *
 * 输出描述:
 * 输出a+b的值，用双引号括起。
 *
 * 输入例子1:
 * "-26"
 * "100"
 *
 * 输出例子1:
 * "74"
 */
public class additionStringNumbers {

    //题目给出的范围不足以让两个int相加溢出
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            String res ="\""+ (Integer.parseInt(a.substring(1,a.length()-1))
                    +Integer.parseInt(b.substring(1,b.length()-1)))+"\"";
            System.out.println(res);
        }
    }

}
