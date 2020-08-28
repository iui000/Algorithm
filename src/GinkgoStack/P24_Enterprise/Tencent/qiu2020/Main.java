


package GinkgoStack.P24_Enterprise.Tencent.qiu2020;
import java.util.*;

public class Main
{

    static class Node{
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }


    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int n, k;

        n = cin.nextInt();
        k = cin.nextInt();
        int[] arr = new int[n];
        Node head = new Node(-1);
        int h = 0;
        for (int i = 0;i<n;i++){
            int tmp = cin.nextInt();
            if(i != k-1){
                arr[h++] = tmp;
            }
        }

        int m = k<n?n-1:n;
        for (int i = 0;i< m;i++){
            System.out.printf("%d%c", arr[i], i==m-1 ? '\n' : ' ');
        }


    }
}
