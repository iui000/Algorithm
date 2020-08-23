package GinkgoStack.P24_Enterprise.Xiechen;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 携程笔试
 * AC
 * 两种砖，长度分别为a，b数量无限
 * 选出k块砖铺路
 * 问，路的长度有哪些可能值（要去重），升序输出
 *
 * 思路：用SortedSet就行
 */
public class PaveRoad {

    static int[] divingBoard(int a, int b, int k) {
        int[] ans;
        if(k == 0){
            return new int[0];
        }

        if(a == b){
            ans = new  int[1];
            ans[0] = a*k;
            return ans;
        }

        SortedSet<Integer> set = new TreeSet<>();
        int c = Math.min(a,b);
        int d = Math.max(a,b);
        a = c;
        b = d;
        for(int i = 0;i<=k;i++){
            set.add(a*(k-i) + b*i);
        }

        ans = new int[set.size()];
        int i = 0;
        for (Integer e :set){
            ans[i++] = e;
        }

        return ans;
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] res;

        int _a;
        _a = Integer.parseInt(in.nextLine().trim());

        int _b;
        _b = Integer.parseInt(in.nextLine().trim());

        int _k;
        _k = Integer.parseInt(in.nextLine().trim());

        res = divingBoard(_a, _b, _k);
        String value = "[]";
        if (res != null && res.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i == 0) {
                    stringBuilder.append("[");
                }
                stringBuilder.append(res[i]);
                if (i == res.length - 1) {
                    stringBuilder.append("]");
                } else {
                    stringBuilder.append(",");
                }
            }
            value = stringBuilder.toString();
        }
        System.out.println(value);
    }
}
