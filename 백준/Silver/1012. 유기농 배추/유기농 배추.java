import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int LETTUCE = 1;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int[][] graph;
    static boolean[][] v;
    static int T, M, N, K;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로
            N = Integer.parseInt(st.nextToken()); // 세로
            K = Integer.parseInt(st.nextToken());

            graph = new int[N][M];
            v = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                graph[Y][X] = LETTUCE;
            }
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (v[j][k] || graph[j][k] != LETTUCE) {
                        continue;
                    }
                    cnt++;
                    v[j][k] = true;
                    DFS(j, k);
                }
            }
            ans.append(cnt).append("\n");
        }

        bw.write(ans.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y, nx = dx[i] + x;
            if (ny >= N || nx >= M || ny < 0 || nx < 0 || v[ny][nx] || graph[ny][nx] != LETTUCE) {
                continue;
            }
            v[ny][nx] = true;
            DFS(ny, nx);
        }
    }

}

