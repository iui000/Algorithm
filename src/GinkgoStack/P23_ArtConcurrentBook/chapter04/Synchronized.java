package GinkgoStack.P23_ArtConcurrentBook.chapter04;

/**
 * 6-10
 */
public class Synchronized {
    public static void main(String[] args) {
        // ��Synchronized Class������м���
        synchronized (Synchronized.class) {

        }
        // ��̬ͬ����������Synchronized Class������м���
        m();
    }

    public static synchronized void m() {
    }
}
