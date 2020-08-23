package GinkgoStack.P21_ZuoBook.C4_recursionAndDP;

/**
 * 最长递增子序列LIS问题
 */
public class Problem_07_LIS {

	public static int[] lis1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}

		int[] dp = getdp1(arr);
		return generateLIS(arr, dp);
	}

    /**
     * 第一种解法
     * O（N^2）
     * @param arr
     * @return
     */
	public static int[] getdp1(int[] arr) {
		int[] dp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		return dp;
	}

    /**
     * 根据前面求出来的dp数组，求出最长递增子序列
     * @param arr
     * @param dp
     * @return
     */
	public static int[] generateLIS(int[] arr, int[] dp) {
		int len = 0;
		int index = 0;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > len) {
				len = dp[i];
				index = i;
			}
		}
		int[] lis = new int[len];
		lis[--len] = arr[index];
		for (int i = index; i >= 0; i--) {
			if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
				lis[--len] = arr[i];
				index = i;
			}
		}
		return lis;
	}

    /**
     * 下面是更好的解法
     * O（N logN）
     * @param arr
     * @return
     */
	public static int[] lis2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp2(arr);//这个过程不一样，是在ends有效区中二分查找，时间复杂度为O（N logN）
		return generateLIS(arr, dp);//生成LIS的过程还是一样，复杂度为O(N)
	}

    /**
     * 在前一个解法上改进，加一个辅助数组ends，
     * 用二分法求解dp[i]的过程中需要用到ends数组和变量right，时间复杂度才降低为O（N logN）
     *
     * 如果ends[b]==c (注意，b处于[0...right]之间),则表示遍历到目前为止，在所有长度为b+1的递增序列中，最小的结尾数是c。
     * ends[0...right]为有效区，ends[right+1...N-1]是无效区，
     * 对无效区上的位置x（即[right+1...N-1]）,ends[x]无意义。
     * @param arr
     * @return
     */
	public static int[] getdp2(int[] arr) {
		int[] dp = new int[arr.length];
		//辅助数组，存的是结尾数值
		int[] ends = new int[arr.length];
		//初始时，ends[0] = arr[0]，ends其他元素都为0
		ends[0] = arr[0];

		//以arr[0]结尾的序列arr[0...0]的最大递增子序列长度当然就为1
		dp[0] = 1;
		int right = 0;//初始时，ends数组的有效区边界为right==0

        //接下来,l、r、m是对ends数组进行二分查找 用到的下标值
		int l = 0;
		int r = 0;
		int m = 0;

		//下面就是计算dp[]的过程，外层循环是遍历arr[]的N个数
		for (int i = 1; i < arr.length; i++) {
		    //二分查找前，对ends左右边界赋初值
			l = 0;
			r = right;
			//这个while过程就是在ends有效区中用二分法找到最左边的 大于或等于 arr[i]的那个数
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {//小于等于都在左半区域找，这是为了确保找到的符合要求的那个数是在最左边的
					r = m - 1;
				}
			}

            /**
             * 查找结束后，分两种情况：
             * 1.如果在有效区找到了那个数，即ends[l](l <= right),那就表示以arr[i]结尾的最长递增序列的长度为l+1,
             * 此时做两个更新操作：
             * 第一，现在arr[i] <= ends[l],这就需要更新ends[l] = arr[i]，因为此时在所有长度为l+1的递增序列中，其结尾数最小值变为了arr[i]。
             * 第二，dp[i]自然就取为l+1;
             *
             * 2.如果没找到，结束时l == right+1,这就表示整个ends有效区没有比arr[i]更大的数，因此这也就发现了比ends有效区长度更长的递增序列；
             * 也需要有两个更新操作：
             * 第一，把有效区扩大，right要取新的值l，将这个目前更大的arr[i]加入到有效区，ends[l] = arr[i];
             * 第二，dp[i]更新为ends有效区长度+1，数值上就等于l + 1; （l它的值大小就刚好等于ends有效区的长度）
             *
             */
			right = Math.max(right, l);//如果找到那个数，有效区就不变，如果没找到，就扩大有效区

			//无论有没有在ends有效区找到那个数，下面这两个更新操作都是一样的
            ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
		printArray(arr);
		printArray(lis1(arr));
		printArray(lis2(arr));

	}
}