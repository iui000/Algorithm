package GinkgoStack.P10_Digital;

/**
 *
 * 400. 第N个数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 *
 * 注意:
 * n 是正数且在32位整数范围内 ( n < 231)。
 *
 * 示例 1:
 *
 * 输入:
 * 3
 *
 * 输出:
 * 3
 * 示例 2:
 *
 * 输入:
 * 11
 *
 * 输出:
 * 0
 *
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 * AC代码
 */
public class FindNthDigit400andOffer44 {
    public static int findNthDigit(int n) {
        if(n < 10){
            return n;
        }
        long res = 0;
        long target = 0;

        int i = 1;//位数
        //2147483647 小心整数溢出
        long sum = 9;
        long pre = 0;

        /**
         * 位数i为1的数字串有9个：1 2 3 ... 9
         * 位数i为2的数字串有90个：10 11 ... 99
         * 位数i为3的数字串有900个：100 101 ... 999
         *  ...
         *
         * 那么这有多少个单数字呢？
         * i*9*P12_Math.pow(10,i-1);
         */
        //假设n=12
        while (n > sum){
            i++;
            sum += i*9*Math.pow(10,i-1);
        }
        //那么可以确定最终答案是某个两位数的其中一个数字，pre等于9
        pre = sum == n?sum: sum - (long)(i*9*Math.pow(10,i-1));


        //按照正常的顺序，找到这个两位数的具体位置(10算作第一个两位数，11是第二个...以此类推)
        long orderOfTarget =  (n - pre + (i-1))/i;//让它向上取整，才能找到正确的顺序
        target = (long)Math.pow(10,i-1) + orderOfTarget-1;//根据它的位数 和 顺序就能确定这个目标值是多少

        //然后再到这个i位数中去找最终的答案，也就是处于第n个位置的那个数字
        long sequenceOfNumber = (n - pre)%i;
        //所求的那个数字res在target中的顺序，如果算出来是0，那res就是target的最低位数字，对10取模即可得到最低位数
        //如果sequenceOfNumber不为0，那么就是从target的高位开始从左往右第sequenceOfNumber个数字，将其取出来即可
        res =  sequenceOfNumber == 0 ?
                target%10 :
                (target/(long)Math.pow(10,i-sequenceOfNumber))%10;

        return (int)res;
    }

    public static void main(String[] args) {
        System.out.print(findNthDigit(1000000000));
    }

}
