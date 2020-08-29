package GinkgoStack.P23_ArtConcurrentBook;


/**
 * 使用“生产者-消费者模式”编写代码实现：线程A随机间隔（10~200ms）按顺序生成1到100的数字（共100个），
 * 放到某个队列中.线程B、C、D即时消费这些数据，线程B消费所有被3整除的数，
 * 线程C消费所有被5整除的数，其它的由线程D进行消费。线程BCD消费这些数据时在控制台中打印出来，
 * 要求按顺序打印这些数据
 * 限时40分钟，可以查API
 *
 * 作者：Easy的幸福
 * 链接：https://www.jianshu.com/p/adc70b51ca06
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

import java.util.LinkedList;
import java.util.PrimitiveIterator;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ProducerConsumer {





        static class MyQueue {

            private Queue<Integer> queue = new LinkedList<>();

            private PrimitiveIterator.OfLong longs = new Random().longs(10, 200).iterator();

            private Lock lock = new ReentrantLock();

            private Condition b = lock.newCondition();
            private Condition c = lock.newCondition();
            private Condition d = lock.newCondition();

            public int b_pull() {
                lock.lock();
                try {
                    try {
                        b.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
                return queue.remove();
            }

            public int c_pull() {
                lock.lock();
                try {
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
                return queue.remove();
            }

            public int d_pull() {
                lock.lock();
                try {
                    try {
                        d.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
                return queue.remove();
            }

            public void start() {
                new Thread(() -> {
                    for (int i = 1; i <= 100; i++) {
                        queue.add(i);
                        lock.lock();
                        try {
                            if (i % 3 == 0) {
                                b.signal();
                            } else if (i % 5 == 0) {
                                c.signal();
                            } else {
                                d.signal();
                            }
                        } finally {
                            lock.unlock();
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(longs.nextLong());
                        } catch (InterruptedException ignore) {

                        }
                    }
                    System.out.println("all numbers are produced.");
                }).start();
            }
        }

        public static void main(String[] args) {
            MyQueue queue = new MyQueue();
            new Thread(() -> {
                while (true) {
                    System.out.println(String.format("B(mod 3) consume : %d", queue.b_pull()));
                }
            }).start();
            new Thread(() -> {
                while (true) {
                    System.out.println(String.format("C(mod 5) consume : %d", queue.c_pull()));
                }
            }).start();
            new Thread(() -> {
                while (true) {
                    System.out.println(String.format("D(other) consume : %d", queue.d_pull()));
                }
            }).start();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.start();
        }

}
