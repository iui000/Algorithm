package GinkgoStack.P10_Digital.integerSum;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
 *
 * 第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
 *
 * 第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
 *
 * 也就是说，我们枚举的三元组 (a, b, c)满足a≤b≤c，保证了只有 (a, b, c)这个顺序会被枚举到，
 * 而 (b, a, c)、(c, b, a)等等这些不会，这样就减少了重复。要
 * 实现这一点，1.我们可以将数组中的元素从小到大进行排序
 * 2.当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，
 * 那么就可以使用双指针的方法，将枚举的时间复杂度从 O(N^2)减少至 O(N)
 *
 *
 */
class threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        //先排序
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        // 第一层循环枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同，保证不重复
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            //接下来双指针法
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];//后面两数之和的目标值
            // 从左往右枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 同样需要和上一次枚举的数不相同，保证不重复
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧，这也是为了保证结果不重复
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();//加入到结果中去
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}