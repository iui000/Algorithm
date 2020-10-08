package GinkgoStack.P9_Hash;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 东西南北，走到重复的路就是True,否则就false
 */
public class RepeatTheRoad {

    private static boolean fun(String s){
        int n = s.length();
        String ss = s.toUpperCase();
        Set<String> path = new HashSet<>();
        path.add("0,0");
        int x = 0;
        int y = 0;
        for(int i = 0;i<n;i++){
            if(ss.charAt(i) == 'E')y++;
            if(ss.charAt(i) == 'W')y--;
            if(ss.charAt(i) == 'S') x++;
            if(ss.charAt(i) == 'N') x--;

            if(path.contains(x+","+y))
                return true;

            path.add(x+","+y);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        /**
         * 惨痛的血泪史，首字母要大写
         */
        System.out.print(fun(s)?"True":"False");

    }
}
