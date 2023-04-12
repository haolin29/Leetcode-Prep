package airbnb;

public class FindMedianInLargeFile {

    public double findMedian(int[] nums) {
        int len = nums.length;

        if (len % 2 == 0) {
            return (helper(nums, Integer.MIN_VALUE, Integer.MAX_VALUE, len / 2)
                    + helper(nums, Integer.MIN_VALUE, Integer.MAX_VALUE, len / 2 + 1)) / 2.0;
        } else {
            return helper(nums, Integer.MIN_VALUE, Integer.MAX_VALUE, len / 2 + 1);
        }
    }

    private double helper(final int[] nums, long l, long r, int k) {
        if (l >= r) return l;

        long mid = l;
        long guess = l + (r - l) / 2;
        int count = 0;

        for (int num : nums) {
            if (num <= guess) {
                count++;
                mid = Math.max(mid, num);
            }
        }

        if (k == count) {
            return mid;
        } else if (count < k) {
            return helper(nums, guess + 1, r, k);
        } else {
            return helper(nums, l, guess, k);
        }

    }

    public static void main(String[] args) {
        int[] nums = {-100,-1,0,0,0,0,0};

        FindMedianInLargeFile test = new FindMedianInLargeFile();
        System.out.println(test.findMedian(nums));

    }
}
