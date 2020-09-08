package GinkgoStack.P12_Math.BasedAlgorithm;

public class RemainderOfLargeNumbers {

    /**
     * 求 (x^a) % p —— 循环求余法
     *
     *     作者：jyd
     *     链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public int remainder1(int x,int a,int p){
       int remainder = 1;
       for(int i = 0;i< a;i++){
           remainder = (remainder * x) % p;
       }
       return remainder;
    }

    /**
     * 快速幂求余：
     * 时间复杂度 O(logN)
     * @param x
     * @param a
     * @param p
     * @return
     */
    public int remainder2(int x,int a,int p){
        int remainder = 1;
        while (a > 0) {
            if(a % 2 == 1)//奇数
                remainder = (remainder * x) % p;

            x = (x * x) % p;
            a /= 2;//向下取整
        }
        return remainder;
    }

}
