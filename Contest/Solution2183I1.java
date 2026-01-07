import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine(); 
            int operations = n / 2;
            for (int i = 0; i < operations; i++) {
                pw.print("0" + (i == operations - 1 ? "\n" : " "));
            }
        }
        
        br.close();
        pw.close();
    }
}