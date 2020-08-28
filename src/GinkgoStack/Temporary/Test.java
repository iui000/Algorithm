package GinkgoStack.Temporary;


import GinkgoStack.P23_ArtConcurrentBook.chapter04.SleepUtils;


/**
 * 结论：静态变量也称为类变量，属于类对象所有，位于方法区，为所有对象共享，共享一份内存，
 * 一旦值被修改，则其他对象均对修改可见，故线程非安全。
 */
public class Test extends Thread{
    static int x = 0;

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            x++;
        }
        System.out.println(x);
    }

    public static void main(String[] args) {
        new Test().start();
        new Test().start();

        SleepUtils.second(1);
        for(int i = 0; i < 100; i++){
            System.out.println(x);
        }

    }

}






