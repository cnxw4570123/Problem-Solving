import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        // Please write your code here.

        int ans = Integer.MAX_VALUE;

        for (int skip = 0; skip < N; skip++) {
            int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
            int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                if (i == skip) continue;

                xMin = Math.min(xMin, x[i]);
                yMin = Math.min(yMin, y[i]);
                xMax = Math.max(xMax, x[i]);
                yMax = Math.max(yMax, y[i]);
            }

            ans = Math.min(ans, (yMax - yMin) * (xMax - xMin));
        }

        System.out.println(ans != Integer.MAX_VALUE ? ans : 0);
    }
}