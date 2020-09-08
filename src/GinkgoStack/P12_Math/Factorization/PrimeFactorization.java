package GinkgoStack.P12_Math.Factorization;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 案例：将一个正整数分解质因数。例如:输入 100 : 打印 100 = 2 * 2 * 5 * 5
 *
 */
public class PrimeFactorization {

    /**
     *  * 思路分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：
     *  *
     *  * 如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
     *  *   如果n>k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数n,重复执行第一步。
     *  *   如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
     */




    /**
     * 写法1，递归
     *
     * @param n
     * @param tmp tmp 初值设为2
     * @param ans
     */
    public static void primeFactor(int n, int tmp, List<Integer> ans){
        while (tmp <= n){
            if(tmp == n){
                ans.add(n);
                break;
            }else if(n>tmp && n%tmp == 0){
                ans.add(tmp);
                n = n/tmp;
                primeFactor(n,tmp,ans);
                break;
            }else if (n>tmp && n%tmp != 0){
                tmp++;
                primeFactor(n,tmp,ans);
                break;
            }
        }
    }

    /**
     * 写法2，迭代
     * @param n
     * @return
     */
    public static List<Integer> primeFactor2(int n){
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 2 ; i <= n ; i ++) {
            if (n % i == 0) {
                ans.add(i);
                n = n / i;
                i = 1;
            }
        }

        return ans;
    }


    /**
     * 将一个正整数分解质因数。例如:输入 100 : 打印 100 = 2 * 2 * 5 * 5
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> ans = primeFactor2(n);
        for (int i = 0 ; i < ans.size() ; i ++) {
            System.out.printf("%d%c", ans.get(i), i== ans.size()-1 ? '\n' : ' ');
        }


        List<Integer> ans2 = new ArrayList<>();
        primeFactor(n,2,ans2);
        for (int i = 0 ; i < ans2.size() ; i ++) {
            System.out.printf("%d%c", ans2.get(i), i== ans2.size()-1 ? '\n' : ' ');
        }

    }
}
