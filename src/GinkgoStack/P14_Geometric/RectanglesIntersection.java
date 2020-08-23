package GinkgoStack.P14_Geometric;

import java.util.Scanner;

/**
 * 讯飞笔试
 *
 * 两个矩形相交
 * AC
 */
public class RectanglesIntersection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //矩形1的对角点
        int[] point1 = new int[2];
        int[] point2 = new int[2];

        //矩形2的对角点
        int[] point3 = new int[2];
        int[] point4 = new int[2];

        point1[0] = scanner.nextInt();
        point1[1] = scanner.nextInt();

        point2[0] = scanner.nextInt();
        point2[1] = scanner.nextInt();

        point3[0] = scanner.nextInt();
        point3[1] = scanner.nextInt();

        point4[0] = scanner.nextInt();
        point4[1] = scanner.nextInt();

        /**
         * 题目并没有明确说是左下角的点 和 右上角的点，或者也没有说这两个点的顺序
         * 所以下面自己手动找出来，方便处理，当然，去找右上角点 和 左下角点也是一样的
         */

        /**
         * 第一个矩形
         */
        int[] leftBottomPoint1 =new int[2];//找出左下角点的坐标
        leftBottomPoint1[0] = point1[0] < point2[0]?point1[0]:point2[0];//x坐标
        leftBottomPoint1[1] = point1[1] < point2[1]?point1[1]:point2[1];//y坐标

        int[] rightUp1 =new int[2];//找出右上角的坐标
        rightUp1[0] = point1[0] > point2[0]?point1[0]:point2[0];//x坐标
        rightUp1[1] = point1[1] > point2[1]?point1[1]:point2[1];//y坐标

        /**
         * 第二个矩形
         */
        int[] leftBottomPoint2 =new int[2];
        leftBottomPoint2[0] = point3[0] < point4[0]?point3[0]:point4[0];
        leftBottomPoint2[1] = point3[1] < point4[1]?point3[1]:point4[1];

        int[] rightUp2 =new int[2];
        rightUp2[0] = point3[0] > point4[0]?point3[0]:point4[0];
        rightUp2[1] = point3[1] > point4[1]?point3[1]:point4[1];

        int res= 0;
        //下面分别将两个矩形投影到X和Y坐标轴，如果他们的投影线段有重叠，那就是有相交区域
        if(Math.min(rightUp1[0], rightUp2[0]) > Math.max(leftBottomPoint1[0], leftBottomPoint2[0]) &&
                Math.min(rightUp1[1], rightUp2[1]) > Math.max(leftBottomPoint1[1], leftBottomPoint2[1])){
            res = 1;
        }

        System.out.print(res);
    }
}
