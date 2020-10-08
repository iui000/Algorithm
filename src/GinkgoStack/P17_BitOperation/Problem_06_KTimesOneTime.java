package GinkgoStack.P17_BitOperation;

/**
 * 在其他数都出现k次的数组中找到只出现1次的数
 * 这是一个通用的算法
 *
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



    /**
     * 方法二：遍历统计
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 只需要修改求余数值 k ，即可实现解决 除了一个数字以外，其余数字都出现k次 的通用问题。
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 位数组 nums的长度；遍历数组占用 O(N) ，每轮中的常数个位运算操作占用 O(1)。
     * 空间复杂度 O(1) ： 数组 counts 长度恒为 32，占用常数大小的额外空间。
     *
     */
    class Solution2 {
        public int singleNumber(int[] nums) {
            //建立一个长度为 32 的数组 countscounts ，记录所有数字的各二进制位的 1的出现次数。
            int[] counts = new int[32];
            for(int num : nums) {
                for(int j = 0; j < 32; j++) {
                    //使用 与运算 ，可获取二进制数字 num的最右一位
                    counts[j] += num & 1;// 更新第 j 位
                    //配合 无符号右移操作 ，可获取 numnum 所有位的值
                    num >>>= 1;// 第 j 位 --> 第 j + 1 位
                }
            }

            int res = 0, k = 3;
            //利用 左移操作 和 或运算 ，可将 counts 数组中各二进位的值恢复到数字 res上
            for(int i = 0; i < 32; i++) {
                res <<= 1;
                res |= counts[31 - i] % k;//将 counts 各元素对k求余，则结果为 “只出现一次的数字” 的各二进制位
            }
            return res;
        }
    }

	public static void main(String[] args) {
		int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
		System.out.println(onceNum(test1, 3));

		int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
		System.out.println(onceNum(test2, 5));

	}

}