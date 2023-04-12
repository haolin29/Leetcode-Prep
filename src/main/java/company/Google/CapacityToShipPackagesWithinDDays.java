package company.Google;

public class CapacityToShipPackagesWithinDDays {

    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = 0;

        for (int weigh : weights) {
            left = Math.max(left, weigh);
            right += weigh;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            int needDays = 1, curSum = 0;

            for (int w : weights) {
                if (curSum + w > mid) {
                    needDays++;
                    curSum = 0;
                }
                curSum += w;
            }

            if (needDays > D) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
