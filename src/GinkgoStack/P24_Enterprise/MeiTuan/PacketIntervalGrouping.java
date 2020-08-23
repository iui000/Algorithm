package GinkgoStack.P24_Enterprise.MeiTuan;

import javafx.util.Pair;

import java.util.*;

/**
 *  美团点评2020校招后台开发方向笔试题
 * 美团骑手包裹区间分组
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 2110年美团外卖火星第3000号配送站点有26名骑手，分别以大写字母A-Z命名，
 * 因此可以称呼这些骑手为黄家骑士特工A，黄家骑士特工B…黄家骑士特工Z，
 * 某美团黑珍珠餐厅的外卖流水线上会顺序产出一组包裹，美团配送调度引擎已经将包裹分配到骑手，
 * 并在包裹上粘贴好骑手名称，如RETTEBTAE代表一组流水线包裹共9个，同时分配给了名字为A B E R T的5名骑手。
 * 请在不打乱流水线产出顺序的情况下，把这组包裹划分为尽可能多的片段，
 * 同一个骑手只会出现在其中的一个片段，返回一个表示每个包裹片段的长度的列表。
 *
 *
 * 输入描述:
 * 输入数据只有一行，为一个字符串(不包含引号)，长度不超过1000，只包含大写字母'A'到'Z'，字符之间无空格。
 *
 *
 * 输出描述:
 * 输出每个分割成片段的包裹组的长度，每个长度之间通过空格隔开
 *
 *
 * 输入例子1:
 * MPMPCPMCMDEFEGDEHINHKLIN
 *
 * 输出例子1:
 * 9 7 8
 *
 * 例子说明1:
 * 划分结果为MPMPCPMCM,DEFEGDE,HINHKLIN。
 *
 * 每个骑手最多出现在一个片段中。
 *
 * 像MPMPCPMCMDEFEGDE,HINHKLIN的划分是错误的，因为划分的片段数较少。
 */
public class PacketIntervalGrouping {

    /**
     * 这个时间复杂度不是O(n）,而是O（n^2）
     */
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        int i=0,j=0,len=s.length();
        while(j<len){
            char c=s.charAt(j);
            int tail=s.lastIndexOf(c);
            int pre=j;
            i=j+1;
            j=tail;
            while(i<j){
                char inner=s.charAt(i);
                j=Math.max(j,s.lastIndexOf(inner));//其实这个复杂度也是O(N)
                i++;
            }
            j++;
            System.out.print(j-pre+" ");
        }
    }


    /**
     * 我的解法
     * 双指针两遍，然后再滑动窗口
     * @param s
     * @return
     */
    private int getMaxGroups(String s){
        Map<Character, Pair<Integer,Integer>> map = new HashMap<>(26);
        int n = s.length();
        for(int i = 0;i<n;i++){
            int j = n-1-i;
            map.putIfAbsent(s.charAt(i),new Pair<>(i,-1));

            map.putIfAbsent(s.charAt(j),new Pair<>(-1,j));
        }

        return 0;
    }

}
