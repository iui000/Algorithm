package GinkgoStack.P10_Digital.integerSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//这种方法是在三数之和的外面套了一层for循环


/**
 * 网上很多人说给出了O(n^2logn)的算法，不过严格来说，也不是真正的O(n^2 log n)
 * https://bbs.csdn.net/topics/390853322
 */
public class fourSum {

    public List<List<Integer>> fourSum(int[] nums, int target){
        /*定义一个返回值*/
        List<List<Integer>> result=new ArrayList<>();

        /*当数组为null或元素小于4个时，直接返回*/
        if(nums==null){return result;}
        if(nums.length < 4){return result;}

        /*对数组进行从小到大排序*/
        Arrays.sort(nums);

        /*数组长度*/
        int length=nums.length;

        /*获取最小的四数之和，如果最小值比目标值大，则也不用找了,直接返回*/
        int min=nums[0]+nums[1]+nums[2]+nums[3];
        if(min > target){
            return result;
        }

        /*获取最大的四数之和，如果最大值比目标值小，则也不用找了,直接返回*/
        int max=nums[length-1]+nums[length-2]+nums[length-3]+nums[length-4];
        if(max<target){
            return result;
        }

        /*定义4个指针firstIndex，twoIndex，left，right ;
         *firstIndex从0开始遍历，
         *twoIndex从firstIndex + 1开始遍历,接下来的过程就是三数之和;
         *left指向twoIndex+1，right指向数组最大值
         */
        for(int firstIndex=0;firstIndex<length-3;firstIndex++){

            /*当firstIndex的值与前面的值相等时忽略*/
            if(firstIndex>0 && nums[firstIndex]== nums[firstIndex-1]){
                continue;
            }

            /*对于当前firstIndex下标，获取此轮最小的四数之和，如果最小值比目标值大，则不用再找了，退出循环*/
            int current4min=nums[firstIndex]+nums[firstIndex+1]+nums[firstIndex+2]+nums[firstIndex+3];
            if(current4min > target){ break; }


            /*对于当前firstIndex下标，获取此轮的最大值，如果最大值比目标值小，直接下一轮*/
            int current4Max=nums[firstIndex]+nums[length-1]+nums[length-2]+nums[length-3];
            if(current4Max < target){
                continue;
            }

            /*第二层循环twoIndex，初始值指向firstIndex+1*/
            for(int twoIndex = firstIndex+1; twoIndex<length-2; twoIndex++){
                /*当twoIndex的值与前面的值相等时忽略*/
                if(twoIndex>firstIndex+1 && nums[twoIndex]==nums[twoIndex-1]){
                    continue;
                }

                int left=twoIndex+1;//定义左指针left指向twoIndex+1
                int right=length-1;//定义指针h指向数组末尾

                /*获取当前最小值，如果最小值比三数的目标值大，可以跳出循环*/
                int current3min=nums[twoIndex]+nums[left]+nums[left+1];
                if(current3min > target - nums[firstIndex]){
                    break;
                }

                /*获取此轮三数的最大值，如果最大值比三数的目标值小，则直接下一轮*/
                int current3Max=nums[twoIndex]+nums[right]+nums[right-1];
                if(current3Max < (target - nums[firstIndex])){
                    continue;
                }

                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (left < right){
                    int current3Sum = nums[twoIndex]+nums[left]+nums[right];
                    if(current3Sum == target - nums[firstIndex]){

                        result.add(Arrays.asList(nums[firstIndex],nums[twoIndex],nums[left],nums[right]));
                        left++;

                        //快速滑过左边的相同值，避免重复结果加入到最终的结果集中
                        while(left<right && nums[left]==nums[left-1]){ left++; }

                        right--;
                        //快速滑过右边的相同值，避免重复结果加入到最终的结果集中
                        while(left<right && firstIndex < right && nums[right]==nums[right+1]){ right--; }
                    }else if(current3Sum > target - nums[firstIndex] ){//右边往前滑
                        right--;
                    }else {//左边向后滑
                        left++;
                    }
                }
            }
        }
        return result;
    }
}