import java.util.Scanner;

public class Main {
    static int[] dy = {0, -1, 0, 1}, dx = {1, 0, -1, 0};
    static int[][] grid;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N][N];
        // Please write your code here.
        int y = N / 2, x = N / 2, dir = 0, move = 1;
        grid[y][x] = 1;

        while (!isNotInRange(y, x)) {
            for (int i = 0; i < move; i++) {
                int ny = y + dy[dir], nx = x + dx[dir];
                if (isNotInRange(ny, nx)){
                    y = ny;
                    x = nx;
                    break;
                }
                grid[ny][nx] = grid[y][x] + 1;
                y = ny;
                x = nx;
            }
            dir = (dir + 1) % 4;

            if (dir == 0 || dir == 2) {
                move++;
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
    
    static boolean isNotInRange(int y, int x) {
        return y >= N || x >= N || y < 0 || x < 0;
    }
}
