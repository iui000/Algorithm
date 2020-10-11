package GinkgoStack.P7_Sort.WiggleSort;

import java.util.Arrays;

/**
 * 324. 摆动排序 II
 * 给定一个无序的数组 nums，将它重新排列成
 * nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 示例 1:
 *
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * 示例 2:
 *
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 *
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class WiggleSortII324 {

    /**
     * 这道题与280题不同的是，严格大于和小于，方法就不那么简单了
     *     作者：力扣 (LeetCode)
     *     链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/x8u5gv/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public class Solution {
        /**
         * 分成长度相等（或者差值为1）两个部分，左边的小于右边的部分
         * 然后进行元素的穿插即可
         * @param nums
         */
        public void wiggleSort(int[] nums) {
            //将原数组copy到临时数组中
            int[] tem = Arrays.copyOf(nums,nums.length);

            int mid = getKth(tem,
                    0, nums.length-1,
                    nums.length/2 + 1);

            int[] ans = new int[nums.length];
            Arrays.fill(ans,mid);//初值是中位数

            //l指向奇数位置下标，
            // r指向偶数位置下标，
            int l, r;
            if (nums.length % 2 == 0) {//长度为偶数
                l = nums.length - 2;//倒数第二个位置，从后往前走；
                r = 1;//第二个位置，从前往后走
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] < mid) {//把小于中位数的元素放在奇数位置
                        ans[l] = nums[i];
                        l -= 2;
                    } else if (nums[i] > mid) {//把大于中位数的元素放在偶数位置
                        ans[r] = nums[i];
                        r += 2;
                    }
                }
            } else {//长度为奇数
                l = 0;
                r = nums.length - 2;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] < mid) {
                        ans[l] = nums[i];
                        l += 2;
                    } else if (nums[i] > mid) {
                        ans[r] = nums[i];
                        r -= 2;
                    }
                }
            }
            //再copy一次
            for (int i = 0; i < nums.length; i++) {
                nums[i] = ans[i];
            }
        }

        /**
         * 获取中位数
         * @param nums
         * @param start
         * @param end
         * @param k
         * @return
         */
        public int getKth(int[] nums, int start, int end, int k) {
            int pivot = nums[end];
            int left = start;
            int right = end;

            while(true) {
                while (nums[left] < pivot && left < right) {
                    left++;
                }
                while (nums[right] >= pivot && left < right) {
                    right--;
                }

                if (left == right) {
                    break;
                }
                swap(nums, left, right);
            }

            swap(nums, left, end);

            if (k == left + 1) {
                return pivot;
            } else if (k < left + 1) {
                return getKth(nums, start, left - 1, k);
            }else {
                return getKth(nums, left + 1, end, k);
            }
        }

        public void swap(int[] nums, int s, int e) {
            int tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
        }
    }


}
