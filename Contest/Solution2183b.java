import java.util.*;
public class Solution2183b {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            boolean[] seen= new boolean[n+2]; 
            for(int i=0;i<n;i++){
                int x = sc.nextInt();
                if(x<= n) 
                    seen[x] = true;
            }
            int mex = 0;
            while(seen[mex]) 
                mex++;
            int ans = Math.min(k-1, mex);
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
        sc.close();
    }
}
