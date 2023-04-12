package company.LinkedIn;

public class Sqrt {
    public static int mySqrt(int x) {
        // -1, 0 ?
        int l = 0;
        int r = x;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int x = 2;
        System.out.println(mySqrt(x));
    }
}
