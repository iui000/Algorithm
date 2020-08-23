package GinkgoStack.P2_StringProblem.Palindrome;

import java.util.Scanner;

/**
 * 京东笔试题
 * 当时通过 %27
 * 对区间[n,m]内的所有正整数，去掉它其中一个数字后，判断是否为回文素数，如果是，就计数
 * 返回计数值。
 * m <= 1000000
 * 比如：
 * 输入：110 120
 * 输出：10
 * 110~119,都可以去掉最后一位变为11，它既是素数也是回文
 * 120，去掉一位后，都不是回文素数
 *
 *
 * 这道题没说最多去掉一位，而说的去掉一位，那意思是必须要去掉一位。
 *
 * 我没有直接遍历删除一个正整数的每一位去尝试，我估计这个暴力法能100%
 */
public class PalindromePrimeJD {

    //是否素数
    private static boolean isPrime(int n){
        if(n<2){
            return false;
        }
        int t = (int) Math.sqrt(n);
        for(int i = 2;i<t;i++){
            if(n%i == 0){//如果一个数，不是它平方根以内的数的倍数，那它就是素数
                return false;
            }
        }

        return true;
    }

    //返回去掉一位后回文串
    private static String[] validPalindrome(String s) {
        String[] res = new String[2];
        int len = s.length();
        int low = 0, high = s.length() - 1;

        boolean ok = false;
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
                if(low == high){//奇数回文
                    ok = true;
                }
                if(high < low){//偶数回文
                    ok = true;
                }
            } else {

                boolean canRemoveLeft = true, canRemoveRight = true;
                //删除右边字符
                for (int i = low, j = high - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        canRemoveRight = false;
                        break;
                    }
                }

                if(canRemoveRight){//删除右边字符剩下的是回文串
                    res[0] = s.substring(0,high)+s.substring(high+1,len);
                }

                //删除左边的字符
                for (int i = low + 1, j = high; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        canRemoveLeft = false;
                        break;
                    }
                }

                if(canRemoveLeft){//删除左边字符剩下的是回文串
                    res[1] = s.substring(0,low)+s.substring(low+1,len);
                }
                return res;
            }
        }

        if(res[0] == null && res[1] == null){
            if(ok){//如果它本身就是回文
                if(len%2==0){//偶数回文，去掉中间两个数字之一，剩下的一定回文串
                    res[0] = s.substring(0,len/2)+s.substring(len/2+1,len);
                    res[1] = s.substring(0,len/2+1)+s.substring(len/2+1+1,len);
                }else {//奇数回文，去掉中间数字即可
                    res[0] = s.substring(0,len/2+1)+s.substring(len/2+1+1,len);
                }
            }
        }
        return res;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int ans = 0;
//
//        for(int i = n;i<= m;i++){
//            String[] tmp = validPalindrome(String.valueOf(i));
//            boolean flag = false;
//
//            if(tmp[0] != null && !tmp[0].isEmpty()){
//                int th = Integer.valueOf(tmp[0]);
//                if(isPrime(th)){
//                    flag = true;
//                }
//            }
//
//            if(!flag && tmp[1] != null && !tmp[1].isEmpty()){
//                int th = Integer.valueOf(tmp[1]);
//                if(isPrime(th)){
//                    flag = true;
//                }
//            }
//
//            if(flag) ans++;
//        }
//

        //暴力
        for(int i = n;i<= m;i++){
            int le = String.valueOf(i).length();
            boolean f = false;
            for(int j = 0;j<le;j++){
                //删除这个数字
                StringBuilder st = new StringBuilder().append(i);
                StringBuilder tmp = st.replace(j,j+1,"");
                if(isPrime(Integer.valueOf(tmp.toString()))){//素数
                    if(tmp.equals(tmp.reverse())){//回文
                        f = true;
                        break;
                    }
                }
            }
            if(f){
                ans++;
            }

        }

        System.out.print(ans);

//        StringBuilder st = new StringBuilder().append(562);
//        st.replace(1,2,"");
//        System.out.print(st.toString());

    }
}
