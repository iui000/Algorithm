package GinkgoStack.P15_DataStruct.LruCache;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：LeetCode-WordBreakByTrieAndStack
 *         链接：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 *         来源：力扣（LeetCode）
 *         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *         运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, val) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2  );
 *
        * cache.put(1,1);
        * cache.put(2,2);
        * cache.get(1);       // 返回  1
        * cache.put(3,3);    // 该操作会使得关键字 2 作废
        * cache.get(2);       // 返回 -1 (未找到)
        * cache.put(4,4);    // 该操作会使得关键字 1 作废
        * cache.get(1);       // 返回 -1 (未找到)
        * cache.get(3);       // 返回  3
        * cache.get(4);       // 返回  4

 *
 *
 */
public class LRUCache2 {
    /**
     * 双向链表节点
     */
    static class  DLinkedNode<K,V> {
        K key;
        V value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}

        public DLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class MyCache<K, V> {
        //hashMap当然还是要用封装好的,key是用户的key,value是存储的节点，用户的value存在该结点中
        private Map<K, DLinkedNode<K,V>> cache = new HashMap<>();

        private int size;//当前元素数量
        private int capacity;//容量
        private DLinkedNode<K,V> HEAD, TAIL;//链表的首尾节点，伪头部和伪尾部节点


        /**
         * 构造时，做必要的初始化
         * @param capacity
         */
        public MyCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            HEAD = new DLinkedNode<K,V>();
            TAIL = new DLinkedNode<K,V>();
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
        }

        public V get(K key) {
            DLinkedNode<K,V> node = cache.get(key);
            if (node == null) {
                return null;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        /**
         * 插入k-v
         * @param key
         * @param value
         */
        public void put(K key, V value) {

            //先通过哈希表定位，确定是否有这个节点
            DLinkedNode<K,V> node = cache.get(key);

            if (node == null) {// 如果 key 不存在，创建一个新的节点

                DLinkedNode<K,V> newNode = new DLinkedNode<>(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部，头部表示最近使用的
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode<K,V> tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            }
            else {
                // 如果 key 存在，修改 val，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        /**
         * 以下是私有方法
         */

        /**
         * 将节点插入到链表头部
         * @param node
         */
        private void addToHead(DLinkedNode<K,V> node) {
            node.prev = HEAD;
            node.next = HEAD.next;

            HEAD.next.prev = node;
            HEAD.next = node;
        }

        /**
         * 删除节点
         * @param node
         */
        private void removeNode(DLinkedNode<K,V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * 移动到头部
         * @param node
         */
        private void moveToHead(DLinkedNode<K,V> node) {
            removeNode(node);
            addToHead(node);
        }

        /**
         * 删除尾节点
         * @return
         */
        private DLinkedNode<K,V> removeTail() {
            DLinkedNode<K,V> res = TAIL.prev;
            removeNode(res);
            return res;
        }

    }

}

