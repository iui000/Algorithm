package GinkgoStack.P21_ZuoBook.C4_recursionAndDP;

public class Problem_21_NQueens {

    /**
     * 普通递归解法
     * @param n
     * @return
     */
	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return process1(0, record, n);
	}

    /**
     * 把递归过程直接设计成逐行放置皇后
     * @param i
     * @param record
     * @param n
     * @return
     */
	public static int process1(int i, int[] record, int n) {
		if (i == n) {//终止条件
			return 1;
		}
		int res = 0;
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {//判断（i,j）能不能放置
				record[i] = j;
				res += process1(i + 1, record, n);//下一行
			}
		}
		return res;
	}

	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {
		    //ֻ只要和已放置的皇后同行/同列/同斜线就不行
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

    /**
     * 最优解，使用了位运算来加速
     * @param n
     * @return
     */
	public static int num2(int n) {
        //因为本方法中位运算的载体是int型变量，所以该方法只能算1~32皇后问题
        //如果想计算更多的皇后问题，需要用包含更多位的变量
		if (n < 1 || n > 32) {
			return 0;
		}
		//upperLim表示当前行哪些位置是可以放置皇后的，1-可以放置，0-不能放置
        //初始值每一位均为1，当前行所有位置均为1，可放置
		int upperLim = n == 32 ? -1 : (1 << n) - 1;//假设是n==8，就为1111 1111
		return process2(upperLim, 0, 0, 0);
	}

    /**
     *
     * @param upperLim 表示当前行哪些位置是可以放置皇后的，1-可以放置，0-不能放置，在递归过程中是始终不变的
     * @param colLim 表示递归计算到上一行为止，在哪些列上已经放置了皇后，1-放置，0-没有放置
     * @param leftDiaLim 表示递归计算到上一行为止，因为受到已经放置的所有皇后的左下方斜线的影响，导致当前行不能放置皇后，1代表不能放置，0代表可以放置。
     *                   也就是说，leftDiaLim每次左移一位，就可以得到之前所有皇后的左下方斜线对当前行的影响
     * @param rightDiaLim 与leftDiaLim类似，rightDiaLim每次右移一位，就可以得到之前所有皇后的右下方斜线对当前行的影响
     * @return 返回值代表 剩余的皇后在之前皇后的影响下，有多少种合法的摆法
     */
	public static int process2(int upperLim, int colLim, int leftDiaLim, int rightDiaLim) {
		if (colLim == upperLim) {
			return 1;
		}

		//pos代表当前行在colLim、leftDiaLim和rightDiaLim这三个状态的影响下，还有那些位置是可供选择的，1-可以选择，0-不能选择
		int pos = 0;
		//mostRightOne代表在pos中，最右边的1在什么位置，然后从右到左依次筛选出pos中可选择的位置进行递归尝试
		int mostRightOne = 0;
		pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));

		int res = 0;
		while (pos != 0) {//不为零，说明就还有位置能够放置
			mostRightOne = pos & (~pos + 1);//这样就找到了最右边那个1的位置，比如1001 1100 & (~1001 1100 + 1) = 0000 0100
			pos = pos - mostRightOne;//表示在mostRightOne所表示那个位置放置皇后
			res += process2(upperLim, colLim | mostRightOne,//将该列记录在colLim之中
					(leftDiaLim | mostRightOne) << 1,//左移运算符,并在低位处补0
					(rightDiaLim | mostRightOne) >>> 1);//>>>无符号右移运算符,无论正负高位补0
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 8;//测试8皇后

        //最优解法用时
		long start = System.currentTimeMillis();
		System.out.println(num2(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");//大约1ms

		//普通解法用时
		start = System.currentTimeMillis();
		System.out.println(num1(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");//大约8ms

	}
}
