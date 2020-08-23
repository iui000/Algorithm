
package GinkgoStack.P24_Enterprise.AiQiyi;
import java.util.*;

public class ValidParentheses {

    private static boolean IsValidExp (String s) {
        int n = s.length();
        if(n % 2 != 0){
            return false;
        }

        Map<Character,Character> p = new HashMap<>();
        p.put(')','(');
        p.put(']','[');
        p.put('}','{');


        Stack<Character> stack = new Stack<>();

        for (int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(p.containsKey(c)){
                if(stack.isEmpty() || stack.peek() != p.get(c)){
                    return false;
                }
                stack.pop();
            }else {
                stack.push(c);
            }

        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        /**
         * 惨痛的血泪史，首字母要大写
         */
        System.out.println(IsValidExp(s)?"True":"False");
    }
}
