package GinkgoStack.P23_ArtConcurrentBook;

public class deadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1){
                System.out.println(Thread.currentThread()+"had get lock1");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"try get lock2");
                synchronized (lock2){
                    System.out.println(Thread.currentThread()+"had get lock2");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (lock2){
                System.out.println(Thread.currentThread()+"had get lock2");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread()+"try get lock1");
                synchronized (lock1){
                    System.out.println(Thread.currentThread()+"had get lock1");
                }
            }
        },"t2").start();

    }
}
