package GinkgoStack.P12_Math.BasedAlgorithm;

public class GCDandLCM {
    public int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    /**
     * 最小公倍数LCM
     * a，b两个数的乘积，然后除以GCD即可;
     * 有些场景a*b容易溢出，所以不能直接乘，得先计算a/GCD，然后再乘以b.
     * @param a
     * @param b
     * @return
     */
    public int lcm(int a, int b) {
        int g = gcd(a,b);
        return a/g*b;
    }
}
