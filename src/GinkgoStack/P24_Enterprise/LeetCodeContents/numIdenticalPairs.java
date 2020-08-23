package GinkgoStack.P24_Enterprise.LeetCodeContents;

import java.util.HashMap;
import java.util.Map;

public class numIdenticalPairs {

    public static int numIdenticalPairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;

        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }

        for(Integer k:map.keySet()){
            int t  = map.get(k);
            res += t*(t-1)/2;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(numIdenticalPairs(nums));
    }


}
