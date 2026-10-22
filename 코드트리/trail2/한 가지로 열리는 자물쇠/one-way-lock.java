import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        // Please write your code here.

        int ans = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 1; k < n + 1; k++) {
                    if(
                        Math.abs(i - a) <= 2 ||
                        Math.abs(j - b) <= 2 ||
                        Math.abs(k - c) <= 2
                    ) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
/*
x +- 2, y +- 2, z +- 2

*/