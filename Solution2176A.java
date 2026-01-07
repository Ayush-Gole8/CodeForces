import java.util.*;

public class Solution2176A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int maxSoFar = arr[0];
            int operations = 0;

            for (int i = 1; i < n; i++) {
                if (arr[i] < maxSoFar) {
                    operations++;
                } else {
                    maxSoFar = arr[i];
                }
            }

            System.out.println(operations);
        }
        sc.close();
    }
}
