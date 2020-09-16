package GinkgoStack.P15_DataStruct;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */
public class MaxQueueOffer59II {

    /**
     * 思路类似最小栈
     *
     * 用两个队列，其中一个辅助队列是单调递减的双端队列
     * c++代码
     * class MaxQueue {
     *     queue<int> q;
     *     deque<int> d;
     * public:
     *     MaxQueue() {
     *     }
     *
     *     int max_value() {
     *         if (d.empty())
     *             return -1;
     *         return d.front();
     *     }
     *
     *     void push_back(int value) {
     *         while (!d.empty() && d.back() < value) {
     *             d.pop_back();
     *         }
     *         d.push_back(value);
     *         q.push(value);
     *     }
     *
     *     int pop_front() {
     *         if (q.empty())
     *             return -1;
     *         int ans = q.front();
     *         if (ans == d.front()) {
     *             d.pop_front();
     *         }
     *         q.pop();
     *         return ans;
     *     }
     * };
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/solution/mian-shi-ti-59-ii-dui-lie-de-zui-da-zhi-by-leetcod/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    //java改写也容易，暂时懒得写
//    class MaxQueue {
//
//
//
//        public MaxQueue() {
//
//        }
//
//        public int max_value() {
//
//        }
//
//        public void push_back(int value) {
//
//        }
//
//        public int pop_front() {
//
//        }
//    }

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */

}
