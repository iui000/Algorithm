package GinkgoStack.P7_Sort.MergeSort;

public class MergeSort {
    /*-------------------------------------------------------------------*/
    //归并排序
    public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h)
            return new int[] { nums[l] };

        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(nums, l, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }

    //--------------------------------------------------
    //李阳的写法
    private int[] aux;

    public void mergeSort(int[] nums) {
        int len = nums.length;
        aux = new int[len];
        sort(nums, 0, len - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    public void  merge(int[] nums, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = nums[i];
        }
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (j > high) {
                nums[k] = aux[i++];
            }else if (i > mid) {
                nums[k] = aux[j++];
            }else if (aux[i] > aux[j]){
                nums[k] = aux[j++];
            }else {
                nums[k] = aux[i++];
            }
        }
    }
}
