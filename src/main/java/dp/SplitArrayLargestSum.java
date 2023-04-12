package dp;

public class SplitArrayLargestSum {
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum[i] = nums[i];
            } else {
                sum[i] = nums[i] + sum[i - 1];
            }
        }

        int[][] dp = new int[m + 1][n];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[1][i] = sum[i];
        }

        // dp[i][j]= min{max(dp[i - 1][k], sums(k + 1, j))}, 0 <= k <j
        for (int i = 2; i <= m; i++) {
            for (int j = i - 1; j < n; j++) {
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.min(Math.max(dp[i - 1][k], sum[j] - sum[k]), dp[i][j]);
                }
            }
        }

        return dp[m][n - 1];
    }


    int[] sum;
    int[][] dp;
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        sum = new int[n];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum[i] = nums[i];
            } else {
                sum[i] = nums[i] + sum[i - 1];
            }
        }


        dp = new int[m + 1][n];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        return helper(nums, n - 1, m);
    }

    // split nums[0] ~ nums[k] into m groups
    private int helper(int[] nums, int k, int m) {
        if (m == 1) return sum[k];
        if (m > k) return Integer.MAX_VALUE;

        if (dp[m][k] != Integer.MAX_VALUE) return dp[m][k];

        int res = 0;
        for (int i = 0; i < k; i++) {
            res = Math.min(res, Math.max(helper(nums, i, m - 1), sum[k] - sum[i]));
        }

        dp[k][m] = res;
        return res;
    }
}
