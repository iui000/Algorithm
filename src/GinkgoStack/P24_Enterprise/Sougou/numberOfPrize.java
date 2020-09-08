package GinkgoStack.P24_Enterprise.Sougou;

import java.util.*;

/**
 * 题意是：
 * 有三个数，表示道具A\B\C的数目；
 * 可以任意用两个道具 换作 另外一种道具；
 * A\B\C三种道具一样一个就能换取一件礼物；
 * 问：最终能换取得礼物数量最大是多少？
 */
public class numberOfPrize {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回能交换奖品的最大数量
     * @param a int整型
     * @param b int整型
     * @param c int整型
     * @return int整型
     */
    public static int numberofprize (int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        int res = arr[0];
        int min = arr[0];

        for (int i = 0; i < arr.length; i++) {
            arr[i] -= min;
        }

        while (arr[2] > 0) {
            if (arr[1] == 0) {
                res += arr[2] / 5;
                return res;
            } else if (arr[1] == arr[2] || arr[1] == arr[2] - 1) {
                res += arr[1] / 2;
                return res;
            }

            int more = arr[2] - arr[1];
            arr[0] = Math.min(more / 2, arr[1]);
            arr[2] -= arr[0] * 2;


            res += arr[0];
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= arr[0];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(numberofprize(4,4,2));
//        System.out.println(numberofprize(9,3,3));
    }



}
