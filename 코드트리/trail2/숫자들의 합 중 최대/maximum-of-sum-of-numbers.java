import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        // Please write your code here.

        int ans = 0;
        for (int i = x; i < y + 1; i++) {
            int tmp = i;

            int sum = 0;
            while (tmp > 0) {
                sum += tmp % 10;
                tmp /= 10;
            }
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }
}