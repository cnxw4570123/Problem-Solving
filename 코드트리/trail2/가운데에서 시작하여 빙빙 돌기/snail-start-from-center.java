import java.util.Scanner;

public class Main {
    static int[] dy = {0, -1, 0, 1}, dx = {1, 0, -1, 0};
    static int[][] grid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        grid = new int[n][n];
        // Please write your code here.
        int y = n / 2, x = n / 2, dir = 0;
        grid[y][x] = 1;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < i + 1; k++) {
                    int ny = y + dy[dir] * k, nx = x + dx[dir] * k;
                    if(ny >= n || nx >= n || ny < 0 || nx < 0 || grid[ny][nx] != 0) {
                        continue;
                    }
                    grid[ny][nx] = grid[y][x] + k;
                }
                y = y + dy[dir] * i;
                x = x + dx[dir] * i;
                dir = (dir + 1) % 4;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int[] g : grid) {
            for (int num : g) {
                ans.append(num).append(" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
