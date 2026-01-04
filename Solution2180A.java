import java.util.*;

public class Solution2180A {
    static int gcd(int x, int y) {
        while (y != 0) {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int l = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            int g = gcd(b, l);
            int maxPrize = l - g + (a % g);

            System.out.println(maxPrize);
        }
        sc.close();
    }
}