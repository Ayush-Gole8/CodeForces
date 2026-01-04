import java.util.*;

public class Solution2180B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.next();
            }
            String s = arr[0]; 
            for (int i = 1; i < n; i++) {
                String option1 = arr[i] + s;
                String option2 = s + arr[i]; 
                if (option1.compareTo(option2) < 0) {
                    s = option1;
                } else {
                    s = option2;
                }
            }
            System.out.println(s);
        }
        sc.close();
    }
}