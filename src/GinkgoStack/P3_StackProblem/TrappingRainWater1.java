package GinkgoStack.P3_StackProblem;

import java.util.Stack;

/**
 * leetCode 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 1.单调栈法
 * 2.双指针法
 */
public class TrappingRainWater1 {
    public static void main(String[] args) {
        int[] mountain = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(mountain));
        System.out.println(trap2(mountain));
    }

    /**
     * 这是自己最开始想的解法,和官方题解的第三种方法的思想不谋而合
     * 时间复杂度O(N),空间复杂度就是栈的最大可能高度O（N）
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();

        int n = height.length;
        if(n <= 1){
            return 0;
        }
        //栈中存的下标
        stack.push(0);
        int currentIndex = 1,res = 0;
        while (currentIndex < n){
            //单调栈，从栈底到栈顶单调递减
            while ( !stack.isEmpty() && height[currentIndex] >= height[stack.peek()]){
                //如果是相等或者大于栈顶，就弹出，然后计算当前能够确定的凹槽蓄水量
                int top = stack.pop();
                //看看栈有没有空，如果为空，就不用算了，因为这个柱子左右能蓄水的体积已经算过了
                if(!stack.isEmpty()){
                    //栈顶柱子背后(下面)的那个柱子，用back表示其下标
                    int back = stack.peek();
                    //这个高度差指的是当前柱子与栈顶柱子背后(下面)的那个柱子back的高度差，在图形上看就是当前这个凹槽的高度差
                    //如果当前柱子与栈顶柱子一样高，计算出来就会是0，这是预期的结果
                    int heightDiff = Integer.min(height[back],height[currentIndex]) - height[top];
                    //当前柱子与back之间的空间距离
                    res += heightDiff * (currentIndex - back - 1);//高度差值 * 距离
                }
            }
            stack.push(currentIndex++);
        }

        return res;
    }

    /**
     * 这是一个官方的最优解,双指针法
     * 时间复杂度O(N),空间复杂度O（1）
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;

        //左右两个方向，各自目前的最大高度
        int left_max = 0, right_max = 0;
        while (left < right) {

            //两边交替进行
            if (height[left] < height[right]) {
                //找到自己的最大值就更新最大值
                if(height[left] >= left_max){
                    left_max = height[left];
                }else {//否则就计算当前柱子与最高值的差值，也就是当前这个柱子能蓄水的大小
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if(height[right] >= right_max){
                    right_max = height[right];
                }else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }
}
