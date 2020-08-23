package GinkgoStack.P1_ArrayProblem;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class FindKthLargest {

    /**
     * 基于快排
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestByQuickSort(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int partition(int[] a,int low,int high){
        int pivot = a[low];
        while (low < high){
            while (low < high && a[high] >= pivot) --high;
            a[low] = a[high];
            while (low < high && a[low] <= pivot) ++low;
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }

    private int quickSelect(int[] a, int low, int high, int targetIndex){
        int p = partition(a,low,high);
        if(p == targetIndex){
            return a[p];
        }
        else {
            return p < targetIndex ? quickSelect(a,p+1,high,targetIndex):quickSelect(a,low,p-1,targetIndex);
        }
    }

    /**
     * 基于堆排序
     * @param nums
     * @param k
     * @return
     */

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        if(k > len || k < 1){
            return -1;
        }
        Heap heap = new Heap(len);
        for(int i = 0;i<len;i++){
            heap.insert(nums[i]);
        }
        for(int j = 0;j<k;j++){
            res = heap.delete();
        }

        return res;
    }

    /**
     * 大顶堆
     */
    public class Heap{
        private static final int DEFAULT_CAPACITY = 10;

        private int currentSize; // Number of elements in heap
        private int[] array; // The heap array

        /**
         * Construct the binary heap.
         */
        public Heap()
        {
            this( DEFAULT_CAPACITY );
        }

        /**
         * Construct the binary heap.
         * @param capacity the capacity of the binary heap.
         */
        public Heap( int capacity )
        {
            currentSize = 0;
            array = new  int[capacity + 1];
        }

        public void insert(int value){
            int hole = ++currentSize;

            for(array[0] = value;array[hole/2] < value;hole = hole/2){
                array[hole] = array[hole/2];
            }
            array[hole] = value;
        }

        public int delete(){
            if(currentSize == 0){
                return -1;
            }
            int max = array[1];
            percolateDown(1);
            return max;
        }

        public void percolateDown(int hole){

            int last = array[currentSize--];
            array[hole] = last;
            int child = 0;
            for(;hole * 2 <= currentSize;hole=child){
                child = hole * 2;//左儿子
                if( child != currentSize && array[child + 1] > array[child]){
                    child++;
                }
                if(last < array[child]){
                    array[ hole ] = array[ child ];//空穴往下落
                }
                else {
                    break;
                }
            }
            array[hole] = last;
        }
    }
}
