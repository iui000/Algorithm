package GinkgoStack.P2_StringProblem;

/**
 * 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class CheckInclusion {

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int right = s1.length()-1;

        for (int i = 0; i < s2.length() - s1.length(); ) {

            //每次都去匹配，时间复杂度有点高,还可以优化
            if (matches(s1map, s2map))
                return true;

            right = i+s1.length();

            if(s1map[s2.charAt(right) - 'a'] == 0){
                if(right + s1.length()>= s2.length() - s1.length()){
                    return false;
                }else {
                    s2map = new int[26];
                    for (int j = right+1; j <= right + s1.length() ;j++) {
                        s2map[s2.charAt(j) - 'a']++;
                    }
                    if (matches(s1map, s2map))
                        return true;
                    i= right+1;
                    continue;
                }
            }
            s2map[s2.charAt(right) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
            i++;
        }
        return matches(s1map, s2map);
    }
    public static boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
       String s1 = "ab", s2 = "eidbaooo";
       System.out.println(checkInclusion(s1,s2));//true

       String s3= "ab" ,s4 = "eidboaoo";
       System.out.println(checkInclusion(s3,s4));//false

       String s5 = "adc", s6 = "dcda";
       System.out.println(checkInclusion(s5,s6));//true

        String s7 = "abc", s8 = "bbbca";
        System.out.println(checkInclusion(s7,s8));//true

        String s9 = "abc", s10 = "ccccbbbbaaaa";
        System.out.println(checkInclusion(s9,s10));//false
    }
}
