import java.io.*;
import java.util.*;

public class Solution105125D {
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
        String s = fs.next();
        
        final long MOD = 1000000007L;
        long[][][] dp = new long[n + 1][4][4]; 
        
        for (int a = 1; a <= 3; a++) {
            for (int b = 1; b <= 3; b++) {
                boolean validA = (s.charAt(0) == '?' || s.charAt(0) - '0' == a);
                boolean validB = (s.charAt(1) == '?' || s.charAt(1) - '0' == b);
                
                if (validA && validB) {
                    dp[2][a][b] = 1;
                }
            }
        }
        
        for (int i = 2; i < n; i++) {
            for (int a = 1; a <= 3; a++) {
                for (int b = 1; b <= 3; b++) {
                    if (dp[i][a][b] == 0) continue;
                    
                    for (int c = 1; c <= 3; c++) {
                        boolean validC = (s.charAt(i) == '?' || s.charAt(i) - '0' == c);
                        if (!validC) continue;
                        if (a != b && b != c && a != c) {
                            continue; 
                        }
                        
                        dp[i + 1][b][c] = (dp[i + 1][b][c] + dp[i][a][b]) % MOD;
                    }
                }
            }
        }
        
        long result = 0;
        for (int a = 1; a <= 3; a++) {
            for (int b = 1; b <= 3; b++) {
                result = (result + dp[n][a][b]) % MOD;
            }
        }
        
        out.println(result);
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

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }
    }
}