package GinkgoStack.P23_ArtConcurrentBook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCByLockCondition {

    public static void main(String[] args) {
        final Business business = new Business();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    business.sub2("B");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    business.sub3("C");
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            business.sub1("A");
        }
    }
    static class Business {
        private int flag = 1;
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        public void sub1(String s) {
            lock.lock();
            try{
                while(flag != 1) {
                    condition1.await();
                }
                System.out.println("A线程输出" + s);
                flag = 2;
                condition2.signal();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void sub2(String s) {
            lock.lock();
            try{
                while(flag != 2) {
                    condition2.await();
                }
                System.out.println("B线程输出" + s);
                flag = 3;
                condition3.signal();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void sub3(String s) {
            lock.lock();
            try{
                while(flag != 3) {
                    condition3.await();
                }
                System.out.println("C线程输出" + s);
                flag = 1;
                condition1.signal();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
