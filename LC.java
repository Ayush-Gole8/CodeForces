class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        if (n == 2) return -1;

        char[] t = arr.clone();
        java.util.Arrays.sort(t);

        int L = 0;
        while (L < n && arr[L] == t[L]) L++;
        int R = n - 1;
        while (R >= 0 && arr[R] == t[R]) R--;

        if (L > 0 || R < n - 1) return 1;

        char minChar = t[0];
        char maxChar = t[n - 1];

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == minChar) return 2;
        }
        for (int i = 1; i < n; i++) {
            if (arr[i] == maxChar) return 2;
        }
        return 3;
    }
}