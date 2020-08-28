package GinkgoStack.P24_Enterprise.Tencent;


/**
 *
 */
public class GrayCode {
    /**
     * 链接：https://www.nowcoder.com/questionTerminal/50959b5325c94079a391538c04267e15
     * 来源：牛客网
     *
     * //方法一：递归
     * //递归的思路就是n位gray码是由n-1位gray码生成，举个例子简单一些：
     * //比如求n=3的gray码，首先知道n=2的gray码是(00,01,11,10)
     * //那么n=3的gray码其实就是对n=2的gray码首位添加0或1生成的，
     * 添加0后变成(000,001,011,010)
     * //添加1后需要顺序反向就变成(110,111,101,100)
     * //组合在一起就是(000,001,011,010,110,111,101,100)
     */



    public String[] getGray(int n) {
        String[] resStrs = null;
        if(n == 1){
            resStrs = new String[]{"0","1"};
        }else{
            String[] strs = getGray(n-1);

            resStrs = new String[2*strs.length];
            for(int i=0; i<strs.length; i++){
                resStrs[i] = "0"+strs[i];
                resStrs[resStrs.length-1-i] = "1"+strs[i];
            }
        }
        return resStrs;
    }
}
