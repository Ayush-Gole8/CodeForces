import java.util.*;
public class SolutionBlackslexpassword {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  
        while (t-- > 0) {
            int k = sc.nextInt();
            int x = sc.nextInt();
            int n = k * x + 1;
            System.out.println(n);
        }
        sc.close();
    }
}