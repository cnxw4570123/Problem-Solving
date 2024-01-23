import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 16_000_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] cost;
    static int N;
    // dp[a][b] = d a번째 도시를 방문하는 조합 b의 가장 적은 비용은 c원이다.
    static int[][] dp;


    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bw.write(DFS(0, 1) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static int DFS(int current, int visit) {
        if (visit == (1 << N) - 1) {
            return cost[current][0] == 0 ? MAX : cost[current][0];
        }
        if (dp[current][visit] != -1) {
            return dp[current][visit];
        }
        dp[current][visit] = MAX;
        for (int i = 0; i < N; i++) {
            // 이미 방문한 노드거나 해당 노드로 이동할 수 없다면 
            if ((visit & (1 << i)) > 0 || cost[current][i] == 0) {
                continue;
            }
            dp[current][visit] = Math.min(dp[current][visit], cost[current][i] + DFS(i, visit | (1 << i)));
        }
        return dp[current][visit];
    }
}