package GinkgoStack.P7_Sort;

/**
 * 选择排序
 * 假设arr[0..i ] 之间已经排序
 * 在arr[i+1 .. n-1]之间找到一个小于arr[i]的值，进行交换
 *
 *
 */
public class selectSort<T extends Number> {
    private T[] arry;

    public T[] getArry() {
        return arry;
    }

    public void setArry(T[] arry) {
        this.arry = arry;
    }

    public T[] sortAsc(){
        int minIndex;
        for (int i = 0; i < arry.length -1; i++) {
            minIndex = i;
            for (int j = i+1; j < arry.length; j++) {
                if(arry[j].doubleValue()  < arry[minIndex].doubleValue()){
                    minIndex = j;
                }
            }

            //交换
            T tmp = arry[i];
            arry[i] = arry[minIndex];
            arry[minIndex] = tmp;
        }

        return arry;
    }

    public static void main(String[] args) {
        Long[] a = {1200L,52L,55555L};
        selectSort<Long> arry = new selectSort<Long>();
        arry.setArry(a);
        arry.sortAsc();

    }


}
