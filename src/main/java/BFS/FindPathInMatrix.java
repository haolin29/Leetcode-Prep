package BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindPathInMatrix {

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Node other) {
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + x;
            hash = 31 * hash + y;
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null)
                return false;
            if (this.getClass() != o.getClass())
                return false;
            Node other = (Node) o;
            return x == other.x && y == other.y;
        }
    }

    int[] dx = {
            0,
            1,
            -1,
            0
    };
    int[] dy = {
            1,
            0,
            0,
            -1
    };

    public List<Node> find(boolean[][] m, Node start, Node end) {
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        Map<Node, Node> prev = new HashMap<>();

        boolean found = false;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (boundaryCheck(nx, ny, m) && m[nx][ny]) {
                    Node next = new Node(nx, ny);

                    if (!prev.containsKey(next)) {
                        prev.put(next, cur);
                        q.offer(next);
                        if (next.isSame(end)) {
                            found = true;
                        }
                    }
                }
            }

            if (found) {
                break;
            }
        }

        List<Node> res = new ArrayList<>();

        if (!prev.containsKey(end))
            return res;

        res.add(end);
        Node cur = end;
        while (!cur.isSame(start)) {
            res.add(prev.get(cur));
            cur = prev.get(cur);
        }

        Collections.reverse(res);
        return res;
    }

    private boolean boundaryCheck(int x, int y, boolean[][] m) {
        return x >= 0 && y >= 0 && x < m.length && y < m[0].length;
    }

}
