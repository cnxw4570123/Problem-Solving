import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static final String points = " GH";
    static int MAX = 10_001;
    static int[] arr = new int[MAX];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int pos = sc.nextInt();
            char c = sc.next().charAt(0);
            arr[pos] = points.indexOf(c);
        }
        // Please write your code here.
        int max = 0;
        for (int s = 1; s < MAX - k + 1; s++) {
            int sum = 0;

            for (int i = 0; i <= k && s + i < MAX; i++) {
                sum += arr[s + i];
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}