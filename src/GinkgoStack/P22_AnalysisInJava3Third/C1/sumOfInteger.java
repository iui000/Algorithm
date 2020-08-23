package GinkgoStack.P22_AnalysisInJava3Third.C1;

import java.util.HashMap;
import java.util.Map;

//一遍hash法
public class sumOfInteger {

   public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>((int) ((float) nums.length / 0.75F + 1.0F));

        for (int i = 0,len = nums.length; i < len; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum value");
    }

    public static void main(String[] arg){
       int[] nums = {5,5,2,6,3,8,9,1,5,2,4,7,3,6,2,5,4};
       int[] arr = twoSum(nums,10);
       System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

}
