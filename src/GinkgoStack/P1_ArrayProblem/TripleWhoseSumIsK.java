package GinkgoStack.P1_ArrayProblem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 牛客
 * 题目描述
 * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 * 注意：
 * 三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
 * 解集中不能包含重复的三元组。
 * 例如，给定的数组 S = {-1 0 1 2 -1 -4},解集为(-1, 0, 1) (-1, -1, 2)
 */
public class TripleWhoseSumIsK {
    ArrayList<ArrayList<Integer>> ans;

    /**
     * 左书代码
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        ans = new ArrayList<>();
        printUniqueTriad(num,0);
        return this.ans;
    }

    private  void printUniqueTriad(int[] arr, int k) {
        if (arr == null || arr.length < 3) {
            return;
        }

        //选定第一个元素，注意是arr.length - 2，剩余的子数组元素不能小于两个嘛
        for (int i = 0; i < arr.length - 2; i++) {
            //保证第一个元素不重复
            if (i == 0 || arr[i] != arr[i - 1]) {
                //打印剩余的
                printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
            }
        }
    }

    /**
     *
     * @param arr
     * @param f 已经选定的第一个元素的下标
     * @param l 剩余子数组左边界
     * @param r 剩余子数组右边界
     * @param k 剩余目标和
     */
    private  void printRest(int[] arr, int f, int l, int r, int k) {
        while (l < r) {
            if (arr[l] + arr[r] < k) {
                l++;
            } else if (arr[l] + arr[r] > k) {
                r--;
            } else {
                //注意这里，l == f + 1
                if (l == f + 1 || arr[l - 1] != arr[l]) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>(3);
                    //System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
                    tmp.add(arr[f]);
                    tmp.add(arr[l]);
                    tmp.add(arr[r]);
                    this.ans.add(tmp);
                }
                l++;
                r--;
            }
        }
    }
}
