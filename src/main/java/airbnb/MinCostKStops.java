package airbnb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MinCostKStops {

    // bfs with prune
    public int minCost(int n, int[][] flights, int src, int dst, int k) {
        // a -> b, cost
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        // directed graph
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];

            map.putIfAbsent(from, new HashMap<>());
            map.putIfAbsent(to, new HashMap<>());

            map.get(from).put(to, flight[2]);
        }

        int cost = Integer.MAX_VALUE;
        // cur node, cost
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0});
        int stops = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] node = q.poll();
                int cur = node[0];
                int curCost = node[1];

                if (cur == dst) {
                    // find the smallest path in end
                    cost = Math.min(cost, curCost);
                }

                for (Map.Entry<Integer, Integer> entry : map.get(cur).entrySet()) {
                    // prune important
                    if (entry.getValue() + curCost > cost) continue;

                    q.offer(new int[]{entry.getKey(), entry.getValue() + curCost});
                }
            }

            if (stops++ > k) {
                break;
            }

        }

        return cost == Integer.MAX_VALUE ? -1 : cost;

    }
}
