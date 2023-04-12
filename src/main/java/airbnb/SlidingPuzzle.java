package airbnb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// find a shortest path between two status

// 需要打印操作步骤，上下左右这种
// 写个class，两个method: boolean isSolvable() 和
// List<String> solve(), 返回走法 "up" "down" "left" "right"
public class SlidingPuzzle {
    // current status -> expected status
    // compress board as a string

    private int[][] b;
    private int n;
    private int m;
    private String start = "";
    private String goal = "";

    public SlidingPuzzle(int[][] b) {
        this.b = b;
        this.n = b.length;
        this.m = b[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                start += b[i][j];
                goal += (i * m + j + 1) % (n * m);
            }
        }

        System.out.println("Start is " + start);
        System.out.println("Goal is " + goal);
    }

    public int canSolve() {
        // build graph, bfs find the shortest path
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        seen.add(start);
        q.add(start);

        int steps = 0;
        int[] dx = {0, 1, 0, -1, 0};

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String cur = q.poll();
                seen.add(cur);

                if (goal.equals(cur)) {
                    return steps;
                }

                int idx = cur.indexOf("0");
                int x = idx / m;
                int y = idx % m;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dx[i + 1];

                    // boundary check
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        int newPos = nx * m + ny;
                        String next = swap(cur, idx, newPos);

                        if (!seen.contains(next)) {
                            q.offer(next);
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public List<String> getSolution() {
        int[] dx = {0, 1, 0, -1, 0};
        String[] pathWord = { "Left", "Up", "Right", "Down" };

        // build graph, bfs find the shortest path
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        Queue<List<String>> pathQ = new LinkedList<>();
        seen.add(start);
        q.add(start);
        pathQ.offer(new ArrayList<>());


        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String cur = q.poll();
                List<String> curPath = pathQ.poll();
                seen.add(cur);

                if (goal.equals(cur)) {
                    return curPath;
                }

                int idx = cur.indexOf("0");
                int x = idx / m;
                int y = idx % m;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dx[i + 1];

                    // boundary check
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        int newPos = nx * m + ny;
                        String next = swap(cur, idx, newPos);

                        if (!seen.contains(next)) {
                            q.offer(next);
                            List<String> newPath = new ArrayList<>(curPath);
                            newPath.add(pathWord[i]);
                            pathQ.offer(newPath);
                        }
                    }
                }
            }

        }

        // can't solve
        return new ArrayList<>();
    }

    private String swap(final String cur, final int idx, final int newPos) {
        StringBuilder sb = new StringBuilder(cur);
        sb.setCharAt(idx, cur.charAt(newPos));
        sb.setCharAt(newPos, cur.charAt(idx));
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] test = {
                {1,2,3},
                {4,5,6},
                {7,0,8}
        };

        SlidingPuzzle slidingPuzzle = new SlidingPuzzle(test);

        System.out.println(slidingPuzzle.canSolve());

        for (String s : slidingPuzzle.getSolution()) {
            System.out.println(s);
        }
    }

}
