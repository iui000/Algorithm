package GinkgoStack.P11_Tree.CountOfDifferentTree;

import java.util.Scanner;

/**
 * n个节点的不用形态的二叉树有多少个？
 * 左中右算作不同的位置，但是节点无编号。
 *
 * 思路：
 * 估计可以用树形DP
 */
public class base2 {

    private static long C(int n, int m){
        n = n-m+1;
        long ans = 1;
        for(int i = 1;i<= m;i++){
            ans *= n++;
            ans /= i;
        }

        return ans % 1_000_000_007;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.print(C(2*n,n)/(n+1));
    }
}
