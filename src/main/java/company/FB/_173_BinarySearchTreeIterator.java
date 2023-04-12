package company.FB;

import java.util.Stack;

import base.TreeNode;

public class _173_BinarySearchTreeIterator {

    Stack<TreeNode> stack = new Stack<>();
    public _173_BinarySearchTreeIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = stack.pop();
        int res = cur.val;

//        if (cur.right != null) {
//
//        }
        cur = cur.right;

        while(cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
