import java.io.*;
import java.util.*;

public class SolutionE {
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
        int[] suf = new int[n + 1];
        suf[n] = n + 5;
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = Math.min(a[i], suf[i + 1]);
        }
        long base = 0;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > suf[i + 1]) {
                base += (a[i] - suf[i + 1]);
            }
        }
        int[] prv = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[st.peek()] >= a[i]) 
                st.pop();
            
            prv[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        long best = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == suf[i]) {
                long gain = i - 1 - prv[i];
                if (gain > best)
                    best = gain;
            }
        }
        out.println(base + best);
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