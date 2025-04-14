import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int M, N, NO_TOMATO = -1, TOMATO = 1;
    static int[][] tomatoes;
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomatoes = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if (tomatoes[i][j] == TOMATO) {
                    q.add(new int[] { i, j, 1 });
                }
            }
        }

        int ans = -1;

        if (q.size() == 0) {
            bw.write(ans + "");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        BFS();
        out: for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j] == 0) {
                    ans = -1;
                    break out;
                }
                ans = Math.max(ans, tomatoes[i][j]);
            }
        }

        bw.write((ans == -1 ? ans : ans - 1) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS() {

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = current[0] + dy[i], nx = current[1] + dx[i];
                if (canNotReach(ny, nx)) {
                    continue;
                }
                q.add(new int[] { ny, nx, current[2] + 1 });
                tomatoes[ny][nx] = current[2] + 1;
            }

        }

    }

    private static boolean canNotReach(int ny, int nx) {
        return ny >= N || nx >= M || ny < 0 || nx < 0 || tomatoes[ny][nx] > 0 || tomatoes[ny][nx] == NO_TOMATO;
    }

}
