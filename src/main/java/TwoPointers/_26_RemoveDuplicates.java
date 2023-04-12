package TwoPointers;

public class _26_RemoveDuplicates {

    int removeDuplicates(int[] nums){
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 0;

        while (fast < nums.length) {
            // 发现不相等的才是需要保留的值
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return slow + 1;
    }

}
