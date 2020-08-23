package GinkgoStack.P23_ArtConcurrentBook;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCByLock {

    private int times;
    private int state;
    private Lock lock =new ReentrantLock();

    public PrintABCByLock(int times) {
        this.times=times;
    }


    public static void main(String[] args) {
        PrintABCByLock printABC =new PrintABCByLock(10);
        new Thread(printABC::printA).start();
        new Thread(printABC::printB).start();
        new Thread(printABC::printC).start();
    }
    public void printA() {
        print("A",0);
    }
    public void printB() {
        print("B",1);
    }
    public void printC() {
        print("C",2);
    }

    public void print(String name,int stateNow) {
        for (int i = 0; i < times;) {
            lock.lock();
            if(stateNow == state % 3) {
                state++;
                i++;
                System.out.println(Thread.currentThread().getName()
                        + ":i=" + i + ":stateNow="+stateNow
                        + ":" +name);
            }
            lock.unlock();
        }
    }
}