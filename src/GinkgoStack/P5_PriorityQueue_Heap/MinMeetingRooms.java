package GinkgoStack.P5_PriorityQueue_Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 示例 1:
 *
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 */
public class MinMeetingRooms {

    /**
     * 思路：
     * 1.根据会议开始时间将所有会议排序；
     * 2.建立一个最小堆，按照会议结束时间；
     * 3.将第一个会议的结束时间加入到堆中，表示需要开启一个新房间
     * 4.接下来迭代已经排序的会议：
     *          如果当前会议A的开始时间晚于 最早结束的那个会议B(既堆顶)的结束时间，那么它们可以公用一个房间，因此将B可以弹出去了；
     *          将A结束时间加入到堆中；
     * 5.迭代结束后，堆的元素数量就等于需要占用的房间数量
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // P7_Sort the intervals by start time
        //根据会议开始时间将所有会议排序
        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    public int compare(final int[] a, final int[] b) {
                        return a[0] - b[0];
                    }
                });

        // Min heap
        //建立一个最小堆，按照会议结束时间；堆的元素数量就等于需要占用的房间数量
        PriorityQueue<Integer> allocator =
                new PriorityQueue<Integer>(
                        intervals.length,
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                return a - b;
                            }
                        });


        // Add the first meeting
        //将第一个会议的结束时间加入到堆中
        allocator.add(intervals[0][1]);

        // Iterate over remaining intervals
        //接下来挨个迭代每个会议，注意，已经按照会议开始时间排序了
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            //如果当前会议A的开始时间晚于 最早结束的那个会议B的结束时间，那么它们可以公用一个房间，因此将B可以弹出去了
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            //前面的第一种情况：是需要将新的会议的结束时间加入到堆中的；
            //第二种情况：如果A的开始时间早于B的结束时间，(注意因为现在会议已经按照开始时间有序，因此A的开始时间实际在B的会议期间内，是冲突的)，
            //           因此需要另外使用一个新的房间，也得将其结束时间加入到堆中；
            //所以两种情况都需要将结束时间加入到堆中
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        //堆的元素数量就等于需要占用的房间数量
        return allocator.size();
    }

}
