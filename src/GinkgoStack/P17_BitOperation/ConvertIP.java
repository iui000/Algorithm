package GinkgoStack.P17_BitOperation;

/**
 * 将一个int数，变成点分十进制ip
 */
public class ConvertIP {

    private static String getIpFrom(int x){
        StringBuilder ans = new StringBuilder();
        int c = 0xFF000000;//定义掩码
        for(int i = 0;i<4;i++){
            //掩码向右滑动三次，取出相应的8 bit，即x & (c >>> (8*i))
            //然后计算出这8位的十进制数，还是向右移动剩余的位数就行
            int p = (x & (c >>> (8*i))) >>> (8*(3-i));
            ans.append(p);
            if(i != 3)
                ans.append(".");
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.print(getIpFrom(236539874));
    }

}
