package GinkgoStack.P24_Enterprise.Pinduoduo;

import java.util.Scanner;

/**
 * 没有完全AC
 */
public class FlyingChess {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        int i = 0;
        while (i<n){
            arr[i] = sc.nextInt();
            i++;
        }

        //paradox
        boolean f = false;
        int sum = 0;
        int back = 0;
        if(k==0){
            f = true;
            System.out.print("paradox");
        }else if(k > 0 && n <= 0){

        }else {
            for(int j = 0;j<n;j++){
                sum += arr[j];
                if(sum >k){
                    sum -= arr[j];
                    back++;
                }else if(sum == k && j != n-1){
                    f = true;
                    System.out.print("paradox");
                    break;
                }else if(sum == k && j == n-1){
                    sum -= arr[j];
                    back++;
                    break;
                }

            }
        }

        if(!f){
            System.out.print(k-sum);
            System.out.print(" ");
            System.out.print(back);
        }
    }
}
