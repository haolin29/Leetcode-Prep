package TwoPointers;

public class _704_BinarySearch {

    // Input: nums = [-1,0,3,5,9,12], target = 9
    //Output: 4

    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int i = 0, j = nums.length;
        // left close, right open

        while (i < j) {
            int m = i + (j - i) / 2;

            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                j = m;
            }
        }

        return -1;
    }
}
