package GinkgoStack.P1_ArrayProblem;

/**
 * 美团面试题
 * 乱序数组，找到某个数，它比之前的数都大，且比之后的数都小
 * 时间复杂度O（n）
 */
public class GreaterThanLeftandLessThanRight {


    public static int search(int[] arr){
        int n = arr.length;

        int[] leftMax = new int[n];//leftMax[i]表示arr[0..i]中的最大值
        int[] rightMin = new int[n];//leftMax[i]表示arr[i...n-1]中的最小值
        leftMax[0] = arr[0];
        rightMin[n-1] = arr[n-1];

        for (int i = 1; i < arr.length; i++) {
            int j = n-1-i;
            if(arr[i] > leftMax[i-1]){
                leftMax[i] = arr[i];
            }else {
                leftMax[i] = leftMax[i-1];
            }

            if(arr[j] < rightMin[j+1]){
                rightMin[j] = arr[j];
            }else {
                rightMin[j] = arr[j+1];
            }
        }

        for (int i = 1; i < arr.length-1; i++) {
            if(leftMax[i-1] < arr[i] && arr[i] < rightMin[i+1]){
                return arr[i];
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,2,1,8,10,9,14,9,8,68};
        System.out.println(search(arr));
    }

}
