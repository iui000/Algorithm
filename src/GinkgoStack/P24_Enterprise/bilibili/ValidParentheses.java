package GinkgoStack.P24_Enterprise.bilibili;

import java.util.Stack;

/**
 * 原题
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class ValidParentheses {
    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean IsValidExp (String s) {
        if(s.length() == 0){
            return true;
        }


        // write code here
        Stack<Character> stack = new Stack<>();

        if(s.charAt(0) == ')' || s.charAt(0) == ']' ||s.charAt(0) == '}'){
            return false;
        }

        boolean ok = true;
        for (int i = 0;i<s.length();i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '['||s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }
            if(s.charAt(i) == ')'){
                if(stack.peek() == '('){
                    stack.pop();
                }else {
                    ok = false;
                    break;
                }
            }

            if(s.charAt(i) == ']'){
                if(stack.peek() == '['){
                    stack.pop();
                }else {
                    ok = false;
                    break;
                }
            }

            if(s.charAt(i) == '}'){
                if(stack.peek() == '{'){
                    stack.pop();
                }else {
                    ok = false;
                    break;
                }
            }

        }

        if(ok && stack.isEmpty()) return true;

        return false;
    }

    public static void main(String[] args) {

    }
}
