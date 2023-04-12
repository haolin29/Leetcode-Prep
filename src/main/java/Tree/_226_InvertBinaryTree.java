package Tree;

import base.TreeNode;

public class _226_InvertBinaryTree {

    //  invert the tree, and return its root.
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        invertHelper(root);

        return root;
    }

    private void invertHelper(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;

        invertHelper(root.left);
        invertHelper(root.right);
    }
}
