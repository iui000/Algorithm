/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package GinkgoStack.P23_ArtConcurrentBook.chapter06;

import java.util.HashMap;
import java.util.UUID;

/**
 * 并发put
 * 
 * @author tengfei.fangtf
 * @version $Id: Snippet.java, v 0.1 2015-7-31 下午11:53:55 tengfei.fangtf Exp $
 */
public class ConcurrentPutHashMap {

    public static void main(String[] args) throws InterruptedException {

        final HashMap<String, String> map = new HashMap<String, String>(2);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    /**
                     * HashMap在并发执行put操作时会引起死循环，
                     * 是因为多线程会导致HashMap的Entry链表 形成环形数据结构，
                     * 一旦形成环形数据结构，Entry的next节点永远不为空，就会产生死循环获 取Entry
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
