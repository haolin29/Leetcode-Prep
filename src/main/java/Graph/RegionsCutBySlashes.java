package Graph;

public class RegionsCutBySlashes {
    class UnionFind {
        int[] p;
        int count;

        public UnionFind(int n) {
            p = new int[n * n * 4];
            count = n * n * 4;

            for (int i = 0; i < n * n * 4; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            if(x != p[x]) {
                p[x] = find(p[x]);
            }

            return p[x];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if(px != py) {
                p[py] = px;
                count--;
            }
        }
    }

    int n;
    public int regionsBySlashes(String[] grid) {
        n = grid.length;
        UnionFind uf = new UnionFind(n);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i > 0) uf.union(f(i - 1, j, 2), f(i, j, 0));
                if(j > 0) uf.union(f(i, j - 1, 1), f(i, j, 3));

                if(grid[i].charAt(j) == '/') {
                    uf.union(f(i, j, 0), f(i, j, 3));
                    uf.union(f(i, j, 0), f(i, j, 2));
                }

                if(grid[i].charAt(j) == '\\') {
                    uf.union(f(i, j, 0), f(i, j, 1));
                    uf.union(f(i, j, 2), f(i, j, 3));
                }

                if(grid[i].charAt(j) == ' ') {
                    uf.union(f(i, j, 0), f(i, j, 1));
                    uf.union(f(i, j, 2), f(i, j, 3));
                    uf.union(f(i, j, 0), f(i, j, 2));
                }
            }
        }

        return uf.count;
    }

    private int f(int i, int j, int k) {
        return (i * n + j) * 4 + k;
    }
}
