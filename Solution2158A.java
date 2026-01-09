import java.util.*;
import java.io.*;
public class Solution2158A {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();
            int y = sc.nextInt();
            int r = sc.nextInt();
            int suspended = r + (y / 2);
            suspended = Math.min(suspended, n);
            System.out.println(suspended);
        }
    }
}
