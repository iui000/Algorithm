package GinkgoStack.P20_DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WordBreakByTrieAndStack {
    public class Trie
    {
        private int SIZE=26;//26个字母
        private TrieNode root;//字典树的根
        private int height;

        public Trie() //初始化字典树
        {
            root = new TrieNode();
        }

        public class TrieNode //字典树节点
        {
            private int num;//有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
            private TrieNode[]  son;//所有的儿子节点
            private boolean isEnd;//是不是最后一个节点
            private char val;//节点的值
            private boolean haveSon;
            private int level;

            TrieNode()
            {
                num=1;
                level = 0;
                son=new TrieNode[SIZE];
                isEnd=false;
                haveSon=false;
            }

            public TrieNode[] getSon() {
                return son;
            }

            public void setEnd(boolean end) {
                isEnd = end;
            }

            public boolean isEnd() {
                return isEnd;
            }

            public int getNum() {
                return num;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }
        }

        public int getHeight() {
            return height;
        }

        //建立字典树
        public void insert(String str) //在字典树中插入一个单词
        {
            if(str==null||str.length()==0)
            {
                return;
            }

            TrieNode node = root;

            char[] letters = str.toCharArray();

            //挨着遍历该单词的每一个字母
            for(int i=0,len=str.length(); i<len; i++)
            {
                int pos = letters[i] - 'a';//获取它的位置，等于ASCII值的差
                if(node.son[pos] == null)//如果没有该字符，则加到儿子中
                {
                    node.haveSon = true;
                    node.son[pos]= new TrieNode();
                    node.son[pos].val = letters[i];
                    node.son[pos].level = node.level + 1;
                    if(node.son[pos].level > height){
                        height = node.son[pos].level;
                    }
                }
                else
                {
                    node.son[pos].num++;//出现次数加1
                }
                node = node.son[pos];//更新当前节点为该儿子
            }
            node.isEnd = true;//最后节点的标志位置位

        }

        /**
         * 计算包含此前缀的单词数量
         * 和构建树的遍历过程类似
         * 目标是要找到最后一个字母上的频率数值，即为以此为前缀的单词数量
         */
        public int countPrefix(String prefix)
        {
            if(prefix==null||prefix.length()==0)
            {
                return-1;
            }

            TrieNode node=root;

            char[] letters = prefix.toCharArray();
            for(int i=0,len=prefix.length(); i<len; i++)
            {
                int pos=letters[i]-'a';
                if(node.son[pos] == null)//此时发现了有一个字母不在树中，那么说明，该前缀不匹配
                {
                    return 0;
                }
                else
                {
                    node = node.son[pos];
                }
            }
            return node.num;
        }

        /**
         * 打印指定前缀的单词
         * @param prefix
         * @return
         */
        public boolean hasPrefix(String prefix)
        {
            if (prefix == null || prefix.length() == 0)
            {
                return false;
            }
            TrieNode node = root;

            char[] letters = prefix.toCharArray();

            //下面的遍历过程就是在确认树中存在该前缀
            for (int i = 0, len = prefix.length(); i < len; i++)
            {
                int pos = letters[i] - 'a';
                if (node.son[pos] == null)
                {
                    return false;
                }
                else
                {
                    node = node.son[pos];
                }
            }

            //根据该前缀，以根节点开始
            return true;
        }

        /**
         * 打印指定前缀的单词
         * @param prefix
         * @return
         */
        public boolean  hasWordContainsPrefix(String prefix)
        {
            if (prefix == null || prefix.length() == 0)
            {
                return false;
            }
            TrieNode node = root;

            char[] letters = prefix.toCharArray();

            //下面的遍历过程就是在确认树中存在该前缀
            for (int i = 0, len = prefix.length(); i < len; i++)
            {
                int pos = letters[i] - 'a';
                if (node.son[pos] == null)
                {
                    return false;
                }
                else
                {
                    node = node.son[pos];
                }
            }

            //根据该前缀，以根节点开始
//            String KK = preTraverse(node, prefix);
//            System.out.println(KK);

            return true;
        }

        /**
         * 遍历经过此前缀的单词.
         * @param node
         * @param prefix
         */
        public String preTraverse(TrieNode node, String prefix)
        {
            if (node.haveSon)
            {
                for (TrieNode child : node.son)
                {
                    if (child != null)
                    {
                        preTraverse(child, prefix + child.val);
                    }
                }
//                return null;
            }

//            System.out.println(prefix);
            return prefix;
        }

        /**
         * 在字典树中查找一个完全匹配的单词.
         * @param str
         * @return
         */
        public boolean has(String str)
        {
            if(str==null||str.length()==0)
            {
                return false;
            }
            TrieNode node=root;
            char[] letters = str.toCharArray();
            for(int i=0,len=str.length(); i<len; i++)
            {
                int pos = letters[i]-'a';
                if(node.son[pos]!=null)
                {
                    node=node.son[pos];
                }
                else
                {
                    return false;
                }
            }
            return node.isEnd;
        }

        /**
         * 前序遍历字典树.
         * 递归实现
         * @param node 指定的根节点
         */
        public void preTraverse(TrieNode node)
        {
            if(node!=null)
            {
                System.out.print("("+node.val+":"+ node.isEnd+")"+"-");
                for(TrieNode child : node.son)
                {
                    preTraverse(child);
                }
            }
        }

        public TrieNode getRoot()
        {
            return this.root;
        }


        /**
         * 在字典树中查找该字符串会有哪些前缀在字典树中，并将他们的位置入栈，直到某一个字母不匹配位置
         * @param str
         * @return
         */
        public boolean canBreak(String str,TrieNode root,Trie trie)
        {
            if(str==null||str.length()==0)
            {
                return false;
            }

            TrieNode node = root;
            char[] letters = str.toCharArray();
            Stack<DivPoint> stack = new Stack<>();
            int left = 0;
            int start = 0;//&& (!stack.isEmpty())
            for(int i=0,len = str.length(); i<len; )
            {
                int pos = letters[i]-'a';
                if(node.getSon()[pos] != null)//存在这个儿子
                {
                    node = node.getSon()[pos];
                    if(node.isEnd()){//通过其数量就可以判断是不是找到了一个单词，接下去继续找
                        DivPoint aDivPoint = new DivPoint(i,start);
                        stack.push(aDivPoint);
                    }else {
                        if(i == len-1){
                            //说明到目前为止，这个子串并不是字典数中的单词
                            //如果是与当前分割点的start不同的分割点直接弹出，直到一个与自己start相同的分割点
                            while ( stack.peek().getStart() != start ){
                                DivPoint invalidPoint = stack.pop();//说明不需要再从这个分割点之后去判断剩下的字符串了

                                start = invalidPoint.getStart();
                                if(stack.isEmpty()){
                                    return false;
                                }
                            }
                            i = stack.peek().getIndex();//取出上一个分割点

                            node = root;
                            start = stack.peek().getIndex() + 1;
                        }

                    }
                    i++;
                }
                else
                {
                    if(i == 0){//如果第一个字母就不能匹配，自然不能拆分
                        return  false;
                    }

                    boolean f = false;
//                    System.out.println(root.getLevel());
                    if( i == len -1){//如果是已经走到最后一个位置，那么，如果字典树中没有以此字母结尾的单词，那么必然整个字符串必然不能拆分

                        for(int j = i; j >=0 && i-j < root.getLevel();j--){
                            String temp = str.substring(j, i+1);
                            if(trie.has(temp)){//是否有该单词
                                f = true;
                                break;
                            }
//                        System.out.println(str.substring(j, i+1));
                        }

                    }else {
                        for(int j = i; j >=0 && i-j < root.getLevel();j--){
                            String temp = str.substring(j, i+1);
                            if(trie.hasWordContainsPrefix(temp)){//是否存在以此为前缀的单词
                                f = true;
                                break;
                            }
//                        System.out.println(str.substring(j, i+1));
                        }
                    }

                    if(!f){
                        return false;
                    }

                    if(stack.isEmpty()){//如果找不到第一个能匹配的单词，当然就不能拆分了
                        return false;
                    }

                    if(!node.isEnd()){
                        //说明到目前为止，这个子串并不是字典数中的单词
                        //如果是与当前分割点的type不同的分割点直接弹出，直到一个与自己type相同的分割点
                        while ( stack.peek().getStart() != start ){
                            DivPoint invalidPoint = stack.pop();//说明不需要再从这个分割点之后去判断剩下的字符串了

                            start = invalidPoint.getStart();
                            if(stack.isEmpty()){
                                return false;
                            }
                        }

                        //如果退的位置与当前
                        if(i - stack.peek().getIndex() > root.getLevel()){
                            return false;
                        }

                        i = stack.peek().getIndex()+1;//取出上一个分割点
                    }
                    //否则呢，就是正常找到单词，将节点设置为根节点，接着查找
                    node = root;
                    start = stack.peek().getIndex() + 1;
                }
            }

            if(stack.peek().getIndex() == str.length() - 1){
                return true;
            }

            return false;
        }
    }

    public class DivPoint //字符串的分割点
    {
        private int index;
        private int start;//表示这些分割点是从同一个位置(原字符串的下标start)开始搜索，而找出来的新的分割点，这个分割点减去start的单词都有共同的前缀。

        public DivPoint(int index, int start) {
            this.index = index;
            this.start = start;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }
    }



    public boolean wordBreak(String s, List<String> wordDict) {
        Trie tree = new Trie();
        if(wordDict.isEmpty()){
            return false;
        }

        for(String str : wordDict)
        {
            tree.insert(str);
        }

        Trie.TrieNode root = tree.getRoot();
        root.setLevel(tree.getHeight());

        return tree.canBreak(s,root,tree);
    }

    public static void main(String[] args) {
//        String s= "catsandog";//false
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("cats");
//        wordDict.add("dog");
//        wordDict.add("sand");
//        wordDict.add("and");
//        wordDict.add("cat");


//        String s= "leetcode";//true
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("leet");
//        wordDict.add("code");


//        String s= "bb";//true
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("a");
//        wordDict.add("b");
//        wordDict.add("bbb");
//        wordDict.add("bbbb");

//        String s= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";//false
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("a");
//        wordDict.add("aa");
//        wordDict.add("aaa");
//        wordDict.add("aaaa");
//        wordDict.add("aaaaa");
//        wordDict.add("aaaaaa");
//        wordDict.add("aaaaaaa");
//        wordDict.add("aaaaaaaaa");
//        wordDict.add("aaaaaaaaaa");

//        "aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//                ["aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","ba"]

        String s= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";//false
        List<String> wordDict = new ArrayList<>();

        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        wordDict.add("ba");

        WordBreakByTrieAndStack wordBreakByTrieAndStack = new WordBreakByTrieAndStack();

        System.out.println(wordBreakByTrieAndStack.wordBreak(s,wordDict));

    }

}
