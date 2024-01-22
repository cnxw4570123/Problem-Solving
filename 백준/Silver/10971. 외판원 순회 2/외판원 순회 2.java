import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] dp, cost;
    static boolean[] v;
    static int N, ans = Integer.MAX_VALUE, value;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            v[i] = true;
            backTracking(1, i, i);
            v[i] = false;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void backTracking(int count, int prev, int start) {
        if (count == N) {
            if(cost[prev][start] == 0){
                return;
            }
            ans = Math.min(ans, value + cost[prev][start]);
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if (v[i] || cost[prev][i] == 0) {
                continue;
            }
            v[i] = true;
            value += cost[prev][i];
            backTracking(count + 1, i, start);
            value -= cost[prev][i];
            v[i] = false;
        }
    }
}