package GinkgoStack.P12_Math.Prime;


/**
 * 华为机考 HJ28-素数伴侣：和为素数的整数对的最大数目
 * 题目描述
 * 题目描述
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，
 * 它们能应用于通信加密。现在密码学会请你设计一个程序，
 * 从已有的N（N为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，
 * 例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，
 * 而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，
 * 当然密码学会希望你寻找出“最佳方案”。
 *
 * 输入:
 *
 * 有一个正偶数N（N≤100），表示待挑选的自然数的个数。后面给出具体的数字，范围为[2,30000]。
 *
 * 输出:
 *
 * 输出一个整数K，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 *
 *
 *
 * 输入描述:
 * 输入说明
 * 1 输入一个正偶数n
 * 2 输入n个整数
 *
 * 输出描述:
 * 求得的“最佳方案”组成“素数伴侣”的对数。
 *
 * 示例1
 * 输入
 * 复制
 * 4
 * 2 5 6 13
 * 输出
 * 复制
 * 2
 */
import org.omg.CORBA.portable.ValueInputStream;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class PrimeMateHuaweiHJ28 {
    /**
     * 二分图最大匹配，匈牙利算法
     *
     * 只有奇数与偶数之和才有可能是素数
     *
     * 1.对输入的正整数进行奇偶分组。设奇数组为odd，偶数组为even。
     * 2.判断X中任意一个奇数与Y中任意一个偶数之和是否为质数。
     *      奇数偶数两两相交，得到数对之和是否为质数的boolean矩阵。是质数为1，否则为0。
     * 3.使用匈牙利算法寻找boolean矩阵的最大匹配。
     */



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

    /**
     * 匈牙利算法
     * 为某一个目标奇数oddNum,找到它的素数伴侣（偶数）,皆以下标表示
     */
    private static boolean Hungarian(int oddNum,boolean[][] edge,int[] match,boolean[] visited){
        for (int i = 0; i < match.length; i++) {
            ////偶数未被访问过并且能与目标奇数组成素数（有关系）
            if(!visited[i] && edge[i][oddNum]){
                visited[i] =true;
                ////当前偶数没有匹配 或 能为被抛弃的奇数找到新的偶数
                if(match[i] == -1 || Hungarian(match[i],edge,match,visited)){
                    match[i] = oddNum;//找到这个偶数even.get(i)
                    visited[i] = false;
                    return true;
                }
            }

        }

        return false;
    }

    /*
    4
    2 5 6 13


    58
621 10618 19556 29534 25791 11133 5713 26642 25994 16095 6618 11447 29386 24436 22551 21467 2633 25704 29460 24325 8964 4087 10560 6478 9615 5119 1114 6773 9409 21549 15336 18995 2151 27404 6296 21066 3147 27037 6177 5650 16224 14352 8999 991 3012 16447 17799 16265 27163 24118 9766 15355 6161 3909 19451 16838 9113 10877
     输出：25
     这个测试用例可能超时，通不过
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 主要负责接收数据
        int N = scanner.nextInt();

        int count = 0;//匹配对数

        List<Integer> even = new ArrayList<>(N);//偶数
        List<Integer> odd = new ArrayList<>(N);//奇数

        for (int i = 0; i < N; i++) {
            int val = scanner.nextInt();
            if(val % 2 == 1){
                odd.add(val);
            }else {
                even.add(val);
            }
        }

        if(odd.isEmpty() || even.isEmpty()){
            System.out.println(count);
            return;
        }

        //建立关系表，二部图中的边
        boolean[][] edge = new boolean[even.size()][odd.size()];
        for (int i = 0; i < even.size(); i++) {
            for (int j = 0; j < odd.size(); j++) {
                int sum = even.get(i) + odd.get(j);
                if(sum != 2 && sum % 2 == 0){
                    continue;
                }
                if(isPrime(sum)){
                    edge[i][j] = true;
                }
            }
        }

        //建立初始匹配表
        int[] match = new int[even.size()];
        Arrays.fill(match,-1);
        //为每一个奇数都尝试去找对应的偶数，
        for (int i = 0; i < odd.size(); i++) {
            //每一次查找都相当于重新分配，标志要清零
            boolean[] visited = new boolean[even.size()];
            if(Hungarian(i,edge,match,visited)){
                count++;
            }
        }

        System.out.println(count);
    }
}
