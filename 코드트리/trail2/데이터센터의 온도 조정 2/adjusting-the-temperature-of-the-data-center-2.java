import java.util.Scanner;
public class Main {
    static int N, C, G, H;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();
        G = sc.nextInt();
        H = sc.nextInt();

        int[] ta = new int[N];
        int[] tb = new int[N];
        for (int i = 0; i < N; i++) {
            ta[i] = sc.nextInt();
            tb[i] = sc.nextInt();
        }
        // Please write your code here.

        int ans = 0;
        for (int temp = -1; temp < 1002; temp++) {
            int total = 0;

            for (int i = 0; i < N; i++) {
                total += calc(temp, ta[i], tb[i]);
            }

            ans = Math.max(total, ans);
        }
        System.out.println(ans);
    }

    static int calc(int temp, int low, int high) {
        if (temp < low) {
            return C;
        }
        if (low <= temp && temp <= high) {
            return G;
        }

        return H;
    }
}