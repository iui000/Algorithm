package GinkgoStack.P1_ArrayProblem;

import java.util.HashMap;

/**
 * 992. K 个不同整数的子数组
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，
 * 则称 A 的这个连续、不一定独立的子数组为好子数组。
 *
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 *
 * 返回 A 中好子数组的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 *
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */
public class SubarraysWithKDifferentIntegers992 {

    class Solution {
        public int subarraysWithKDistinct(int[] A, int K) {

            int left1 = 0, left2 = 0;
            int ans = 0;
            Window window1 = new Window(), window2 = new Window();
            //遍历求以A[right]为右边界的包含K个不同整数的子数组个数
            for (int right = 0; right < A.length; right++) {

                window1.add(A[right]);   //窗口向右伸展
                window2.add(A[right]);

                while (window1.getDifferent() > K) {//[i..j]子数组满足要求第一个起始下标
                    window1.shrink(A[left1]);
                    left1++;
                }

                while (window2.getDifferent() >= K) {//[i..j]子数组满足要求的结束下标
                    window2.shrink(A[left2]);
                    left2++;
                }

                ans += (left2 - left1);
            }
            return ans;
        }
    }


    class Window {
        //记录窗口中各元素出现的次数{元素值：元素个数}
        private HashMap<Integer, Integer> map = new HashMap<>();
        //窗口内不同元素的数量
        private int different = 0;

        public void add(int ele) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
            //新加了一个原来没有的元素，则窗口内不同元素数量+1
            if (map.get(ele) == 1)
                different++;
        }

        public void shrink(int ele) {
            map.put(ele, map.get(ele) - 1);
            //移除窗口中的最后一个ele，则窗口内不同元素的数量就会-1（少了ele这个元素）
            if (map.get(ele) == 0)
                different--;
        }

        public int getDifferent() {
            return different;
        }
    }



}
