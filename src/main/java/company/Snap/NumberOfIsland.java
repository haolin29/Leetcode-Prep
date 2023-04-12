package company.Snap;

public class NumberOfIsland {
    class UnionFind {

        int[] parent;
        int m, n;
        int count = 0;

        public UnionFind(char[][] grid) {
            m = grid.length;
            n = grid[0].length;

            parent = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                }
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            int px = parent[x];
            int py = parent[y];

            if (px != py) {
                parent[py] = px;
                count--;
            }
        }
    }

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if (x >= 0 && x < grid.length && y >= 0 && y < n && grid[x][y] == '1') {
                            int cur = i * n + j;
                            int next = x * n + y;

                            uf.union(cur, next);
                        }
                    }
                }
            }
        }

        return uf.count;
    }

    public static void main(String[] args) {

    }
}
