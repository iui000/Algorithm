package GinkgoStack.P20_DynamicProgramming;

public class DynamicProgramming {
    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }

    public static int maxSubArray(int[] nums) {

        int pre = 0,maxSum = nums[0];

        for(int i = 0;i<nums.length;i++){
            pre = Integer.max(nums[i],pre + nums[i]);
            maxSum = Integer.max(maxSum,pre);
        }

        return maxSum;
    }


}
