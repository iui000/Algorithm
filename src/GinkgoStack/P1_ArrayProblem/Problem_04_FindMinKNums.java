package GinkgoStack.P1_ArrayProblem;

/**
 * 找到无序数组中最小的k个数
 * 注意：不单是找到第k小的数，而是找到前k个数。
 * (题目并没有要求这k个数有序输出，只是找到这k个数)
 * 如果是找到第k小的数，常规的好方法是，在快排上稍微改进一下，也就是每次只是递归一半。
 *
 *
 * 常规的快排和堆排序就不说了，下面有几种优秀的方法
 *
 */
public class Problem_04_FindMinKNums {

    /**
     * 对堆排序的改进
     * 这个方法要掌握
     * O(N*logK)
     */
	public static int[] getMinKNumsByHeap(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}

		//先用arr[0...k-1]的前k个数建立一个大根堆
		int[] kHeap = new int[k];
		for (int i = 0; i != k; i++) {
			heapInsert(kHeap, arr[i], i);
		}

		//接着前面的arr[k]继续遍历
		for (int i = k; i != arr.length; i++) {
			if (arr[i] < kHeap[0]) {//如果当前元素小于堆顶
				kHeap[0] = arr[i];//那么就将它作为新的顶，开始下筛调整堆
				heapify(kHeap, 0, k);
			}
		}
		return kHeap;
	}

    /**
     * 元素插入堆中
     * @param arr
     * @param value
     * @param index
     */
	public static void heapInsert(int[] arr, int value, int index) {
		arr[index] = value;
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (arr[parent] < arr[index]) {//上滤
				swap(arr, parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}

    /**
     * 替换堆顶，重新向下筛选，调整堆
     * @param arr
     * @param index
     * @param heapSize
     */
	public static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int largest = index;
		while (left < heapSize) {//直到最后一个叶子
			if (arr[left] > arr[index]) {//如果左儿子比它大
				largest = left;
			}
			if (right < heapSize && arr[right] > arr[largest]) {//如果右儿子比它大
				largest = right;
			}

			if (largest != index) {//说明某儿子比它大，交换
				swap(arr, largest, index);
			} else {//否则没有儿子比它大，最终位置确定，结束
				break;
			}

			index = largest;//交换完了后，继续往下走
			left = index * 2 + 1;
			right = index * 2 + 2;
		}
	}

    /**
     * BFPRT算法，这个算法可以记住思路，代码有点多
     * O(N)
     *
     * @param arr
     * @param k
     * @return
     */
	public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int minKth = getMinKthByBFPRT(arr, k);
		int[] res = new int[k];
		int index = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] < minKth) {
				res[index++] = arr[i];
			}
		}
		for (; index != res.length; index++) {
			res[index] = minKth;
		}
		return res;
	}

	public static int getMinKthByBFPRT(int[] arr, int K) {
		int[] copyArr = copyArray(arr);
		return select(copyArr, 0, copyArr.length - 1, K - 1);
	}

	public static int[] copyArray(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i != res.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	public static int select(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		int pivot = medianOfMedians(arr, begin, end);
		int[] pivotRange = partition(arr, begin, end, pivot);
		if (i >= pivotRange[0] && i <= pivotRange[1]) {
			return arr[i];
		} else if (i < pivotRange[0]) {
			return select(arr, begin, pivotRange[0] - 1, i);
		} else {
			return select(arr, pivotRange[1] + 1, end, i);
		}
	}

	public static int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		int offset = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offset];
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = beginI + 4;
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
		}
		return select(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		int[] range = new int[2];
		range[0] = small + 1;
		range[1] = big - 1;
		return range;
	}

	public static int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	public static void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
		}
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
		// sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
		printArray(getMinKNumsByHeap(arr, 10));
		printArray(getMinKNumsByBFPRT(arr, 10));

	}

}
