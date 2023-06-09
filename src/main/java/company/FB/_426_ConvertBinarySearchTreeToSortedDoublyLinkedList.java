package company.FB;

public class _426_ConvertBinarySearchTreeToSortedDoublyLinkedList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node dummy = new Node(0, null, null);

        prev = dummy;
        helper(root);

        prev.right = dummy.right;
        dummy.right.left = prev;

        return dummy.right;
    }

    private void helper(Node root) {
        if (root == null) {
            return;
        }

        helper(root.left);
        root.left = prev;
        prev.right = root;
        prev = root;
        helper(root.right);
    }
}
