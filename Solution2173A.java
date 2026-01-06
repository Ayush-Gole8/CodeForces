import java.util.Scanner;

public class Solution2173A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            String s = sc.next();
            int cnt = 0;
            int forcedAwakeUntil = -1;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    forcedAwakeUntil = Math.max(forcedAwakeUntil, i + k);
                } else {
                    if (i <= forcedAwakeUntil) {
                    } else {
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }
}