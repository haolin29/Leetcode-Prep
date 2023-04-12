package company.FB;

public class _162_FindPeakElement {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l + 1 < r) {
            int m = (l + r) / 2;

            if (nums[m] < nums[m + 1]) {
                l = m;
            } else if (nums[m] > nums[m + 1]) {
                r = m;
            }
        }

        return nums[l] > nums[r] ? l : r;
    }

    public int findPeakElement_huahua(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (nums[m] > nums[m + 1]) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return l;
    }
}
