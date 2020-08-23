package GinkgoStack.P2_StringProblem;

/**
 * 百度一面的面试题
 */
public class KMP {

    private int[] getNextArray(String s){
        if(s.length() == 1){
            return new int[]{-1};
        }
        char[] model = s.toCharArray();
        int len = s.length();
        int[] next = new int[len];//表示的是前缀和后缀相等的最大长度
        next[0] = -1;
        next[1] = 0;
        int pre = 0;
        int cur = 2;
        while (cur < len){
            if(model[cur-1] == model[pre]){
                next[cur++] = ++pre;
            }else if(pre > 0) {
                pre = next[pre];
            }else {//最左位置
                next[cur++] = 0;
            }
        }

        return next;
    }

    private int KMP(String s,String t){
        int[] next = getNextArray(t);
        int i = 0;
        int j = 0;
        while (i < s.length() && j<t.length() ){
            if (s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }else if(next[j] == -1){//next[0] = -1;
                i++;
            }else {
                j = next[j];//滑动
            }
        }
        return  j == t.length() ? i-j:-1;
    }

    public static void main(String[] args) {

    }
}
