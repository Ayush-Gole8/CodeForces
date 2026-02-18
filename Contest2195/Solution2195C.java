import java.io.*;
import java.util.*;

public class Solution2195C {
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
        int inf = (int) 1e9;
        boolean[][] ok = new boolean[7][7];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i != j && i + j != 7)
                    ok[i][j] = true;
            }
        }
        int[] dp = new int[7];
        int[] ndp = new int[7];
        Arrays.fill(dp, inf);

        for (int v = 1; v <= 6; v++) {
            dp[v] = (a[0] == v ? 0 : 1);
        }

        for (int i = 1; i < n; i++) {
            Arrays.fill(ndp, inf);
            for (int v = 1; v <= 6; v++) {
                int c = (a[i] == v ? 0 : 1);
                for (int u = 1; u <= 6; u++) {
                    if (ok[u][v]) {
                        ndp[v] = Math.min(ndp[v], dp[u] + c);
                    }
                }
            }
            int[] t = dp;
            dp = ndp;
            ndp = t;
        }

        int ans = inf;
        for (int v = 1; v <= 6; v++) {
            ans = Math.min(ans, dp[v]);
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