package GinkgoStack.P12_Math.DivisibleBy7;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 亲7数
 *
 * 不确定是对的
 *
 * 给定一个数字数组，每个元素是0-9的单个数字，存在重复值。
 * 问，这些数字的排列组合形成的所有数中(自己要去重)，有多少个数是可以被7整除的。
 *
 * 这道题并没有告诉数组的长度，所以就可能很长，这点注意
 */
public class Divisible7 {
    List<Integer> arr;
    int count = 0;
    Set<String> set = new HashSet<>();

    private boolean isDivisible7(List<Integer> a){
        long m = 0;
        for(int i = a.size()-1;i>= 0;i--){
            m += Math.pow(10,a.size()-1-i)*a.get(i);
        }
        return  m % 7 == 0;
    }

    private long toInt(List<Integer> a){
        long m = 0;
        for(int i = a.size()-1;i>= 0;i--){
            m += Math.pow(10,a.size()-1-i)*a.get(i);
        }
        return  m;
    }

    private List<Integer> subtraction(List<Integer> a, List<Integer> b){
        int i = a.size()-1,j = b.size()-1;
        List<Integer> res = new LinkedList<>();

        while (j>=0 && i>=0){
            if(a.get(i) < b.get(j)){
                //向前借位
                a.set(i-1,a.get(i-1)-1);

                ((LinkedList<Integer>) res).addFirst(a.get(i) + 10 - b.get(j));
            }else {
                ((LinkedList<Integer>) res).addFirst(a.get(i)- b.get(j));
            }

            i--;
            j--;
        }

        while (i>=0 && a.get(i) != 0 ){
            ((LinkedList<Integer>) res).addFirst(a.get(i--));
        }

        List<Integer> ans = new ArrayList<>();
        Iterator<Integer> iterator = res.iterator();
        while (iterator.hasNext()){//将前导0去掉
            int tmp = iterator.next();
            if(tmp != 0){
                ans.add(tmp);
                break;
            }else if(!iterator.hasNext()){
                ans.add(tmp);
            }
        }
        while (iterator.hasNext()){
            ans.add(iterator.next());
        }
        return ans;
    }


    private  void swap(int from,int to){
        if(this.arr.get(from) == this.arr.get(to)){
            return;
        }else {
            int tmp = arr.get(from);
            arr.set(from,arr.get(to));
            arr.set(to,tmp);
        }
    }

    private void allOrder(int b,int e){
        if(b == e){
            System.out.println(arr.toString());
            if(!set.contains(arr.toString())){
                set.add(arr.toString());
                if(arr.size() <= 3){
                    if(isDivisible7(arr))
                        count++;
                }else {
                    List<Integer> aaa = arr.subList(0,arr.size()-3);
                    List<Integer> bbb = arr.subList(arr.size()-3,arr.size());
                    List<Integer> mmm  = new ArrayList<>();
                    while (aaa.size() > 3){
                        mmm = subtraction(aaa,bbb);
                        if(mmm.size() > 3){
                            aaa = mmm.subList(0,mmm.size()-3);
                            bbb = mmm.subList(mmm.size()-3,mmm.size());
                        }
                    }

                    long t1 = toInt(aaa);
                    long t2 = toInt(bbb);
                    if(t1 >= t2){
                        mmm = subtraction(aaa,bbb);
                    }else {
                        mmm = subtraction(bbb,aaa);
                    }

                    if(isDivisible7(mmm))
                        count++;
                }
            }


        }else {
            for(int i  = b;i<= e;i++){

                swap(b,i);
                allOrder(b+1,e);
                swap(i,b);
            }
        }
    }


    /**
     * 返回亲7数个数
     * @param digit int整型一维数组 组成亲7数的数字数组
     * @return int整型
     */
    public  int reletive_7 (int[] digit) {
        this.arr = Arrays.stream(digit).boxed().collect(Collectors.toList());
        // write code here
        allOrder(0,digit.length-1);
        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] digit = new int[]{2,3,1,1,2};
        int[] digit2 = new int[]{1,0,0};

        List<Integer> list = Arrays.stream(digit).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(digit2).boxed().collect(Collectors.toList());

        Divisible7 solution = new Divisible7();

        System.out.println(solution.isDivisible7(list));
        System.out.println(solution.subtraction(list,list2).toString());
        System.out.println(solution.reletive_7(digit));

    }
}
