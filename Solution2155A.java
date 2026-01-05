import java.util.*;

public class Solution2155A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int matches = 2 * (n - 1);
            System.out.println(matches);
        }
        sc.close();
    }
}