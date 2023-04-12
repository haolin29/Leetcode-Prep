package company.TwoSigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SewingSystem {

    public static void main(String[] args) {
        int[] parent = {-1, 0, 1, 2};
        int[] nums = {1, 4, 3, 4};
        System.out.println("----------");
        SewingSystem test = new SewingSystem();
        System.out.println(test.minDiff(parent, nums));

        System.out.println("----------");
        List<Integer> parent1 = Arrays.asList(-1, 0, 1, 2);
        List<Integer> nums1 = Arrays.asList(1, 4, 3, 4);
        System.out.println(drainagePartition(parent1, nums1));

        List<Integer> parent2 = Arrays.asList(-1, 0, 0, 0);
        List<Integer> nums2 = Arrays.asList(10, 11, 10, 10);
        System.out.println(drainagePartition(parent2, nums2));
    }

    public static int drainagePartition(List<Integer> parent, List<Integer> inputs) {
        // build a tree using hashmap
        // p: children
        Map<Integer, List<Integer>> childrenMap = new HashMap<>();

        for (int i = 0; i < parent.size(); i++) {
            // skip root
            if (parent.get(i) == -1) {
                continue;
            }

            childrenMap.putIfAbsent(parent.get(i),  new ArrayList<>());
            childrenMap.get(parent.get(i)).add(i);
        }

        //build map to record the sum of water that flows through the node
        Map<Integer, Integer> waterMap = new HashMap<>();

        //go through all nodes using dfs and sum up the water
        int totalSum = dfs(waterMap, childrenMap, inputs, 0);

        int result = Integer.MAX_VALUE;

        // iterate all nodes to find cutting point
        for (int node : waterMap.keySet()) {
            // skip root
            if (node == 0) {
                continue;
            }

            //diff = abs(water of childNodes - (totalSum - sumWater of childNode))
            //compare all and get the minimum
            result = Math.min(result, Math.abs(totalSum - waterMap.get(node) * 2));
        }
        return result;
    }

    private static int dfs(Map<Integer, Integer> waterMap, Map<Integer, List<Integer>> childrenMap, List<Integer> inputs, int curNodeIdx) {
        int sum = inputs.get(curNodeIdx);

        // leaf node, stop searching
        if (!childrenMap.containsKey(curNodeIdx)) {
            waterMap.put(curNodeIdx, sum);
            return sum;
        }

        //sum up all water that flows into the node
        for (int child : childrenMap.get(curNodeIdx)) {
            sum += dfs(waterMap, childrenMap, inputs, child);
        }

        waterMap.put(curNodeIdx, sum);
        return sum;
    }

    private int min = Integer.MAX_VALUE;
    private TreeNode root = null;

    class TreeNode {
        int val = 0;
        int sum = 0;
        List<TreeNode> children = new ArrayList<>();
        public TreeNode() {

        }
        public TreeNode(int val, int sum, List<TreeNode> children) {
            this.val = val;
            this.sum = sum;
            this.children = children;
        }
    }

    public int minDiff(int[] parents, int[] nums) {
        if (parents == null || parents.length == 0
                || nums == null || nums.length == 0) {
            return 0;
        }

        buildTree(parents, nums);
        if (root.children.size() == 0) {
            throw new IllegalArgumentException("NOT ABLE TO CUT SINCE ONLY ONE NODE");
        }

        subTreeSum(root);
        findMinCut(root);

        return this.min;
    }

    private List<TreeNode> initial(int size) {
        List<TreeNode> treeNodes = new ArrayList<>();
        while (size-- > 0) {
            treeNodes.add(new TreeNode());
        }
        return treeNodes;
    }

    private void buildTree(int[] parents, int[] nums) {
        List<TreeNode> treeNodes = initial(nums.length);
        for (int i = 0; i < parents.length; i++) {
            TreeNode cur = treeNodes.get(i);
            cur.val = nums[i];
            if (parents[i] == -1) {
                root = cur;
                continue;
            }

            TreeNode parent = treeNodes.get(parents[i]);
            parent.children.add(cur);
        }
    }

    //traverse tree, calculate subtree sum
    private void subTreeSum(TreeNode node) {
        if (node == null)
            return;

        int sum = 0;
        for (TreeNode next : node.children) {
            subTreeSum(next);
            sum += next.sum;
        }

        node.sum = sum + node.val;
        System.out.println("node sum:" + node.sum);
    }

    private void findMinCut(TreeNode node) {
        if (node == null) {
            return;
        }

        for (TreeNode next : node.children) {
            findMinCut(next);

//            if (node == root) continue;
            min = Math.min(min, Math.abs(root.sum - next.sum * 2));
            System.out.println("min:" + min + " = " + root.sum + " - " + next.sum * 2);
        }
    }


}
