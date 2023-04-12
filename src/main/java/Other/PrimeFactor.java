package Other;

public class PrimeFactor {

    public static void find(int num) {

        int factor = 2;

        while(factor <= num) {
            if (num % factor == 0) {
                System.out.println(factor);
                num /= factor;
            } else {
                factor++;
            }
        }
    }

    public static void main(String[] args) {
        find(100);
    }
}
