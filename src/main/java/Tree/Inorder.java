package Tree;

public class Inorder {
    class Node {
        Node left;
        Node right;
        Node parent;
    }

    public void traverse(Node root) {
        if (root == null) return;

        Node cur = root;
        while(cur != null) {
            while(cur != null) {
                cur = cur.left;
            }

            cur = cur.parent;
            // print left
            System.out.println(cur);
        }
    }
}
