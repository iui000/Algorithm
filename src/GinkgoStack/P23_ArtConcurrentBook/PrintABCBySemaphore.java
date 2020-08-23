package GinkgoStack.P23_ArtConcurrentBook;

import java.util.concurrent.Semaphore;

public class PrintABCBySemaphore {

    private int times;
    //注意，第一个信号量的初始值是1，后两个是0
    private Semaphore semaphoreA =new Semaphore(1);
    private Semaphore semaphoreB =new Semaphore(0);
    private Semaphore semaphoreC =new Semaphore(0);

    public PrintABCBySemaphore(int times) {
        this.times=times;
    }

    public void printA() {
        print("A",semaphoreA,semaphoreB);
    }
    public void printB() {
        print("B",semaphoreB,semaphoreC);
    }
    public void printC() {
        print("C",semaphoreC,semaphoreA);
    }


    public void print(String name,Semaphore current,Semaphore next) {
        for(int i=0;i<times;i++) {
            try {
                current.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +":" + i+ " :" + name);
            next.release();
        }

    }

    public static void main(String[] args) {
        PrintABCBySemaphore printABCBySemaphore =new PrintABCBySemaphore(10);
        new Thread(printABCBySemaphore::printA).start();
        new Thread(printABCBySemaphore::printB).start();
        new Thread(printABCBySemaphore::printC).start();
    }
}