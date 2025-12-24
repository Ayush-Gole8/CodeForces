import java.util.*;

public class Solution2167B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            String target = sc.next();
            char[] sArr = s.toCharArray();
            char[] tArr = target.toCharArray();
            Arrays.sort(sArr);
            Arrays.sort(tArr);
            if (Arrays.equals(sArr, tArr)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}