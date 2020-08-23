package GinkgoStack.P24_Enterprise.DJ;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * 对一个字符串表示的数字，删除k个位，输出剩余最小的那个数
 * 71245323308 4
 *
 *
 * 100  1
 * 0
 *
 * 123456 3
 * 123
 */
public class MinValueAfterRemoveK {
    /**
     * 单调栈
     * 很可惜，时间不够了，当时有点细节没对，导致没通过
     * 现在应该可以AC
     *
     * @param s
     * @param k
     * @return
     */
    private static String minValueAfterRemoveK(String s,int k){
        Deque<Character> stack = new LinkedList<>();
        stack.addLast(s.charAt(0));
        StringBuilder ans = new StringBuilder();
        int c = k;
        int i = 1;
        for(;i<s.length();i++){
            Character tmp = s.charAt(i);
            if(stack.isEmpty() || tmp.compareTo(stack.peekLast()) >= 0){
                stack.addLast(tmp);
            }else {
                while (!stack.isEmpty() &&   c > 0 && tmp.compareTo(stack.peekLast()) < 0){
                    stack.pollLast();
                    c--;
                }
                stack.addLast(tmp);
            }
        }

        //如果次数没有用完
        while (c > 0){//可以证明此时单调栈里面的元素是升序
            //那么直接弹出最后的几个
            if(!stack.isEmpty()){
                stack.pollLast();
            }else {
                break;
            }
            c--;
        }

        if(stack.isEmpty()){
            return "0";
        }

        //接下来去掉前导0，输出结果
        while (!stack.isEmpty() && stack.peekFirst().equals('0')){
            stack.pollFirst();
        }

        if(stack.isEmpty()){
            return "0";
        }

        for (Character e:stack){
            ans.append(e);
        }
        return ans.length() > 0 ? ans.toString():"0";
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();

        System.out.print(minValueAfterRemoveK(s,k));
    }
}
