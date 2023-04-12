package company.FB;

public class FlattenMutliLevelList {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    public Node flatten(Node head) {
        if (head == null) return head;

        helper(head);
        return head;
    }

    // return tail of current level
    private Node helper(Node head) {
        // case 1 null
        if (head == null) return null;

        // case 2 no child, no next
        if (head.child == null) {
            if (head.next == null) {
                return head;
            } else {
                // case 3 no child has next
                return helper(head.next);
            }
        } else {
            // has child
            Node child = head.child;
            head.child = null;

            Node next = head.next;
            Node childTail = helper(child);

            head.next = child;
            child.prev = head;

            // case 5 : has next
            if (next != null) {
                childTail.next = next;
                next.prev = childTail;
                return helper(childTail);
            }

            // case 4 : no next
            return childTail;
        }
    }


}
