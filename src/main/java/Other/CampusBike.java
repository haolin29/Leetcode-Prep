package Other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class CampusBike {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // pq each worker pair with each bike based on manhattan dist
        // use visited array for worker and bike
        // skip the visited worker and bike and assign unused bike for worker

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a1, a2) -> {
            if (a1[0] == a2[0]) {
                if (a1[1] == a2[1]) {
                    return a1[2] - a2[2];
                } else {
                    return a1[1] - a2[1];
                }
            } else {
                return a1[0] - a2[0];
            }
        });

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = getManhattan(workers[i], bikes[j]);
                pq.offer(new int[]{dist, i, j});
            }
        }

        int n = workers.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Set<Integer> visitedBikes = new HashSet<>();

        while (visitedBikes.size() < n) {
            int[] cur = pq.poll();

            if (result[cur[1]] == -1 && !visitedBikes.contains(cur[2])) {
                result[cur[1]] = cur[2];
                visitedBikes.add(cur[2]);
            }
        }

        return result;

    }

    private int getManhattan(final int[] worker, final int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
