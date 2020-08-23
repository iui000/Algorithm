package GinkgoStack.P24_Enterprise.YuanFudao;

import java.util.Scanner;
import java.util.Stack;

/**
 * 逆时针打印树的最外层
 * 包括左边界，叶子，和右边界
 */
public class OuterLayersOfTree {

    /**
     * 思路1：直接根据下标计算，效率比较高，O（log n）
     * 思路2：层序遍历，记录每层第一个节点和最后一个节点
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n <= 0){
            return;
        }

        int[] tree = new int[n+1];
        for (int i = 1;i<=n;i++){
            tree[i] = sc.nextInt();
        }

        if( n<=3){
            for (int p = 1;p<=n;p++){
                System.out.printf("%d%c", tree[p], p==n ? '\n' : ' ');
            }
        }


        Stack<Integer> stack = new Stack<>();
        double height = 2;
        for(int j = 1;j<=n;){//实际只会进行height次循环
            System.out.printf("%d%c", tree[j], ' ');

            //用栈存储树右边外层，当树是满二叉树时，栈中没有包括右下角的叶子。
            int rightIndex = (int)Math.pow(2,height) -1;
            if(rightIndex < n){
                stack.push(tree[rightIndex]);
            }

            //表示当前层的最左边节点的下标
            j = (int)Math.pow(2,height-1);

            if(rightIndex == n && j<= n){//说明到了最后一层，而且是满二叉树
                for (int h = j;h<= n;h++){//从左到右打印叶子节点
                    System.out.printf("%d%c", tree[h], ' ');
                }
                break;
            }

            if(rightIndex > n && j<= n){//说明是完全二叉树
                for (int h = j;h<= n;h++){//打印最后一层的叶子
                    System.out.printf("%d%c", tree[h], ' ');
                }
                for (int h = n/2+1;h< rightIndex/2;h++){//打印倒数第二层的叶子
                    System.out.printf("%d%c", tree[h], ' ');
                }
                break;
            }
            height++;
        }

        //最后打印右边界的节点
        while (!stack.isEmpty()){
            int tt = stack.pop();
            if(!stack.isEmpty()){
                System.out.printf("%d%c", tt, ' ');
            }else {
                System.out.print( tt);
            }
        }
    }
}
