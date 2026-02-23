import java.io.*;
import java.util.*;

public class Solution2198D {
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
        int k = fs.nextInt();
        
        // Minimum turns is n (all consecutive pairs)
        if (k < n) {
            out.println("NO");
            return;
        }
        
        // Maximum turns is when all pairs are interleaved
        int maxK = n / 2 + n; // floor(n/2) + n
        if (n % 2 == 1) maxK++; // Add 1 if n is odd
        
        if (k > maxK) {
            out.println("NO");
            return;
        }
        
        out.println("YES");
        
        // If k = n, use all consecutive pairs
        if (k == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(i).append(' ').append(i).append(' ');
            }
            out.println(sb.toString().trim());
            return;
        }
        
        // Find m such that pattern [1,2,...,m,1,2,...,m] + [m+1,m+1,...,n,n] gives k turns
        // For m even: 3m/2 + (n-m) = k → m/2 = k - n → m = 2(k-n)
        // For m odd: (3m+1)/2 + (n-m) = k → (m+1)/2 = k - n → m = 2(k-n) - 1
        
        int extra = k - n; // Extra turns needed beyond minimum
        int m = -1;
        
        // Try even m
        int evenM = 2 * extra;
        if (evenM >= 2 && evenM <= n) {
            m = evenM;
        }
        
        // Try odd m
        int oddM = 2 * extra - 1;
        if (oddM >= 1 && oddM <= n) {
            // Verify: for odd m >= 3, turns = (3m+1)/2
            // For m=1, turns = 1
            if (oddM == 1 && extra == 0) {
                m = 1;
            } else if (oddM >= 3) {
                m = oddM;
            }
        }
        
        if (m == -1) {
            out.println("NO");
            return;
        }
        
        // Construct the sequence
        StringBuilder sb = new StringBuilder();
        // First m values in order
        for (int i = 1; i <= m; i++) {
            sb.append(i).append(' ');
        }
        // Then the same m values again
        for (int i = 1; i <= m; i++) {
            sb.append(i).append(' ');
        }
        // Remaining pairs consecutively
        for (int i = m + 1; i <= n; i++) {
            sb.append(i).append(' ').append(i).append(' ');
        }
        out.println(sb.toString().trim());
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