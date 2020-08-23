package GinkgoStack.P24_Enterprise.DiDi;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 这道题有点无聊
 * n = abc + acc;
 * 找到这样成对三位数，满足 n = abc + acc;
 * 输出有多少对，以及每一对的，按照abc的大小顺序
 */

public class ABCaddACC {
    static List<int[]> res = new LinkedList<>();

    private static int func(int n){
        int x = n/2;
        int a = x/100;

        int y = n - a*100*2;
        if(y % 2 != 0){//不能为奇数
            return 0;
        }

        int nn = y%10;//个位数
        if(nn % 2 != 0 ){//必须为偶数
            return 0;
        }
        int[] c = new int[2];
        c[0] = nn/2;//c只能有这两个值
        c[1] = (10 + nn/2)/2;

        int count = 0;
        for(int b = 0;b<9;b++){
            for (int i = 0;i<2;i++){
                if(c[i] != a){
                    if(b != c[i] && b != a){
                        int mm = 100*a + 10*b + c[i];
                        int kk = 100*a + 10*c[i] + c[i];
                        if(mm + kk == n){
                            int[] arr = new int[]{mm,kk};
                            res.add(arr);
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if(n/2 <100 || n/2 >= 1000){
            System.out.println(0);
            return ;
        }

        if(n % 2 != 0){//奇数
            System.out.println(0);
            return ;
        }

        int hh = func(n);
        System.out.println(hh);
        for(int[] e:res){
            for(int j =0;j < 2;j++){
                System.out.printf("%d%c", e[j], j==1 ? '\n' : ' ');
            }
        }

    }


}
