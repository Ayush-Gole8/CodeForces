import java.io.*;
import java.util.*;

public class Solution2162B {
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
        String s = fs.next();
        int N = 1 << n;

        for (int mask = 0; mask < N; mask++) {
            StringBuilder p = new StringBuilder();
            StringBuilder x = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1)
                    p.append(s.charAt(i));
                else
                    x.append(s.charAt(i));
            }

            if (!isNonDecreasing(p))
                continue;

            if (isPalindrome(x)) {
                int k = p.length();
                out.println(k);
                if (k == 0)
                    out.println();
                else {
                    boolean first = true;
                    for (int i = 0; i < n; i++) {
                        if (((mask >> i) & 1) == 1) {
                            if (!first)
                                out.print(' ');
                            out.print(i + 1);
                            first = false;
                        }
                    }
                    out.println();
                }
                return;
            }
        }
        out.println(-1);
    }

    static boolean isNonDecreasing(CharSequence p) {
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i - 1) > p.charAt(i))
                return false;
        }
        return true;
    }

    static boolean isPalindrome(CharSequence s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
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