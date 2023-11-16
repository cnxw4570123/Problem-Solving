import java.io.*;
import java.util.*;


public class Main {
    static int[][] board;
    static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
    static int N, M, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateArray(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void rotateArray(int start) {
        if (N - 2 * start < 2 || M - 2 * start < 2) {
            return;
        }
        rotateArray(start + 1);

        int cnt = 2 * (N + M - 4 * start - 2);
        int rotateCount = R % cnt;
        for (int i = 0; i < rotateCount; i++) {
            rotate(start);
        }
    }

    static void rotate(int start) {
        int temp = board[start][start];
        int y = start, x = start, idx = 0;

        while (idx < 4) {
            int ny = y + dy[idx], nx = x + dx[idx];
            if (ny < start || nx < start || ny >= N - start || nx >= M - start) {
                idx++;
                continue;
            }
            board[y][x] = board[ny][nx];
            y = ny;
            x = nx;
        }
        board[start + 1][start] = temp;
    }
}
