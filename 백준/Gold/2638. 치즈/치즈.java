import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int[][] map, nextMap;

    static boolean[][] v;

    static Queue<int[]> q = new ArrayDeque<>();
    static Stack<int[]> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = -Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    q.add(new int[]{i, j});
                }
            }
        }
        int airNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !v[i][j]) {
                    DFS(i, j, airNum++);
                }
            }
        }
        System.out.println(BFS());
        br.close();
    }

    static void DFS(int y, int x, int airNum) {
        v[y][x] = true;
        map[y][x] = airNum;

        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y, nx = dx[i] + x;
            if (ny >= N || nx >= M || ny < 0 || nx < 0 || v[ny][nx] || map[ny][nx] != 0) {
                continue;
            }
            DFS(ny, nx, airNum);
        }
    }

    static int BFS() {
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            int qSize = q.size();
            nextMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                nextMap[i] = Arrays.copyOf(map[i], M);
            }

            for (int i = 0; i < qSize; i++) {
                int[] current = q.poll();
                int outerAir = 0;
                for (int j = 0; j < 4; j++) {
                    int ny = current[0] + dy[j], nx = current[1] + dx[j];
                    // 치즈거나 내부 공기면
                    if (ny >= N || nx >= M || ny < 0 || nx < 0 || map[ny][nx] != 1) {
                        continue;
                    }
                    outerAir++;
                }
                if (outerAir >= 2) {
                    nextMap[current[0]][current[1]] = 1;
                    s.push(current);
                } else {
                    q.add(current);
                }
            }
            map = nextMap;
            while (!s.isEmpty()) {
                int[] temp = s.pop();

                for (int i = 0; i < 4; i++) {
                    int ny = temp[0] + dy[i], nx = temp[1] + dx[i];
                    if (ny >= N || nx >= M || ny < 0 || nx < 0 || map[ny][nx] <= 1) {
                        continue;
                    }
                    map[ny][nx] = 1;
                    s.push(new int[]{ny, nx});
                }
            }
        }
        return time;
    }
}