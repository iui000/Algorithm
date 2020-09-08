package GinkgoStack.P3_StackProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * 单调栈
 */
public class Problem_08_MonotonousStack {

    /**
     * 原问题
     * @param arr
     * @return
     */
	//源数组中无重复元素的情况
	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		
		//遍历阶段
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int popIndex = stack.pop();
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
				res[popIndex][0] = leftLessIndex;
				res[popIndex][1] = i;
			}
			stack.push(i);
		}
		
		//清算阶段
		while (!stack.isEmpty()) {
			int popIndex = stack.pop();
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
			res[popIndex][0] = leftLessIndex;
			res[popIndex][1] = -1;
		}
		
		return res;
	}

    /**
     * 进阶问题
     * @param arr
     * @return
     */
	//源数组中可能有重复元素的情况
	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];//结果二维数组
		
		//创建栈，注意不同点：这里栈的类型参数是泛型列表，
		Stack<List<Integer>> stack = new Stack<>();
		
		//遍历阶段
		for (int i = 0; i < arr.length; i++) {
			
			//弹出规则
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				List<Integer> popIs = stack.pop();
				
				// 取位于下面位置的列表中，最晚加入的那个
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
						stack.peek().size() - 1);
				
				//得出左右最近最小元素的下标，加入到结果数组
				for (Integer popi : popIs) {
					res[popi][0] = leftLessIndex;
					res[popi][1] = i;
				}
			}
			
			//加入规则
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(Integer.valueOf(i));//如果与当前栈顶列表中的元素相等，则加入到该列表中
			} else {//否则，新建一个数组列表，并入栈
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		
		//清算阶段
		while (!stack.isEmpty()) {
			List<Integer> popIs = stack.pop();
			// 取位于下面位置的列表中，最晚加入的那个
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
					stack.peek().size() - 1);
			for (Integer popi : popIs) {
				res[popi][0] = leftLessIndex;
				res[popi][1] = -1;
			}
		}
		return res;
	}

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;//元素值初始化为下标
		}
		//再随机交换元素，打乱顺序
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	//这是传统的正确的方法（不过时间复杂度为O(N平方)）
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
				return false;
			}
		}

		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
//		int testTimes = 2000000;
		int testTimes = 10;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
			
			if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
				System.out.println("Oops!");
				printArray(arr1);
				break;
			}else {
				printArray(arr1);
				System.out.println(Arrays.deepToString(getNearLessNoRepeat(arr1)));
			}
			
			if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
				System.out.println("Oops!");
				printArray(arr2);
				break;
			}else {
				printArray(arr2);
				System.out.println(Arrays.deepToString(getNearLess(arr2)));
			}
		}
	}
}
