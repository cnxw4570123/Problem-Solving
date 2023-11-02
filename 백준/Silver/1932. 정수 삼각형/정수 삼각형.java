import java.io.*;
import java.util.*;


public class Main {
    static int N, ans = 0;

    static int[][] dp, triangle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < N; i++) {
            search(N - 1, i);
        }
        System.out.println(ans);
        br.close();
    }

    static int search(int depth, int idx) {
        if (idx > N || idx < 0 || depth < 0) {
            return 0;
        }

        if (dp[depth][idx] != Integer.MIN_VALUE) {
            return dp[depth][idx];
        }
        dp[depth][idx] = Math.max(search(depth - 1, idx - 1), search(depth - 1, idx)) + triangle[depth][idx];
        ans = Math.max(dp[depth][idx], ans);

        return dp[depth][idx];
    }

}