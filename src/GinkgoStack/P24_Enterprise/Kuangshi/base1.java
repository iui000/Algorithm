package GinkgoStack.P24_Enterprise.Kuangshi;

import java.util.ArrayList;
import java.util.Scanner;

public class base1 {

    /**
     *
     * @param nums int整型一维数组
     * @return int整型一维数组
     */
    public static int[] product (int[] nums) {
        // write code here

        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int n = nums.length;

        int[] left = new int[n];
        int mu = 1;
        left[0] = 1;
        for (int i = 1;i<n;i++){
            mu *= nums[i-1];
            left[i] = mu;
        }

        int[] ans = new int[n];
        ans[n-1] = 1;
        mu = 1;
        for (int i = n -2;i>= 0;i--){
            mu *= nums[i+1];
            ans[i] = mu;
        }

        for (int i = 0;i<n;i++){
            ans[i] = ans[i] * left[i];
        }

        return ans;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        int[] arr = new int[]{1,2,3};
        int[] ans = new int[3];
        ans =  product(arr);
    }
}
