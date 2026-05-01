import java.io.*;
import java.util.*;

public class SolutionC {
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
        long[] arr = fs.nextLongArray(n);
        List<Long> div6 = new ArrayList<>();
        List<Long>  div3 = new ArrayList<>();
        List<Long>  div2 = new ArrayList<>();
        List<Long> others = new ArrayList<>();
        for (long x : arr) {
            if (x % 6 == 0)
                div6.add(x);
            else if (x % 3 == 0)
                div3.add(x);
            else if (x % 2 == 0)
                div2.add(x);
            else
                others.add(x);
        }
        List<Long> res = new ArrayList<>();
        res.addAll(div6);
        res.addAll(div2);
        res.addAll(others);
        res.addAll(div3);
        for (int i = 0; i < res.size(); i++) {
            out.print(res.get(i));
        if (i + 1 < res.size()) 
            out.print(" ");
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