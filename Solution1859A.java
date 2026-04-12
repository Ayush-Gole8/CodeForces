import java.io.*;
import java.util.*;

public class Solution1859A {
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

        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[0]) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) {
            out.println(-1);
            return;
        }
        int minVal = Arrays.stream(a).min().getAsInt();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        for (int x : a) {
            if (x == minVal)
                b.add(x);
            else
                c.add(x);
        }
        out.println(b.size() + " " + c.size());
        for (int x : b)
            out.print(x + " ");
        out.println();
        for (int x : c)
            out.print(x + " ");
        out.println();
    }

    int n = fs.nextInt();
    int[] a = fs.nextIntArray(n);
    boolean allEqual = true;for(
    int i = 1;i<n;i++)
    {
        if (a[i] != a[0]) {
            allEqual = false;
            break;
        }
    }if(allEqual)
    {
        out.println(-1);
        return;
    }
    int minVal = Arrays.stream(a).min().getAsInt();
    List<Integer> b = new ArrayList<>();
    List<Integer> c = new ArrayList<>();for(
    int x:a)
    {
        if (x == minVal)
            c.add(x);
        else
            b.add(x);
    }out.println(b.size()+" "+c.size());for(
    int x:b)out.print(x+" ");out.println();for(
    int x:c)out.print(x+" ");out.println();
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
}}