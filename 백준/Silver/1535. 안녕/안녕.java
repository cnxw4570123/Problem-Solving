import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, MAX_HEALTH = 100;
    static int[] health, joy;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        health = new int[N + 1];
        joy = new int[N + 1];
        dp = new int[N + 1][MAX_HEALTH];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            health[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            joy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < MAX_HEALTH; j++) {
                if (j < health[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i - 1][j - health[i]] + joy[i], dp[i - 1][j]);
            }
        }
        bw.write(dp[N][MAX_HEALTH - 1] + "");

        bw.flush();
        bw.close();
        br.close();
    }
}