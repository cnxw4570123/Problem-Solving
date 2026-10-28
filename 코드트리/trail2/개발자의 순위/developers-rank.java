import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[k][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                int player = sc.nextInt();
                arr[i][player - 1] = j;
            }
        }
        // Please write your code here.
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j) continue;

                boolean satisfy = true;
                for (int rounds = 0; rounds < k; rounds++) {
                    if (arr[rounds][i] > arr[rounds][j]) {
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

        System.out.println(ans);
    }
}