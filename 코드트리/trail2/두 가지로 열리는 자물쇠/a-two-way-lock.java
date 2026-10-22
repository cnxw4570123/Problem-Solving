import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int a2 = sc.nextInt();
        int b2 = sc.nextInt();
        int c2 = sc.nextInt();
        // Please write your code here.
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 1; k < n + 1; k++) {
                    if(
                        satisfy(a, i, n) && 
                        satisfy(b, j, n) &&
                        satisfy(c, k, n)
                    ) {
                        ans++;
                        continue;
                    }

                    if(
                        satisfy(a2, i, n) &&
                        satisfy(b2, j, n) &&
                        satisfy(c2, k, n) 
                    ) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static boolean satisfy(int a, int b, int n) {
        int temp1 = Math.min(a, b), temp2 = Math.max(a, b);
        return (temp2 + 1) % n == temp1 || (temp2 + 2) % n == temp1 || Math.abs(temp1 - temp2) <= 2;
    }
}