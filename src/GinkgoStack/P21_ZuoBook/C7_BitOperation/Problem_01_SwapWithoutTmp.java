package GinkgoStack.P21_ZuoBook.C7_BitOperation;

/**
 * 不是用任何额外的变量，交换两个整数的值
 */
public class Problem_01_SwapWithoutTmp {

	public static void main(String[] args) {
		int a = 16;
		int b = 111;
		System.out.println(a);
		System.out.println(b);
		a = a ^ b;//结果看成c
		b = a ^ b;//此时a是异或值，看成c, c ^ b 就是 a
		a = a ^ b;//右边的c ^ a 就是 b;
		System.out.println(a);
		System.out.println(b);
	}

}
