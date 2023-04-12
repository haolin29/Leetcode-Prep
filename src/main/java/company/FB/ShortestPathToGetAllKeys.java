package company.FB;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathToGetAllKeys {

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();

        int[][][] seen = new int[m][n][64];
        Queue<int[]> q = new LinkedList<>();

        int allKeys = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);

                if (c == '@') {
                    q.offer(new int[]{i, j, 0});
                    seen[i][j][0] = 1;
                } else if (c >= 'a' && c <= 'f') {
                    allKeys |= (1 << (c - 'a'));
                }
            }
        }

        int step = 0;
        int[] dir = new int[]{-1, 0, 1, 0, -1};

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int keys = cur[2];

                if (keys == allKeys) return step;

                for (int k = 0; k < 4; k++) {
                    int nextX = x + dir[k];
                    int nextY = y + dir[k + 1];
                    int nextKeys = keys;

                    if (nextX < 0 || nextY < 0 || nextX == m || nextY == n) continue;
                    char c = grid[nextX].charAt(nextY);

                    if (c == '#') continue;
                    // don't have the key for lock
                    if (c >= 'A' && c <= 'F' && (keys & (1 << (c - 'A'))) == 0) continue;

                    if (c >= 'a' && c <= 'f') {
                        nextKeys |= (1 << (c - 'a'));
                    }

                    if (seen[nextX][nextY][nextKeys] == 1) continue;
                    q.offer(new int[]{nextX, nextY, nextKeys});
                    seen[nextX][nextY][nextKeys] = 1;
                }
            }
            step++;
        }

        return -1;
    }
}
