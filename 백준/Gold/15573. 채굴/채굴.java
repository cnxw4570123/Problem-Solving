import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, K;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static Queue<int[]> q = new ArrayDeque<>();
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int low = 1, high = 1_000_000;
        while (low < high) {
            int d = (low + high) >> 1;

            if (BFS(d) < K) {
                low = d + 1;
            } else{
                high = d;
            }
        }
        bw.write(high + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int BFS(int d){
        prepareMining(d);
        int cnt = q.size();

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + cur[0], nx = dx[i] + cur[1];
                if (ny >= N || nx >= M || ny < 0 || nx < 0 || v[ny][nx]) {
                    continue;
                }
                v[ny][nx] = true;
                if (map[ny][nx] > d) {
                    continue;
                }
                q.add(new int[]{ny, nx});
                cnt += 1;
            }
        }
        return cnt;
    }

    static void prepareMining(int d){
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j : new int[]{0, M - 1}) {
                v[i][j] = true;
                if(map[i][j] > d){
                    continue;
                }
                q.add(new int[]{i, j});
            }
        }

        for (int i = 1; i < M - 1; i++) {
            v[0][i] = true;
            if(map[0][i] > d){
                continue;
            }
            q.add(new int[]{0, i});
        }
    }
}
