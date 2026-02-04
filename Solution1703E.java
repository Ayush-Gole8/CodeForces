import java.io.*;
import java.util.*;

public class Solution1703E {
    static FastScanner fs = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) {
        int t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }
        out.flush();
    }

    static void solve() {
        int n = fs.nextInt();
        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            String row = fs.next();
            for (int j = 0; j < n; j++) {
                grid[i][j] = row.charAt(j) - '0';
            }
        }
        int operations = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int c1 = grid[i][j];
                int c2 = grid[j][n - 1 - i];
                int c3 = grid[n - 1 - i][n - 1 - j];
                int c4 = grid[n - 1 - j][i];
                int sum = c1 + c2 + c3 + c4;
                operations += Math.min(sum, 4 - sum);
            }
        }

        out.println(operations);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() {
            try {
                if (ptr >= len) {
                    len = in.read(buffer);
                    ptr = 0;
                    if (len <= 0)
                        return -1;
                }
                return buffer[ptr++];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= 32) {
                if (c == -1)
                    return null;
            }
            do {
                sb.append((char) c);
            } while ((c = read()) > 32);
            return sb.toString();
        }

        int nextInt() {
            int c = read();
            while (c <= 32)
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }

        long nextLong() {
            int c = read();
            while (c <= 32)
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
    }
}