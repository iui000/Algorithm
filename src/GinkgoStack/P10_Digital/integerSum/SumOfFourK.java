package GinkgoStack.P10_Digital.integerSum;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SumOfFourK {


    /**
     * 华为面试题
     * 每次从数组中选择一个数，选4次（可以重复选某一个数），使得其和为k,如何可以返回true
     * 要求： n^2 log n
     * @param arr
     * @param k
     * @return
     */
    //其实可以n^2
    private static boolean sumOfFourK(String[] arr,int k){
        int n = arr.length;
        Set<Integer> set  = new HashSet<>(n*n,0.75F);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
               set.add(Integer.parseInt(arr[i])* Integer.parseInt(arr[j]));
            }
        }
        for (Integer e :set) {
            if(set.contains(k - e)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] arr = s.split(",");
        int k = sc.nextInt();
        System.out.println(sumOfFourK(arr,k));
    }
}
