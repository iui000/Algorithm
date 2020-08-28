package GinkgoStack.P24_Enterprise.Tencent.qiu2020;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class KthSubstr {

    /**
     * 把所有长度小于等于K的子串排序找第K个即可。这样保证前K个字串一定被选择同时不会超时。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s  = sc.next();
        int k = sc.nextInt();
        SortedSet<String> sortedSet = new TreeSet<>();
        int n = s.length();

        for (int i = 0;i<n;i++){
            //把所有长度小于等于K的子串排序找第K个即可。
            for (int j = i;j<n && j+1-i<=k;j++){
                String tmp = s.substring(i,j+1);
                sortedSet.add(tmp);
            }
        }

        String ans = "";
        for (int i = 1;i<= k;i++){
            ans = ((TreeSet<String>) sortedSet).pollFirst();
        }

        System.out.print(ans);
    }
}
