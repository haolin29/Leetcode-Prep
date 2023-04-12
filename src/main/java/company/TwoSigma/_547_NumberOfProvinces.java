package company.TwoSigma;

public class _547_NumberOfProvinces {

    // Union Find
    // find the connected nodes group
    public int findCircleNum(int[][] isConnected) {
        UF uf = new UF(isConnected.length);

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[0].length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.count;

    }

    public static void main(String[] args) {
        _547_NumberOfProvinces ts = new _547_NumberOfProvinces();

        int[][] isConnected = {
                {1,1,0},{1,1,0},{0,0,1}};

        int[][] isConnected2 = {{1,0,0},{0,1,0},{0,0,1}};

        System.out.println(ts.findCircleNum(isConnected));
        System.out.println(ts.findCircleNum(isConnected2));
    }

    static class UF {

        int[] parent;
        int count;

        public UF(int size) {
            parent = new int[size];
            count = size;

            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[px] = py;
                count--;
            }
        }

        public int find(int x) {
            int p = parent[x];

            while (parent[p] != p) {
                parent[p] = parent[parent[p]]; // path compression by halving
                p = parent[p];
            }

            return p;
        }
    }
}
