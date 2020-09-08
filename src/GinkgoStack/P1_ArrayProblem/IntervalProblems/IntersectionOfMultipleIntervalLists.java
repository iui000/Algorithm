package GinkgoStack.P1_ArrayProblem.IntervalProblems;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 百度笔试题
 * M个区间列表求交集
 * 可惜了呀~~~
 * 应该可以AC
 */
public class IntersectionOfMultipleIntervalLists {
/*
1
10 2
3
1 2
4 5
8 8
2
1 4
6 8

输出：
4
1 2 4 8
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            //n的取值并不大，直接用标记法
            int N = scanner.nextInt();//区间范围最大值,取值并不大，题目说最大1000
            int M = scanner.nextInt();//m个区间列表，m最大10

            int[] lable = new int[N+1];//被标记
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 1; i <= M; i++) {//根据每一个特性，对lable进行标记
                int k = scanner.nextInt();//该特性有k个区间
                for (int j = 0; j < k; j++) {//每一个区间
                    int begin = scanner.nextInt();//小区间的起始位置
                    int end = scanner.nextInt();//小区间的结束位置
                    for (int h = begin; h <= end; h++) {
                        if(lable[h] == M-1){//说明这是M个特性都能覆盖的编号
                            res.add(h);
                        }

                        if(lable[h] > i-1){//防止同一个区间列表中的某些区间有重叠，造成重复加1
                            lable[h] = i-1;
                        }

                        lable[h]++;//标记加1，注意，这个标记操作要放在最后
                    }
                }
            }

            System.out.println(res.size());
            for(int i = 0;i < res.size();i++){
                System.out.printf("%d%c", res.get(i), i==res.size()-1 ? '\n' : ' ');
            }

        }
    }
}
