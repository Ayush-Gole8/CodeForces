import java.io.*;
import java.util.*;

public class Solution2193B {
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
        int[] a = fs.nextIntArray(n);
        int[] suffMax = new int[n];
        int[] idxMax = new int[n];
        suffMax[n - 1] = a[n - 1];
        idxMax[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            if (a[i] > suffMax[i + 1]) {
                suffMax[i] = a[i];
                idxMax[i] = i;
            } else {
                suffMax[i] = suffMax[i + 1];
                idxMax[i] = idxMax[i + 1]; 
            }
        }

        boolean done = false;
        for (int i = 0; i < n; ++i) {
            if (suffMax[i] > a[i]) {
                int l = i, r = idxMax[i];
                while (l < r) {
                    int tmp = a[l];
                    a[l] = a[r];
                    a[r] = tmp;
                    l++;
                    r--;
                }
                done = true;
                break;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (i > 0)
                out.print(' ');
            out.print(a[i]);
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