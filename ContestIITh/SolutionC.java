import java.io.*;
import java.util.*;

public class SolutionC {
    static FastScanner fs = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) {
        int t = fs.nextInt();
        while (t-- > 0) solve();
        out.flush();
    }

    static void solve() {
        int n = fs.nextInt();

        int fixedOnes = 0, questions = 0;
        for (int i = 1; i <= n; i++) {
            char c = fs.next().charAt(0);
            if (c == '1') fixedOnes++;
            else if (c == '?') questions++;
        }

        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt(), v = fs.nextInt();
            g[u].add(v);
            g[v].add(u);
        }

        int[] parent = new int[n + 1];
        int[] order = new int[n];
        int top = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[top++] = u;
            for (int v : g[u]) {
                if (v == parent[u]) continue;
                parent[v] = u;
                stack.push(v);
            }
        }

        int[] size = new int[n + 1];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int v = order[i];
            size[parent[v]] += size[v];
        }

        int[] freq = new int[n + 1];
        for (int i = 2; i <= n; i++) freq[size[i]]++;

        int ans = 0;
        for (int k = fixedOnes; k <= fixedOnes + questions; k++) {
            if (k <= n) ans = Math.max(ans, freq[k]);
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
                    if (len <= 0) return -1;
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
                if (c == -1) return null;
            }
            do {
                sb.append((char) c);
            } while ((c = read()) > 32);
            return sb.toString();
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
