import java.util.Scanner;
public class Main {
    static int MAX = 999;
    static int[] numbers, strikes, balls;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        numbers = new int[n];
        strikes = new int[n];
        balls = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
            strikes[i] = sc.nextInt();
            balls[i] = sc.nextInt();
        }
        // Please write your code here.
        int ans = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 1; k < 10; k++) {
                    if(i == j || j == k || k == i) {
                        continue;
                    }

                    boolean success = true;
                    for (int l = 0; l < n; l++) {
                        int hundred = numbers[l] / 100;
                        int ten = numbers[l] % 100 / 10;
                        int one = numbers[l] % 10;

                        int strike = 0, ball = 0;

                        if (hundred == i) {
                            strike++;
                        }

                        if (ten == j) {
                            strike++;
                        }

                        if (one == k) {
                            strike++;
                        }

                        if(hundred == j || hundred == k) {
                            ball++;
                        }

                        if(ten == i || ten == k) {
                            ball++;
                        }

                        if(one == i || one == j) {
                            ball++;
                        }

                        if (strikes[l] != strike || balls[l] != ball) {
                            success = false;
                            break;
                        }
                    }
                    if (success) {
                        ans++;
                    }
                }
                
            }
        }

        System.out.println(ans);
    }
}