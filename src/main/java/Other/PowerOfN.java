package Other;

public class PowerOfN {


    public static boolean isPowerOf(int num, int n) {
        while (num >= n && num % n == 0) {
            num /= n;
        }

        return num == 1;
    }

    public static void main(String[] args) {
        int num = 1024;
        int n = 2;

        System.out.println(isPowerOf(num, n));
    }
}
