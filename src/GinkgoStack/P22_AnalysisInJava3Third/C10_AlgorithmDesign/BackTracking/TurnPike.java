package GinkgoStack.P22_AnalysisInJava3Third.C10_AlgorithmDesign.BackTracking;

import java.util.*;

/**
 * @author 李亚林
 * 参考了书上的伪代码
 * 收费公路重建
 * 给定一组距离值，表示某一组点集（n个点）在一维坐标轴X上两两之间的水平距离，第一个点所处位置是x1 = 0,
 * 目标是根据这一组距离值，判断这一组距离集合是否存在一组点与之匹配，如果存在，那么找到一组与之匹配的某一组点集。
 */
public class TurnPike {
    class DistSet{
        SortedMap<Integer,Integer> distMap;

        public DistSet(int[] dist) {
            int len = dist.length;
            distMap = new TreeMap<>();

            for(int i = 0;i<len;i++){
                distMap.putIfAbsent(dist[i],0);
                distMap.put(dist[i],distMap.get(dist[i])+1);
            }
        }

        public int findMax(){
            return distMap.lastKey();
        }

        public int deleteMax(){
            int maxKey = distMap.lastKey();
            distMap.remove(maxKey);
            return maxKey;
        }


        public boolean contains(int key){
            return distMap.containsKey(key);
        }

        public int remove(int key){
            if(distMap.containsKey(key)){
                int count = distMap.get(key);
                if(count <= 1){
                    distMap.remove(key);
                }else {
                    distMap.put(key,count-1);
                }
                return key;
            }else {
                return -1;
            }
        }

        public void insert(int key){
            distMap.putIfAbsent(key,0);
            distMap.put(key,distMap.get(key)+1);
        }

        public  boolean isEmpty(){
            return distMap.isEmpty();
        }

    }

    /**
     * 表示能否将处于X[left]与X[right]之间的某个X[index]设置为值location；
     * 判断标准是：location与点x[j] (其中1 <= j < left且right < j <= n)的距离dist是否都处在distMap中，注意相同的dist是有个数限制的。
     *
     * 如果能，那么就从DistSet中删除相应的dist,并将他们返回distList
     * 如果不能，那么返回null
     * @param x 点集合
     * @param d dist集合
     * @param location 准备要设置的某点的坐标
     * @param n 总点数
     * @param left 左边界
     * @param right 右边界
     * @return
     */
    private List<Integer> distCanRemoveFromMap(int[] x,DistSet d,int location,int n,int left,int right){
        List<Integer> distList = new ArrayList<>();
        boolean allExist = true;

        for(int j = 1;j<left;j++){//判断左侧
            if(d.remove(Math.abs(location - x[j])) == -1){//如果出现删除失败,就直接结束，之后再将已经删除的dist重新插入回去
                allExist = false;
                break;
            }else {
                distList.add(Math.abs(location - x[j]));
            }
        }
        if(allExist){//再判断右侧
            for(int j = right+1;j<=n;j++){
                if(d.remove(Math.abs(location-x[j])) == -1){
                    allExist = false;
                    break;
                }else {
                    distList.add(Math.abs(location - x[j]));
                }
            }
        }

        if(allExist){
            return distList;
        }else {//删除失败时，得将删除的还原，并返回空
            for(Integer dist:distList){
                d.insert(dist);
            }
            return null;
        }
    }


    /**
     *
     * @param x  存储各点坐标
     * @param dist 假设目前已经是非递减有序的
     * @param n 点的数量
     * @return
     */
    public boolean turnPike(int[] x,int[] dist,int n){
        int len  = dist.length;
        if(len != n*(n-1)/2){
            return false;
        }

        DistSet d = new DistSet(dist);

        x[1] = 0;//x1==0
        x[n] = d.deleteMax();//最后一个即为当前的最大距离值
        x[n-1] = d.deleteMax();

        if(d.contains(x[n] - x[n-1])){
            d.remove(x[n] - x[n-1]);
            return place(x,d,n,2,n-2);
        } else{//
            return false;
        }

    }

    /**
     * 回溯算法
     */
    private boolean place(int[] x,DistSet d,int n, int left,int right){
        boolean found = false;
        if(d.isEmpty()){
            return true;
        }

        int dmax = d.findMax();

        /**
         * 先判断此xRight与其他点的距离关系是否符合要求,如果符合要求，就返回可以删除的dist
         */
        int xRight = dmax;
        List<Integer> distListCanRemove = distCanRemoveFromMap(x,d,xRight,n,left,right);
        if(distListCanRemove != null){
            /**
             * 如果符合要求，然后将x[right]设置为xRight,去试探可不可行
             */
            x[right] = xRight;

            //删除这些dist元素，因为distCanRemoveFromMap里面已经删除过了，所以这里注释掉
//            for(Integer dist:distListCanRemove){
//                d.remove(dist);
//            }
            //接着去判断余下的dist是否可行，right-1
            found = place(x,d,n,left,right-1);
            if(!found){//如果余下的不可行，那得回溯，将原来删除的dist重新插入回去
                //把原来删除的dist值，重新插入回去
                for(Integer dist:distListCanRemove){
                    d.insert(dist);
                }
            }
        }

        /**
         * 如果第一次试探失败，那就尝试将x[left]设置为先x[n] - dmax，看看是否可行
         * 先判断xLeft与其他点的距离关系是否符合要求
         */
        int xLeft = x[n] - dmax;
        distListCanRemove = distCanRemoveFromMap(x,d,xLeft,n,left,right);
        if(!found && distListCanRemove != null){
            /**
             * 如果符合要求，然后将x[left]设置为xLeft,去试探可不可行
             */
            x[left] = xLeft;
            //删除这些dist元素,因为distCanRemoveFromMap里面已经删除过了，所以这里注释掉
//            for(Integer dist : distListCanRemove){
//                d.remove(dist);
//            }
            found = place(x,d,n,left+1,right);
            if(!found){//如果余下的不可行，那得回溯，将原来删除的dist重新插入回去
                //把原来删除的dist值，重新插入回去
                for(Integer dist : distListCanRemove){
                    d.insert(dist);
                }
            }
        }

        return found;
    }

    public static void main(String[] args) {
        int[] dist = new int[]{1,2,2,2,3,3,3,4,5,5,5,6,7,8,10};
        int n = 6;
        int[] points = new int[n+1];

        TurnPike pike = new TurnPike();

        System.out.println(pike.turnPike(points,dist,n));
        System.out.println("--------------");
        for (int i = 1;i<=n;i++){
            System.out.print(points[i]+ " ");
        }

    }

}
