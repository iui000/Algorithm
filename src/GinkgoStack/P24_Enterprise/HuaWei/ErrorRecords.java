package GinkgoStack.P24_Enterprise.HuaWei;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * 简单错误记录
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 64M，其他语言128M
 *
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 * 处理:
 * 1.记录最多8条错误记录，对相同的错误记录(即文件名称和行号完全匹配)只记录一条，错误计数增加；(文件所在的目录不同，文件名和行号相同也要合并)
 * 2.超过16个字符的文件名称，只记录文件的最后有效16个字符；(如果文件名不同，而只是文件名的后16个字符和行号相同，也不要合并)
 * 3.输入的文件可能带路径，记录文件名称不能带路径
 *
 * 输入描述:
 * 一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
 *
 *     文件路径为windows格式
 *
 *     如：E:\V1R2\product\fpgadrive.c 1325
 *
 * 输出描述:
 * 将所有的记录统计并将结果输出，格式：文件名 代码行数 数目，一个空格隔开，如: fpgadrive.c 1325 1
 *
 *     结果根据数目从多到少排序，数目相同的情况下，按照输入第一次出现顺序排序。
 *
 *     如果超过8条记录，则只输出前8条记录.
 *
 *     如果文件名的长度超过16个字符，则只输出后16个字符
 *
 * 输入例子1:
 * E:\V1R2\product\fpgadrive.c 1325
 *
 * 输出例子1:
 * fpgadrive.c 1325 1
 */
public class ErrorRecords {



    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        //题目要求：结果根据数目从多到少排序，数目相同的情况下，按照输入第一次出现顺序排序。
        //因此可以用LinkedHashMap而不是hashMap
        //当然，我觉得最好是加入一个时间戳字段用HashMap，后面排序就能用快排，而不是复杂度更高的稳定排序，比如这样：
//        Map<String, Pair<Integer,Integer>> map = new HashMap<>(); Pair 里面分别表示频次和时间戳
        Map<String, Integer> map = new LinkedHashMap<>();
        String key;
        String filename;
        String path;
        while(in.hasNext()){
            path = in.next();
            //将路径转换为文件名
            int id = path.lastIndexOf('\\');
            //如果找不到说明只有文件名没有路径
            filename = id<0  ? path : path.substring(id+1);
            int linenum = in.nextInt();
            //统计频率
            key = filename+" "+linenum;
            if(map.containsKey(key)){
                map.put(key, map.get(key)+1);
            }else{
                map.put(key, 1);
                //也可以加入一个时间戳字段，后面排序用
            }
        }

        in.close();

        //对记录进行排序，得用稳定排序，这里用了链表，O（N^2）复杂度
        //我觉得可以用时间戳，然后快排更好点
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String, Integer>>(){
            //降序
            @Override
            public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
                return arg1.getValue()-arg0.getValue();
            }
        });


        //只输出前8条
        int m=0;
        for(Map.Entry<String, Integer> mapping : list){
            m++;
            if(m<=8){
                String[] str = mapping.getKey().split(" ");
                String k = str[0].length()>16 ? str[0].substring(str[0].length()-16) : str[0];
                String n = str[1];
                System.out.println(k+" "+n+" "+mapping.getValue());
            }else{
                break;
            }

        }

    }

}




