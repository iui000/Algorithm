package GinkgoStack.P7_Search.RotatedSortedArray;

/**
 33. 搜索旋转排序数组
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

 你可以假设数组中不存在重复的元素。

 你的算法时间复杂度必须是 O(log n) 级别。

 示例 1:

 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4
 示例 2:

 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1
 */
public class SearchInRotatedSortedArray33 {
    /**
     * 循环有序的数组进行二分查找.
     * 你可以假设数组中不存在重复的元素。
     * @param nums
     */
    public static int findTarget(int[] nums, int target){
        int n = nums.length;
        if(n<=0)
            return -1;
        int left = 0, right = n-1;
        while(left<=right)
        {
            int mid = left + ((right-left)/2);
            if(nums[mid] == target){
                return mid;
            }
            //说明转折点在右半边
            if(nums[left] <= nums[mid])
            {
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            else //转折点在左半边
            {
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 自我拓展
     *
     * 怎样以O(log n)的复杂度找到最大值和最小值
     * 这也是一个二分查找
     *
     * 剑指 Offer 11. 旋转数组的最小数字
     * 代码还需要验证
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int binarySearchMinIndex(int[] nums, int left,int right){

        if(left >= right){
            return left;
        }
        if(nums[left] < nums[right]){
            return left;
        }
        int m = (left+right)/2;
        if(nums[m] > nums[left]  && nums[m] > nums[right]){
            binarySearchMinIndex(nums,m+1,right);
        }
        if(nums[m] < nums[left]  && nums[m] < nums[right]){
            binarySearchMinIndex(nums,left,m+1);
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums =new int[]{4,5,6,7,0,1,2};


        int n = nums.length;
        int minIndex = binarySearchMinIndex(nums,0,n-1);
        int maxIndex = minIndex > 0 ? minIndex-1:n-1;
        System.out.print(maxIndex);

        findTarget(nums,0);
    }


}
