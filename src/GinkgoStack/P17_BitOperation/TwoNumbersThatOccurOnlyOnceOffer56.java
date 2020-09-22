package GinkgoStack.P17_BitOperation;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 */
public class TwoNumbersThatOccurOnlyOnceOffer56 {

    /**
     * 直接用左神的代码就行
     */

    /**
     * 有两个数A,B出现奇数次，其他的都是偶数次。
     *
     * 可以直接解决的题目：剑指 Offer 56 - I. 数组中数字出现的次数
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eO = 0, eOhasOne = 0;
        //这一遍后，eO就是A和B的异或
        for (int curNum : arr) {
            eO ^= curNum;
        }

        /**
         * eO必定至少有一位是1，假设是第k位；
         * 那么A和B中必定有一个在第k位是1，另一个数的第k位是0
         */
        //为了方便，找到异或值的最右边的那个位1
        int rightOne = eO & (~eO + 1);

        /**
         * 接下来第二次遍历
         * 这一次遍历用新的变量eOhasOne，
         * 而且，只需要那些在第k位上为1的数，因为只有A或B只可能是这些数中某一个，
         * 其他的忽略。
         *
         * 这一遍结束后，eOhasOne就必然是A或B中的一个
         */
        for (int cur : arr) {
            if ((cur & rightOne) != 0) {
                eOhasOne ^= cur;
            }
        }
        //最后，利用第一遍的结果eO和第二遍的结果eOhasOne，就能够计算出A或B中的另一个数了。
        System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
    }
}
