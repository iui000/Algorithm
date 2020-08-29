package GinkgoStack.P15_DataStruct;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. 全 O(1) 的数据结构
 * 请你实现一个数据结构支持以下操作：
 *
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 *  
 *
 * 挑战：
 *
 * 你能够以 O(1) 的时间复杂度实现所有操作吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 真正做到左右操作时间复杂度为O(1)，[其实时间复杂度为O（1）,并不代表所用时间更快]，总体的空间复杂度为O（n）
 * HashMap + 双向链表 + 空间压缩 (多个key对应一个value,但是某一个key的value改变，并不会影响其他key的value)
 * @author 李亚林
 */
class AllOne {

    private Map<String,DNode> map;
    private DLinkList dlinkList;

    class DNode{
        int value;
        Set<String> keySet;
        DNode pre;
        DNode next;

        public DNode(String key,int value) {
            this.value = value;
            this.keySet = new HashSet<>();
            if(key != null && !key.isEmpty()){
                this.keySet.add(key);
            }
        }

        //将此节点插入到某个节点的前面
        public void insertFrontOf(DNode dNode){
            this.pre = dNode.pre;
            this.next = dNode;

            dNode.pre.next = this;
            dNode.pre = this;
        }

        //将此节点插入到某一个节点的后面
        public void insertAfter(DNode dNode){
            this.pre = dNode;
            this.next = dNode.next;

            dNode.next.pre = this;
            dNode.next = this;
        }

        public String toString(){
            return ""+this.value +":"+keySet.toString();
        }

    }

    class DLinkList{
        DNode Head;
        DNode Tail;

        public DLinkList() {
            this.Head = new DNode(null,1);
            this.Tail = new DNode(null,1);
            Head.next = Tail;
            Tail.pre = Head;
        }

