package company.LinkedIn;

public class _261_GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            if(!uf.union(edge[0], edge[1])) {
                return false;
            }
        }

        return uf.count == 1;
    }

    class UnionFind {
        int[] p;
        int count;

        public UnionFind(int n) {
            p = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            if (x != p[x]) {
                p[x] = find(p[x]);
            }

            return p[x];
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                p[px] = p[py];
                count--;
                return true;
            } else {
                return false;
            }
        }
    }
}
