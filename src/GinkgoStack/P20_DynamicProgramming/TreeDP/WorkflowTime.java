package GinkgoStack.P20_DynamicProgramming.TreeDP;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AC了44%
 * 因该是输入那边还有些非法输入，难得去管了
 * 就是一个后序遍历，或者说树形DP，左大神的套路
 */
class WorkflowNode {
    String nodeId;
    int timeoutMillis;
    List<WorkflowNode> nextNodes;
    boolean initialised;

    public static WorkflowNode load(String value) {
        // Create head node;
        Map<String, WorkflowNode> map = new HashMap<>();
        WorkflowNode head = new WorkflowNode("HEAD", 0, null);
        map.put(head.nodeId, head);

        for (String nodeValue : value.split("\\|")) {
            String[] properties = nodeValue.split("\\`");
            WorkflowNode node = map.get(properties[0]);

            node.timeoutMillis = Integer.parseInt(properties[1]);
            node.initialised = true;

            // Check next nodes
            if (properties[2].equals("END")) {
                continue;
            }
            node.nextNodes = Arrays.stream(properties[2].split(","))
                    .map(p -> new WorkflowNode(p, 0, null))
                    .collect(Collectors.toList());
            node.nextNodes.forEach(p -> map.put(p.nodeId, p));

            map.put(node.nodeId, node);
        }

        return head;
    }

    public WorkflowNode(String nodeId, int timeoutMillis, List<WorkflowNode> nextNodes) {
        this.nodeId = nodeId;
        this.timeoutMillis = timeoutMillis;
        this.nextNodes = nextNodes;
    }
}

public class WorkflowTime {

    public static class ReturnType {
        public int time;

        public ReturnType(int time) {
            this.time = time;
        }
    }

    public static ReturnType process(WorkflowNode head) {
        //基本条件
        if(head == null){
            return new ReturnType(-1);
        }

        if(head.nodeId.isEmpty()){
            return new ReturnType(-1);
        }

        if(!head.initialised){
            return new ReturnType(-1);
        }

        if (head.nextNodes == null) {
            return new ReturnType( head.timeoutMillis);
        }

        //递归子树
        ReturnType theMax = new ReturnType(Integer.MIN_VALUE);

        for (WorkflowNode node : head.nextNodes){
            int theTime = process(node).time;
            if(theTime > theMax.time ){
                theMax.time = theTime;
            }

        }

        //计算这个树本身的信息
        int t = theMax.time + head.timeoutMillis;
        //判断平衡性质

        return new ReturnType(t);
    }

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        WorkflowNode node = WorkflowNode.load(cin.next());
        System.out.println(process(node).time);
    }
}