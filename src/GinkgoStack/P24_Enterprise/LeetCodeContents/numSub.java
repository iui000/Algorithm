package GinkgoStack.P24_Enterprise.LeetCodeContents;

public class numSub {
    public static int numSub(String s) {
        char[] arr = s.toCharArray();
        long res = 0;
        long count = 0;
        long mo = 1000000000+7;
        for(char c:arr){
            if(c == '1'){
                count++;
            }else {
                if(count > 0){
                    res += count*(count+1)/2;
                }
                count = 0;
            }
        }
        if(count >0){
            res += count*(count+1)/2;
        }

        return (int)(res % mo);
    }

    public static void main(String[] args) {
        String s= "0110111";
        System.out.println(numSub(s));
    }
}
