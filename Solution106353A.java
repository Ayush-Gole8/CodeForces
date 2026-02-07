import java.io.*;
import java.util.*;

public class Solution106353A {
    static FastScanner fs = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int n = (int) fs.nextLong();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add((int) fs.nextLong());
        }

        List<String> operations = new ArrayList<>();
        for (int iter = 0; iter < 2000 && !isSorted(a); iter++) {
            boolean found = false;
            for (int i = 0; i <= n - 3; i++) {
                for (int j = 0; j <= n - 3; j++) {
                    List<Integer> temp = new ArrayList<>(a);
                    performOperation(temp, i, j);
                    if (isBetter(temp, a)) {
                        a = temp;
                        operations.add((i + 1) + " " + (j + 1));
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }
            if (!found && !isSorted(a)) {
                operations.add("1 1");
                break;
            }
        }

        out.println(operations.size());
        for (String op : operations) {
            out.println(op);
        }
    }

    static boolean isSorted(List<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != i + 1)
                return false;
        }
        return true;
    }

    static void performOperation(List<Integer> a, int i, int j) {
        List<Integer> extracted = new ArrayList<>();
        for (int k = 0; k < 3; k++) {
            extracted.add(a.get(i));
            a.remove(i);
        }

        for (int k = 0; k < 3; k++) {
            a.add(j + k, extracted.get(k));
        }
    }

    static boolean isBetter(List<Integer> a, List<Integer> b) {
        int scoreA = 0, scoreB = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == i + 1)
                scoreA++;
            if (b.get(i) == i + 1)
                scoreB++;
        }
        return scoreA > scoreB;
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