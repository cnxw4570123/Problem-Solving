import java.util.Scanner;

public class Main {
    static int MAX = 100;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        int ans = 0;
        for (int k = 1; k < MAX + 1; k++) {
            int current = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] - k == k - arr[i]) {
                        current++;
                    }
                }
            }
            ans = Math.max(ans, current);
        }
        System.out.println(ans);
    }
}