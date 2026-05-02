
/**
 * ============================================================
 * Competitive Programming Template — Java
 * Author  : Ayush
 * Created : 2026
 * ============================================================
 *
 * Usage:
 *   - Single-test problems : set T = 1 in main()
 *   - Interactive problems  : uncomment interactive() call
 *   - Heavy recursion       : raise setRecursionLimit inside extras()
 *
 * Sections (search by tag):
 *   [IO]          FastScanner + output helpers
 *   [MATH]        GCD, LCM, modular arithmetic, power, inverse
 *   [COMBINATORICS] nCr / nPr with precomputed factorials
 *   [BITWISE]     popcount, log2, nextPow2
 *   [GRAPH]       Adjacency-list builder, BFS, DFS, Dijkstra
 *   [DSU]         Union-Find with rank + path compression
 *   [SEGTREE]     Generic segment tree (point-update, range-query)
 *   [BINARY SEARCH] Integer + real-valued binary search wrappers
 *   [SORTING]     Coordinate compression
 *   [STRING]      KMP failure function
 *   [UTILS]       Pair, array printing, swap, reverse
 *   [SOLVE]       Entry point — write your solution here
 * ============================================================
 */

import java.io.*;
import java.util.*;

public class DBalancedRound {

    // ─────────────────────────────────────────────────────────
    // [IO]
    // ─────────────────────────────────────────────────────────
    static FastScanner sc = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    /** Call out.flush() at the very end of main — already done below. */
    static void print(Object o) {
        out.print(o);
    }

    static void println(Object o) {
        out.println(o);
    }

    static void println() {
        out.println();
    }

    static void printf(String fmt, Object... args) {
        out.printf(fmt, args);
    }

    // ─────────────────────────────────────────────────────────
    // [MATH]
    // ─────────────────────────────────────────────────────────
    static final long MOD = 1_000_000_007L;
    static final long MOD2 = 998_244_353L;
    static final long INF = Long.MAX_VALUE / 2;
    static final int INFi = Integer.MAX_VALUE / 2;

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    /** Fast modular exponentiation: base^exp % mod */
    static long power(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    /** Modular inverse (mod must be prime) */
    static long inv(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    static long add(long a, long b, long mod) {
        return (a % mod + b % mod + mod) % mod;
    }

    static long sub(long a, long b, long mod) {
        return ((a - b) % mod + mod) % mod;
    }

    static long mul(long a, long b, long mod) {
        return a % mod * (b % mod) % mod;
    }

    /** Integer square root (floor) */
    static long isqrt(long n) {
        long r = (long) Math.sqrt(n);
        while (r * r > n)
            r--;
        while ((r + 1) * (r + 1) <= n)
            r++;
        return r;
    }

    // ─────────────────────────────────────────────────────────
    // [COMBINATORICS] — call initCombinatorics(maxN) first
    // ─────────────────────────────────────────────────────────
    static long[] fact, invFact;

    static void initCombinatorics(int maxN) {
        fact = new long[maxN + 1];
        invFact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++)
            fact[i] = fact[i - 1] * i % MOD;
        invFact[maxN] = inv(fact[maxN], MOD);
        for (int i = maxN - 1; i >= 0; i--)
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
    }

    static long C(int n, int r) {
        if (r < 0 || r > n)
            return 0;
        return fact[n] % MOD * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    static long P(int n, int r) {
        if (r < 0 || r > n)
            return 0;
        return fact[n] % MOD * invFact[n - r] % MOD;
    }

    // ─────────────────────────────────────────────────────────
    // [BITWISE]
    // ─────────────────────────────────────────────────────────
    static int popcount(long x) {
        return Long.bitCount(x);
    }

    static int msb(long x) {
        return 63 - Long.numberOfLeadingZeros(x);
    } // floor(log2(x))

    static long nextPow2(long x) {
        return x <= 1 ? 1 : Long.highestOneBit(x - 1) << 1;
    }

    // ─────────────────────────────────────────────────────────
    // [GRAPH]
    // ─────────────────────────────────────────────────────────

    /** Read n nodes, m edges (1-indexed, undirected by default). */
    static List<Integer>[] buildGraph(int n, int m) {
        @SuppressWarnings("unchecked")
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++)
            g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            g[u].add(v);
            g[v].add(u);
        }
        return g;
    }

