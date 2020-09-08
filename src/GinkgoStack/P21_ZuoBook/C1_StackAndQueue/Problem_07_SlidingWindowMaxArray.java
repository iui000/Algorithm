package GinkgoStack.P21_ZuoBook.C1_StackAndQueue;

import java.util.LinkedList;

public class Problem_07_SlidingWindowMaxArray {

	public static int[] getMaxWindow(int[] arr, int w) {
		//边界判断
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		
		//创建双端队列
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		
		//创建结果数组
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		
		//遍历输入数组
		for (int i = 0; i < arr.length; i++) {
			//队列放入规则
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			
			//队列弹出规则
			if (qmax.peekFirst() == i - w) {
				qmax.pollFirst();
			}
			
			//窗口出现，把以队首值为下标的元素添加到结果数组中
			if (i >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		printArray(getMaxWindow(arr, w));
	}

}
