import java.util.Scanner;

public class Main {
    static int[] dy = {0, 1, 1, 1}, dx = {1, 1, 0, -1};
    static int MAX = 19;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                int target = arr[i][j];

                if (target == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++){
                    int count = 1;
                    for (int l = 1; l < 5; l++) {
                        int r = i + dy[k] * l, c = j + dx[k] * l;

                        if (r >= MAX || c >= MAX || r < 0 || c < 0 || target != arr[r][c]) {
                            break;
                        }
                        count++;
                    }

                    if (count == 5) {
                        System.out.println(target);
                        System.out.printf("%d %d", i + dy[k] * 2 + 1, j + dx[k] * 2 + 1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
        
    }
}