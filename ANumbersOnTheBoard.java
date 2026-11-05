import java.io.*;

public class ANumbersOnTheBoard {
    static int[][] MAX_M = new int[101][5051];
    static {
        for (int r = 1; r <= 100; r++) {
            for (int m = 1; m <= r; m++) {
                int minS = m * (m + 1) / 2;
                int maxS = m * (2 * r - m + 1) / 2;
                for (int K = minS; K <= maxS; K++) {
                    if (m > MAX_M[r][K]) {
                        MAX_M[r][K] = m;
                    }
                }
            }
        }
    }

    // ─────────────────────────────────────────────────────────
    // [IO]
    // ─────────────────────────────────────────────────────────
    static FastScanner sc = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    static void print(Object o)   { out.print(o); }
    static void println(Object o) { out.println(o); }
    static void println()         { out.println(); }

    // ─────────────────────────────────────────────────────────
    // [SOLVE]
    // ─────────────────────────────────────────────────────────
    static void solve() {
        int l = sc.nextInt(),r = sc.nextInt();
        int n = r - l + 1;
        long tot = (long) (l + r) * n / 2;
        if (l <= tot && tot <= r) {
            println(0);
            return;
        }
        int hasZero = (l <= 0 && 0 <= r) ? 1 : 0;
        int maxA = hasZero;
        int nPos = Math.max(0, r),nNeg = Math.max(0, -l);

        if (nPos > 0 && nNeg > 0) {
            int maxKPos = nPos * (nPos + 1) / 2;
            int maxKNeg = nNeg * (nNeg + 1) / 2;
            int maxK = Math.min(maxKPos, maxKNeg);

            for (int K = 1; K <= maxK; K++) {
                int m = MAX_M[nPos][K];
                int k = MAX_M[nNeg][K];
                if (m > 0 && k > 0) {
                    maxA = Math.max(maxA, m + k + hasZero);
                }
            }
        }

        println(n - 1 - maxA);
    }

    // ─────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) solve();
        out.flush();
    }

    // ─────────────────────────────────────────────────────────
    // FastScanner
    // ─────────────────────────────────────────────────────────
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() {
            try {
                if (ptr >= len) { len = in.read(buf); ptr = 0; if (len <= 0) return -1; }
                return buf[ptr++];
            } catch (IOException e) { throw new RuntimeException(e); }
        }

        private int skipWS() { int c = read(); while (c <= 32) c = read(); return c; }

        int nextInt() {
            int c = skipWS(), sgn = 1, val = 0;
            if (c == '-') { sgn = -1; c = read(); }
            while (c > 32) { val = val * 10 + (c - '0'); c = read(); }
            return val * sgn;
        }
    }
}