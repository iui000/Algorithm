package GinkgoStack.P1_ArrayProblem;


/**
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 *
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 *
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 */
public class BuildingProductArrayOffer66 {

    class Solution {
        public int[] constructArr(int[] a) {
            if(a.length == 0) return new int[0];
            int[] b = new int[a.length];
            b[0] = 1;
            int tmp = 1;
            for(int i = 1; i < a.length; i++) {//左边前缀乘积
                b[i] = b[i - 1] * a[i - 1];
            }
            for(int i = a.length - 2; i >= 0; i--) {//右边后缀乘积
                tmp *= a[i + 1];
                b[i] *= tmp;
            }
            return b;
        }
    }
}
