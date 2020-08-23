/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package GinkgoStack.P23_ArtConcurrentBook.chapter06;

import java.util.HashMap;
import java.util.UUID;

/**
 * ����put
 * 
 * @author tengfei.fangtf
 * @version $Id: Snippet.java, v 0.1 2015-7-31 ����11:53:55 tengfei.fangtf Exp $
 */
public class ConcurrentPutHashMap {

    public static void main(String[] args) throws InterruptedException {

        final HashMap<String, String> map = new HashMap<String, String>(2);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    /**
                     * HashMap�ڲ���ִ��put����ʱ��������ѭ����
                     * ����Ϊ���̻߳ᵼ��HashMap��Entry���� �γɻ������ݽṹ��
                     * һ���γɻ������ݽṹ��Entry��next�ڵ���Զ��Ϊ�գ��ͻ������ѭ���� ȡEntry
                     */
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
