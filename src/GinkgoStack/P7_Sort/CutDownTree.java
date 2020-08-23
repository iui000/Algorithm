package GinkgoStack.P7_Sort;

import java.util.*;

/**
 * 浪潮笔试题
 * 道路两旁各有50棵树，左边是奇数编号1 3 5 ...49，右边是偶数编号2 4 6...50.
 * 现在随机砍掉n棵树（编号随机）
 * 问：剩下的树中，紧挨着的树(同一侧，比如6和8就算紧挨着)最大数目有多少棵？(只算同一侧连续)，答案给出起始编号 和 数目
 * 如果有多个等长连续段，那起始位置输出最小编号
 * 比如：
 * 5
 * 9 15 27 35 6
 *
 * 输出：
 * 8 47
 * (偶数编号侧从8开始，有紧挨着47棵树)
 */
public class CutDownTree {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(0);
        list2.add(0);
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int i = 0;

        if(N == 0){
            System.out.print(1);
            System.out.print(" ");
            System.out.print(50);
            return;
        }

        while (i<N && sc.hasNextInt()){
            int tmp = sc.nextInt();
            if(tmp%2== 0){
                list2.add(tmp/2);
            }else {
                list1.add((tmp+1)/2);//奇数
            }
            i++;
        }
        sc.close();
        list1.add(51);
        list2.add(51);
        list1.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        list2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        int m = 0;
        int res = Integer.MIN_VALUE;
        int len1 = list1.size();
        int len2 = list2.size();

        for(int j = len1-1,k = len2-1;j >= 1|| k>=1;j--,k--){
            if(j >= 1){
                if(list1.get(j) - list1.get(j-1) -1 >= res){
                    m = (list1.get(j-1)+1)*2-1;
                    res = list1.get(j) - list1.get(j-1) -1;
                }
            }
            if(k >= 1){
                if(list2.get(k) - list2.get(k-1) -1 >= res){
                    m = (list2.get(k-1)+1)*2;
                    res = list2.get(k) - list2.get(k-1) -1;
                }
            }

        }
        System.out.print(m);
        System.out.print(" ");
        System.out.print(res);

    }
}
