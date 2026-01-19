import java.io.*;
import java.util.*;

public class Solution2025A {
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
        String s = fs.next();
        String t = fs.next();
        int n = s.length();
        int m = t.length();
        int lcp = 0;
        while (lcp < n && lcp < m && s.charAt(lcp) == t.charAt(lcp)) {
            lcp++;
        }
        int opt1 = n + m;
        int opt2 = Integer.MAX_VALUE;
        if (lcp > 0) {
            opt2 = n + m - lcp + 1;
        }
        out.println(Math.min(opt1, opt2));
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