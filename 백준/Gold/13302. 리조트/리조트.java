import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1_100_000;
    static final int ONE_DAY = 10_000, THREE_DAY = 25_000, FIVE_DAY = 37_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;

    static boolean[] canNotVisit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        canNotVisit = new boolean[N + 1];
        dp = new int[N + 1][N + 1]; // dp[a][b] = c a일을 b개의 쿠폰으로 이용하는 최소 비용은 c원이다.

        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                canNotVisit[Integer.parseInt(st.nextToken())] = true;
            }
        }
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        bw.write(DFS(1, 0) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int DFS(int day, int coupon) {
        if (day > N) {
            return 0;
        }
        if (dp[day][coupon] != -1) {
            return dp[day][coupon];
        }
        dp[day][coupon] = MAX;
        if (canNotVisit[day]) {
            return dp[day][coupon] = Math.min(dp[day][coupon], DFS(day + 1, coupon));
        }
        if (coupon >= 3) {
            dp[day][coupon] = Math.min(dp[day][coupon], DFS(day + 1, coupon - 3));
        }
        dp[day][coupon] = Math.min(dp[day][coupon], DFS(day + 1, coupon) + ONE_DAY);
        dp[day][coupon] = Math.min(dp[day][coupon], DFS(day + 3, coupon + 1) + THREE_DAY);
        dp[day][coupon] = Math.min(dp[day][coupon], DFS(day + 5, coupon + 2) + FIVE_DAY);

        return dp[day][coupon];
    }

}