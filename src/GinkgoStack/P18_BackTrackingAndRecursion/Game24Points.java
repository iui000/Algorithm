package GinkgoStack.P18_BackTrackingAndRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode原题
 * 679. 24 点游戏
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，)
 * 的运算得到 24。
 *
 * 示例 1:
 *
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 *
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 *
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。
 * 例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 */
public class Game24Points {

    private static boolean backTracking(List<Double> nums){
        if(nums.size() == 0){
            return false;
        }

        if(nums.size() == 1){
            return Math.abs(nums.get(0) -24) < 1e-6;
        }

        for(int i =0;i< nums.size();i++){
            for(int j = 0;j< nums.size();j++){
                if(i != j){
                    List<Double> tmp = new ArrayList<>();
                    for(int k = 0;k<nums.size();k++){
                        if(k != i && k!= j){
                            tmp.add(nums.get(k));
                        }
                    }

                    for(int k = 0;k<4;k++){
                        if(k<2 && j >i)
                            continue;
                        if(k == 0){
                            tmp.add(nums.get(i)+ nums.get(j));
                        }
                        if(k == 1){
                            tmp.add(nums.get(i)* nums.get(j));
                        }
                        if(k == 2){
                            tmp.add(nums.get(i)- nums.get(j));
                        }

                        if(k == 3){
                            if(nums.get(j) == 0)
                                continue;
                            tmp.add(nums.get(i) /nums.get(j));
                        }

                        if(backTracking(tmp)){
                            return true;
                        }

                        tmp.remove(tmp.size()-1);
                    }
                }
            }
        }

        return false;
    }


    public static boolean can24Points (int[] arr) {
        // write code here
        List<Double> cur = new ArrayList<>();

        for (int e:arr){
            cur.add((double)e);
        }

        return backTracking(cur);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{7,2,1,10};
        System.out.print(can24Points(arr));
    }
}
