import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String IMPOSSIBLE = "IMPOSSIBLE";
    static char SANGEUN = '@', FIRE = '*', WALL = '#';
    static int FIRE_V = 0, SANGEUN_V = 1;
    static Set<Character> CAN_NOT_GO = Set.of('*', '#');
    static int T, H, W;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static char[][] map;
    static Queue<int[]> sangeunQ = new ArrayDeque<>(), fireQ = new ArrayDeque<>();
    static boolean[][][] v;

    public static void main(String[] args) throws IOException {
        StringBuilder ans = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            sangeunQ.clear();
            fireQ.clear();

            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            v = new boolean[2][H][W];

            for (int i = 0; i < H; i++) {
                String row = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = row.charAt(j);
                    if (map[i][j] == SANGEUN) {
                        sangeunQ.add(new int[]{i, j});
                        map[i][j] = '.';
                        v[SANGEUN_V][i][j] = true;
                        continue;
                    }

                    if (map[i][j] == FIRE) {
                        fireQ.add(new int[]{i, j});
                        map[i][j] = '.';
                        v[FIRE_V][i][j] = true;
                    }
                }
            }
            int time = BFS();
            ans.append(time == -1 ? IMPOSSIBLE : time).append("\n");
        }
        bw.write(ans.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int BFS() {
        int t = 1;
        int[] sangeun = sangeunQ.peek();
        if (sangeun[0] == 0 || sangeun[0] == H - 1 || sangeun[1] == 0 || sangeun[1] == W - 1) {
            return t;
        }
        while (!sangeunQ.isEmpty()) {
            int sanguenQSize = sangeunQ.size(), fireQSize = fireQ.size();

            // 불 먼저 이동
            for (int i = 0; i < fireQSize; i++) {
                int[] current = fireQ.poll();

                for (int direction = 0; direction < 4; direction++) {
                    int ny = current[0] + dy[direction], nx = current[1] + dx[direction];

                    if (isFireNotReachable(ny, nx)) {
                        continue;
                    }

                    v[FIRE_V][ny][nx] = true;
                    fireQ.add(new int[]{ny, nx});
                }
            }

            // 상근이 이동
            for (int i = 0; i < sanguenQSize; i++) {
                int[] current = sangeunQ.poll();

                for (int direction = 0; direction < 4; direction++) {
                    int ny = current[0] + dy[direction], nx = current[1] + dx[direction];

                    if (isSangeunNotReachable(ny, nx)) {
                        continue;
                    }
                    // 해당 좌표가 불이 붙지 않았고 벽에 붙어있으면 다음번에 탈출 가능
                    if (ny == 0 || ny == H - 1 || nx == 0 || nx == W - 1) {

                        return t + 1;
                    }
                    v[SANGEUN_V][ny][nx] = true;
                    sangeunQ.add(new int[]{ny, nx});
                }
            }
            t++;
        }
        return -1;

    }

    static boolean isFireNotReachable(int ny, int nx) {
        return ny >= H || nx >= W || ny < 0 || nx < 0 || v[FIRE_V][ny][nx] || map[ny][nx] == WALL;
    }

    static boolean isSangeunNotReachable(int ny, int nx) {
        return ny >= H || nx >= W || ny < 0 || nx < 0 || map[ny][nx] == WALL || v[SANGEUN_V][ny][nx]
            || v[FIRE_V][ny][nx];
    }


}

