import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(s[i]);
            }
            if (a[0] == 0 && a[n - 1] == 0) {
                System.out.println("Bob");
            } else {
                System.out.println("Alice");
            }
        }
    }
}