package GinkgoStack.P27_Common;


import com.sun.deploy.util.StringUtils;

import java.util.*;

public class Base {

    /**
     * 快捷键
     * https://www.cnblogs.com/wtjqs/p/10486747.html
     * itar 普通的for循环
     */

    /**
     * 二进制字符串转为十进制数
     * 十进制数转为二进制字符串
     *      *     作者：LeetCode-Solution
     *      *     链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
     *      *     来源：力扣（LeetCode）
     *      *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    public static void main(String[] args) {
        /**
         * 字符串转为整型
         */
        String number = "266352449";
        int num = Integer.parseInt(number);//默认转为十进制
        long lon = Long.parseLong(number);//默认转为十进制
        String two = addBinary("1101","10");//二进制
        System.out.println(num);
        System.out.println(lon);
        System.out.println(two);

        /**
         * 输出规定的精度值,四舍五入
         */
        System.out.printf("%.4f",2.6326545);
        System.out.println(String.format("%.2f", 2.6326545));
        System.out.println();

        /**
         * 输出以空格等为分隔符的字符串
         */
        int a = 1;
        int n = 10;
        for(int i = 0;i < n;i++){
            System.out.printf("%d%c", a, i==n-1 ? '\n' : ' ');
        }

        /**
         * 以空格等分隔符来分割字符串
         */
        String s = "2,6,3,5,4,9,2,1,5,6,3,22,1";
        String[] arr = s.split(",",4);//注意第二个参数，表示分成几部分
        for(String e:arr){
            System.out.println(e);
        }

        /**
         * 把集合转换成字符串
         */
        List<Character> list = new ArrayList<>();
        list.add('A');
        list.add('B');
        //StringUtils要用apache的包
//        System.out.println(StringUtils.join(list,","));


        /**
         * 输出值有可选项时的试探做法
         */
        //System.out.println("YES");
        //看下 case 通过率，假设是 60%，那么说明结果为 YES 有 60% 的概率，所以可以这样写代码：
        System.out.println((new Random().nextInt() % 100) < 60 ? "YES" : "NO");


        /**
         * Math.round() 方法返回一个最接近的 int、long 型值，四舍五入。
         * round 表示"四舍五入"，算法为Math.floor(x+0.5) ，即将原来的数字加上 0.5 后再向下取整，
         *      * 所以 Math.round(11.5) 的结果为 12，Math.round(-11.5) 的结果为 -11。
         */


        /**
         * List转换成数组
         * https://www.jianshu.com/p/7eee157f74fc
         * Object[] toArray();
         * <T> T[] toArray(T[] a);
         */
        List<String> strList = new ArrayList<>();
        strList.add("list-a");
        strList.add("list-b");
        String[] strArr = strList.toArray(new String[strList.size()]);//必须是泛型数组，基本类型数据没办法


        /**
         * 数组转换成List
         * https://www.jianshu.com/p/7eee157f74fc
         * 可以使用Arrays.asList()或者Collections.addAll()方法,
         * 推荐使用Collections.addAll()方法，
         * 这样得到的返回对象可以往里面添加新的元素。
         */
        String[] strArray = { "array-a", "array-b" };
        //注意该方法,Arrays.asList返回值是java.util.Arrays类中一个私有静态内部类java.util.Arrays.ArrayList，
        //它并非java.util.ArrayList类。
        //java.util.Arrays.ArrayList类具有set()，get()，contains()等方法，
        //但是不支持添加add()或删除remove()方法,调用这些方法会报错UnsupportedOperationException。
        List<String> sList = Arrays.asList(strArray);
//        sList.add("array-c");//运行时，调用这些方法会报错UnsupportedOperationException。
        //解决方法是新建一个List对象,并且加入返回的sList，然后再add新元素：
        List<String> strListNew = new ArrayList<>(sList);
        strListNew.add("array-c");


        //更好的办法是Collections.addAll()方法
        String[] strArra = { "array-a", "array-b" };
        List<String> strL = new ArrayList<>(strArra.length);
        Collections.addAll(strL, strArra);
        strL.add("array-c");

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


    /**
     * Math.round() 方法返回一个最接近的 int、long 型值，四舍五入。
     *
     * round 表示"四舍五入"，算法为Math.floor(x+0.5) ，即将原来的数字加上 0.5 后再向下取整，
     * 所以 Math.round(11.5) 的结果为 12，Math.round(-11.5) 的结果为 -11。
     *
     * 语法
     * 该方法有以下几种语法格式：
     *
     * long round(double d)
     *
     * int round(float f)
     * 参数
     * d -- double 或 float 的原生数据类型
     *
     * f -- float 原生数据类型
     *
     * 返回值
     * 返回一个最接近的int、long型值，方法会指定返回的数据类型。
     */
}
