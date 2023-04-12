package Other;

public class Fibonacci {

    public static int fib(int n) {
        if (n < 2) {
            System.out.println(n);
            return n;
        }

        int num = fib(n - 2) + fib(n - 1);

        System.out.println(num);

        return num;
    }

    public static void main(String[] args) {
        int n = 5;

        fib(5);
    }
}
