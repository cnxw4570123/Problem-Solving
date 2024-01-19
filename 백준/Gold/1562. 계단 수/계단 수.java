import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_DIGIT = 1024;
    static final int MOD = 1_000_000_000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 10자리 bit이므로 2^10의 공간 필요
        dp = new int[N + 1][10][MAX_DIGIT]; // dp[a][b][c] = d -> b로 끝나는 a자리수에서 나올 수 있는 조합 c의 개수는 d이다.

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i]++; // 1자리 수 i로 끝나는 수 -> i번째 비트를 켜고 해당 조합을 + 1해줌
        }

        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    for (int k = 0; k < MAX_DIGIT; k++) {
                        dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j + 1][k]) % MOD;
                    }
                } else if (j == 9) {
                    for (int k = 0; k < MAX_DIGIT; k++) {
                        dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j - 1][k]) % MOD;
                    }
                } else {
                    for (int k = 0; k < MAX_DIGIT; k++) {
                        dp[i][j][k | (1 << j)] =
                                (dp[i][j][k | (1 << j)] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[N][i][MAX_DIGIT - 1]) % MOD;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}