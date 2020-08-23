package GinkgoStack.P24_Enterprise.MeiTuan;

import java.util.ArrayList;
import java.util.Scanner;

public class checkUserName {

    /*
    5
    Ooook
    Hhhh666
    ABCD
    Meituan
    6666
     */
    private static String va(String s){
        if(s.length() < 2 || s.length() > 20){
            return "Wrong";
        }

        if(Character.isDigit(s.charAt(0))){
            return "Wrong";
        }

        if(!Character.isAlphabetic(s.charAt(0))){
            return "Wrong";
        }

        boolean num = false;
        for(int i = 0;i<s.length();i++){
            if(!Character.isAlphabetic(s.charAt(i)) &&
                    !Character.isDigit(s.charAt(i))){
                return "Wrong";
            }
            if(Character.isDigit(s.charAt(i))){
                num = true;
            }
        }

        return num?"Accept":"Wrong";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<Integer> res = new ArrayList<>(T);


        String[] ss = new String[T];
        for (int i = 0;i<T;i++){
            String tmp = sc.next();
            System.out.println(va(tmp));
        }
    }
}
