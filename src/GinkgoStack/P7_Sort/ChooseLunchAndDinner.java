package GinkgoStack.P7_Sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 拼多多笔试题
 * n套午餐
 * m套晚餐
 *
 * 每顿餐都有热量值 和 美味值
 * 在满足不小于目标美味值T的情况下，选择一顿午餐和一顿晚餐(可以只选一餐或者不选任何一餐），使得热量总和最小
 */
public class ChooseLunchAndDinner {

    //要找到第一个 大于等于 target的那个数的下标值
    private static int binarySearch(int[][] items, int low, int high, int target){
        int mid = low + (high - low)/2;
        if(low == high){
            return low;
        }
        if(items[mid][1] >= target ){
            if(mid == 0){
                return 0;
            }else if(items[mid-1][1] < target){
                return mid;
            }
            return binarySearch(items,low,mid-1,target);
        } else {
            return binarySearch(items,mid+1,high,target);
        }
    }


    private static int getMin(int[][] a,int[][] b,int T){

        int min = Integer.MAX_VALUE;

        int n = a.length;
        int m = b.length;
        for(int k = 0;k<n;k++){
            if(a[k][1] >= T){
                break;
            }

            //可以试探选在该午/晚餐
            //再根据剩下的美味值 去配一份热量值最小的午/晚餐
            int theIndex = binarySearch(b,0,m-1,T-a[k][1]);

            //有可能另外一个数组中找不到大于等于target的，因此要确认一下
            if(a[k][1] + b[theIndex][1] < T){
                continue;
            }

            min = a[k][0] + b[theIndex][0] < min ? a[k][0] + b[theIndex][0] : min;
            ++theIndex;

            for (;theIndex < m;theIndex++){
                //因为热量值已经排序了，所以如果美味值和前一个相等，那么它的热量值一定大于等于前一个食物的热量值，所以不用计算了
                if(b[theIndex][1] == b[theIndex-1][1]){
                    continue;
                }else {
                    min = a[k][0] + b[theIndex][0] < min ? a[k][0] + b[theIndex][0] : min;
                }
            }
        }
        return min;
    }

    /**
     * 测试用例
     * 5 2 10
     * 3 3
     * 1 5
     * 4 5
     * 2 6
     * 4 6
     * ------------
     * 2 4
     * 3 4
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int T = sc.nextInt();

        int[][] lunch= new int[n][2];
        int i = 0,min = Integer.MAX_VALUE;
        while (i < n) {
            lunch[i][0] = sc.nextInt();
            lunch[i][1] = sc.nextInt();
            min = lunch[i][1]>=T ? (lunch[i][0]<min ? lunch[i][0]:min):min;
            i++;
        }

        int[][] dinner= new int[m][2];
        int j = 0;
        while (j < m) {
            dinner[j][0] = sc.nextInt();
            dinner[j][1] = sc.nextInt();
            min = dinner[j][1] >= T ? (dinner[j][0]<min ? dinner[j][0]:min):min;
            j++;
        }

        if (T == 0) {
            System.out.print(0);
            return;
        }

        //对他们进行升序排序，按照美味值排序，如果美味值相等，再按照热量值排序
        Arrays.sort(lunch,(arr1,arr2) ->{
            return arr1[1]-arr2[1]!= 0?arr1[1]-arr2[1] : arr1[0]-arr2[0];
        });

        Arrays.sort(dinner,(arr1,arr2) ->{
            return arr1[1]-arr2[1]!= 0?arr1[1]-arr2[1] : arr1[0]-arr2[0];
        });

        /*
        //测试排序结果
        for (int[] arr:lunch) {
            for (int e:arr ){
                System.out.print(e);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("------------");
        for (int[] arr:dinner) {
            for (int e:arr ){
                System.out.print(e);
                System.out.print(" ");
            }
            System.out.println("");
        }
        */

        if(lunch[n-1][1] + dinner[m-1][1] < T){
            System.out.print(-1);
            return;
        }

        if(lunch[0][1] >T && dinner[0][1] > T){
            System.out.print(min);
            return;
        }


        /**
         * 接下来，说明，一定存在一个套餐组合能够达到T
         */

        //先选午餐，再选晚餐
        int min1 = getMin(dinner,lunch,T);
        min = min1 < min ? min1:min;
        //先选晚餐，再选午餐
        int min2 = getMin(lunch,dinner,T);
        min = min2 < min ? min2:min;

        if(min == Integer.MAX_VALUE){
            System.out.print(-1);
        }else {
            System.out.print(min);
        }
    }
}
