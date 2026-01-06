import java.util.*;
import java.io.*;

public class Solution2164A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-->0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int x = sc.nextInt();
            int min = arr[0];
            int max = arr[0];
            for (int i = 0; i < n; i++) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
            }
            if (x >= min && x <= max) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }
}
