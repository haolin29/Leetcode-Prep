package Other;

import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {
    class Node {
        int x;
        int y;
        int num;

        public Node(final int x, final int y, final int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.num - n2.num);

        int max = Integer.MIN_VALUE;
        int range = Integer.MAX_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            pq.offer(new Node(i, 0, nums.get(i).get(0)));

            max = Math.max(max, nums.get(i).get(0));
        }

        int start = -1;
        int end = -1;
        while (pq.size() == nums.size()) {
            Node cur = pq.poll();

            if (max - cur.num < range) {
                range = max - cur.num;

                start = cur.num;
                end = max;
            }

            if (cur.y + 1 < nums.get(cur.x).size()) {
                int next = nums.get(cur.x).get(cur.y + 1);
                pq.offer(new Node(cur.x, cur.y + 1, next));

                max = Math.max(max, next);
            }
        }

        return new int[]{start, end};
    }
}
