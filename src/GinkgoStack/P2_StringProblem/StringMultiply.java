package GinkgoStack.P2_StringProblem;

import java.math.BigInteger;
import java.util.Arrays;

public class StringMultiply {

    private static int MAX_LEN = 220;
    /**
     * 仅用作验证正确性，题目要求不允许这样写
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        Long n1 = Long.parseLong(num1);
        Long n2 = Long.parseLong(num2);

        BigInteger bigInteger = BigInteger.valueOf(n1).multiply(BigInteger.valueOf(n2));
        return bigInteger.toString();
    }

    /**
     * 自己的解法
     * 按照乘法规则模拟一便即可
     * 时间复杂度是O(n*m)
     *         int n = num1.length();
     *         int m = num2.length();
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply2(String num1, String num2) {
        if(num1.isEmpty() || num2.isEmpty()){
            return "0";
        }

        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int n = num1.length();
        int m = num2.length();

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        MAX_LEN = n+m;
        int[] res = new int[MAX_LEN];
        int first = 0;

        for(int i = n-1,offset = 0;i>=0;i--,offset++){

            int aaa = chars1[i]-'0';

            int[] midRes = new int[m + offset + 1];//为什么加1呢，因为十进制x位的数 乘以 十进制y位的数,结果最多x+y位
            int multiCarry = 0;
            int multiIndex = m;//相当于剩下offset个位置补上0

            for(int h = m-1; h >= 0;h--,multiIndex--){
                int bbb = chars2[h]-'0';
                int ccc = aaa * bbb + multiCarry;
                multiCarry = ccc / 10;
                int low = ccc % 10;
                midRes[multiIndex] = low;
            }
            midRes[multiIndex] = multiCarry;
//            System.out.println(Arrays.toString(midRes));


            //每算完一轮就做一次加法

            first = add(midRes,res);
            System.out.println(first);
            System.out.println(Arrays.toString(res));
        }

        while (res[first] == 0){first++;}
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = first;i<MAX_LEN;i++){
            stringBuilder.append(res[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * 数据加法
     */
    private static int add(int[] num, int[] res) {

        int carry = 0;
        int index = MAX_LEN-1;
        int len = num.length;

        for(int i = len-1;i >= 0;i--,index--){
            int tmp = num[i] + res[index] + carry;
            carry = tmp/10;
            res[index] = tmp % 10;
        }
        while (carry != 0){
            int tmp = res[index] + carry;
            carry = tmp/10;
            res[index--] = tmp % 10;
        }

        return index+1;

    }

    public static void main(String[] args) {
        String num1 = "123", num2 = "456";
        System.out.println(multiply2(num1,num2));
    }
}
