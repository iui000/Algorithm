package GinkgoStack.P2_StringProblem;

public class StringReverse {

    public void reverse(char[] s, int l, int r) {
        if (l >= r) return;
        char c = s[l];

        s[l++] = s[r];
        s[r--] = c;

        reverse(s, l, r);
    }

    public void reverseString(char[] s) {
        reverse(s, 0, s.length - 1);
    }

}


