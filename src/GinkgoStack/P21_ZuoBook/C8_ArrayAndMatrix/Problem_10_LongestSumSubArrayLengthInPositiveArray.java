package GinkgoStack.P21_ZuoBook.C8_ArrayAndMatrix;


/**
 * 未排序正数数组中，累加和为给定值K的最长子数组长度
 *
 * 因为是正数，可以直接用普通的滑动窗口
 */
public class Problem_10_LongestSumSubArrayLengthInPositiveArray {

	public static int getMaxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {//小于目标值，扩张
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {//大于目标值，收缩
				sum -= arr[left++];
			}
		}
		return len;
	}

	public static int[] generatePositiveArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 10) + 1;
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
		int len = 20;
		int k = 15;
		int[] arr = generatePositiveArray(len);
		printArray(arr);
		System.out.println(getMaxLength(arr, k));

	}

}
