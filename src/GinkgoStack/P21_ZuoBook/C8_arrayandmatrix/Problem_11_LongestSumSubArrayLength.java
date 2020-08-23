package GinkgoStack.P21_ZuoBook.C8_arrayandmatrix;

import java.util.HashMap;

/**
 * 未排序数组中累加和为给定值的最长子数组长度
 * 变型：
 * 1.给定一个无序数组arr,其中元素可正、可负、可0，求arr所有的子数组中正数与负数个数相等的最长子数组长度
 * 解决办法：先把数组arr中的正数全部变成1，负数全部变成-1,而0不变；然后求累加和为0的最长子数组长度即可。
 *
 * 2.给定一个无序数组arr，其中元素只是1或0，求arr所有的子数组中0和1个数相等的最长子数组长度。
 * 解决办法：把数组中的0全部变为-1,而1不变，然后求累加和为0的最长子数组长度即可。
 *
 */
public class Problem_11_LongestSumSubArrayLength {

	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important
		int len = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				len = Math.max(i - map.get(sum - k), len);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
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
