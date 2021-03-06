package GinkgoStack.P6_SlidingWindow;


/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class AllAnagramsString {

/**
 * 相当于，输入一个串S，一个串T，找到S中所有T的排列，返回它们的起始索引。
 * 跟寻找字符串的排列一样，只是找到一个合法异位词（排列）之后将起始索引加入res即可。
 */

/**
 * vector<int> findAnagrams(string s, string t) {
 *     unordered_map<char, int> need, window;
 *     for (char c : t) need[c]++;
 *
 *     int left = 0, right = 0;
 *     int valid = 0;
 *     vector<int> res; // 记录结果
 *     while (right < s.size()) {
 *         char c = s[right];
 *         right++;
 *         // 进行窗口内数据的一系列更新
 *         if (need.count(c)) {
 *             window[c]++;
 *             if (window[c] == need[c])
 *                 valid++;
 *         }
 *         // 判断左侧窗口是否要收缩
 *         while (right - left >= t.size()) {
 *             // 当窗口符合条件时，把起始索引加入 res
 *             if (valid == need.size())
 *                 res.push_back(left);
 *             char d = s[left];
 *             left++;
 *             // 进行窗口内数据的一系列更新
 *             if (need.count(d)) {
 *                 if (window[d] == need[d])
 *                     valid--;
 *                 window[d]--;
 *             }
 *         }
 *     }
 *     return res;
 * }
 */

}
