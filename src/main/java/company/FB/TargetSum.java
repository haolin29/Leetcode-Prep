package company.FB;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    public int find(int[] nums, int target) {

        Map<String, Integer> map = new HashMap<>();
        return dfs(nums, target, 0, 0, map);
    }

    private int dfs(final int[] nums, final int target, final int start, int sum, final Map<String, Integer> map) {
        String key = start + "->" + sum;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (start == nums.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }

        int cur = nums[start];
        int add = dfs(nums, target, start + 1, sum + 1, map);
        int minus = dfs(nums, target, start + 1, sum - 1, map);

        map.put(key, add + minus);

        return add + minus;

    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        TargetSum test = new TargetSum();
        System.out.println(test.find(nums, target));

    }
}
