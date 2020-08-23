package GinkgoStack.P5_PriorityQueue_Heap;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 广联达笔试
 * 每次给最矮的草撒催化剂
 * AC代码
 */
public class Seeding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        int m= scanner.nextInt();
        int x= scanner.nextInt();
        int t = 1;
        PriorityQueue<Integer> heap = new PriorityQueue<>(n);
        while (t<=n){
            heap.add(scanner.nextInt());
            t++;
        }

        for(int i = 0;i<m;i++){
            int c = heap.poll();
            heap.add(c+x);
        }

        System.out.print(heap.peek());
    }
}
