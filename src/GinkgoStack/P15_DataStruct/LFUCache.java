package GinkgoStack.P15_DataStruct;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LFUCache {

    // 缓存的节点信息
    // 节点的数据结构
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer freq; // 这个节点发生get或者set的次数总和
        public Node pre; // 节点之间是双向链表所以有上一个节点
        public Node next;// 节点之间是双向链表所以有下一个节点

        public Node(Integer key, Integer value, Integer freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    // 桶结构
    public static class NodeList {
        public Node HEAD; // 桶的头节点
        public Node TAIL; // 桶的尾节点

        public NodeList() {
            HEAD = new Node(-1,-1,-1);
            TAIL = new Node(-1,-1,-1);
            HEAD.next = TAIL;
            TAIL.pre = HEAD;
        }


        /**
         * 删除节点
         * @param node
         */
        private void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }



    private int minFreq, capacity;
    Map<Integer, Node> records;
    Map<Integer, LinkedList<Node>> freqMap;

    public LFUCache(int capacity) {
        this.minFreq = Integer.MAX_VALUE;
        this.capacity = capacity;

        this.records = new HashMap<>(capacity,0.75F);
        this.freqMap = new HashMap<>();
    }


    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (!records.containsKey(key)) {
            if(records.size() == capacity){
                //必须弹出一个node
                //通过 minFreq 拿到 freq_table[minFreq] 链表的末尾节点
                LinkedList<Node> curList =  freqMap.get(minFreq);
                curList.removeLast();
                if(curList.size() == 0){
                    freqMap.remove(minFreq);
                }
            }

            //插入到1的map中
            LinkedList<Node> curList =  freqMap.get(1);
            if(curList == null){
                curList = new LinkedList<>();
            }
            curList.addFirst(new Node(key,value,1));

            minFreq = 1;
        }else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node node = records.get(key);
            LinkedList<Node> curList =  freqMap.get(node.freq);
            // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
            if(curList.size() <= 1){
                freqMap.remove(node.freq);
                if (minFreq == node.freq)
                    minFreq += 1;
            }else {
                curList.remove(node);
            }

            node.freq++;
            node.value = value;//更新缓存的值

            // 插入到 freq + 1 的map中.链表的头部
            freqMap.get(node.freq).addFirst(node);
        }
    }

    public Integer get(int key) {
        if (capacity == 0)
            return -1;

        if (!records.containsKey(key)) {
            return null;
        }

        Node node = records.get(key);
        LinkedList<Node> curList =  freqMap.get(node.freq);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if(curList.size() <= 1){
            freqMap.remove(node.freq);
            if (minFreq == node.freq) minFreq += 1;
        }else {
            //这里可以用自己的链表来优化
            curList.remove(node);
        }

        node.freq++;

        // 插入到 freq + 1 的map中.链表的头部
        freqMap.get(node.freq).addFirst(node);
        return node.value;
    }





}
