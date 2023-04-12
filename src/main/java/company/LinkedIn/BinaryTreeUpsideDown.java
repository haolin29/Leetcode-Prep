package company.LinkedIn;

import base.TreeNode;

public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // base case: null or leaf node, just return
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);

        TreeNode left = root.left;
        left.left = root.right;
        left.right = root;

        root.left = null;
        root.right = null;

        return newRoot;
    }
}
