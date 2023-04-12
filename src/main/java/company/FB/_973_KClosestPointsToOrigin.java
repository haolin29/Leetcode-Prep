package company.FB;

import java.util.Arrays;

public class _973_KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        int l = 0;
        int r = points.length - 1;

        while (l < r) {
            int m = partition(points, l, r);

            if (m == K) {
                break;
            } else if (m < K) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return Arrays.copyOfRange(points, 0, K);

    }

    private int partition(int[][] points, int l, int r) {
        int[] pivot = points[l];

        while (l < r) {
            while (l < r && compare(points[r], pivot) >= 0) {
                r--;
            }

            points[l] = points[r];

            while (l < r && compare(points[l], pivot) <= 0) {
                l++;
            }

            points[r] = points[l];
        }

        points[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - (p2[0] * p2[0] + p2[1] * p2[1]) ;
    }
}
