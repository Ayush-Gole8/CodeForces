import java.io.*;
import java.util.*;

public class Solution2195D {
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
        long n = fs.nextLong();
        long[] a = fs.nextLongArray(n);
        long[] ans = new long[(int) n];
        for (int i = 1; i < n - 1; i++) {
            ans[i] = (a[i - 1] - 2 * a[i] + a[i + 1]) / 2;
        }
        long sum = 0;
        for (int i = 1; i < n - 1; i++) {
            sum += ans[i] * i;
        }
        ans[(int) (n - 1)] = (a[0] - sum) / (n - 1);

        long sum2 = 0;
        for (int i = 1; i < n - 1; i++) {
            sum2 += ans[i] * (n - 1 - i);
        }
        ans[0] = (a[(int) (n - 1)] - sum2) / (n - 1);

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + (i == n - 1 ? "" : " "));
        }
        out.println();

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

        public long[] nextLongArray(long n) {
            long[] a = new long[(int) n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
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