package GinkgoStack.P7_Sort;

import java.util.Arrays;
import java.util.Comparator;


/**
 * 老虎笔试题
 *
 * 还有小bug，继续优化
 *
 * 有一条路线，有1000个站点
 * 现在给出n组人：
 *         int[][] group = new int[][]{
 *                 {2,1,4},//人数，上车点，下车点
 *                 {5,3,5},
 *                 {4,2,6}
 *         };
 *
 * 车的容量为capacity
 * 问，能最少派多少辆车，把这些乘客从起点送到他们的目的终点
 */
public class MinCars {

    class Car{
        int cap = 0;//剩余容量
        int[] down = new int[1001];//表示这一辆车在1~1000的每个位置需要需下去的人数

        public Car(int cap) {
            this.cap = cap;
        }
    }

    private  int minCar(int[][] group,int cap){
        int ans = 0;

        //先排序
        Arrays.sort(group, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] != o2[1]?o1[1] - o2[1]:o1[2] - o2[2];
            }
        });
        boolean end = false;
        int startGroup = 0;
        while (!end){
            Car car = new Car(cap);//派一辆车
            end = true;
            for(int i = startGroup;i<group.length;i++){

                int upIndex = group[i][1];//上车点
                int downIndex = group[i][2];//下车点

                /**
                 * 先下后上
                 */
                if(car.down[upIndex] > 0){//这里有下车的人
                    car.cap += car.down[upIndex];
                    car.down[upIndex] = 0;
                }

                if(car.cap > 0){
                    if(car.cap >= group[i][0]){//这些人全部上车
                        car.cap -= group[i][0];
                        //记录这辆车在这个下车点需要下去多少人
                        car.down[downIndex] += group[i][0];
                        group[i][0] = 0;
                    }else {//否则,就只上来一部分人
                        end = false;//说明还剩余人没有上车，得再派车辆
                        group[i][0] -= car.cap;
                        car.cap = 0;//然后车车没有剩余容量了

                        //记住这第一组剩下的人，下一辆车就直接从这一组人开始，提高效率
                        startGroup = startGroup>0? startGroup:i;
                    }
                }
            }

            ans++;
        }

        return ans;
    }


    public static void main(String[] args) {
        int[][] group = new int[][]{
                {2,1,4},
//                {5,3,5},
                {4,2,6}
        };

        MinCars demo = new MinCars();

        System.out.println(demo.minCar(group,5));
    }
}
