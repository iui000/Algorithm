package GinkgoStack.P23_ArtConcurrentBook;

/**
 * 百度面试
 * 写一个死锁
 */
public class DeadLockDemo {
    private static Object lock1 = new Object();//资源1
    private static Object lock2 = new Object();//资源2

    public static void main(String[] args) {
        new Thread(()->{
            synchronized(lock1){
                System.out.println(Thread.currentThread() + "get lock1");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + "waiting get lock2");
                synchronized (lock2){
                    System.out.println(Thread.currentThread() + "get lock2");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized(lock2){
                System.out.println(Thread.currentThread() + "get lock2");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + "waiting get lock1");
                synchronized (lock1){
                    System.out.println(Thread.currentThread() + "get lock1");
                }
            }
        },"t2").start();
    }

}
