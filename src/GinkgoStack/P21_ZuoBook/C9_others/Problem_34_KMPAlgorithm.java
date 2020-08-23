package GinkgoStack.P21_ZuoBook.C9_others;

public class Problem_34_KMPAlgorithm {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (next[mi] == -1) {
				si++;
			} else {
				mi = next[mi];
			}
		}
		return mi == ms.length ? si - mi : -1;
	}

    //计算nextArray,有动态规划的思想，next[i]表示0 ~ i-1的前缀与后组最长匹配长度
    public static int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }

        //它和模式的长度是一样的
        int[] next = new int[ms.length];
        next[0] = -1;//ms[0]之前没有字符，就是-1
        next[1] = 0;//前缀不能包含ms[i-1],后缀不能包含ms[0],因此为0

        int pos = 2;//从第二个位置开始计算，表示当前位置
        int cn = 0;//前缀的最后一个字符的位置下标


        while(pos < next.length){
            if(ms[pos -1] == ms[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{//cn == 0
                next[pos++] = 0;
            }
        }
        return next;
    }


	public static void main(String[] args) {
		String str = "abcabcababaccc";
		String match = "ababa";
		System.out.println(getIndexOf(str, match));//应该输出6


        String str1 = "abcabcababacccsrgesrgsegehrthrttyds48dtfthbdrth47yjtergsertgnhdrtnh8383883";
        String match1 = "47y";
        System.out.println(getIndexOf(str1, match1));//应该输出47
	}

}
