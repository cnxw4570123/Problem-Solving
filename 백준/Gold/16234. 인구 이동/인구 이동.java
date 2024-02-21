import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, L, R, day;
    static boolean[][] v;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<int[]> pos = new ArrayList<>();
        int count = 0;
        do {
            count = 0;
            // count += pos.size() - 1 바뀐 노드 수 세기
            // 모든 노드가 이동이 불가능하면 종료
            v = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j]) {
                        continue;
                    }
                    pos.clear();
                    int avg = DFS(i, j, pos) / pos.size();
                    pos.forEach(arr -> {
                        map[arr[0]][arr[1]] = avg;
                    });
                    // 적어도 자기 자신은 포함하므로 - 1
                    count += pos.size() - 1;
                }
            }
            // 인접 국가간 이동이 있으면
            if (count > 0) {
                day++;
            }
        } while (count > 0);
        bw.write(day + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int DFS(int y, int x, List<int[]> pos) {
        ArrayDeque<int[]> s = new ArrayDeque<>();
        s.add(new int[]{y, x});
        v[y][x] = true;
        int total = map[y][x];
        pos.add(new int[]{y, x});
        while (!s.isEmpty()) {
            int[] cur = s.pollLast();

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + cur[0], nx = dx[i] + cur[1];
                if (ny >= N || nx >= N || ny < 0 || nx < 0 || v[ny][nx]) {
                    continue;
                }
                int diff = Math.abs(map[cur[0]][cur[1]] - map[ny][nx]);
                if (diff > R || diff < L) {
                    continue;
                }
                v[ny][nx] = true;
                int[] next = new int[]{ny, nx};
                pos.add(next);
                s.add(next);
                total += map[ny][nx];
            }
        }
        return total;
    }

}
