import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][][] v;
    static Queue<Detective> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseUnsignedInt(st.nextToken());
        M = Integer.parseUnsignedInt(st.nextToken());
        map = new char[N][M];
        v = new boolean[6][N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'D') {
                    q.add(new Detective(i, j, 0, Direction.DOWN));
                }
            }
        }
        bw.write(BFS() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int BFS() {
        while (!q.isEmpty()) {
            Detective detective = q.poll();
            int y = detective.pos[0], x = detective.pos[1];
            if (map[y][x] == 'R' && detective.prison == Direction.DOWN) {
                return detective.time;
            }
            if (v[detective.prison.ordinal()][y][x]) {
                continue;
            }
            v[detective.prison.ordinal()][y][x] = true;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                Direction newDirection = Direction.move(detective.prison, i);
                if (ny >= N || nx >= M || ny < 0 || nx < 0 || map[ny][nx] == '#' || v[newDirection.ordinal()][ny][nx] || (map[ny][nx] == 'R' && newDirection != Direction.DOWN)) {
                    continue;
                }
                q.add(new Detective(ny, nx, detective.time + 1, newDirection));
            }
        }
        return -1;
    }
}

class Detective {
    int[] pos;
    int time;
    Direction prison;

    public Detective(int y, int x, int time, Direction prison) {
        this.pos = new int[]{y, x};
        this.time = time;
        this.prison = prison;
    }
}

enum Direction {
    DOWN,
    RIGHT,
    LEFT,
    BACKWARD,
    FORWARD,
    UP;

    public static Direction move(Direction prison, int idx) {
        if (prison == DOWN || prison == UP) {
            if (idx == 0) {
                return prison == DOWN ? LEFT : RIGHT;
            }
            if (idx == 1) {
                return prison == DOWN ? RIGHT : LEFT;
            }
            if (idx == 2) {
                return prison == DOWN ? FORWARD : BACKWARD;
            }
            return prison == DOWN ? BACKWARD : FORWARD;
        }
        if (prison == FORWARD || prison == BACKWARD) {
            if (idx == 2) {
                return prison == FORWARD ? UP : DOWN;
            }
            if (idx == 3) {
                return prison == FORWARD ? DOWN : UP;
            }
            return prison;
        }
        if (idx == 0) {
            return prison == LEFT ? UP : DOWN;
        }
        if (idx == 1) {
            return prison == LEFT ? DOWN : UP;
        }
        return prison;

    }
}