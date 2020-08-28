package GinkgoStack.P7_Search;

public class BinarySearch {


    /**
     * @Description: 找出最左侧的下标
     */
    public static int searchFloor(int[] arr, int tar){
        int l = 0, r = arr.length - 1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(arr[mid] >= tar)//关键
                r = mid - 1;
            else
                l = mid + 1;
        }

        if(l < arr.length && arr[l] == tar)
            return l;

        return -1;
    }

    /**
     * @Description: 找出最后一个符合的下标
     */
    public static int searchCeil(int[] arr, int tar){
        int l = 0, r = arr.length - 1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(arr[mid] <= tar)//关键
                l = mid + 1;
            else
                r = mid - 1;
        }

        if(r >= 0 && arr[r] == tar)
            return r;

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,5,5,6,7,8,8,8,9};
        System.out.println(searchCeil(arr, 5));
        System.out.println(searchFloor(arr, 5));
        System.out.println(searchFloor(arr, 1));
    }

}
