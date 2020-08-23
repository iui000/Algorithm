package GinkgoStack.P20_DynamicProgramming.TreeDP;

import java.util.ArrayList;


/**
 * 将一棵树分为多个组，每一组的节点数必须是偶数
 * 每个组只能有一个根节点
 *
 * 问最多分为多少组
 */
public class GroupingTree {

    public static class Node {
        public int value;
        public ArrayList<Node> sons;

        public Node(int value) {
            this.value = value;
            this.sons = new ArrayList<>();
        }
    }

    public static int grouping(Node head) {
        Info info =  process(head);
        return info.ok?info.groups:0;
    }

    public static class Info {
        public boolean ok;
        public int groups;
        public int sizes;

        public Info() {
        }

        public Info(boolean ok, int groups, int sizes) {
            this.ok = ok;
            this.groups = groups;
            this.sizes = sizes;
        }
    }

    public static Info process(Node head) {
        //叶子节点，基本条件
        if (head.sons == null || head.sons.size() == 0) {
            return new Info(false, 0,1);
        }

        //接下来根据子树的情况来决定怎么分组
        //计算这个树本身的信息
        Info myInfo = new Info();

        //递归子树
        int total = 0;
        for(Node node :head.sons){
            Info info = process(node);
            myInfo.groups += info.groups;
            total += info.sizes;
        }

        myInfo.sizes = total+1;
        if(myInfo.sizes % 2 != 0){//总数是奇数
            myInfo.ok = false;
            return myInfo;
        }

        /**
         * 否则总数是偶数，
         * 而且一定不可能每个子树都能分组，因为加上根节点后的总节点数是偶数
         * 所以，不能分组的那些子树的节点总和必然大于零，将它们与根节点分成一组，总和也必然是偶数，
         */
        myInfo.groups++;
        myInfo.ok = true;

        return myInfo;
    }

    public static void main(String[] args) {
        Node head = new Node(0);

        head.sons.add(new Node(1));
        head.sons.add(new Node(3));
        head.sons.add(new Node(2));

        head.sons.get(0).sons.add(new Node(7));
        head.sons.get(1).sons.add(new Node(4));


        head.sons.get(1).sons.get(0).sons.add(new Node(6));
        head.sons.get(1).sons.get(0).sons.add(new Node(5));

        System.out.println(grouping(head));

    }
}
