import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int MAX = 1001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        // Please write your code here.
        int ans = 0;
        int[] counts = new int[MAX];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    Arrays.fill(counts, 0);
                    for (int l = 0; l < n; l++) {
                        if(l == i || l == j || l == k) continue;

                        for (int start = a[l]; start < b[l] + 1; start++) {
                            counts[start]++;
                        }
                    }

                    boolean satisfy = true;
                    for (int l = 0; l < MAX; l++) {
                        if(counts[l] > 1) {
                            satisfy = false;
                            break;
                        }
                    }

                    if (!satisfy) {
                        continue;
                    }
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}