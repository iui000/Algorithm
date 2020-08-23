package GinkgoStack.P11_Tree.Trie;

/**
 * 字典树的实现
 * 来源于百度百科
 */
public class Trie
{
    private int SIZE=26;//26个字母
    private TrieNode root;//字典树的根

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

        TrieNode()
        {
            num=1;
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
    public String hasPrefix(String prefix)
    {
        if (prefix == null || prefix.length() == 0)
        {
            return null;
        }
        TrieNode node = root;

        char[] letters = prefix.toCharArray();

        //下面的遍历过程就是在确认树中存在该前缀
        for (int i = 0, len = prefix.length(); i < len; i++)
        {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null)
            {
                return null;
            }
            else
            {
                node = node.son[pos];
            }
        }

        //根据该前缀，以根节点开始
        preTraverse(node, prefix);
        return null;
    }

    /**
     * 遍历经过此节点的单词.
     * @param node
     * @param prefix
     */
    public void preTraverse(TrieNode node, String prefix)
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
            return;
        }
        System.out.println(prefix);
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

    public static void main(String[]args)
    {
        Trie tree = new Trie();

        String[] strs = {"banana","band","bee","absolute","acm",};
        String[] prefix= {"ba","b","band","abc",};

        //把所有单词加到字典树中
        for(String str : strs)
        {
            tree.insert(str);
        }

        System.out.println(tree.has("abc"));

        tree.preTraverse(tree.getRoot());

        System.out.println();

        //CutDownTree.printAllWords();
        for(String pre:prefix)
        {
            int num = tree.countPrefix(pre);
            System.out.println(pre+""+num);
        }
    }
}