    /** Weighted graph — edges stored as {neighbor, weight}. */
    static List<long[]>[] buildWeightedGraph(int n, int m) {
        @SuppressWarnings("unchecked")
        List<long[]>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++)
            g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            long w = sc.nextLong();
            g[u].add(new long[] { v, w });
            g[v].add(new long[] { u, w });
        }
        return g;
    }

    /** BFS — returns dist[] from src (-1 if unreachable). */
    static int[] bfs(List<Integer>[] g, int src) {
        int n = g.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : g[u])
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
        }
        return dist;
    }

    /** Dijkstra — returns dist[] from src (INF if unreachable). */
    static long[] dijkstra(List<long[]>[] g, int src) {
        int n = g.length;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[] { 0, src });
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (d > dist[u])
                continue;
            for (long[] e : g[u]) {
                int v = (int) e[0];
                long w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new long[] { dist[v], v });
                }
            }
        }
        return dist;
    }

    /** Iterative DFS — returns visited order. */
    static List<Integer> dfs(List<Integer>[] g, int src) {
        int n = g.length;
        boolean[] vis = new boolean[n];
        List<Integer> order = new ArrayList<>();
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(src);
        while (!stk.isEmpty()) {
            int u = stk.pop();
            if (vis[u])
                continue;
            vis[u] = true;
            order.add(u);
            for (int v : g[u])
                if (!vis[v])
                    stk.push(v);
        }
        return order;
    }

    /** Topological sort (Kahn's BFS). Returns empty if cycle exists. */
    static List<Integer> topoSort(List<Integer>[] g, int n) {
        int[] indeg = new int[n + 1];
        for (int u = 1; u <= n; u++)
            for (int v : g[u])
                indeg[v]++;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++)
            if (indeg[i] == 0)
                q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : g[u])
                if (--indeg[v] == 0)
                    q.add(v);
        }
        return order;
    }

    // ─────────────────────────────────────────────────────────
    // [DSU] Union-Find
    // ─────────────────────────────────────────────────────────
    static class DSU {
        int[] parent, rank;
        int components;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++)
                parent[i] = i;
            components = n;
        }

        int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b)
                return false;
            if (rank[a] < rank[b]) {
                int t = a;
                a = b;
                b = t;
            }
            parent[b] = a;
            if (rank[a] == rank[b])
                rank[a]++;
            components--;
            return true;
        }

        boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }

    // ─────────────────────────────────────────────────────────
    // [SEGTREE] Point-update, Range-query (sum). Adapt op() freely.
    // ─────────────────────────────────────────────────────────
    static class SegTree {
        int n;
        long[] tree;

        SegTree(int n) {
            this.n = n;
            tree = new long[4 * n];
        }

        SegTree(long[] arr) {
            n = arr.length;
            tree = new long[4 * n];
            build(arr, 1, 0, n - 1);
        }

        void build(long[] arr, int node, int l, int r) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(arr, 2 * node, l, mid);
            build(arr, 2 * node + 1, mid + 1, r);
            tree[node] = op(tree[2 * node], tree[2 * node + 1]);
        }

        /** Override this for min/max/gcd etc. */
        long op(long a, long b) {
            return a + b;
        } // sum segment tree

        long identity() {
            return 0L;
        }

        void update(int idx, long val) {
            update(1, 0, n - 1, idx, val);
        }

        void update(int node, int l, int r, int idx, long val) {
            if (l == r) {
                tree[node] = val;
                return;
            }
            int mid = (l + r) >> 1;
            if (idx <= mid)
                update(2 * node, l, mid, idx, val);
            else
                update(2 * node + 1, mid + 1, r, idx, val);
            tree[node] = op(tree[2 * node], tree[2 * node + 1]);
        }

        long query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        long query(int node, int l, int r, int ql, int qr) {
            if (qr < l || r < ql)
                return identity();
            if (ql <= l && r <= qr)
                return tree[node];
            int mid = (l + r) >> 1;
            return op(query(2 * node, l, mid, ql, qr),
                    query(2 * node + 1, mid + 1, r, ql, qr));
        }
    }

    // ─────────────────────────────────────────────────────────
    // [BINARY SEARCH]
    // ─────────────────────────────────────────────────────────

    /** Returns largest x in [lo, hi] where check(x) is true, or lo-1 if none. */
    static long binarySearchLast(long lo, long hi, java.util.function.LongPredicate check) {
        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (check.test(mid))
                lo = mid;
            else
                hi = mid - 1;
        }
        return check.test(lo) ? lo : lo - 1;
    }

    /** Returns smallest x in [lo, hi] where check(x) is true, or hi+1 if none. */
    static long binarySearchFirst(long lo, long hi, java.util.function.LongPredicate check) {
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (check.test(mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        return check.test(lo) ? lo : lo + 1;
    }

    // ─────────────────────────────────────────────────────────
    // [SORTING] Coordinate Compression
    // ─────────────────────────────────────────────────────────

    /** Returns compressed array. Values mapped to 0-indexed ranks. */
    static int[] compress(long[] arr) {
        long[] sorted = arr.clone();
        Arrays.sort(sorted);
        sorted = Arrays.stream(sorted).distinct().toArray();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = Arrays.binarySearch(sorted, arr[i]);
        return res;
    }

    // ─────────────────────────────────────────────────────────
    // [STRING]
    // ─────────────────────────────────────────────────────────

    /** KMP failure function (also called pi/lps array). */
    static int[] kmpFailure(String s) {
        int n = s.length();
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            int j = f[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = f[j - 1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            f[i] = j;
        }
        return f;
    }

    /** All starting indices of pattern in text (0-indexed). */
    static List<Integer> kmpSearch(String text, String pattern) {
        String s = pattern + "#" + text;
        int[] f = kmpFailure(s);
        List<Integer> positions = new ArrayList<>();
        int pl = pattern.length();
        for (int i = pl + 1; i < s.length(); i++)
            if (f[i] == pl)
                positions.add(i - 2 * pl);
        return positions;
    }

    // ─────────────────────────────────────────────────────────
    // [UTILS]
    // ─────────────────────────────────────────────────────────
    static <T> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static void printArr(int[] a) {
        for (int x : a)
            out.print(x + " ");
        out.println();
    }

    static void printArr(long[] a) {
        for (long x : a)
            out.print(x + " ");
        out.println();
    }

    static long[] toLong(int[] a) {
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i++)
            r[i] = a[i];
        return r;
    }

    /** Pair helper (Comparable by first, then second). */
    static class Pair<A extends Comparable<A>, B extends Comparable<B>>
            implements Comparable<Pair<A, B>> {
        A first;
        B second;

        Pair(A f, B s) {
            first = f;
            second = s;
        }

        @Override
        public int compareTo(Pair<A, B> o) {
            int c = first.compareTo(o.first);
            return c != 0 ? c : second.compareTo(o.second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    // ─────────────────────────────────────────────────────────
    // [SOLVE] ← Write your problem logic here
    // ─────────────────────────────────────────────────────────
    static void solve() {
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] a = sc.nextLongArray(n);
        Arrays.sort(a);
        int maxx = 1, curr = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] - a[i - 1] <= k) {
                curr++;
                maxx = Math.max(maxx, curr);
            } else {
                curr = 1;
            }
        }

        println(n - maxx);
    }

    // ─────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        int t = sc.nextInt(); // set t = 1 for single-test problems
        while (t-- > 0)
            solve();
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
                if (ptr >= len) {
                    len = in.read(buf);
                    ptr = 0;
                    if (len <= 0)
                        return -1;
                }
                return buf[ptr++];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int skipWS() {
            int c = read();
            while (c <= 32)
                c = read();
            return c;
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int c = skipWS();
            if (c == -1)
                return null;
            do {
                sb.append((char) c);
            } while ((c = read()) > 32);
            return sb.toString();
        }

        int nextInt() {
            int c = skipWS(), sgn = 1, val = 0;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }

        long nextLong() {
            int c = skipWS();
            int sgn = 1;
            long val = 0;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        int[][] nextIntMatrix(int r, int c) {
            int[][] m = new int[r][c];
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    m[i][j] = nextInt();
            return m;
        }
    }
}