import java.io.*;
import java.util.*;

public class Solution2198C2 {
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
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        // map: root_value -> count of starting points with that root
        TreeMap<Long, Long> map = new TreeMap<>();
        long answer = 0;

        for (int i = 0; i < n; i++) {
            long v = a[i];
            long newRoots = 0;

            if (i > 0) {
                if (v > a[i - 1] + 1) {
                    // All existing starting points start a new root
                    newRoots = i;
                    map.clear();
                } else {
                    // Starting points with root >= v start a new root
                    NavigableMap<Long, Long> tail = map.tailMap(v, true);
                    for (Long cnt : tail.values()) {
                        newRoots += cnt;
                    }
                    tail.clear();
                }
            }

            // Merge: all new roots + new starting point l=i get root = v
            map.merge(v, newRoots + 1, Long::sum);

            // Each new root contributes to (n - i) subarrays ending at i..n-1
            answer += (long) (n - i) * (newRoots + 1);
        }

        out.println(answer);
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
