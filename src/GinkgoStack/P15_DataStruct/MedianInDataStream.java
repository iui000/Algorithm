package GinkgoStack.P15_DataStruct;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 */
public class MedianInDataStream {
    /**
     *
     * 这道题我自己一下子就想到大顶堆和小顶堆了，看了题解后不谋而合
     * 作者：jyd
     *  链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/mian-shi-ti-41-shu-ju-liu-zhong-de-zhong-wei-shu-y/
     *         来源：力扣（LeetCode）
     *         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class MedianFinder {

        //如果奇数个元素，后半段多一个元素
        Queue<Integer> AfterHalf, FirstHalf;
        public MedianFinder() {
            AfterHalf = new PriorityQueue<>(); // 小顶堆，保存较大的一半
            FirstHalf = new PriorityQueue<>((x, y) -> (y - x)); // 大顶堆，保存较小的一半
        }

        public void addNum(int num) {
            if(AfterHalf.size() != FirstHalf.size()) {//元素不等时，先插入后半段，然后将最小元素弹出来，放到前半段
                AfterHalf.add(num);
                FirstHalf.add(AfterHalf.poll());
            } else {//如果元素数量想等时，先插入前半边，然后将最大元素弹出来，插入到后半边
                FirstHalf.add(num);
                AfterHalf.add(FirstHalf.poll());
            }
        }


        public double findMedian() {
            return AfterHalf.size() != FirstHalf.size() ?
                    AfterHalf.peek() :  //n奇数
                    (AfterHalf.peek() + FirstHalf.peek()) / 2.0;//n偶数
        }
    }
}




