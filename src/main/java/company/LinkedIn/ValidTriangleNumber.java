package company.LinkedIn;

import java.util.Arrays;

// 2019-12-30 22:55
public class ValidTriangleNumber {
    // a + b > c
    // c should be max
    // we can lock c pointer, and calculate the a,b range
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;

        int n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            int a = 0, b = i - 1;

            while(a < b) {
                if (nums[a] + nums[b] > nums[i]) {
                    count += b - a;
                    b--;
                } else {
                    a++;
                }
            }

        }

        return count;
    }


}
