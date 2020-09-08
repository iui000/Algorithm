package GinkgoStack.P24_Enterprise.Sougou;

/**
 * 将目标房屋建造在目前这一排房屋的某个位置，必须紧挨着某一栋房屋；
 * 问有多少种方案？
 *
 * 就是个简单插入区间的题
 */
public class Houses {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回能创建的房屋数
     * @param t int整型 要建的房屋面宽
     * @param xa int整型一维数组 已有房屋的值，其中 x0 a0 x1 a1 x2 a2 ... xi ai 就是所有房屋的坐标和房屋面宽。
     *           其中坐标是有序的（由小到大）
     * @return int整型
     */
    public static int getHouses (int t, int[] xa) {
        int n = xa.length;
        int k  = 0;

        int ans = 2;
        for (int i = 2; i < xa.length-1; i = i+2) {
            double gap = xa[i] - xa[i-2] - (double)xa[i+1]/2.0 - (double)xa[i-1]/2.0;
            if(gap == (double)t ){
                ans++;
            }
            if(gap > (double)t ){
                ans += 2;
            }

        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(getHouses(2,new int[]{-1,4,5,2}));
    }
}