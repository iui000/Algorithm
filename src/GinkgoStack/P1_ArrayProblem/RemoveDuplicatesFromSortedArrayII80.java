package GinkgoStack.P1_ArrayProblem;


/**
 * 删除排序数组中的重复项 II
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * Java
 *
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/algorithm-and-interview-skills/xizabg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RemoveDuplicatesFromSortedArrayII80 {

    class Solution {

        public int removeDuplicates(int[] nums) {

            //
            // Initialize the counter and the second pointer.
            //
            int j = 1, count = 1;

            //
            // Start from the second element of the array and process
            // elements one by one.
            //
            for (int i = 1; i < nums.length; i++) {

                //
                // If the current element is a duplicate, increment the count.
                //
                if (nums[i] == nums[i - 1]) {
                    count++;
                } else {
                    //
                    // Reset the count since we encountered a different element
                    // than the previous one.
                    //
                    count = 1;
                }

                //
                // For a count <= 2, we copy the element over thus
                // overwriting the element at index "j" in the array
                //
                if (count <= 2) {//如果次数在两次以内，进行覆盖，前指针往后挪。
                    nums[j++] = nums[i];
                }
                //如果次数超过两次，前指针不动，后指针往后走
            }
            return j;
        }
    }

}
