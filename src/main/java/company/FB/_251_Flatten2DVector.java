package company.FB;

public class _251_Flatten2DVector {

    int m, n;
    int[][] v;
    public _251_Flatten2DVector(int[][] v) {
        m = 0;
        n = 0;
        this.v = v;
    }

    public int next() {
        if (hasNext()) {
            return v[m][n++];
        }
        return -1;
    }

    public boolean hasNext() {
        // move to next available slot
        while (m < v.length && n == v[m].length) {
            m++;
            n = 0;
        }

        return m < v.length;
    }
}
