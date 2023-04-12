package Subarray;

public class SubarrayLargerThanZero {

    public boolean hasSubarray(int[] nums, int k) {
        int n = nums.length;
        // ' test '

        int sum = 0;
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        if (sum >= 0) {
            return true;
        }

        int prev = 0;
        for (int i = k; i < n; i++) {
            sum += nums[i];

            prev += nums[i - k];
            minSum = Math.min(minSum, prev);

            if (sum >= minSum) {
                return true;
            }
        }
        return false;
    }
}