        public DNode deleteDNode(DNode dNode){
            dNode.pre.next = dNode.next;
            dNode.next.pre = dNode.pre;
            return dNode;
        }

        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            DNode node = Head.next;
            while (node != Tail){
                stringBuilder.append(node.toString() + "\n");
                node = node.next;
            }
            return stringBuilder.toString();
        }
    }

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>(50);
        dlinkList = new DLinkList();
    }

    /** Inserts a new key <Key> with val 1. Or increments an existing key by 1. */
    public void inc(String key) {//往上爬一步梯子
        if(key == null || key.isEmpty()){
            return;
        }
        DNode currentNode;
        //map中含有此key
        if(map.containsKey(key)){
            DNode theNode = map.get(key);
            int targetValue = theNode.value+1;//目标值
            //如果相邻前节点的值 不等于 目标值,则建立一个新节点(提醒，节点中存的是value，及其对应的key集合)，并将其插入到原来节点的前一个位置
            if(theNode.pre.value != targetValue || theNode.pre == dlinkList.Head ){
                currentNode = new DNode(key,targetValue);
                currentNode.insertFrontOf(theNode);//将新的节点插入到原来节点之前
                map.put(key,currentNode);//将key与这个新的节点相关联
                theNode.keySet.remove(key);
            }
            //如果相邻前节点的值 等于 目标值，则直接将key与前节点关联，然后更新各自的计数值即可
            else {
                currentNode = theNode.pre;
                currentNode.keySet.add(key);
                map.put(key,currentNode);

                theNode.keySet.remove(key);
            }
            //如果原来节点计数值达到0，则表示没有key与之关联，则删除它
            if(theNode.keySet.isEmpty()){
                dlinkList.deleteDNode(theNode);
            }
        }
        //map中并没有此key,那就先看看当前value最小值是不是1,在决定是否建立新节点
        else {
            if(!map.isEmpty() && dlinkList.Tail.pre.value == 1){//如果是1，则不需要建立新节点，直接设置引用
                currentNode = dlinkList.Tail.pre;
                currentNode.keySet.add(key);
            }
            else {//如果不是1，那么就建立新节点，之后新的最小值就是1，更新伪尾节点
                currentNode = new DNode(key,1);
                currentNode.insertFrontOf(dlinkList.Tail);//将此节点插入到伪尾节点之后
            }

            map.put(key,currentNode);//将key和此节点关联
            //此时，要更新伪尾节点的内容
            dlinkList.Tail.value = dlinkList.Tail.pre.value;//这是当前的最小值
            dlinkList.Tail.keySet = dlinkList.Tail.pre.keySet;//最小值对应的key集合
        }

        //如果爬到了链表头部,那么就应该更新Head节点的内容，成为最大值
        if(currentNode.pre == dlinkList.Head){
            dlinkList.Head.keySet = currentNode.keySet;
            dlinkList.Head.value = currentNode.value;
        }
    }

    /** Decrements an existing key by 1. If Key's val is 1, remove it from the data structure. */
    public void dec(String key) {//在梯子中往下退一步，过程和前面的inc()类似
        if(key == null || key.isEmpty()){
            return;
        }
        DNode currentNode;
        //map中含有此key
        if(map.containsKey(key)){
            DNode theNode = map.get(key);
            if(theNode.value <= 1){//根据题意，如果在减1操作之前，此节点的value是1，则删除此key
                //在我们的数据结构中，不能粗暴地直接删除某个value节点，因为可能有其他key引用它
                if(theNode.keySet.size() > 1){

                    theNode.keySet.remove(key);
                    currentNode = theNode;
                }else {
                    currentNode = dlinkList.deleteDNode(theNode).pre;
                }
                map.put(key,null);
                map.remove(key);
                return;//然后返回
            }
            //说明theNode.value大于1
            int targetValue = theNode.value-1;//目标值
            //如果相邻后节点的值 不等于 目标值,则建立一个新节点(再次提醒，节点中存的是value,及其对应的key集合)，并将其插入到原来节点的后一个位置
            if(theNode.next.value != targetValue || theNode.next == dlinkList.Tail){
                theNode.keySet.remove(key);
                currentNode = new DNode(key,targetValue);
                currentNode.insertAfter(theNode);//将新的节点插入到原来节点之后
                map.put(key,currentNode);//将key与这个新的节点相关联
            }
            //如果相邻后节点的值 等于 目标值，则直接将key与后节点关联，然后更新各自的计数值即可
            else {
                theNode.keySet.remove(key);
                currentNode = theNode.next;
                currentNode.keySet.add(key);
                map.put(key,currentNode);
            }
            //如果原来节点计数值达到0，则表示没有key与之关联，则删除它
            if(theNode.keySet.isEmpty()){
                dlinkList.deleteDNode(theNode);
            }

            //最后，如果它退到了尾部，我们得设置下尾部的情况，得到最小值对应的key
            if(currentNode.next == dlinkList.Tail){
                dlinkList.Tail.keySet = currentNode.keySet;
                dlinkList.Tail.value = currentNode.value;
            }
        }

        //map中并没有此key,根据题意，则什么也不做
    }

    /** Returns one of the keys with maximal val. */
    public String getMaxKey() {
        if(dlinkList.Head.next.keySet.isEmpty()){
            return "";
        }
        return dlinkList.Head.next.keySet.iterator().next();
    }

    /** Returns one of the keys with Minimal val. */
    public String getMinKey() {
        if(dlinkList.Tail.pre.keySet.isEmpty()){
            return "";
        }
        return dlinkList.Tail.pre.keySet.iterator().next();
    }

    public String toString(){
        return "map:" +"\n"+ map.toString() +"\n" + "link:"+"\n"+dlinkList.toString();
    }


    public static void main(String[] args) {
        AllOne obj = new AllOne();

        obj.inc("hello");
        obj.inc("world");
        obj.inc("leet");
        obj.inc("code");
        obj.inc("DS");
        obj.inc("leet");

        System.out.println("maxKey:"+obj.getMaxKey());

        obj.inc("DS");
        obj.dec("leet");

        System.out.println("maxKey:"+obj.getMaxKey());

        obj.dec("DS");
        obj.inc("hello");

        System.out.println("maxKey:"+obj.getMaxKey());

        obj.inc("hello");
        obj.inc("hello");

        obj.dec("world");
        obj.dec("leet");
        obj.dec("code");
        obj.dec("DS");

        System.out.println("maxKey:"+obj.getMaxKey());

        obj.inc("new");
        obj.inc("new");
        obj.inc("new");
        obj.inc("new");
        obj.inc("new");
        obj.inc("new");

        System.out.println("maxKey:"+obj.getMaxKey());
        System.out.println("minKey:"+obj.getMinKey());

        //正确答案应该是
        //["leet","DS","hello","new","hello"]

//        System.out.println("================================");
//        System.out.println(obj.toString());
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
