import java.util.*;

public class Solution2167C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            boolean hasOdd = false, hasEven = false;
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                if (arr[i] % 2 == 0) hasEven = true;
                else hasOdd = true;
            }
            if (hasOdd && hasEven) {
                Arrays.sort(arr);
            }
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}