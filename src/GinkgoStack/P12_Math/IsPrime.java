package GinkgoStack.P12_Math;

public class IsPrime {
    //是否素数
    private static boolean isPrime(int n){
        if(n<2){
            return false;
        }
        int t = (int) Math.sqrt(n);
        for(int i = 2;i<t;i++){
            if(n%i == 0){//如果一个数，不是它平方根以内的数的倍数，那它就是素数
                return false;
            }
        }

        return true;
    }
}
