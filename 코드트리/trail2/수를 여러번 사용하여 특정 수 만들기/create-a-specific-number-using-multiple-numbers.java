import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        // Please write your code here.

        int ans = 0;
        for (int i = 0; i * A <= C; i++) {
            int sum = i * A;
            
            int bCount = (C - sum) / B;

            sum += bCount * B;
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }
}