package GinkgoStack.P24_Enterprise.DiDi;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/2ee36922acfe4b26bb7cceb5fc9a89a2
 * 来源：牛客网
 *
 * 给定两个数R和n，输出R的n次方，其中0.0<R<99.999, 0<n<=25
 *
 * 输入描述:
 * 多组测试用例，请参考例题的输入处理 输入每行一个浮点数 R 其中0.0 < R <99.999， 一个整数 n 其中0 < n <=25
 *
 *
 * 输出描述:
 * 输出R的n次方
 * 示例1
 * 输入
 * 95.123 12
 * 0.1 1
 * 输出
 * 548815620517731830194541.899025343415715973535967221869852721
 * 0.1
 */
public class Pow {

    /**
     *     链接：https://www.nowcoder.com/questionTerminal/2ee36922acfe4b26bb7cceb5fc9a89a2
     *     来源：牛客网
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s;

        while (sc.hasNext()){
            String r = sc.next(); //用string来存储，因为double和float都是不能准确的表示小数的，只是以概数来表示
            int n = sc.nextInt();
            BigDecimal d = new BigDecimal(r);
            BigDecimal ans = new BigDecimal(r);
            for(int i=1;i<n;i++){
                ans = ans.multiply(d);
            }

            s= ans.stripTrailingZeros().toPlainString(); // 去除不必要的零，转换为字符串，防止科学记数法
            System.out.println(s);
        }
    }

}
