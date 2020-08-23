package GinkgoStack.P24_Enterprise.BaiDuBestCoder;

import java.util.Scanner;

/**
 * 本题包含若干组测试数据。
 *
 * 第一行两个整数n，m,表示有n个怪兽，m种技能。
 *
 * 接下来n行，每行两个整数，a[i],b[i]，分别表示怪兽的生命值和防御力。
 *
 * 再接下来m行，每行两个整数k[i]和p[i]，分别表示技能的消耗晶石数目和技能的伤害值。
 *
 * 数据范围:
 *
 * 1<=n<=100000
 *
 * 1<=m<=1000
 *
 * 1<=a[i]<=1000
 *
 * 0<=b[i]<=10
 *
 * 0<=k[i]<=100000
 *
 * 0<=p[i]<=1000
 *
 *
 * Output
 * 对于每组测试数据，输出最小的晶石消耗数量，如果不能击败所有的怪兽，输出-1
 *
 * Sample Input
 * 1 2      //第一行两个整数n，m,表示有n个怪兽，m种技能。
 *
 * 3 5      //a[i],b[i]，分别表示怪兽的生命值和防御力。
 * 7 10     //k[i]和p[i]，分别表示技能的消耗晶石数目和技能的伤害值。
 * 6 8
 * -------------
 * 1 2
 *
 * 3 5      //a[i],b[i]，分别表示怪兽的生命值和防御力。
 * 10 7     //k[i]和p[i]，分别表示技能的消耗晶石数目和技能的伤害值。
 * 8 6
 * Sample Output
 * Copy
 * 6
 * 18
 */

/**
 * 这道题开始以为是背包问题，但是解决不了，估计是用回溯法
 */
public class BeatMonster {

    class StoneProfile implements Comparable<Double>{
        private int stones;
        private int damage;
        private int life;
        private Double value;

        public StoneProfile(int stones, int damege, int life) {
            this.stones = stones;
            this.damage = damage;
            this.life = life;
        }

        public int getStones() {
            return stones;
        }

        public void setStones(int stones) {
            this.stones = stones;
        }

        public double getValue(){
            value = 1.0/(double)life*1.0/damage*1.0*stones;
           return  value;
        }

        @Override
        public int compareTo(Double o) {
            return o.compareTo(value);
        }
    }

