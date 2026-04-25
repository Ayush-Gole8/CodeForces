import java.io.*;
import java.util.*;

public class Solution1904A {
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
        long a = fs.nextLong();
        long b = fs.nextLong();
        long xK = fs.nextLong();
        long yK = fs.nextLong();
        long xQ = fs.nextLong();
        long yQ = fs.nextLong();

        int[] dx = { -1, 1, -1, 1 };
        int[] dy = { -1, -1, 1, 1 };

        Set<String> kingHits = new HashSet<>();
        Set<String> queenHits = new HashSet<>();

        for (int j = 0; j < 4; j++) {
            kingHits.add(encode(xK + dx[j] * a, yK + dy[j] * b));
            kingHits.add(encode(xK + dx[j] * b, yK + dy[j] * a));

            queenHits.add(encode(xQ + dx[j] * a, yQ + dy[j] * b));
            queenHits.add(encode(xQ + dx[j] * b, yQ + dy[j] * a));
        }

        int ans = 0;
        for (String pos : kingHits) {
            if (queenHits.contains(pos)) {
                ans++;
            }
        }

        out.println(ans);
    }

    static String encode(long x, long y) {
        return x + "," + y;
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

        int nextInt() {
            return (int) nextLong();
        }
    }
}
