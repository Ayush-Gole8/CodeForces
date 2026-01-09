import java.io.*;
import java.util.*;
public class Solution2157A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int[] freq = new int[n + 1];

            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                if (x <= n) {
                    freq[x]++;
                }
            }
            int deletions = 0;

            for (int x = 0; x <= n; x++) {
                if (x == 0) {
                    deletions += freq[x];
                } else if (freq[x] < x) {
                    deletions += freq[x];
                } else {
                    deletions += freq[x] - x;
                }
            }

            System.out.println(deletions);
        }
        sc.close();
    }
}
