package GinkgoStack.P6_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class template {

    /* 滑动窗口算法框架 */
    void slidingWindow(String s, String t) {
        //根据具体情况选数据结构，比如一些字符串的题，适合用数组来作为map
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        for(int i = 0;i < t.length();i++){
            need.put(s.charAt(i),need.getOrDefault(s.charAt(i),0)+1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            //...

            /*** debug 输出的位置 ***/
            System.out.printf("window: [%d, %d)\n", left, right);
            /********************/

            // 判断左侧窗口是否要收缩
            //while (window needs shrink) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                //...
            //}
        }
    }
}
