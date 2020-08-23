package GinkgoStack.P2_StringProblem.Palindrome;

/**
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 */
public class IsPalindrome {

    //也可以用自己的判断方法
    private static boolean isCharOrDigit(char c){
        if((c >= 'a' && c <= 'z') ||
                (c>= 'A' && c<='Z')||
                (c>= '0' && c<='9' )){
            return true;
        }
        return false;
    }


    /**
     * 左右双指针
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int len = chars.length;
        if(len <= 1){
            return true;
        }
        for(int i=0,j=len-1;i<=j;i++,j--){
            while (i<j && !Character.isDigit(chars[i]) && !Character.isAlphabetic(chars[i])){
                i++;
            }
            while (i<j && !Character.isDigit(chars[j]) && !Character.isAlphabetic(chars[j])){
                j--;
            }
            if(i == j){
                return true;
            }
            if(chars[i] != chars[j]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
}
