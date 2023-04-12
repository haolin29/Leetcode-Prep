package company.FB;

public class NumberOfIslands {

    class UnionFind {

        int count;
        int[] parents;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parents = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        int idx = i * n + j;
                        parents[idx] = idx;
                        count++;
                    }
                }
            }
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }

            return parents[x];
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX != parentY) {
                parents[parentX] = parentY;
                count--;
            }
        }
    }

    public int getNum(char[][] grid) {
        UnionFind uf = new UnionFind(grid);

        int[] dx = {1,0,-1,0,1};

        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {

                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dx[k + 1];

                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            int idx1 = i * n + j;
                            int idx2 = x * n + y;

                            uf.union(idx1, idx2);
                        }
                    }
                }
            }
        }

        return uf.count;
    }
}
