package GinkgoStack.P22_AnalysisInJava3Third.C1;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestProgram {
    SortedMap<String,String> sortedMap = new TreeMap<>();

    public static <AnyType>
    AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp){
        int maxIndex = 0;
        for(int i= 1;i < arr.length;i++){
            if(cmp.compare(arr[i],arr[maxIndex])>0){
                maxIndex = i;
            }
        }

        return arr[maxIndex];
    }

    public static void main(String[] Args){
        String[] arr = {"ZEBRA","alligator","crocodile"};
        System.out.println(findMax(arr,new CaseInsensitiveCompare()));


        BigDecimal a =new BigDecimal("4.0");
        System.out.println("a values is:"+a);

        BigDecimal b =new BigDecimal("4.00");
        System.out.println("b values is:"+b);

        int RESULT = a.compareTo(b);
        System.out.println(RESULT);



    }
}
