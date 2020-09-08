package GinkgoStack.P15_DataStruct.LruCache;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现本题的两种操作，需要用到一个哈希表和一个双向链表。在面试中，面试官一般会期望读者能够自己实现一个简单的双向链表，
 * 而不是使用语言自带的、封装好的数据结构。
 * 在 Python 语言中，有一种结合了哈希表与双向链表的数据结构 OrderedDict，只需要短短的几行代码就可以完成本题。
 * 在 Java 语言中，同样有类似的数据结构 LinkedHashMap。
 * 这些做法都不会符合面试官的要求
 *
 * 作者：LeetCode-WordBreakByTrieAndStack
 * 链接：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * 关于LinkedHashMap，有篇帖子讲得不错：https://www.jianshu.com/p/8f4f58b4b8ab
 */
class LRUCacheUseLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheUseLinkedHashMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}