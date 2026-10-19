import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();
        // Please write your code here.

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = 0; l < n - 2; l++) {
                        if (k == i && Math.abs(j - l) <= 2) continue;

                        int rect1 = arr[i][j] + arr[i][j + 1] + arr[i][j + 2];
                        int rect2 = arr[k][l] + arr[k][l + 1] + arr[k][l + 2];
                        max = Math.max(max, rect1 + rect2);

                    }
                }
            }
        }
        System.out.println(max);
    }
}