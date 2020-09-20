package GinkgoStack.P19_DivideAndConquer;


/**
 * 302. 包含全部黑色像素的最小矩形
 * 图片在计算机处理中往往是使用二维矩阵来表示的。
 *
 * 假设，这里我们用的是一张黑白的图片，那么 0 代表白色像素，1 代表黑色像素。
 *
 * 其中黑色的像素他们相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素
 * （像素点是水平或竖直方向连接的）。
 *
 * 那么，给出某一个黑色像素点 (x, y) 的位置，
 * 你是否可以找出包含全部黑色像素的最小矩形（与坐标轴对齐）的面积呢？
 *
 *
 *
 * 示例:
 *
 * 输入:
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * 和 x = 0, y = 2
 *
 * 输出: 6
 */
public class SmallestRectangleEnclosingBlackPixels302 {

    /**
     * 分治，二分法
     */
    class Solution {
        public int minArea(char[][] image, int x, int y) {
            int m = image.length;
            int n = image[0].length;

            int left = searchMin(image, 0, y, true);
            int right = searchMax(image, y, n, true);
            int top = searchMin(image, 0, x, false);
            int bottom = searchMax(image, x, m, false);

            //易错，应该加一
            return (right - left + 1) * (bottom - top + 1);
        }

        private int searchMin(char[][] image, int start, int end, boolean isHorizontal){
            while(start < end){
                int mid = start + (end - start) / 2;
                if(!containsOne(image, mid, isHorizontal)){
                    start = mid + 1;
                }else{
                    //一定是1
                    end = mid;
                }
            }
            return end;
        }

        private int searchMax(char[][] image, int start, int end, boolean isHorizontal){
            while(start < end){
                int mid = start + (end - start) / 2;
                if(containsOne(image, mid, isHorizontal)){
                    start = mid + 1;
                }else{
                    //一定是0
                    end = mid;
                }
            }
            return end - 1;
        }

        private boolean containsOne(char[][] image, int mid, boolean isHorizontal){
            if(isHorizontal){
                for(int row = 0; row < image.length; row++){
                    if(image[row][mid] == '1'){
                        return true;
                    }
                }
            }else{
                for(int col = 0; col < image[0].length; col++){
                    if(image[mid][col] == '1'){
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
