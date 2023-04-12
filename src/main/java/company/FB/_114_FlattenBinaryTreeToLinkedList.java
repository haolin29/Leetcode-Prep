package company.FB;

import base.TreeNode;

public class _114_FlattenBinaryTreeToLinkedList {

    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        if (prev != null) {
            prev.right = root;
            prev.left = null;
        }

        prev = root;
        flatten(root.left);
        flatten(right);
    }
}
