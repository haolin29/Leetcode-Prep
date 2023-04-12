package dp;


// {1, 101, 2, 3, 100, 4, 5},
// then output should be 106 (1 + 2 + 3 + 100)
public class MaximalSumIncreasingSubsequence {

    public static int find(int[] nums) {
        // current idx as end, the maximum sum
        int[] dp = new int[nums.length];

//        int max = Integer.MIN_VALUE;

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int max = 0;

            for (int j = 0; j < i; j++) {
                // increasing seq && max sum
                if (dp[j] > max && nums[j] < nums[i]) {
                    max = dp[j];
                }
            }

            dp[i] = nums[i] + max;

            if (dp[i] > res) {
                res = dp[i];
            }
        }

        return res;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 101, 2, 3, 100, 4, 5};
        System.out.println(find(arr));

        int[] arr1 = new int[]{10, 5, 4, 3};
        System.out.println(find(arr1));
    }
}
