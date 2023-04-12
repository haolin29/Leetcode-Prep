package dp;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    Map<String, Integer> map = new HashMap<>();
    public int findTargetSumWays(int[] nums, int S) {
        return dfs(nums, S, 0, 0);
    }

    private int dfs(int[] nums, int S, int pos, int sum) {
        String key = pos + "+" + sum;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (pos == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        }

        int add = dfs(nums, S, pos + 1, sum + 1);
        int minus = dfs(nums, S, pos + 1, sum - 1);

        map.put(key, add + minus);

        return add + minus;
    }


}
