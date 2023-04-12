package company.LinkedIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2019-12-29 17:20
// LC 47
public class PermutationsII {

    // backtracking
    // reduce duplicates: use index

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        boolean[] seen = new boolean[nums.length];
        dfs(nums, res, new ArrayList<>(), seen);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] seen) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // second time see the same num
            // Same number can be only used once at each depth.
            if(seen[i] || i != 0 && seen[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }

            path.add(nums[i]);
            seen[i] = true;
            dfs(nums, res, path, seen);
            path.remove(path.size() - 1);
            seen[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};

        PermutationsII test = new PermutationsII();
        List<List<Integer>> res = test.permuteUnique(nums);
        for (List<Integer> list :res) {
            System.out.println(list);
        }

    }

}
