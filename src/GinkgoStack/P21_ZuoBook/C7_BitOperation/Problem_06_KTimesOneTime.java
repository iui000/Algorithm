package GinkgoStack.P21_ZuoBook.C7_BitOperation;

/**
 * 在其他数都出现k次的数组中找到只出现1次的数
 * 要求：时间O（n）,空间O(1）
 * 思路：
 * k个相同的k进制数进行无进位相加，得到的结果就是每一位都为0的k进制数；
 * 遍历结束后，剩下的就是那个只出现一次的数。
 *
 * 不过这期间需要将十进制数转换成k进制数，最后将结果还原为十进制数
 */
public class Problem_06_KTimesOneTime {

    /**
     * 找到那个只出现一次的数
     * @param arr
     * @param k
     * @return
     */
	public static int onceNum(int[] arr, int k) {
		int[] eO = new int[32];//无进位相加的结果
		for (int i = 0; i != arr.length; i++) {
			setExclusiveOr(eO, arr[i], k);//将每个数都转换成k进制的数，然后与e0进行无进位相加
		}
		//最后得到的这个数就是结果，将其还原为十进制数
		int res = getNumFromKSysNum(eO, k);
		return res;
	}

    /**
     * 将value转换成k进制的数,并与eO进行无进位相加
     *
     * @param eO
     * @param value
     * @param k
     */
	public static void setExclusiveOr(int[] eO, int value, int k) {
		int[] curKSysNum = getKSysNumFromNum(value, k);//将它转换成k进制的数
		for (int i = 0; i != eO.length; i++) {//两个数的无进位相加
			eO[i] = (eO[i] + curKSysNum[i]) % k;
		}
	}

    /**
     * 将一个数转换成k进制的数，结果是数组
     * @param value
     * @param k
     * @return
     */
	public static int[] getKSysNumFromNum(int value, int k) {
		int[] res = new int[32];
		int index = 0;
		while (value != 0) {
			res[index++] = value % k;
			value = value / k;
		}
		return res;
	}

    /**
     * 将一个k进制的数转（以数组表示）换为十进制
     * @param eO
     * @param k
     * @return
     */
	public static int getNumFromKSysNum(int[] eO, int k) {
		int res = 0;
		for (int i = eO.length - 1; i != -1; i--) {
			res = res * k + eO[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
		System.out.println(onceNum(test1, 3));

		int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
		System.out.println(onceNum(test2, 5));

	}

}