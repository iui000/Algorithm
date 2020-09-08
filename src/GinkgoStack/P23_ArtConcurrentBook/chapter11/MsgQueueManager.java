package GinkgoStack.P23_ArtConcurrentBook.chapter11;import java.util.concurrent.BlockingQueue;import java.util.concurrent.LinkedTransferQueue;/** * 我们在一个长连接服务器中使用了这种模式，生产者1负责将所有客户端发送的消息存放 * 在阻塞队列1里，消费者1从队列里读消息，然后通过消息ID进行散列得到N个队列中的一个， * 然后根据编号将消息存放在到不同的队列里，每个阻塞队列会分配一个线程来消费阻塞队列 * 里的数据。如果消费者2无法消费消息，就将消息再抛回到阻塞队列1中，交给其他消费者处 理。 * * @author tengfei.fangtf */public class MsgQueueManager implements IMsgQueue {    /**     * ��Ϣ�ܶ���     */    public final BlockingQueue<Message> messageQueue;    private MsgQueueManager() {        messageQueue = new LinkedTransferQueue<Message>();    }    public void put(Message msg) {        try {            messageQueue.put(msg);        } catch (InterruptedException e) {            Thread.currentThread().interrupt();        }    }    public Message take() {        try {            return messageQueue.take();        } catch (InterruptedException e) {            Thread.currentThread().interrupt();        }        return null;    }}