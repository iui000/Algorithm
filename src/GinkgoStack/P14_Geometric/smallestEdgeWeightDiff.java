package GinkgoStack.P14_Geometric;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
class Eage{
    int u = 0;
    int v = 0;
    int w = 0;

    public Eage(int us, int vs, int ws) {
        u = us;
        v = vs;
        w = ws;
    }
}

/**
 * 网易笔试
 * 由图生成 边权差值最小的树
 * 没有AC
 */
public class smallestEdgeWeightDiff{

    private static int find(int[] a,int x){
        if(a[x] == x){
            return x;
        }
        int tmp = find(a,x);
        a[x] = tmp;
        return  tmp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Eage> eages = new ArrayList<>(m);
        int[] parent = new int[n];

        int res = Integer.MAX_VALUE;

        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            eages.add(new Eage(u,v,w));
        }

        eages.sort(new Comparator<Eage>() {
            @Override
            public int compare(Eage o1, Eage o2) {
                return o1.w - o2.w;
            }
        });

        for(int i = 0;i<= m-n+1;i++){
            int tmp = -1;
            int sum = 0;
            for(int j = 1;j<= n;j++){
                parent[j] = j;
            }

            for(int j = i;j<m;j++){
                int pu = find(parent,eages.get(j).u);
                int pv = find(parent,eages.get(j).v);

                if(pu != pv){
                    sum++;
                    parent[pu] = pv;
                    if(sum == n-1){
                        tmp = eages.get(j).w - eages.get(i).w;
                        break;
                    }
                }
            }

            if(tmp != -1 && tmp<res)
                res = tmp;
        }


        if(res < Integer.MAX_VALUE){
            System.out.print(res);
        }else {
            System.out.print(-1);
        }
    }
}
