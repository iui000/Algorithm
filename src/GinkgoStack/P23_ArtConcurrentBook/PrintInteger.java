package GinkgoStack.P23_ArtConcurrentBook;


import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 题目：
 *
 * 顺序递增打印正整数，从1开始打印到100，中间换行分隔。
 * 不允许重复打印出相同的数字，比如打印结果里出现2个5，3个6之类的。
 * 要求如下：
 * 1、使用三个线程A、B、C，其中线程A打印3的倍数，B打印5的倍数，C打印其他数字。
 */
public class PrintInteger {
    /**
     *
     * @author 13哥
     * https://blog.csdn.net/bohu83/article/details/102828798
     */


        //计数器
        final static AtomicInteger count = new AtomicInteger(0);
        //信号量
        static Semaphore sp3 = new Semaphore(1);
        static Semaphore sp5 = new Semaphore(1);
        static Semaphore other = new Semaphore(1);

        public static void main(String[] args) {


            Thread threadA  =new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        int p =  count.get();
                        if(p>100){
                            break;
                        }
                        if((p % 3 == 0) && (p % 5 != 0)) {
                            sp3.acquireUninterruptibly();
                            System.out.println("threadA :" + p);
                            count.getAndIncrement();
                        }
                        sp5.release();
                        other.release();
                    }
                }
            });

            Thread threadB   =new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        int p =  count.get();
                        if(p>100){
                            break;
                        }
                        if((p % 5 == 0) && (p % 3 != 0)) {
                            sp5.acquireUninterruptibly();
                            System.out.println("threadB : " + p);
                            count.getAndIncrement();
                        }
                        sp3.release();
                        other.release();
                    }
                }
            });

            Thread threadC   =new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        int p = count.get();
                        if(p>100){
                            break;
                        }
                        if(((p % 5 != 0) && (p % 3 != 0)||(p % 15 == 0))) {
                            other.acquireUninterruptibly();
                            System.out.println("threadc : " + p);
                            count.getAndIncrement();
                        }
                        sp3.release();
                        sp5.release();
                    }
                }
            });

            threadA.start();
            threadB.start();
            threadC.start();


        }



}
