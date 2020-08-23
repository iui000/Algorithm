package GinkgoStack.P17_BitOperation;


import java.util.HashSet;
import java.util.Set;

/**
 * 898. 子数组按位或操作
 * 我们有一个非负整数数组 A。
 *
 * 对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），
 * 我们对 B 中的每个元素进行按位或操作，获得结果 A[i] | A[i+1] | ... | A[j]。
 *
 * 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
 *
 *
 *
 * 示例 1：
 *
 * 输入：[0]
 * 输出：1
 * 解释：
 * 只有一个可能的结果 0 。
 * 示例 2：
 *
 * 输入：[1,1,2]
 * 输出：3
 * 解释：
 * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 * 产生的结果为 1，1，2，1，3，3 。
 * 有三个唯一值，所以答案是 3 。
 * 示例 3：
 *
 * 输入：[1,2,4]
 * 输出：6
 * 解释：
 * 可能的结果是 1，2，3，4，6，以及 7 。
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 10^9
 */
public class SubarrayBitwiseORs {

    /**
     * 思路：方法一：集合
     * 效率不算高，但比暴力要高
     * @param A
     * @return
     */
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);//初始加入0，任何数和0执行或运算都等于它自己
        for (int x:A){
            Set<Integer> tmp = new HashSet<>();
            for(int y : cur){
                tmp.add(x|y);
            }
            tmp.add(x);//别忘了它自己
            cur = tmp;

            ans.addAll(cur);//合并到最终结果中去
        }

        return ans.size();
    }

    /**
     * dp
     */

    public int subarrayBitwiseORsByDP(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
            for (int j = i - 1; j >= 0; j--) {
                if ((arr[i] | arr[j]) == arr[j]) {
                    // arr[j]的置位位置包含了arr[i]的置位位置，
                    // 那么已经无需考虑arr[i]的加入与否
                    break;
                }
                // 求值的同时保留积累状态
                arr[j] |= arr[i];
                set.add(arr[j]);
            }
        }
        return set.size();
    }
}
