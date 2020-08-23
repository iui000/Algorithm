package GinkgoStack.P2_StringProblem.Log;

import java.util.Arrays;

/**
 * 937. 重新排列日志文件
 * 你有一个日志数组 logs。每条日志都是以空格分隔的字串。
 *
 * 对于每条日志，其第一个字为字母与数字混合的 标识符 ，除标识符之外的所有字为这一条日志的 内容 。
 *
 * 除标识符之外，所有字均由小写字母组成的，称为 字母日志
 * 除标识符之外，所有字均由数字组成的，称为 数字日志
 * 题目所用数据保证每个日志在其标识符后面至少有一个字。
 *
 * 请按下述规则将日志重新排序：
 *
 * 所有 字母日志 都排在 数字日志 之前。
 * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序；
 * 数字日志 应该按原来的顺序排列。
 * 返回日志的最终顺序。
 *
 *
 *
 * 示例 ：
 *
 * 输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 *
 * 提示：
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] 保证有一个标识符，并且标识符后面有一个字。
 */
public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs) {

        Arrays.sort(logs, (log1, log2) -> {
            //注意limit参数，表示the result threshold，也就是结果被分为最多两段，会以第一个空格将原字符串分割成2部分
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);

            //split1[1]就是日志的内容，根据日志内容的第一个字符来判断是字母日志还是数字日志
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {//如果两条日志都是字母日志，那么按照内容字典序排序
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                //如果内容相同，就按照日志标识来排序
                return split1[0].compareTo(split2[0]);
            }

            //到此，如果第一条日志是数字日志：
            //                      第二条也是数字日志，那么就认为是相等的，不改变其原始相对顺序
            //                      第二条是字母日志，就认为数字日志更大，排在后面，返回1
            //如果第一条是字母日志，那么第二条就必然是数字日志了，因此字母日志排在前头，返回-1
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;

    }
}
