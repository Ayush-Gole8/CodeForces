import java.io.*;
import java.util.*;

public class Main {
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') if (c == -1) return Integer.MIN_VALUE;
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        StringBuilder out = new StringBuilder();
        while (t-- > 0) {
            int n = fs.nextInt();
            int maxColor = n * n;
            int[] freq = new int[maxColor + 1];
            int maxf = 0;
            for (int i = 0; i < n * n; i++) {
                int x = fs.nextInt();
                freq[x]++;
                if (freq[x] > maxf) maxf = freq[x];
            }
            if (maxf > n * (n - 1)) out.append("NO\n");
            else out.append("YES\n");
        }
        System.out.print(out.toString());
    }
}