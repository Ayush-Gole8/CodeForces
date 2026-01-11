import java.io.*;
import java.util.*;

public class SolutionB {
    static FastScanner fs = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static void main(String[] args) {
        solve();       
        out.flush();
    }
    static void solve() {
        int n = fs.nextInt();
        int m = fs.nextInt();
        long[] bal = new long[n + 1];
        for (int i = 0; i < m; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            long x = fs.nextLong();
            bal[a] -= x;
            bal[b] += x;
        }
        ArrayDeque<Integer> pos = new ArrayDeque<>();
        ArrayDeque<Integer> neg = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (bal[i] > 0) pos.add(i);
            else if (bal[i] < 0) neg.add(i);
        }
        ArrayList<long[]> ans = new ArrayList<>();
        while (!pos.isEmpty() && !neg.isEmpty()) {
            int u = neg.peekFirst();
            int v = pos.peekFirst();
            long take = Math.min(-bal[u], bal[v]);
            ans.add(new long[]{u, v, take});
            bal[u] += take;
            bal[v] -= take;
            if (bal[u] == 0) neg.pollFirst();
            if (bal[v] == 0) pos.pollFirst();
        }
        out.println(ans.size());
        for (long[] tr : ans) {
            out.println(tr[0] + " " + tr[1] + " " + tr[2]);
        }
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
                    if (len <= 0) return -1;
                }
                return buffer[ptr++];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            int c = read();
            while (c <= 32) c = read();
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
            while (c <= 32) c = read();
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
    }
}