    private int beatMonster(int theLifeValue, int defense ,int[] stone,  int[] damage){
        int res = 0;
        int n = stone.length;//多少个技能
        if(n == 0 ){//如果没有技能
            return -1;
        }
        int maxK = Integer.MIN_VALUE;
        int maxP = Integer.MIN_VALUE;
        for(int i =0;i<n;i++){
            if(stone[i]>maxK){
                maxK = stone[i];//找出哪个技能消耗精石最多
            }
            if(damage[i] > maxP){//找出哪个技能的伤害最高
                maxP = damage[i];
            }
        }
        if(maxP <= defense){
            return -1;
        }
        boolean oneKill = true;
        int oneKillMinStones = Integer.MAX_VALUE;
        int[] actualDamage = new int[n+1];//每个技能的实际伤害,就是减去防御值，相当于物品大小
        int numActual = 1;
        StoneProfile[] actualProfits = new StoneProfile[n+1];//每个技能的实际收益，伤害值/石头
        int[] actualStones = new int[n+1];
        for(int i=0;i<n;i++){
            if(damage[i]>defense){
                actualDamage[numActual] = damage[i] - defense;
                actualStones[numActual] = stone[i];
//                actualProfits[numActual] = (double) actualDamage[numActual]*1.0/actualStones[i]*1.0;//用的石头越少,那么价值就越大
                actualProfits[numActual] = new StoneProfile(stone[i],actualDamage[numActual],theLifeValue);

                //如果存在某个技能的实际伤害小于怪兽血量，那就不一定选择一次技能杀掉它
                if(actualDamage[numActual] < theLifeValue && actualDamage[numActual] > 0){
                    oneKill = false;
                }
                if(actualDamage[numActual] >= theLifeValue){
                    oneKillMinStones = stone[i] < oneKillMinStones?stone[i]:oneKillMinStones;
                }
                numActual++;
            }

        }

        if(oneKill){//如果所有技能的实际伤害都高于这个怪兽的血量，那么只能一个技能杀掉，可以直接选择消耗石头最少的那个技能
            return oneKillMinStones;
        }else {

            int remainingCapacity = 0;
            double theValueIfSelected;
            double theValueNotSelected;

            int M = theLifeValue+maxP-defense-1;//因为必须要把怪兽打死，所以背包大小应该要扩充，大于怪兽的血量加上单个技能最高伤害
            double[] dp = new double[M+1];
//            Arrays.fill(dp,Integer.MAX_VALUE);
            int[][] mo = new int[numActual][M+1];
            int[][] er = new int[numActual][M+1];
            for(int i = 1;i<numActual;i++){//每个物品

                //请注意！！，这里必须从前往后，并且只需要更新那些j大于当前物品size的位置，这个过程中，某些物品会被重复选到
                //如果当前的背包大小 大于等于该物品的大小，才有机会选择这个物品（技能）

                for(int j = actualDamage[i];j<=M;j++){
                    /**
                     * 背包当前大小j，
                     * 如果偷这个物品，则最大的价值就是：物品的价值 + 剩余容量能够装下的最大价值；
                     * 如果不偷这个物品，则最大价值就是前面所有物品在j容量下能获得的最大价值
                     */
                    remainingCapacity =  j - actualDamage[i];//剩余容量
                    theValueIfSelected = actualProfits[i].getValue() + dp[remainingCapacity];

                    theValueNotSelected = dp[j];//旧的dp[j]表示不偷这个物品能获得的最大价值，也就是容量总和在不大于j的情况下(可选物品是前面i-1个物品)能获得的最大价值
                    //如果用了这个技能收益更大，那么就用，否则就不用
//                    if(theValueIfSelected > theValueNotSelected){
//                        mo[j] = actualStones[i] + mo[remainingCapacity];
//                    }else {
//                        mo[j] = mo[j];
//                    }
                    if(theValueIfSelected > theValueNotSelected){
                        mo[i][j] = actualStones[i];
                        dp[j] =  theValueIfSelected;
                    }else {
                        dp[j] = theValueNotSelected;
                    }


                }

                //每一轮dp过后，都得记住选择了哪些技能，这样才能统计出最终实际使用了哪些技能
            }

            int count = 0,index = 0;//表示选了多少个技能
            //倒着把选择了的物品找出来
            res = 0;
            index = theLifeValue;
            for(int num = numActual-1;num >=1 ;num--){
                while (mo[num][index] != 0){//说明最终的结果选择了第num个技能
                    System.out.println("选择了："+actualDamage[num]);
                    res += actualStones[num];
                    index = index - actualDamage[num];
                }
            }
            //注意，这里取怪兽血量值的dp[m]
//            res = count * (maxK+ 1) - dp[theLifeValue];
//            System.out.println("实际最小消耗："+res);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BeatMonster beatMonster = new BeatMonster();
//        boolean hasNextTest = false;
//        if(scanner.hasNextInt()){
//            hasNextTest = true;
//        }else {
//            hasNextTest = false;
//        }
        while (scanner.hasNextInt()){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int i = 0;
            int j = 0;
            int res = 0;
            int[] lifeValue = new int[n];
            int[] defenseValue = new int[n];

            //2个技能
            int[] stone = new int[m];
            int[] damage = new int[m];
            while (i<n){//存储怪兽
                lifeValue[i] = scanner.nextInt();
                defenseValue[i] = scanner.nextInt();
                i++;
            }
            while (j<m){//存储技能
                stone[j] = scanner.nextInt();
                damage[j] = scanner.nextInt();
                j++;
            }
            int k = 0;
            //计算每一个怪兽消耗的最小石头数目
            for(k = 0;k<n;k++){
                int killOne = beatMonster.beatMonster(lifeValue[k],defenseValue[k],stone,damage);
                if(killOne == -1){
                    System.out.println(-1);
                    break;
                }else {
                    res += killOne;
                }
            }
            if(k == n){//说明全部怪兽都可以消灭
                System.out.println(res);
            }
//            if(!scanner.hasNextInt()){
//                hasNextTest = false;
//            }
        }
        scanner.close();
    }
}
