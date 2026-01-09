import java.io.*;
import java.util.*;

public class Solution2156A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < t; i++) {
            long n = Long.parseLong(br.readLine().trim());
            long ans = (n - 1) / 2;
            out.append(ans).append('\n');
        }
        System.out.print(out.toString());
    }

}