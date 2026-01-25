import java.io.*;
import java.util.*;

public class Solution2193D {
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
        int[] a = fs.nextIntArray((int) n);
        int[] b = fs.nextIntArray((int) n);
        Arrays.sort(a);

        long[] prefB = new long[(int) n + 1];
        for (int i = 1; i <= n; i++) {
            prefB[i] = prefB[i - 1] + b[i - 1];
        }
        long best = Long.MIN_VALUE;
        for (int i = 0; i < n;) {
            int val = a[i];
            int cnt = (int) (n - i);
            int k = upBound(prefB, cnt) - 1;
            long score = (long) val * k;
            if (score > best) {
                best = score;
            }
            int j = i + 1;
            while (j < n && a[j] == val) {
                j++;
            }
            i = j;
        }
        out.println(best);
    }

    static int upBound(long[] arr, long target) {
        int l = 0;
        int h = arr.length;
        while (l < h) {
            int mid = (l + h) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return l;
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