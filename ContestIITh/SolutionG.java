import java.io.*;
import java.util.*;

public class SolutionG {
    static FastScanner fs = new FastScanner();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = fs.nextInt();
        while (t-- > 0) solve();
        out.flush();
    }

    static void solve() {
        out.println("? 0 37");
        out.flush();
        long R = fs.nextLong();

        long D = 37L * 37 + 4 * R;
        long sqrtD = (long) Math.sqrt(D);
        long x = (-37 + sqrtD) / 2;
        out.println("! " + x);
        out.flush();
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
                    if (len < 0) return -1;
                }
                return buffer[ptr++];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            int c, s = 1, x = 0;
            while ((c = read()) <= 32);
            if (c == '-') { s = -1; c = read(); }
            do x = x * 10 + (c - '0');
            while ((c = read()) > 32);
            return x * s;
        }

        long nextLong() {
            int c, s = 1;
            long x = 0;
            while ((c = read()) <= 32);
            if (c == '-') { s = -1; c = read(); }
            do x = x * 10 + (c - '0');
            while ((c = read()) > 32);
            return x * s;
        }
    }
}
