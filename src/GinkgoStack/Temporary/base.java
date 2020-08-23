package GinkgoStack.Temporary;

import java.util.ArrayList;
import java.util.Scanner;

public class base {

    private static void fun(){

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<Integer> res = new ArrayList<>(T);

        for (int i = 0;i<T;i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
        }

        for(int i = 0;i<T;i++){
            if(i != T-1){
                System.out.println(res.get(i));
            }else {
                System.out.print(res.get(i));
            }
        }

    }
}
