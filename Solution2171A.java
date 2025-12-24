import java.util.*;
public class Solution2171A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            if (n % 2 == 1) {
                System.out.println(0);
            } else {
                System.out.println(n / 4 + 1);
            }
        }
    }
}