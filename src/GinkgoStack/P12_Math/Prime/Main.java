package GinkgoStack.P12_Math.Prime;

import java.util.Scanner;


/**
 * 华为机考 HJ28-素数伴侣：和为素数的整数对的最大数目
 * AC代码
 */
public class Main{
    //是否素数
    private static boolean isPrime(int n){
        if(n<2){
            return false;
        }
        int t = (int) Math.sqrt(n);
        for(int i = 2;i<t;i++){
            if(n%i == 0){//如果一个数，不是它平方根以内的数的倍数，那它就是素数
                return false;
            }
        }

        return true;
    }



    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        while(scan.hasNextInt()){
            int n=scan.nextInt();
            int m[]=new int[n];
            int oddCount=0;

            for(int i=0;i<n;i++){

                m[i]=scan.nextInt();
                if(m[i]%2==1)
                    oddCount++;
            }

            int odd[]=new int[oddCount];
            int even[]=new int[n-oddCount];
            int h=0;
            int z=0;
            for(int j=0;j<n;j++){
                if(m[j]%2==1){
                    odd[h++]=m[j];
                }else{
                    even[z++]=m[j];
                }
            }


            int sum=0;
            int edge[][]=new int[oddCount][n-oddCount];

            for(int j=0;j<odd.length;j++){
                for(int k=0;k<even.length;k++){
                    int friend=odd[j]+even[k];
                    if(isPrime(friend)){
                        edge[j][k]=1;
                    }
                }
            }


            boolean visited[] = new boolean[n-oddCount];

            int match[]= new int[n-oddCount];
            for(int i=0;i<odd.length;i++){
                if(find(edge,even,visited,i,match)){
                    sum++;
                }
            }
            System.out.println(sum);
        }
    }
    public static boolean find(int edge[][],int even[],boolean visited[],int odd,int match[]){
        for(int i=0;i<even.length;i++){

            if(!visited[i] && edge[odd][i]==1){
                visited[i]=true;

                if(match[i]==0 || find(edge,even,visited,match[i]-1,match)){
                    match[i]=odd+1;
                    visited[i]=false;
                    return true;
                }
                //一开始加了else，超时，后来才明白,不释放查找节点的状态是因为当ycode为
                //false的时候，说明当前节点已经不可能再让出节点给新的节点匹配，避免重 复计算
                //else{
                //visited[i]=false;
                //}


            }
        }
        return false;
    }
}
