package array;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class _1_TwoSum {

    // Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int remain = target - num;
            if (map.containsKey(remain)) {
                return new int[]{map.get(remain), i};
            } else {
                map.put(num, i);
            }
        }

        return new int[0];
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2,7,11,15;9",
            "3,2,4;6",
            "3,3;6",
    }, delimiter = ';')
    void testTwoSum(String input, int target) {
        int[] nums = toIntArray(input.split(","));
        int[] actual = new _1_TwoSum().twoSum(nums, target);
        System.out.println(actual[0] + ", " + actual[1]);
    }

    private int[] toIntArray(String[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }
}
