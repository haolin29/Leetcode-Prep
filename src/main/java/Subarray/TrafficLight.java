package Subarray;

public class TrafficLight {

    public int[] flipLights(String[] lights) {
        int[] nums = new int[lights.length];

        // 找到和最大的区间
        for (int i = 0; i < lights.length; i++) {
            if (lights[i].equals("R")) {
                nums[i] = 1;
            } else {
                nums[i] = -1;
            }
        }

        int[] res = new int[2];
        int maxSoFar = 0;
        int maxEndingHere = 0;
        int start = 0, end = 0, nextStart = 0;

        for (int i = 0; i < nums.length; i++) {
            maxEndingHere += nums[i];

            if (maxEndingHere < 0) {
                if (i < nums.length - 1) {
                    nextStart = i + 1;
                }

                maxEndingHere = 0;
            } else if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                end = i;
                start = nextStart;
            }
        }

        res[0] = start;
        res[1] = end;

        return res;
    }
}
