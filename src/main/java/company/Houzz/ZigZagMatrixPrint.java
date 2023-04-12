package company.Houzz;

public class ZigZagMatrixPrint {

    public void print(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        // 45 degree
        int[] dx = {-1, 1};
        int[] dy = {1, -1};

        int direction = 0;

        int i = 0, j = 0;

        while(i < rows && j < cols) {
            System.out.println(array[i][j]);

            int nextI = i + dx[direction % 2];
            int nextJ = j + dy[direction % 2];

            if (check(nextI, nextJ, rows, cols)) {
                i = nextI;
                j = nextJ;
            } else {
                // top right direction
                if (direction % 2 == 0) {
                    nextI = i;
                    nextJ = j + 1;
                    if (check(nextI, nextJ, rows, cols)) {
                        i = nextI;
                        j = nextJ;
                    } else {
                        i++;
                    }
                } else {
                    nextI = i + 1;
                    nextJ = j;

                    if (check(nextI, nextJ, rows, cols)) {
                        i = nextI;
                        j = nextJ;
                    } else {
                        j++;
                    }
                }

                direction++;
            }
        }
    }

    private boolean check(final int nextI, final int nextJ, final int rows, final int cols) {
        if (nextI >= 0 && nextI < rows && nextJ >=0 && nextJ < cols) {
            return true;
        }
        return false;
    }
}
