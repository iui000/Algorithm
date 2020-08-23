package GinkgoStack.P8_LinkedList;


/**
 * 138. 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的 深拷贝。
 *
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 */
public class CopyRandomList {

    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 题解的第三种方法
     * 将每个拷贝节点都放在原来对应节点的旁边。
     * 这种旧节点和新节点交错的方法让我们可以在不需要额外空间的情况下解决这个问题。
     *
     * 1.遍历原来的链表并拷贝每一个节点，将拷贝节点放在原来节点的旁边，创造出一个旧节点和新节点交错的链表。
     * 只是用了原来节点的值拷贝出新的节点。原节点 next 指向的都是新创造出来的节点。
     * cloned_node.next = original_node.next；
     * original_node.next = cloned_node；
     *
     * 2.迭代这个新旧节点交错的链表，并用旧节点的 random 指针去更新对应新节点的 random 指针。
     * 比方说， B 的 random 指针指向 A ，意味着 B' 的 random 指针指向 A' 。
     *
     * 3.现在 random 指针已经被赋值给正确的节点， next 指针也需要被正确赋值，
     * 以便将新的节点正确链接同时将旧节点重新正确链接。
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // 1.Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;//注意这里，更新新的ptr指针
        }

        ptr = head;//将ptr复原到头节点

        // 2.Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        //3. Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_new = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_new;
    }
}

