package company.FB;

public class _200_NumberOfIslands {

    // 2020-02-29 17:44
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        for (int k = 0; k < 4; k++) {
            dfs(grid, i + dx[k], j + dy[k]);
        }
    }

    class UnionFind {
        int[] p;
        int count;

        public UnionFind(char[][] grid) {
            p = new int[grid.length * grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j] == '1') {
                        count++;

                        int d = toLine(grid[0].length, i, j);
                        p[d] = d;
                    }
                }
            }
        }

        public int find(int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }

            return p[x];
        }

        public void union(int x, int y) {
            int px = p[x];
            int py = p[y];

            if (px != py) {
                p[px] = p[py];
                count--;
            }
        }
    }

    public int numIslands_uf(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if (valid(grid, x, y) && grid[x][y] == '1') {
                            uf.union(toLine(m, x, y), toLine(m, i, j));
                        }
                    }
                }
            }
        }

        return uf.count;
    }

    private int toLine(int m, int x, int y) {
        return x * m + y;
    }

    private boolean valid(char[][] grid, int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }
}
