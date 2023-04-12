package company.FB;

import base.TreeNode;

public class _285_InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        if (root == null) {
//            return null;
//        }

        TreeNode res = null;

        while(root != null) {
            if (root.val > p.val) {
                res = p;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return res;
    }
}
