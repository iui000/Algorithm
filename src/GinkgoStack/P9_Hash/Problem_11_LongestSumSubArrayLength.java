package GinkgoStack.P9_Hash;

import java.util.HashMap;

/**
 * 未排序数组中累加和为给定值K的最长子数组长度
 * 给定一个无序数组,其中元素可正、负、可0，给定一个目标值K，求数组中的子数组中累加和为k的最长子数组长度
 *
 * 变型：
 * 1.给定一个无序数组arr,其中元素可正、可负、可0，求arr所有的子数组中正数与负数个数相等的最长子数组长度
 * 解决办法：先把数组arr中的正数全部变成1，负数全部变成-1,而0不变；然后求累加和为0的最长子数组长度即可。
 *
 * 2.给定一个无序数组arr，其中元素只是1或0，求arr所有的子数组中0和1个数相等的最长子数组长度。
 * 解决办法：把数组中的0全部变为-1,而1不变，然后求累加和为0的最长子数组长度即可。
 *
 */
public class Problem_11_LongestSumSubArrayLength {

    /**
     * 解法
     * 前缀和求差值（等于k） + Hash
     * 时间复杂度O(N) 空间复杂度O（n）
     * @param arr
     * @param k
     * @return
     */

	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		//key 为和，value 为该sum值最早出现的位置
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		//这个初值很重要，意义是，如果任何一个数都不加，前缀和为0，这样从位置0开始的子数组才被我们考虑到了，否则，会漏掉0位置的元素
		map.put(0, -1); // important

		int len = 0;
		int sum = 0;//前缀和
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			//如果某个前缀和等于sum - k，假设map.get(sum - k)记为j,那么arr[j+1 ... i]的和就是k
			if (map.containsKey(sum - k)) {
			    //更新len
				len = Math.max(i - map.get(sum - k), len);
			}

			//如果map中不存在该sum,那么就加入
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}

			//已经存在就什么也不做
		}
		return len;
	}

    /**
     *
     * @param size
     * @return
     */
	public static int[] generateArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 11) - 5;
		}
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = generateArray(20);
		printArray(arr);
		System.out.println(maxLength(arr, 10));

	}

}
