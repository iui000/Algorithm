package GinkgoStack.P0_Common;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Base {

    public static void main(String[] args) {
        //字符串转为整型
        String number = "266352449";
        int num = Integer.parseInt(number);
        long lon = Long.parseLong(number);
        System.out.println(num);
        System.out.println(lon);

        //输出规定的精度值,四舍五入
        System.out.printf("%.4f",2.6326545);
        System.out.println(String.format("%.2f", 2.6326545));
        System.out.println();

        //输出以空格等为分隔符的字符串
        int a = 1;
        int n = 10;
        for(int i = 0;i < n;i++){
            System.out.printf("%d%c", a, i==n-1 ? '\n' : ' ');
        }

        //以空格等分隔符来分割字符串
        String s = "2,6,3,5,4,9,2,1,5,6,3,22,1";
        String[] arr = s.split(",",4);//注意第二个参数，表示分成几部分
        for(String e:arr){
            System.out.println(e);
        }

        //把集合转换成字符串
        List<Character> list = new ArrayList<>();
        list.add('A');
        list.add('B');
        System.out.println(StringUtils.join(list,","));



    }

    /**
     * int：
     *
     * int 数据类型是32位、有符号的以二进制补码表示的整数；
     * 最小值是 -2,147,483,648（-2^31）；
     * 最大值是 2,147,483,647（2^31 - 1）；
     * 一般地整型变量默认为 int 类型；
     * 默认值是 0 ；

     * long：
     *
     * long 数据类型是 64 位、有符号的以二进制补码表示的整数；
     * 最小值是 -9,223,372,036,854,775,808（-2^63）；
     * 最大值是 9,223,372,036,854,775,807（2^63 -1）；
     * 这种类型主要使用在需要比较大整数的系统上；
     */
}
