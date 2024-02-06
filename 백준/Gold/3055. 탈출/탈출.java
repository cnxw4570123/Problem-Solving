import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final String CAN_NOT_ESCAPE = "KAKTUS";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int R, C;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int[] start, end;
    static char[][] forest;
    static boolean[][] dochiV, waterV;
    static Queue<int[]> waterQ = new ArrayDeque<>(), dochiQ = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        forest = new char[R][C];
        dochiV = new boolean[R][C];
        waterV = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                forest[i][j] = row.charAt(j);
                if (forest[i][j] == 'D') {
                    end = new int[]{i, j};
                    continue;
                }
                if (forest[i][j] == 'S') {
                    dochiQ.add(new int[]{i, j});
                    dochiV[i][j] = true;
                    continue;
                }
                if (forest[i][j] == '*') {
                    waterQ.add(new int[]{i, j});
                    waterV[i][j] = true;
                }
            }
        }
        int ans = BFS();
        bw.write(ans == -1 ? CAN_NOT_ESCAPE : ans + "");
        bw.flush();
        bw.close();
        br.close();
    }


    static int BFS() {
        int time = 0;
        while (!dochiQ.isEmpty()) {
            int waterSize = waterQ.size();
            for (int i = 0; i < waterSize; i++) {
                int[] curWater = waterQ.poll();

                for (int j = 0; j < 4; j++) {
                    int ny = curWater[0] + dy[j], nx = curWater[1] + dx[j];
                    if (ny >= R || nx >= C || ny < 0 || nx < 0 || forest[ny][nx] == 'D' || forest[ny][nx] == 'X'
                            || waterV[ny][nx]) {
                        continue;
                    }
                    waterV[ny][nx] = true;
                    waterQ.add(new int[]{ny, nx});
                }
            }
            int dochiSize = dochiQ.size();
            for (int i = 0; i < dochiSize; i++) {
                int[] curDochi = dochiQ.poll();

                if (Arrays.equals(curDochi, end)) {
                    return time;
                }

                for (int j = 0; j < 4; j++) {
                    int ny = curDochi[0] + dy[j], nx = curDochi[1] + dx[j];
                    if (ny >= R || nx >= C || ny < 0 || nx < 0 || forest[ny][nx] == '*' || forest[ny][nx] == 'X'
                            || waterV[ny][nx] || dochiV[ny][nx]) {
                        continue;
                    }
                    dochiV[ny][nx] = true;

                    dochiQ.add(new int[]{ny, nx});
                }
            }
            time++;
        }
        return -1;
    }
}