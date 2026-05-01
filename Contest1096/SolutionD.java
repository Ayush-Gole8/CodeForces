import java.io.*;
import java.util.*;

public class SolutionD {
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
        long[] arr = fs.nextLongArray(2 * n);
        List<Integer>[] pos = new ArrayList[n];
        for (int i = 0; i < n; i++)
            pos[i] = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++)
            pos[(int) arr[i]].add(i);

        int l = 2 * n, r = -1;
        int ans = 0;
        for (int k = 0; k < n; k++) {
            l = Math.min(l, pos[k].get(0));
            r = Math.max(r, pos[k].get(1));
            boolean changed = true;
            int tl = l, tr = r;
            while (changed) {
                changed = false;
                for (int i = tl; i <= tr; i++) {
                    int x = (int) arr[i];
                    int nl = Math.min(tl, pos[x].get(0));
                    int nr = Math.max(tr, pos[x].get(1));
                    if (nl < tl || nr > tr) {
                        tl = nl;
                        tr = nr;
                        changed = true;
                    }
                }
            }
            boolean valid = true;
            for (int i = 0; i <= k; i++) {
                if (pos[i].get(0) < tl || pos[i].get(1) > tr) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                ans = k + 1;
        }
        out.println(ans);
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