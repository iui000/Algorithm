package GinkgoStack.P24_Enterprise.HuaWei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main11 {

    private static int[] getNextArray(String s){
        if(s.length() == 1){
            return new int[]{-1};
        }
        char[] model = s.toCharArray();
        int len = s.length();
        int[] next = new int[len];//表示的是前缀和后缀相等的最大长度
        next[0] = -1;
        next[1] = 0;
        int pre = 0;
        int cur = 2;
        while (cur < len){
            if(model[cur-1] == model[pre]){
                next[cur++] = ++pre;
            }else if(pre > 0) {
                pre = next[pre];
            }else {//最左位置
                next[cur++] = 0;
            }
        }

        return next;
    }

    private static int KMP(String s,String t,int[] next){
//        int[] next = getNextArray(t);
        int i = 0;
        int j = 0;
        while (i < s.length() && j<t.length() ){
            if (s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }else if(next[j] == -1){//next[0] = -1;
                i++;
            }else {
                j = next[j];//滑动
            }
        }
        return  j == t.length() ? i-j:-1;
    }


/*
135682318
23457
14282123
14231728
3
1724153
 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(25);

        String a = "";
        while (in.hasNext()) {
            String tmp = in.next();

            if(tmp.length() == 1){
//                Character c = tmp.charAt(0);
                a = tmp;
                break;

            }else {
                list.add(tmp);
            }
        }
        String t = in.next();
        in.close();

        String pattern = getPattern(t,a);
        if(pattern.isEmpty() ){
            for (String s:list) {
                System.out.println(s);
            }
        }else {
            func(list,pattern,a);
        }

    }

    private static String getPattern(String s, String  a) {
        Character A = a.charAt(0);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
             Character tmp = s.charAt(i);
             if(tmp.compareTo(A) < 0){
                 ans.append(tmp);
             }
        }
        return ans.toString();
    }

    static void func(List<String> list, String p,String a) {
        int[] next = getNextArray(p);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).length() > 0){
                String s = getPattern(list.get(i),a);
                if(KMP(s,p,next) != -1){
                    System.out.println(list.get(i));
                }
            }
        }
    }
}

