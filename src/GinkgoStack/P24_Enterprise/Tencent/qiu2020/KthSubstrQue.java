package GinkgoStack.P24_Enterprise.Tencent.qiu2020;



import java.util.*;

public class KthSubstrQue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s  = sc.next();
        int k = sc.nextInt();

        Set<String> set = new HashSet<>();
        int co = 0;
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        SortedSet<String> sortedSet = new TreeSet<>();


        for (int i = 0;i<s.length();i++){
            for (int j = i;j<s.length();j++){
                String tmp = s.substring(i,j+1);
                if(queue.size() < k){
                    queue.add(tmp);
                }else {
                    String ss = queue.peek();
                    if(tmp.compareTo(ss) < 0 && !queue.contains(tmp)){
                        queue.poll();
                        queue.add(tmp);
                    }
                }
            }
        }

        String ans = queue.poll();
//        for (int i = 1;i<= k;i++){
//            ans = queue.poll();
//        }

        System.out.print(ans);
    }
}
