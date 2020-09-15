package GinkgoStack.P21_ZuoBook.C4_RecursionAndDP;

import java.util.Arrays;

/**
 * 跳棋
 */
public class Problem_19_JumpGame {

    /**
     * 时间复杂度O（n）
     * @param arr
     * @return
     */
	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int jump = 0;//代表目前跳了多少步
		int cur = 0;//表示如果只能跳jump步，最远到达的位置。
		int next = 0;//表示如果再多跳一步，最远能够到达的位置。
		for (int i = 0; i < arr.length; i++) {
			if (cur < i) {
				jump++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return jump;
	}

    /**
     * 自己的解法，空间和时间复杂度稍微高点，但是好理解
     * @param arr
     * @return
     */
    private static int jump2(int[] arr){
	    int[] dp = new int[arr.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0;i< arr.length;i++){
            for(int j = 1;j<= arr[i] && (i+j <= arr.length-1);j++){
                dp[i+j] = Math.min(dp[i+j],dp[i] + 1);
            }
        }

        return dp[arr.length-1];
    }

	public static void main(String[] args) {
		int[] arr = { 3, 2, 3, 1, 1, 4 };
		System.out.println(jump(arr));
        System.out.println(jump2(arr));
	}

}
