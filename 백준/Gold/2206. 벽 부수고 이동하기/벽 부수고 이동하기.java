import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, WALL = 1;
    static int[][] map;
    static boolean[][][] v;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        bw.write(BFS() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int BFS() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0, 0, 0));
        v[0][0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y == N - 1 && p.x == M - 1) {
                return p.cnt + 1;
            }

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i], nx = p.x + dx[i];
                if (ny >= N || nx >= M || ny < 0 || nx < 0 ) {
                    continue;
                }
                if (map[ny][nx] != WALL && !v[p.breaked][ny][nx]) {
                    v[p.breaked][ny][nx] = true;
                    q.add(new Point(ny, nx, p.cnt + 1, p.breaked));
                    continue;
                }
                // 벽이거나 현재 블록을 방문했거나
                if (map[ny][nx] == WALL && p.breaked < 1 && !v[p.breaked + 1][ny][nx]) {
                    v[p.breaked + 1][ny][nx] = true;
                    q.add(new Point(ny, nx, p.cnt + 1, p.breaked + 1));
                }
            }
        }
        return -1;
    }

    static class Point {

        int y;
        int x;
        int cnt;
        int breaked;

        public Point(int y, int x, int cnt, int breaked) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.breaked = breaked;
        }
    }

}

