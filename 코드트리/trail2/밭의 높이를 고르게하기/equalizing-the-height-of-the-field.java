import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();
        int t = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            int target = Math.abs(sc.nextInt() - h);
            arr[i] = arr[i - 1] + target;
        }
        // Please write your code here.

        int ans = Integer.MAX_VALUE;
        for (int i = t; i < n + 1; i++) {
            ans = Math.min(ans, arr[i] - arr[i - t]);
        }
        System.out.println(ans);
    }
}