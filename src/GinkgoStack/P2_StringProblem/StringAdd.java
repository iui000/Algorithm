package GinkgoStack.P2_StringProblem;


/**
 * AC代码
 * 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * 思路：数字字符串的按照加法规则计算即可，注意考虑进位，和最后一个进位
 */
public class StringAdd {

    public static String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();

        if(len1 == 0 && len2 == 0){
            return "";
        }

        if(num1.equals("0")){
            return num2;
        }
        if(num2.equals("0")){
            return  num1;
        }

        int N = len1 > len2?len1+1:len2+1;
        StringBuilder stringBuilder = new StringBuilder();
        int[] res = new int[N];
        int carry = 0;
        int k = N-1;
        int i = len1-1,j = len2-1;
        while (i>=0 && j>=0){
            int sum = num1.charAt(i)-'0' + num2.charAt(j)-'0' + carry;
            carry = sum/10;
            res[k--] = sum%10;
            i--;
            j--;
        }
        while (i>=0){
            int sum = num1.charAt(i)-'0' + carry;
            carry = sum/10;
            res[k--] = sum%10;
            i--;
        }

        while (j>=0){
            int sum = num2.charAt(j)-'0' + carry;
            carry = sum/10;
            res[k--] = sum%10;
            j--;
        }

        if(carry > 0){
            res[k--] = carry;
        }

        for(;k<N-1;k++){
            stringBuilder.append(res[k+1]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s1 = "408";
        String s2 = "5";
        System.out.print(addStrings(s1,s2));
    }
}
