package GinkgoStack.P24_Enterprise.Tencent;
import java.util.*;


public class WeChatRedPacket {

    /**
     *
     */
    int getValue(int[] gifts, int n) {
        // write code here
        if(gifts.length <n) return 0;
        if(gifts.length ==0) return 0;

        int count=0,mainElement = 0;
        for(int i=0;i<n;i++)
        {
            if(count==0)
            {
                mainElement=gifts[i];
                count=1;
            }
            else{
                if(mainElement==gifts[i])
                    count++;
                else
                    count--;
            }
        }

        int size=0;
        for(int i=0;i<n;i++){
            if(mainElement==gifts[i])
                size++;
        }

        return (size>n/2)?mainElement:0;
    }

    public int getValueBySort(int[] gifts, int n) {
        Arrays.sort(gifts);
        int ans = gifts[n/2];
        int num = 0;
        for(int i = 0; i < gifts.length; i++) {
            if(gifts[i] == ans) {
                num++;
            }
        }
        return num <= n/2 ? 0 : ans;
    }


}
