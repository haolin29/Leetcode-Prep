package company.LinkedIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import base.TreeNode;

public class _272_ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // find all predecessor and successor to stack in reverse order

        Stack<TreeNode> pre = new Stack<>();
        Stack<TreeNode> suc = new Stack<>();

        TreeNode cur = root;
        while(cur != null) {
            if (cur.val >= target) {
                suc.push(cur);
                cur = cur.left;
            } else {
                pre.push(cur);
                cur = cur.right;
            }
        }

        List<Integer> res = new ArrayList<>();
        while (k-- > 0) {
            if (pre.empty() && suc.empty()) {
                break;
            }

            if (pre.empty()) {
                res.add(getSuccessor(suc));
            } else if (suc.empty()) {
                res.add(getPredecessor(pre));
            } else if (suc.peek().val - target > target - pre.peek().val) {
                res.add(getPredecessor(pre));
            } else {
                res.add(getSuccessor(suc));
            }
        }

        return res;
    }

    private Integer getPredecessor(Stack<TreeNode> pre) {
        TreeNode top = pre.pop();
        TreeNode cur = top.left;

        while (cur != null) {
            pre.push(cur);
            cur = cur.right;
        }
        return top.val;
    }

    private Integer getSuccessor(Stack<TreeNode> suc) {
        TreeNode top = suc.pop();
        TreeNode cur = top.right;

        while (cur != null) {
            suc.push(cur);
            cur = cur.left;
        }
        return top.val;
    }
}
