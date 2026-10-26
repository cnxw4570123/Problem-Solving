import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        // Please write your code here.

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(i == j || i == k || j == k) {
                        continue;
                    }
                    if(x[i] == x[j] && y[i] == y[k]) {
                        int side = Math.abs(y[j] - y[i]), side2 = Math.abs(x[k] - x[i]);

                        ans = Math.max(ans, side * side2);
                        continue;
                    }

                    if(x[i] == x[k] && y[i] == y[j]) {
                        int side = Math.abs(y[k] - y[i]), side2 = Math.abs(x[j] - x[i]);
                        ans = Math.max(ans, side * side2);    
                    }
                }
            }
        }
        System.out.println(ans);
    }
}