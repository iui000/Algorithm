package GinkgoStack.P22_AnalysisInJava3Third.C1;

import java.util.Comparator;

public class CaseInsensitiveCompare implements Comparator<String> {
    public int compare(String lhs, String rhs){
        return lhs.compareToIgnoreCase(rhs);
    }
}
