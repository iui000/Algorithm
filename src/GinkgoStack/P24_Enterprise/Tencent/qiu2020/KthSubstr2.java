package GinkgoStack.P24_Enterprise.Tencent.qiu2020;

import java.util.PriorityQueue;
import java.util.Scanner;

public class KthSubstr2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int len = str.length();
        int k = scanner.nextInt();

        // 大顶堆
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));

        for (int i = 0; i < len; i++) {
            // 如果第一个字符不符合（大），根据字典数的定义，后面就全部不符合了
            if (!pq.isEmpty()) {
                String peekStr = pq.peek();
                if (str.charAt(i) > peekStr.charAt(0)) continue;
            }
            for (int j = i + 1; j < len; j++) {
                String ele = str.substring(i, j);

                if (pq.size() < k) {
                    pq.add(ele);
                    continue;
                }

                //其实这个pq.contains(ele)复杂度比较高，是遍历队列中的全部元素
                //哎，没办法，这道题是以时间换空间
                //如果用set会超出空间
                if (ele.compareTo(pq.peek()) < 0 && !pq.contains(ele)) {
                    pq.poll();
                    pq.add(ele);
                }
            }
        }
        System.out.println(pq.peek());
    }



}
