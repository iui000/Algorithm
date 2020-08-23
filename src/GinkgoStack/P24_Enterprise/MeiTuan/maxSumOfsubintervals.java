package GinkgoStack.P24_Enterprise.MeiTuan;

import java.util.*;

/**
 * 就是一个数组，每删除一个数，求出剩余子区间的最大和
 * 正解估计是线段树
 */
public class maxSumOfsubintervals {

    static class Interval {
        int begin = 0;
        int end = 0;
        int value = 0;

        public Interval(int begin, int end, int value) {
            this.begin = begin;
            this.end = end;
            this.value = value;
        }
    }


    /*
    5
    3 2 4 4 5
    4 3 5 2 1
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] s = new int[n+1];

        int[] w = new int[n+1];
        int[] r = new int[n+1];
        int sum = 0;
        for (int i = 1;i<=n;i++){
            w[i]= sc.nextInt();
            sum += w[i];
            s[i]  = sum;
            r[i]= sc.nextInt();
        }


        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o2.value - o1.value;
            }
        });

        Interval in =  new Interval(1,n,s[n]);

        Map<Integer, Interval> map = new HashMap<>();
        for(int i = 1;i<= n;i++){
            map.put(i,in);
        }

        queue.add(in);

        for (int i = 0;i<n;i++){
            if( i >  queue.peek().begin && i < queue.peek().end){
                Interval tmp = queue.poll();
                Interval left = new Interval(tmp.begin,i-1,s[i-1] - s[tmp.begin-1]);
                Interval right = new Interval(i+1,tmp.end,s[tmp.end] - s[i]);
                queue.add(left);
                queue.add(right);
                System.out.println(queue.peek().value);
            }

            else if(i == queue.peek().begin){

            }

            else if(i == queue.peek().end){

            }

            else {
                //否者就直接遍历队列，找到对应的区间，然后根据情况拆分区间
            }
        }

    }
}
