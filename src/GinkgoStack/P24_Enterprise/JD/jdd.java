package GinkgoStack.P24_Enterprise.JD;


import java.util.HashMap;
import java.util.Map;

public class jdd {
/*
    abab a
    qeqe

    a - q
    b - e

    baba
    qeqe

    b - q
    a - e

    askfkjfkfl
    efwefmlmee

    a - e
    s - primeFactor
    k - w
    primeFactor - e
    k - primeFactor
    j - m
*/
    private static boolean fun(String s,String t){
        if(s.length() == 0 || t.length() == 0){
            return false;
        }

        if(s.length() != t.length()){
            return false;
        }

        int n = s.length();
        Map<Character,Character> map = new HashMap<>(128,0.75F);
        boolean ans = true;
        for(int i = 0;i<n;i++ ){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)){
                if(map.get(a) != b || map.get(map.get(a)) != a) {//
                    ans =  false;
                    break;
                }
            }else{
                map.put(a,b);
                map.put(b,a);
            }
        }

        return ans;
    }

    public static void fun(int[] arr, int N) {

    }

    public static void main(String[] args) {
        String s = "askfkjfkfl";
        String t = "efwefmlmee";
        System.out.println(fun(s,t));
    }
}
