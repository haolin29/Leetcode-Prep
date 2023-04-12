package company.FB;

import java.util.Random;

public class RandomPickIndex {

    Random random = new Random();
    int[] nums;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
    }

    public int pickIndex(int target) {
        int count = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;

            if (random.nextInt(++count) == 0) {
                result = i;
            }
        }

        return result;
    }
}
