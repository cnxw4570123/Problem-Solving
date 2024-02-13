import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] map, v;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new int[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
        BFS();
        if (N == 1 && M == 1) {
            v[N - 1][M - 1] = 0;
        }
        bw.write(v[N - 1][M - 1] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void BFS() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(v[i], 100_001);
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // 더 적은 벽 부수기 횟수로 움직일 수 있음
            if (v[cur[0]][cur[1]] < cur[2]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i], nx = cur[1] + dx[i];
                if (ny >= N || nx >= M || ny < 0 || nx < 0) {
                    continue;
                }
                if (map[ny][nx] == 0 && v[ny][nx] > cur[2]) {
                    q.add(new int[]{ny, nx, cur[2]});
                    v[ny][nx] = cur[2];
                } else if (map[ny][nx] == 1 && v[ny][nx] > cur[2] + 1) {
                    q.add(new int[]{ny, nx, cur[2] + 1});
                    v[ny][nx] = cur[2] + 1;
                }
            }
        }
    }
}