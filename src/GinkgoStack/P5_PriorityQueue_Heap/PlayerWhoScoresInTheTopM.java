package GinkgoStack.P5_PriorityQueue_Heap;


import java.util.*;

/**
 * 字节笔试
 * AC
 * n次提交，分数可累加。输出前m名的编号，如果分数相同，标号较小者排在前面。
 */
public class PlayerWhoScoresInTheTopM {

/*
5 3
1 1
2 2
3 3
4 4
4 4
 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        m = m > n ? n:m;

        HashMap<Integer,Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            long sco = sc.nextLong();
            map.putIfAbsent(id,0L);
            map.put(id,map.get(id) + sco);
        }

        Queue<Map.Entry<Integer,Long>> queue = new PriorityQueue<>(m,
                new Comparator<Map.Entry<Integer, Long>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Long> o1,
                                       Map.Entry<Integer, Long> o2) {
                        return (int)(o1.getValue().equals(o2.getValue()) ?
                                o2.getKey() - o1.getKey(): //这里要小心
                                o1.getValue() - o2.getValue()) ;
                    }
                }
        );
        for (Map.Entry<Integer,Long> e : map.entrySet()){
            if(queue.size() < m){
                queue.offer(e);
            }else if(e.getValue() > queue.peek().getValue() ||
                    (e.getValue().equals(queue.peek().getValue()) &&
                            e.getKey() < queue.peek().getKey()) ){
                queue.poll();
                queue.offer(e);
            }
        }

        int[] res = new int[m];
        for (int i = m-1; i >= 0 && !queue.isEmpty(); i--) {
            res[i] = queue.poll().getKey();
        }
        for(int i = 0;i < m;i++){
            System.out.printf("%d%c", res[i], i==m-1 ? '\n' : ' ');
        }

    }
}
