package GinkgoStack.P21_ZuoBook.C8_ArrayAndMatrix;

/**
 * 8.9 不重复打印 排序数组中相加和为给定值的所有二元组和三元组
 */
public class Problem_09_PrintUniquePairAndTriad {

    /**
     * 不重复二元组
     * 左右双指针
     * @param arr
     * @param k 目标和
     */
	public static void printUniquePair(int[] arr, int k) {
		if (arr == null || arr.length < 2) {
			return;
		}

		int left = 0;
		int right = arr.length - 1;
		//循环条件
		while (left < right) {
		    //如果和小于目标值，左边移动
			if (arr[left] + arr[right] < k) {
				left++;
			} else if (arr[left] + arr[right] > k) {
			    //如果和大于目标值，右边移动
				right--;
			} else {
			    //如果等于目标值，那么要排除重复的二元组
                //left为零时当然不重复
                //如果左边值等于前一个值，那么之前一定打印过这个二元组，因此无序重复打印
				if (left == 0 || arr[left - 1] != arr[left]) {
					System.out.println(arr[left] + "," + arr[right]);
				}
				//两边都收缩
				left++;
				right--;
			}
		}
	}


    /**
     * 不重复三元组
     * 类似于二元组的方法
     * 先固定第一个元素，然后从后面的子数组中找二元组
     * 如何保证不重复？
     * 首先保证第一个元素不重复，然后二元组不重复
     * @param arr
     * @param k
     */
	public static void printUniqueTriad(int[] arr, int k) {
		if (arr == null || arr.length < 3) {
			return;
		}

		//选定第一个元素，注意是arr.length - 2，剩余的子数组元素不能小于两个嘛
		for (int i = 0; i < arr.length - 2; i++) {
		    //保证第一个元素不重复
			if (i == 0 || arr[i] != arr[i - 1]) {
			    //打印剩余的
				printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
			}
		}
	}

    /**
     *
     * @param arr
     * @param f 已经选定的第一个元素的下标
     * @param l 剩余子数组左边界
     * @param r 剩余子数组右边界
     * @param k 剩余目标和
     */
	public static void printRest(int[] arr, int f, int l, int r, int k) {
		while (l < r) {
			if (arr[l] + arr[r] < k) {
				l++;
			} else if (arr[l] + arr[r] > k) {
				r--;
			} else {
			    //注意这里，l == f + 1
				if (l == f + 1 || arr[l - 1] != arr[l]) {
					System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
				}
				l++;
				r--;
			}
		}
	}

    /**
     * 测试
     * @param arr
     */
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int sum = 10;
		int[] arr1 = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
		printArray(arr1);
		System.out.println("====");
		printUniquePair(arr1, sum);
		System.out.println("====");
		printUniqueTriad(arr1, sum);

	}

}
