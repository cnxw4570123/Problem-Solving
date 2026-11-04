import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] cups = new int[3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }
        // Please write your code here.

        int ans = 0;
        for (int stone = 0; stone < 3; stone++) {
            int score = 0;
            Arrays.fill(cups, 0);
            cups[stone]++;
            for (int i = 0; i < n; i++) {
                swap(a[i] - 1, b[i] - 1);
                if (cups[c[i] - 1] == 1) score++;
            }
            ans = Math.max(ans, score);
        }
        System.out.println(ans);
    }

// 0 1 2 -> 1 저장 -> 0 2 2 -> 0 2 1
    static void swap(int a, int b) {
        int temp = cups[a];
        cups[a] = cups[b];
        cups[b] = temp;
    }
}