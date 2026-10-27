import java.util.Scanner;
public class Main {
    static int[] working = new int[1000];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            B[i] = sc.nextInt();

            for (int j = A[i]; j < B[i]; j++) {
                working[j]++;
            }
        }
        // Please write your code here.

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = A[i]; j < B[i]; j++) {
                working[j]--;
            }

            int sum = 0;
            for (int j = 1; j < 1000; j++) {
                if(working[j] > 0) sum++;
            }

            ans = Math.max(ans, sum);
            for (int j = A[i]; j < B[i]; j++) {
                working[j]++;
            }
        }

        System.out.println(ans);
    }
}